package edu.utep.cybershare.vlc.sources;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import edu.utep.cybershare.vlc.ontology.Discipline;
import edu.utep.cybershare.vlc.ontology.Institution;
import edu.utep.cybershare.vlc.ontology.Person;
import edu.utep.cybershare.vlc.ontology.Project;
public abstract class ProjectSource {
	
	protected List<Project> projects;
	
	private Hashtable<String,Person> people;
	private Hashtable<String,Institution> institutions;
	private Hashtable<String,Discipline> disciplines;
	
	public abstract List<Project> getProjects();

	public ProjectSource(){
		projects = new ArrayList<Project>();
		
		people = new Hashtable<String,Person>();
		institutions = new Hashtable<String,Institution>();
		disciplines = new Hashtable<String,Discipline>();
	}

	protected Discipline getDiscipline(){
		return null;
	}
}
