package edu.utep.cybershare.vlc.build;

import java.net.URL;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.utep.cybershare.vlc.util.NSFAwardsUtils;


public class NASADirector {

	private static int TIMEOUT = 5000;
	private static String BASE_URL = "https://earthdata.nasa.gov";
	private static String ACCESS_PROJECTS = "https://earthdata.nasa.gov/our-community/community-data-system-programs/access-projects";
	private NASABuilder builder;
	
	public NASADirector(NASABuilder builder){
		this.builder = builder;
	}
	
	public void construct(){
		Document awardsHTML = null;
		try{
			Connection connection = Jsoup.connect(ACCESS_PROJECTS);
			connection.timeout(TIMEOUT);
			awardsHTML = connection.get();
		}
		catch(Exception e){e.printStackTrace();}
		
		Elements categorizations = awardsHTML.getElementsByTag("h3");
		String categoryName;
		Element projectList;
		String link;
		String projectTitle;
		for(Element category : categorizations){
			categoryName = category.data();
			
			projectList = category.nextElementSibling();
			for(Element aTag : projectList.getElementsByTag("a")){
				link = aTag.attributes().get("href");
				projectTitle = aTag.ownText();
				populateProject(categoryName, projectTitle, link);
			}
		}
	}
	
	
	private void populateProject(String category, String projectTitle, String relativeProjectPageLink){
		Document projectHTML = null;
		String projectPageLink = BASE_URL + relativeProjectPageLink;
		System.out.println(projectPageLink);
		try{
			Connection connection = Jsoup.connect(projectPageLink);
			connection.timeout(TIMEOUT);
			projectHTML = connection.get();
		}
		catch(Exception e){e.printStackTrace();}
		
		Element content = projectHTML.getElementsByClass("field-item").get(0);

		String projectHomepage = getProjectHomepage(content);
		String abstractText = getAbstractText(content);			
		
		buildPIs(content);
		buildInstitutions(abstractText);
		builder.buildDiscipline(category);
		builder.buildSubject(category);
		
		URL projectPageURL = null;
		try{projectPageURL = new URL(projectPageLink);}
		catch(Exception e){e.printStackTrace();}
		
		builder.buildProject(projectTitle, abstractText, null, null, projectPageURL);		
	}
	
	private String getAbstractText(Element innerDiv){
		return innerDiv.getElementsByTag("p").get(0).ownText();
	}

	private String getProjectHomepage(Element content){
		String homepage = null;
		Element pTag = content.getElementsByTag("p").get(0);
		Element firstBRTag = pTag.getElementsByTag("br").get(0);
		Element projectHomePageTag = firstBRTag.previousElementSibling();
		
		if(projectHomePageTag != null)
			homepage = projectHomePageTag.attributes().get("href");
		
		return homepage;
	}
	
	private void buildInstitutions(String abstractText){
		//the damn institutions are embedded within the abstract text :(
		
		String[] abstractParts = abstractText.split("-");
		String piPlusInstitution;
		
		// skip first segment because it is the actual abstract
		for(int i = 1; i < abstractParts.length; i ++){
			piPlusInstitution = abstractParts[i].trim();
			String[] piAndInstitution = piPlusInstitution.split(",");
			String role = piAndInstitution[0].trim();
			String institution = piAndInstitution[1].trim();
			
			if(role.equals("PI"))
				builder.buildHostingInstitution(institution);
			else if(role.equals("Co-PI"))
				builder.buildInstitution(institution);
		}
	}
	
	private void buildPIs(Element content){
		Element pTag = content.getElementsByTag("p").get(0);
		
		String piName;
		String firstName;
		String lastName;
		String email;

		boolean firstPersonEncountered = true;
		for(Element anchor : pTag.getElementsByTag("a")){
			if(anchor.attributes().get("href").startsWith("mailto:") && firstPersonEncountered){
				firstPersonEncountered = false;
				piName = anchor.text();
				firstName = NSFAwardsUtils.getFirstName(piName);
				lastName = NSFAwardsUtils.getLastName(piName);
				email = anchor.attributes().get("href").substring(7);
				
				builder.buildPrincipalInvestigator(firstName, lastName, email);
			}
			else if(anchor.attributes().get("href").startsWith("mailto:") && !firstPersonEncountered){
				piName = anchor.text();
				firstName = NSFAwardsUtils.getFirstName(piName);
				lastName = NSFAwardsUtils.getLastName(piName);
				email = anchor.attributes().get("href").substring(7);
				
				builder.buildCoPrincipalInvestigator(firstName, lastName, email);
			}
		}
	}
	
	public static void main(String[] args){
		NASADirector dir = new NASADirector(null);
		dir.construct();
	}	
}