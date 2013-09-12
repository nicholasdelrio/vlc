package edu.utep.cybershare.vlc.builder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;

public class ModelProduct {
	private Hashtable<String, Person> people;
	private Hashtable<String, Project> projects;	
	private Hashtable<String, Institution> institutions;
	private Hashtable<String, URI> dbpediaResources;

	public ModelProduct(){
		people = new Hashtable<String,Person>();
		projects = new Hashtable<String,Project>();	
		institutions = new Hashtable<String, Institution>();
		dbpediaResources = new Hashtable<String, URI>();
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