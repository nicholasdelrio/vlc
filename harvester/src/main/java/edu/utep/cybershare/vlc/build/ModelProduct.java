package edu.utep.cybershare.vlc.build;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.utep.cybershare.vlc.model.Agency;
import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;

public class ModelProduct {
	private HashMap<String, Person> people;
	private HashMap<String, Project> projects;	
	private HashMap<String, Institution> institutions;
	private HashMap<String, URI> dbpediaResources;
	private HashMap<String, Agency> agencies;

	public ModelProduct(){
		people = new HashMap<String,Person>();
		projects = new HashMap<String,Project>();	
		institutions = new HashMap<String, Institution>();
		dbpediaResources = new HashMap<String, URI>();
		agencies = new HashMap<String, Agency>();
	}
	public Institution getInstitution(String key){
		Institution institution = institutions.get(key);
		if(institution == null){
			institution = new Institution(key);
			institutions.put(key, institution);
		}
		return institution;
	}
	
	public Agency getAgency(String key){
		Agency agency = agencies.get(key);
		if(agency == null){
			agency = new Agency(key);
			agencies.put(key, agency);
		}
		return agency;
	}
	
	public Person getPerson(String firstName, String lastName){
		String key = Person.getIdentification(firstName, lastName);
		Person person = people.get(key);
		if(person == null){
			person = new Person(firstName, lastName);
			people.put(key, person);
		}
		return person;
	}
	public Project getProject(String key){
		Project project = projects.get(key);
		if(project == null){
			project = new Project(key);
			projects.put(key, project);
		}
		return project;
	}
	
	public URI getDBPediaResource(URI key){
		URI uri = dbpediaResources.get(key);
		if(uri == null)
			dbpediaResources.put(key.toASCIIString(), key);
		return uri;
	}
	public boolean projectExists(Project aProject){
		return projects.get(aProject.getIdentification()) != null;
	}
	public Project removeProject(Project aProject){
		return projects.remove(aProject.getIdentification());
	}
	public List<Person> getPeople(){
		return new ArrayList<Person>(people.values());
	}
	public List<Institution> getInstitutions(){
		return new ArrayList<Institution>(institutions.values());
	}
	public List<Agency> getAgencies(){
		return new ArrayList<Agency>(agencies.values());
	}
	public List<Project> getProjects(){
		return new ArrayList<Project>(projects.values());
	}
	public List<URI> getDBPediaResources(){
		return new ArrayList<URI>(dbpediaResources.values());
	}
}