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
	
	private int counter;
	
	public Builder(ModelProduct aProduct){
		product = aProduct;
		counter = 0;
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
		
		//don't roll back counter
	}
	
	public void buildAgency(String name){
		fundingAgency = product.getAgency(name);
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
		
		//Figure out if it is a multi-institutional project with multiple PI's at different institutions given their own rewards,
		//although the title of the project is identical (for NSF usually)
		Project project = new Project(title);
		if(product.projectExists(project) && this.principalInvestigator != null){
			Person existingPI = product.getProject(project.getIdentification()).getPrincipalInvestigator();
			if(existingPI != null && !existingPI.getIdentification().equals(principalInvestigator.getIdentification()))
				project = product.getProject(title + "_" + counter++); // since we are changing the title of the project, this method will create a new project
			else
				project = product.getProject(title);
		}
		// this is the case when the project is multi-institutional but still only a single PI and a single award amount, so it is already populated but needs
		// more adornment by either adding co-pi's or pi and hosting institution
		else if(product.projectExists(project) && this.principalInvestigator == null){
			project = product.getProject(title);
		}
		// the project is new
		else{
			project = product.getProject(title);
			project.setAbstractText(summary);
			project.setStartDate(startDate);
			project.setEndDate(endDate);
			project.setAwardAmount(awardAmount);
			project.setGrantIdentification(grantID);
			project.setAwardHomepage(awardHomepage);
		}
		
		populateWithBuiltParts(project);
		reset();
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