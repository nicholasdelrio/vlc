package edu.utep.cybershare.vlc.builder;

import java.io.File;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.utep.cybershare.vlc.builder.source.XMLSet_NSF;
import edu.utep.cybershare.vlc.util.NSFAwardsUtils;

public class NSFDirector {
	
	private Builder builder;
	
	public NSFDirector(Builder builder){
		this.builder = builder;
	}
	
	public void construct(XMLSet_NSF awardsXML){
		DocumentBuilderFactory dbFactory;
		DocumentBuilder dBuilder;
		Document awardsDoc;
		try{
			for(File awardFile : awardsXML){
				dbFactory = DocumentBuilderFactory.newInstance();
				dBuilder = dbFactory.newDocumentBuilder();
				awardsDoc = dBuilder.parse(awardFile);	 
				awardsDoc.getDocumentElement().normalize();
				populateProjects(awardsDoc);
				dBuilder.reset();
			}
		}catch(Exception e){e.printStackTrace();}
	}
	
	private void populateProjects(Document awardsDoc){
		NodeList awardsList = awardsDoc.getElementsByTagName("Award");
		
		for (int i = 0; i < awardsList.getLength(); i++) {						
			Node awardNode = awardsList.item(i);
			populateProject(awardNode);
		}
	}
		
	private void populateProject(Node awardNode){
		Element awardElement = (Element) awardNode;
			
		String piName = getPI(awardElement);
		String program = getProgram(awardElement);
		String title = awardElement.getElementsByTagName("Title").item(0).getTextContent();
		String abstractText = awardElement.getElementsByTagName("Abstract").item(0).getTextContent();				
		String startDateString = awardElement.getElementsByTagName("StartDate").item(0).getTextContent();
		String endDateString = awardElement.getElementsByTagName("ExpirationDate").item(0).getTextContent();
		String awardAmountString = awardElement.getElementsByTagName("AwardedAmountToDate").item(0).getTextContent();
		int awardAmount = new Integer(awardAmountString);
		String grantID = awardElement.getElementsByTagName("AwardNumber").item(0).getTextContent();
		URL awardHomepage = NSFAwardsUtils.getAwardHomepageURL(grantID);
		
		GregorianCalendar startDate = NSFAwardsUtils.getDate(startDateString);
		GregorianCalendar endDate = NSFAwardsUtils.getDate(endDateString);

		builder.buildDiscipline(program);
		builder.buildSubject(program);
		
		buildInstitution(awardElement);
		buildCoPrincipalInvestigators(awardElement);
		builder.buildPrincipalInvestigator(NSFAwardsUtils.getFirstName(piName), NSFAwardsUtils.getLastName(piName));
		builder.buildProject(title, abstractText, startDate, endDate, awardAmount, grantID, awardHomepage);
	}

	private void buildCoPrincipalInvestigators(Element awardElement){
		List<String> coiNames = getCoInvestigators(awardElement);

		for(String coiName : coiNames){
			builder.buildCoPrincipalInvestigator(NSFAwardsUtils.getFirstName(coiName), NSFAwardsUtils.getLastName(coiName));
		}
	}
	
	private List<String> getCoInvestigators(Element awardElement){
		NodeList pis = awardElement.getElementsByTagName("Co-PIName");
		List<String> pisList = NSFAwardsUtils.nodeListConverter(pis);		
		return pisList;
	}
	
	private void buildInstitution(Element awardElement){
		String institutionName = awardElement.getElementsByTagName("Organization").item(0).getTextContent();		
		String city = awardElement.getElementsByTagName("OrganizationCity").item(0).getTextContent();		
		String state = awardElement.getElementsByTagName("OrganizationState").item(0).getTextContent();				
		String zipCode = awardElement.getElementsByTagName("OrganizationZip").item(0).getTextContent();
		String address = awardElement.getElementsByTagName("OrganizationStreet").item(0).getTextContent();		

		builder.buildInstitution(institutionName, city, state, zipCode, address);
	}
		
	private String getProgram(Element awardElement){
		String program = null;
		NodeList programs = awardElement.getElementsByTagName("Program");
		if(programs.getLength() > 0)
			program = programs.item(0).getTextContent();
		
		return program;
	}
	
	private String getPI(Element awardElement){
		String pi = null;
		NodeList pis = awardElement.getElementsByTagName("PrincipalInvestigator");
		if(pis.getLength() > 0)
			pi = pis.item(0).getTextContent();
		
		return pi;
	}
}