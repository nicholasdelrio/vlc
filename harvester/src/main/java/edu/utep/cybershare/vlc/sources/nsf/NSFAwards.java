package edu.utep.cybershare.vlc.sources.nsf;

import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.utep.cybershare.vlc.ontology.Discipline;
import edu.utep.cybershare.vlc.ontology.Institution;
import edu.utep.cybershare.vlc.ontology.Person;
import edu.utep.cybershare.vlc.ontology.Project;
import edu.utep.cybershare.vlc.sources.ProjectSource;

public class NSFAwards extends ProjectSource {
	
	private static final String CACHED_AWARD_DATA = "./cached-award-data/";
	private static final String NSF = CACHED_AWARD_DATA + "NSF/";
	private static final File AWARDS_NSF_SEMANTIC_WEB_File = new File(NSF + "awards-nsf-semantic-web.xml");

	public NSFAwards() throws Exception {
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document awardsDoc = dBuilder.parse(AWARDS_NSF_SEMANTIC_WEB_File);	 
		awardsDoc.getDocumentElement().normalize();
		
		populateProjects(awardsDoc);
	}
	
	private void populateProjects(Document awardsDoc){
		NodeList awardsList = awardsDoc.getElementsByTagName("Award");
			
		for (int i = 0; i < awardsList.getLength(); i++) {
			Node awardNode = awardsList.item(i);
				
			if (awardNode.getNodeType() == Node.ELEMENT_NODE) {
				Element awardElement = (Element) awardNode;
				
				String piName = awardElement.getElementsByTagName("PrincipalInvestigator").item(0).getTextContent();
				String coiName = awardElement.getElementsByTagName("Co-PIName").item(0).getTextContent();
				String institutionName = awardElement.getElementsByTagName("Organization").item(0).getTextContent();
				String disciplineName = awardElement.getElementsByTagName("Program").item(0).getTextContent();
				String title = awardElement.getElementsByTagName("Title").item(0).getTextContent();
				String abstractText = awardElement.getElementsByTagName("Abstract").item(0).getTextContent();
				
				String startDateString = awardElement.getElementsByTagName("StartDate").item(0).getTextContent();
				String endDateString = awardElement.getElementsByTagName("ExpirationDate").item(0).getTextContent();;
				
				GregorianCalendar startDate = NSFAwardsUtils.getDate(startDateString);
				GregorianCalendar endDate = NSFAwardsUtils.getDate(endDateString);
				
				Institution institution = this.getInstitution(institutionName);
				Discipline discipline = this.getDiscipline(disciplineName);		
				Person hasPrincipalInvestigator = getPerson(NSFAwardsUtils.getFirstName(piName), NSFAwardsUtils.getLastName(piName), discipline, institution);

				Person hasCoPrincipalInvestigator = getPerson(NSFAwardsUtils.getFirstName(piName), NSFAwardsUtils.getLastName(piName), discipline, institution);
				ArrayList<Person> coPrincipalInvestigators = new ArrayList<Person>();
				coPrincipalInvestigators.add(hasCoPrincipalInvestigator);

				this.addProject(hasPrincipalInvestigator, coPrincipalInvestigators, title, abstractText, startDate, endDate);
			}
		}
	}
}
