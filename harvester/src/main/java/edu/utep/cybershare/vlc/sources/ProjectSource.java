package edu.utep.cybershare.vlc.sources;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

import edu.utep.cybershare.vlc.ontology.Discipline;
import edu.utep.cybershare.vlc.ontology.Institution;
import edu.utep.cybershare.vlc.ontology.Person;
import edu.utep.cybershare.vlc.ontology.Project;
public abstract class ProjectSource {
		
	private Hashtable<String,Person> people;
	private Hashtable<String,Project> projects;
	private Hashtable<String,Institution> institutions;
	private Hashtable<String,Discipline> disciplines;
	
	public ProjectSource(){
		projects = new Hashtable<String,Project>();	
		people = new Hashtable<String,Person>();
		institutions = new Hashtable<String,Institution>();
		disciplines = new Hashtable<String,Discipline>();
	}
	
	public List<Project> getProjects(){
		
		System.out.println("projects size: " + projects.values().size());
		
		return new ArrayList<Project>(projects.values());
	}

	protected void addProject(
			Person hasPrincipalInvestigator,
			List<Person> hasCoPrincipalInvestigators,
			String hasTitle,
			String hasAbstract,
			GregorianCalendar hasStartDate_Funding,
			GregorianCalendar hasEndDate_Funding){
		
		Project project = projects.get(hasTitle);
		
		if(project == null){
			project = new Project();
			project.setHasPrincipalInvestigator(hasPrincipalInvestigator);
			project.setHasCoPrincipalInvestigator(hasCoPrincipalInvestigators);
			project.setHasTitle(hasTitle);
			project.setHasAbstract(hasAbstract);
			project.setHasStartDate_Funding(hasStartDate_Funding);
			project.setHasEndDate_Funding(hasEndDate_Funding);
			projects.put(hasTitle, project);
		}
	}

	protected Discipline getDiscipline(String disciplineName){
		Discipline discipline = disciplines.get(disciplineName);
		if(discipline == null){
			discipline = new Discipline();
			discipline.setHasName(disciplineName);
			disciplines.put(disciplineName, discipline);
		}
		return discipline;
	}
	
	protected Institution getInstitution(String institutionName){
		Institution institution = institutions.get(institutionName);
		if(institution == null){
			institution = new Institution();
			institution.setHasName(institutionName);
			institutions.put(institutionName, institution);
		}
		return institution;
	}
	
	protected Person getPerson(String firstName, String lastName, Discipline discipline, Institution institution){
		Person person = people.get(lastName + ", " + firstName);
		if(person == null){
			person = new Person();
			person.setHasFirstName(firstName);
			person.setHasLastName(lastName);
			person.addHasDiscipline(discipline);
			person.addAffiliatedWithInstitution(institution);
			people.put(lastName + ", " + firstName, person);
		}
		return person;
	}
	
	@Override
	public String toString(){
		int i = 0;
		List<Project> projects = getProjects();
		String projectListing = "--- Projects ---\n";
		for(Project project : projects){
			System.out.println(i ++);
			projectListing += "project: " + project + "\n\n";
		}
		
		return projectListing;
	}
}