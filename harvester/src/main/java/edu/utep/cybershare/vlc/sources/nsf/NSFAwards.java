package edu.utep.cybershare.vlc.sources.nsf;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.utep.cybershare.vlc.ontology.Project;

public class NSFAwards {
	
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
		
		try{			
			NodeList awardsList = awardsDoc.getElementsByTagName("Award");
			
			for (int i = 0; i < awardsList.getLength(); i++) {
				Node awardNode = awardsList.item(i);
	 	 
				if (awardNode.getNodeType() == Node.ELEMENT_NODE) {
					Element awardElement = (Element) awardNode;
	 
				System.out.println("Staff id : " + eElement.getAttribute("id"));
				System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
				System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
				System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
				System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
	 
			}
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	  }
	}
	
	private Project getNextProject()
	{
		
	}
}
