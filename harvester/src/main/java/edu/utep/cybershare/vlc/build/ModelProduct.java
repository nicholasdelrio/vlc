package edu.utep.cybershare.vlc.build;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;

public class ModelProduct {
	private HashMap<String, Person> people;
	private HashMap<String, Project> projects;	
	private HashMap<String, Institution> institutions;
	private HashMap<String, URI> dbpediaResources;

	public ModelProduct(){
		people = new HashMap<String,Person>();
		projects = new HashMap<String,Project>();	
		institutions = new HashMap<String, Institution>();
		dbpediaResources = new HashMap<String, URI>();
	}
	
	Institution getInstitution(String key){
		return institutions.get(key);
	}
	
	void addInstitution(Institution value){
		institutions.put(value.getIdentification(), value);
	}
	
	Person getPerson(String key){
		return people.get(key);
	}
	
	void addPerson(Person value){
		people.put(value.getIdentification(), value);
	}
	
	Project getProject(String key){
		return projects.get(key);
	}
	
	void addProject(Project value){
		projects.put(value.getIdentification(), value);
	}
	
	URI getDBPediaResource(String key){
		return dbpediaResources.get(key);
	}
	
	void addDBPediaResource(URI value){
		dbpediaResources.put(value.toASCIIString(), value);
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
	
	public List<Project> getProjects(){
		return new ArrayList<Project>(projects.values());
	}
	public List<URI> getDBPediaResources(){
		return new ArrayList<URI>(dbpediaResources.values());
	}
}