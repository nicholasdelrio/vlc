package edu.utep.cybershare.vlc.pipeline.filter;

import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import edu.utep.cybershare.vlc.build.ModelProduct;
import edu.utep.cybershare.vlc.build.source.Awards;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;
import edu.utep.cybershare.vlc.pipeline.Pipeline.Filter;

public class ProjectsFilter implements Filter {
	private Hashtable<String, Boolean> projectsOfInterest;
	private Hashtable<String, Boolean> peopleOfInterest;

	private ModelProduct product;
	
	public ProjectsFilter(){
		populateProjectsOfInterest();
		populatePeopleOfInterest();
	}
	
	public ModelProduct process(ModelProduct product) {
		this.product = product;		
		
		for(Project aProject : product.getProjects()){
			if(!isTargetProject(aProject))
				product.removeProject(aProject);
		}		
		return product;
	}
	
	private void populatePeopleOfInterest(){
		peopleOfInterest = new Hashtable<String, Boolean>();
		
		File csvFile = FilterData.getPeople();
		
		try{
			CSVReader reader = new CSVReader(new FileReader(csvFile));
			List<String[]> records = reader.readAll();
		    String firstName;
		    String lastName;
		    String properName;
		    Person tempPerson;
		    for(String[] record : records){
		    	firstName = record[0];
		    	lastName = record[1];
		    	tempPerson = new Person(firstName, lastName);
		    	properName = tempPerson.getIdentification();
		    	peopleOfInterest.put(properName, new Boolean(true));
		    }
			reader.close();
		}catch(Exception e){e.printStackTrace();}
	}
	
	private void populateProjectsOfInterest(){
		projectsOfInterest = new Hashtable<String, Boolean>();
		
		File csvFile = FilterData.getWaterSustainabilityProjects();
		
		try{
			CSVReader reader = new CSVReader(new FileReader(csvFile));
			List<String[]> records = reader.readAll();
			String projectTitle;
		    for(String[] record : records){
		    	projectTitle = record[1];
		    	projectsOfInterest.put(projectTitle, new Boolean(true));
		    }
			reader.close();
		}catch(Exception e){e.printStackTrace();}

	}
	
	private boolean isTargetProject(Project aProject){
		return this.projectsOfInterest.get(aProject.getIdentification()) != null || isTargetProjectViaPeople(aProject) || product.isFundedByAgency(Awards.getAGENCY_NASA(), aProject);
	}
		
	private boolean isTargetProjectViaPeople(Project aProject){
		String firstName;
		String lastName;
		
		boolean isTargetProject = false;
		
		if(aProject.getPrincipalInvestigator() == null){
			return false;
		}
		
		//check if pi is target first
		firstName = aProject.getPrincipalInvestigator().getFirstName();
		lastName = aProject.getPrincipalInvestigator().getLastName();
		isTargetProject |= isTargetPerson(firstName, lastName);
		
		//check co-pis
		Collection<Person> coPIS = aProject.getCoPrincipalInvestigators();
		for(Person aPerson : coPIS){
			firstName = aPerson.getFirstName();
			lastName = aPerson.getLastName();
			isTargetProject |= isTargetPerson(firstName, lastName);
		}
		
		return isTargetProject;
	}
	private boolean isTargetPerson(String firstName, String lastName){
		Person tempPerson = new Person(firstName, lastName);
		if(peopleOfInterest.get(tempPerson.getIdentification()) == null)
			return false;
		return true;
	}

}
