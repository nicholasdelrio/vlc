package edu.utep.cybershare.vlc.harvesting.filter;

import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import edu.utep.cybershare.vlc.builder.ModelProduct;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;

/**
 * Filters projects based on a listing of people populated by Deana Pennington for demo purposes.
 * @author Nicholas Del Rio
 *
 */

public class PeopleOfInterestFilter implements Filter {
	
	private Hashtable<String, Boolean> peopleOfInterest;

	public PeopleOfInterestFilter(){
		peopleOfInterest = new Hashtable<String, Boolean>();
		
		File csvFile = new File("./people-of-interest/vlc-demo-people.csv");
		
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
				product.getProjects().remove(aProject);
		}		
		return product;
	}
	
	private boolean isTargetProject(Project aProject){
		String firstName;
		String lastName;
		
		boolean isTargetProject = false;
		
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

	/*
	public void filterAndDumpCSV(List<Project> projects){
		List<Project> filteredProjects = filter(projects);
		try{
			CSVWriter writer = new CSVWriter(new FileWriter("./people-of-interest/target-vlc-projects.csv"), ',');
			// feed in your array (or convert your data to an array)

			String[] header = new String[]{"Project Title", "Principal Investigator", "Co-Principal Investigators", "Related to Project"};
			writer.writeNext(header);
			Collection<Person> coPis;
			Person pi;
			String[] aRecord = new String[3];
			for(Project aProject : filteredProjects){
				aRecord[0] = aProject.getTitle();
				pi = aProject.getPrincipalInvestigator();
				aRecord[1] = pi.getFirstName() + " " + pi.getLastName();
			
				String coPiList = "";
				coPis = aProject.getCoPrincipalInvestigators();
				for(Person coPi : coPis){
					if(coPi.getFirstName().equals("null-name"))
						coPiList += "None";
					else
						coPiList += coPi.getFirstName() + " " + coPi.getLastName() + ", ";
				}
				
				if(coPiList.lastIndexOf(", ") > -1)
					coPiList = coPiList.substring(0, coPiList.lastIndexOf(", "));
				
				aRecord[2] = coPiList;
			
				writer.writeNext(aRecord);
			}
			writer.close();
		}catch(Exception e){e.printStackTrace();}
	}
	*/
}