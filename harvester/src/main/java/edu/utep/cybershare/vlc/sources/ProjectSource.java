package edu.utep.cybershare.vlc.sources;
import java.util.Set;

import edu.utep.cybershare.vlc.ontology.Institution;
import edu.utep.cybershare.vlc.ontology.Person;
import edu.utep.cybershare.vlc.ontology.Project;
public abstract class ProjectSource {
	
	public abstract Set<Project> getProjects();
	public abstract Set<Person> getPeople();
	public abstract Set<Institution> getInstitutions();
}
