package edu.utep.cybershare.vlc.build;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.apache.commons.lang.WordUtils;

import edu.utep.cybershare.vlc.model.Agency;
import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;

public abstract class Builder{

	private static String dbpediaResourceBaseURIString = "http://dbpedia.org/resource/";
	private static URI dbpediaResourceBaseURI;
	
	protected ArrayList<Person> coPrincipalInvestigators;
	protected Person principalInvestigator;
	protected Institution hostingInstitution;
	protected ArrayList<Institution> institutions;
	protected ArrayList<URI> disciplines;
	protected ArrayList<URI> subjects;
	protected Agency fundingAgency;
	
	private ModelProduct product;
	
	public Builder(ModelProduct aProduct){
		product = aProduct;
		setDBPediaBaseURI();
		reset();
	}
	
	private void setDBPediaBaseURI(){
		try{dbpediaResourceBaseURI = new URI(Builder.dbpediaResourceBaseURIString);}
		catch(Exception e){e.printStackTrace();}
	}
	
	private void reset(){
		coPrincipalInvestigators = new ArrayList<Person>();
		disciplines = new ArrayList<URI>();
		subjects = new ArrayList<URI>();
		institutions = new ArrayList<Institution>();
		principalInvestigator = null;
		hostingInstitution = null;
		fundingAgency = null;

	}
	
	public void buildAgency(URI uri){
		fundingAgency = product.getAgency(uri);
	}
	
	public void buildDiscipline(String name){
		this.disciplines.add(getDBPediaResource(name));
	}	
	public void buildSubject(String name){
		this.subjects.add(getDBPediaResource(name));
	}
	
	public void buildInstitution(String name, String city, String state, String zipCode, String address){
		Institution institution = product.getInstitution(name);
		institution.setCity(city);
		institution.setState(state);
		institution.setZipCode(zipCode);
		institution.setAddress(address);
		this.institutions.add(institution);
	}
	
	public void buildHostingInstitution(String name){
		Institution institution = product.getInstitution(name);
		this.hostingInstitution = institution;
	}
	
	public void buildCoPrincipalInvestigator(String firstName, String lastName, String email){
		this.coPrincipalInvestigators.add(this.getPerson(firstName, lastName, email));
	}
	
	public void buildPrincipalInvestigator(String firstName, String lastName, String email){
		this.principalInvestigator = this.getPerson(firstName, lastName, email);
	}
	
	public void buildProject(
			String title,
			String summary,
			GregorianCalendar startDate,
			GregorianCalendar endDate,
			int awardAmount,
			String grantID,
			URL awardHomepage){
		
		String id = getProjectID(grantID, title);
		Project project = new Project(id);
		
		if(startDate == null)
			System.out.println("something is wrong, there should alwasys be s start date: " + project.getIdentification());
		

		if(product.projectExists(project)){
			//something is weird, we have two projects with the same grant id each with a PI
			//now lets narrow it down and check if the pis are the same or different
			if(project.isSet_principalInvestigator() && principalInvestigator != null){
				if(project.getPrincipalInvestigator().getIdentification().equals(principalInvestigator.getIdentification()))
					System.err.println("Multiple instances of same project with different PI's");
				else
					System.err.println("Multiple instances of same project with same PI's - duplicate record");
			}
			else{//merge the projects
				project = product.getProject(id);
				this.populateProject(project, title, summary, startDate, endDate, awardAmount, grantID, awardHomepage);
			}
		}
		else{
			project = product.getProject(id);
			this.populateProject(project, title, summary, startDate, endDate, awardAmount, grantID, awardHomepage);
		}
		
		populateWithBuiltParts(project);
		reset();
	}
	
	private String getProjectID(String grantID, String title){
		if(grantID != null)
			return title + grantID;
		return title;
	}
	
	private void populateProject(
			Project project,
			String title,
			String summary,
			GregorianCalendar startDate,
			GregorianCalendar endDate,
			int awardAmount,
			String grantID,
			URL awardHomepage){
		project.setTitle(title);
		project.setAbstractText(summary);
		project.setStartDate(startDate);
		project.setEndDate(endDate);
		project.setAwardAmount(awardAmount);
		project.setGrantIdentification(grantID);
		project.setAwardHomepage(awardHomepage);
	}
	
	public ModelProduct getResult(){
		return product;
	}
	
	private URI getDBPediaResource(String name){
		name = name.toLowerCase();
		name = WordUtils.capitalize(name);		
		name = name.replaceAll(" ", "_");
		String resourceURIString = dbpediaResourceBaseURI.toASCIIString() + name;
		try{
			URI dbpediaResourceURI = new URI(resourceURIString);
			return product.getDBPediaResource(dbpediaResourceURI);
		}catch(Exception e){e.printStackTrace();}
		return null;
	}
	
	protected abstract void populateWithBuiltParts(Project aProject);
	
	private Person getPerson(String firstName, String lastName, String email){		
		Person person = product.getPerson(firstName, lastName);
		
		for(URI discipline : disciplines)
			person.addDiscipline(discipline);
					
		if(email != null)
			person.setEmail(email);
		
		return person;
	}
}