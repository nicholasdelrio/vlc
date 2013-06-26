package edu.utep.cybershare.vlc.sources.nsf;

import java.io.File;
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
	}
	
	private void populateProjects(Document awardsDoc){
		NodeList awardsList = awardsDoc.getElementsByTagName("Award");
			
		for (int i = 0; i < awardsList.getLength(); i++) {
			Node awardNode = awardsList.item(i);
				
			if (awardNode.getNodeType() == Node.ELEMENT_NODE) {
				Element awardElement = (Element) awardNode;
				projects.add(getProject(awardElement)); //superclass field: projects
			}
		}
	}
	
	
	private Project getProject(Element awardElement){
	
		Project aProject = new Project();
		
		Person principalInvestigator = new Person();
		String properName = awardElement.getElementsByTagName("PrincipalInvestigator").item(0).getTextContent();
		return null;
	}

	@Override
	public List<Project> getProjects() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Person getPerson(String properName, Institution institution, Discipline discipline){
		String firstName = NSFAwardsUtils.getFirstName(properName);
		String lastName = NSFAwardsUtils.getLastName(properName);
		return null;
		
	}
}
