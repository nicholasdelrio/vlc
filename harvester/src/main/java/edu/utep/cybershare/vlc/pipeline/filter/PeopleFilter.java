package edu.utep.cybershare.vlc.pipeline.filter;

import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import edu.utep.cybershare.vlc.build.ModelProduct;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;
import edu.utep.cybershare.vlc.pipeline.Pipeline.Filter;
import edu.utep.cybershare.vlc.util.FilterSourceData;

/**
 * Filters projects based on a listing of people populated by Deana Pennington for demo purposes.
 * @author Nicholas Del Rio
 *
 */

public class PeopleFilter implements Filter {
	
	private Hashtable<String, Boolean> peopleOfInterest;

	public PeopleFilter(){
		peopleOfInterest = new Hashtable<String, Boolean>();
		
		File csvFile = FilterSourceData.getPeople();
		
		try{
			CSVReader reader = new CSVReader(new FileReader(csvFile));
			List<String[]> records = reader.readAll();
		    String firstName;
		    String lastName;
		    String properName;
		    for(String[] record : records){
		    	firstName = record[0];
		    	lastName = record[1];
		    	properName = getProperName(firstName, lastName);
		    	peopleOfInterest.put(properName, new Boolean(true));
		    }
			reader.close();
		}catch(Exception e){e.printStackTrace();}
	}
	
	public ModelProduct process(ModelProduct product) {
		for(Project aProject : product.getProjects()){
			if(!isTargetProject(aProject))
				product.removeProject(aProject);
		}		
		return product;
	}
	
	private boolean isTargetProject(Project aProject){
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
		if(peopleOfInterest.get(getProperName(firstName, lastName)) == null)
			return false;
		return true;
	}

	private String getProperName(String firstName, String lastName){
		return lastName + ", " + firstName;
	}
}