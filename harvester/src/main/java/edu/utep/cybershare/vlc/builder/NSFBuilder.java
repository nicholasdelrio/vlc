package edu.utep.cybershare.vlc.builder;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;

public class NSFBuilder implements Builder {

	private static URI dbpediaResource;
	
	private ArrayList<Person> coPrincipalInvestigators;
	private Person principalInvestigator;
	private Institution institution;
	private ArrayList<URI> disciplines;
	private ArrayList<URI> subjects;
	
	private ModelProduct product;
	
	public NSFBuilder(){
		try{dbpediaResource = new URI("http://dbpedia.org/resource/");}
		catch(Exception e){e.printStackTrace();}
		
		coPrincipalInvestigators = new ArrayList<Person>();
		
		product = new ModelProduct();
	}
	
	public void buildDiscipline(String name){
		this.disciplines.add(getDBPediaResource(name));
	}	
	public void buildSubject(String name){
		this.subjects.add(getDBPediaResource(name));
	}
	public void buildInstitution(String name, String city, String state, String zip, String address){
		Institution institution = product.getInstitution(name);
		if(institution == null){
			institution = new Institution(name);
			institution.setAddress(address);
			institution.setCity(city);
			institution.setState(state);
			product.addInstitution(institution);
		}
		this.institution = institution;
	}
	
	public void buildCoPrincipalInvestigator(String firstName, String lastName){
		this.coPrincipalInvestigators.add(this.getPerson(firstName, lastName));
	}
	
	public void buildPrincipalInvestigator(String firstName, String lastName){
		this.principalInvestigator = this.getPerson(firstName, lastName);
	}
	
	public void buildProject(
			String title,
			String summary,
			GregorianCalendar startDate,
			GregorianCalendar endDate,
			int awardAmount,
			String grantIdentification,
			URL awardHomepage){
		
		Project project = product.getProject(title);
		
		if(project == null){
			project = new Project(title);
			project.setAbstractText(summary);
			project.setAwardAmount(awardAmount);
			project.setStartDate(startDate);
			project.setEndDate(endDate);
			project.setGrantIdentification(grantIdentification);
			project.setAwardHomepage(awardHomepage);
	
			product.addProject(project);
		}
		
		populateWithBuiltParts(project);
	}	
	
	public ModelProduct getResult(){
		return product;
	}
	
	private URI getDBPediaResource(String name){
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
			project.addHostingInstitution(institution);
		}
		
		for(Person coPrincipalInvestigator : this.coPrincipalInvestigators)
			project.addCoPrincipalInvestigator(coPrincipalInvestigator);
		
		for(URI subject : subjects)
			project.addSubject(subject);
	}
	
	private Person getPerson(String firstName, String lastName){
		Person person = new Person(firstName, lastName);
		person = product.getPerson(person.getIdentification());

		if(person == null){
			person = new Person(firstName, lastName);
			
			for(URI discipline :disciplines)
				person.addDiscipline(discipline);

			person.addAffiliatedInstitution(institution);
			product.addPerson(person);
		}
		else{
			for(URI discipline : disciplines)
				person.addDiscipline(discipline);
			
			person.addAffiliatedInstitution(institution);
		}
		return person;
	}
}