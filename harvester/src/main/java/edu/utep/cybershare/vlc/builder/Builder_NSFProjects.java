package edu.utep.cybershare.vlc.builder;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;

import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;

public class Builder_NSFProjects implements Builder {

	private static URI dbpediaResource;
	
	private static Hashtable<String, Person> people = new Hashtable<String,Person>();
	private static Hashtable<String, Project> projects = new Hashtable<String,Project>();	
	private static Hashtable<String, Institution> institutions = new Hashtable<String, Institution>();
	private static Hashtable<String, URI> dbpediaResources = new Hashtable<String, URI>();
	
	private ArrayList<Person> coPrincipalInvestigators;
	private Person principalInvestigator;
	private Institution institution;
	private URI discipline;
	private URI subject;
		
	public Builder_NSFProjects(){
		try{dbpediaResource = new URI("http://dbpedia.org/resource/");}
		catch(Exception e){e.printStackTrace();}
		
		coPrincipalInvestigators = new ArrayList<Person>();
	}
	
	public void buildDiscipline(String name){
		this.discipline = getDBPediaResource(name);
	}	
	public void buildSubject(String name){
		this.subject = getDBPediaResource(name);
	}
	public void buildInstitution(String name, String city, String state, String zip, String address){
		Institution institution = institutions.get(name);
		if(institution == null){
			institution = new Institution(name);
			institution.setAddress(address);
			institution.setCity(city);
			institution.setState(state);
			institutions.put(institution.getIdentification(), institution);
		}
		this.institution = institution;
	}
	
	public void buildCoPrincipalInvestigator(String firstName, String lastName){
		this.coPrincipalInvestigators.add(this.getPerson(firstName, lastName));
	}
	
	public void buildPrincipalInvestigator(String firstName, String lastName){
		this.principalInvestigator = this.getPerson(firstName, lastName);
	}
	
	public Project buildProject(
			String title,
			String summary,
			GregorianCalendar startDate,
			GregorianCalendar endDate,
			int awardAmount,
			String grantIdentification,
			URL awardHomepage){
		
		Project project = projects.get(title);
		
		if(project == null){
			project = new Project(title);
			project.setSummary(summary);
			project.setAwardAmount(awardAmount);
			project.setStartDate(startDate);
			project.setEndDate(endDate);
			project.setGrantIdentification(grantIdentification);
			project.setAwardHomepage(awardHomepage);
	
			projects.put(project.getIdentification(), project);
		}
		
		this.populateWithBuiltParts(project);
		return project;
	}	
	
	private URI getDBPediaResource(String name){
		String resourceURIString = dbpediaResource + name;
		URI dbpediaResourceURI = dbpediaResources.get(resourceURIString);
		
		if(dbpediaResourceURI == null)
			try{
				dbpediaResourceURI = new URI(resourceURIString);
				dbpediaResources.put(dbpediaResourceURI.toASCIIString(), dbpediaResourceURI);
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
		
		project.setSubject(subject);
	}
	
	private Person getPerson(String firstName, String lastName){
		Person person = new Person(firstName, lastName);
		person = people.get(person.getIdentification());

		if(person == null){
			person = new Person(firstName, lastName);
			person.addDiscipline(discipline);
			person.addAffiliatedInstitution(institution);
			people.put(person.getIdentification(), person);
		}
		else{
			person.addDiscipline(discipline);
			person.addAffiliatedInstitution(institution);
		}
		return person;
	}
}