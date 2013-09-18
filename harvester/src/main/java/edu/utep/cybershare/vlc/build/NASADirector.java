package edu.utep.cybershare.vlc.build;

import java.io.File;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import edu.utep.cybershare.vlc.build.source.Awards;
import edu.utep.cybershare.vlc.build.source.NASAAwards;


public class NASADirector {
	private static String AWARDS_BASE_URL = "https://earthdata.nasa.gov";
	private NASABuilder builder;
	
	public NASADirector(NASABuilder builder){
		this.builder = builder;
	}
	
	public void construct(NASAAwards awards){
		Document awardDocument = null;
		
		for(File awardFile : awards){
			try{
				awardDocument = Jsoup.parse(awardFile, null);
				System.out.println("processing: " + awardFile.getName());
				populateProject(awardDocument, getCategory(awardFile));
			}
			catch(Exception e){e.printStackTrace();}
		}			
	}
	
	private String getCategory(File awardFile){
		File parentDirectory = new File(awardFile.getParent());
		return parentDirectory.getName();
	}
	
	private String getProjectTitle(Document awardDocument){
		for(Element metaElement : awardDocument.getElementsByTag("meta")){
			String title = metaElement.attributes().get("content");
			String aboutURI = metaElement.attributes().get("about");
			if(title != null && aboutURI != null && !aboutURI.isEmpty()){
				System.out.println("title: " + title);
				System.out.println("aboutURI: " + aboutURI);
				return title;
			}
		}
		return null;
	}
	
	private URL getAwardHomepage(Document awardDocument){
		for(Element metaElement : awardDocument.getElementsByTag("meta")){
			String title = metaElement.attributes().get("content");
			String aboutURI = metaElement.attributes().get("about");
			if(title != null && aboutURI != null && !aboutURI.isEmpty()){
				try{return new URL(AWARDS_BASE_URL + aboutURI);}
				catch(Exception e){e.printStackTrace();}
			}
		}
		return null;
	}
	
	private void populateProject(Document awardDocument, String category){

		String projectTitle = getProjectTitle(awardDocument);
		
		Element content = null;
		try{content = awardDocument.getElementsByClass("field-item").get(0);}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("current project: " + projectTitle);
		}
		

		String abstractText = getAbstractText(content);			
		System.out.println(abstractText);
		
		URL awardPageURL = this.getAwardHomepage(awardDocument);
		
		buildPIs(content);
		buildInstitutions(abstractText);
		builder.buildAgency(Awards.getAGENCY_NASA().toString());
		builder.buildDiscipline(category);
		builder.buildSubject(category);
		builder.buildProject(projectTitle, abstractText, null, null, 0, null, awardPageURL);		
	}
	
	private String getAbstractText(Element innerDiv){
		return innerDiv.getElementsByTag("p").get(0).ownText();
	}
	
	private void buildInstitutions(String abstractText){
		//the damn institutions are embedded within the abstract text :(
		
		String[] abstractParts = abstractText.split(" - ");
		String piPlusInstitution;
		
		// skip first segment because it is the actual abstract
		for(int i = 1; i < abstractParts.length; i ++){
			piPlusInstitution = abstractParts[i].trim();
			if(piPlusInstitution.startsWith("PI") || piPlusInstitution.startsWith("Co-PI")){
				System.out.println(piPlusInstitution);
				String[] piAndInstitution = piPlusInstitution.split(",");
				String role = piAndInstitution[0].trim();
				String institution = piAndInstitution[1].trim();
			
				if(role.equals("PI"))
					builder.buildHostingInstitution(institution);
				else if(role.equals("Co-PI"))
					builder.buildInstitution(institution, null, null, null, null);
			}
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
}