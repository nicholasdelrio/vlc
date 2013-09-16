package edu.utep.cybershare.vlc.build;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.apache.commons.lang.WordUtils;

import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;

public class NASABuilder implements Builder {

	private static URI dbpediaResource;
	
	private ArrayList<Person> coPrincipalInvestigators;
	private Person principalInvestigator;
	private Institution hostingInstitution;
	private ArrayList<Institution> institutions;
	private ArrayList<URI> disciplines;
	private ArrayList<URI> subjects;
	
	private ModelProduct product;
	
	public NASABuilder(){
		try{dbpediaResource = new URI("http://dbpedia.org/resource/");}
		catch(Exception e){e.printStackTrace();}
		
		// create the builder product
		product = new ModelProduct();
		
		// clear the local resources
		reset();
	}
	
	private void reset(){
		coPrincipalInvestigators = new ArrayList<Person>();
		disciplines = new ArrayList<URI>();
		subjects = new ArrayList<URI>();
		institutions = new ArrayList<Institution>();
		principalInvestigator = null;
		hostingInstitution = null;
	}
	
	public void buildDiscipline(String name){
		this.disciplines.add(getDBPediaResource(name));
	}	
	public void buildSubject(String name){
		this.subjects.add(getDBPediaResource(name));
	}
	
	public void buildInstitution(String name){
		Institution institution = product.getInstitution(name);
		if(institution == null){
			institution = new Institution(name);
			product.addInstitution(institution);
		}
		this.institutions.add(institution);
	}
	
	public void buildHostingInstitution(String name){
		Institution institution = product.getInstitution(name);
		if(institution == null){
			institution = new Institution(name);
			product.addInstitution(institution);
		}
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
			URL awardHomepage){
		
		Project project = product.getProject(title);
		
		if(project == null){
			project = new Project(title);
			project.setAbstractText(summary);
			project.setStartDate(startDate);
			project.setEndDate(endDate);
			project.setAwardHomepage(awardHomepage);
	
			product.addProject(project);
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
		String resourceURIString = dbpediaResource + name;
		URI dbpediaResourceURI = product.getDBPediaResource(resourceURIString);
		
		if(dbpediaResourceURI == null)
			try{
				dbpediaResourceURI = new URI(resourceURIString);
				product.addDBPediaResource(dbpediaResourceURI);
			}
			catch(Exception e){e.printStackTrace();}
		
		return dbpediaResourceURI;
	}
	
	private void populateWithBuiltParts(Project project){

		if(this.principalInvestigator != null){
			project.setPrincipalInvestigator(this.principalInvestigator);
			project.addHostingInstitution(hostingInstitution);
		}
		
		for(Person coPrincipalInvestigator : this.coPrincipalInvestigators)
			project.addCoPrincipalInvestigator(coPrincipalInvestigator);
		
		for(URI subject : subjects)
			project.addSubject(subject);
	}
	
	private Person getPerson(String firstName, String lastName, String email){		
		Person person = new Person(firstName, lastName); //temporary object to calculate identifier
		person = product.getPerson(person.getIdentification());

		if(person == null){
			person = new Person(firstName, lastName);			
			product.addPerson(person);
		}
		
		for(URI discipline : disciplines)
			person.addDiscipline(discipline);
					
		if(email != null)
			person.setEmail(email);
		
		return person;
	}
}