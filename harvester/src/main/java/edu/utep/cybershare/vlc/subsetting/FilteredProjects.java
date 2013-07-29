package edu.utep.cybershare.vlc.subsetting;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import edu.utep.cybershare.vlc.ontology.Person;
import edu.utep.cybershare.vlc.ontology.Project;

public class FilteredProjects {
	
	private Hashtable<String, Boolean> peopleOfInterest;

	public FilteredProjects(){
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
	
	public List<Project> filter(List<Project> projects){
		HashSet<Project> filteredProjectsSet = new HashSet<Project>();
		ArrayList<Project> filteredProjects = new ArrayList<Project>();
		for(Project aProject : projects){
			if(isTargetProject(aProject))
				filteredProjectsSet.add(aProject);
		}
		
		Iterator<Project> projectIterator = filteredProjectsSet.iterator();
		while(projectIterator.hasNext())
			filteredProjects.add(projectIterator.next());
		
		return filteredProjects;
	}
	
	private boolean isTargetProject(Project aProject){
		String firstName;
		String lastName;
		
		boolean isTargetProject = false;
		
		//check if pi is target first
		firstName = aProject.getHasPrincipalInvestigator().getHasFirstName();
		lastName = aProject.getHasPrincipalInvestigator().getHasLastName();
		isTargetProject |= isTargetPerson(firstName, lastName);
		
		//check co-pis
		Collection<Person> coPIS = aProject.getHasCoPrincipalInvestigator();
		for(Person aPerson : coPIS){
			firstName = aPerson.getHasFirstName();
			lastName = aPerson.getHasLastName();
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
				aRecord[0] = aProject.getHasTitle();
				pi = aProject.getHasPrincipalInvestigator();
				aRecord[1] = pi.getHasFirstName() + " " + pi.getHasLastName();
			
				String coPiList = "";
				coPis = aProject.getHasCoPrincipalInvestigator();
				for(Person coPi : coPis){
					if(coPi.getHasFirstName().equals("null-name"))
						coPiList += "None";
					else
						coPiList += coPi.getHasFirstName() + " " + coPi.getHasLastName() + ", ";
				}
				
				if(coPiList.lastIndexOf(", ") > -1)
					coPiList = coPiList.substring(0, coPiList.lastIndexOf(", "));
				
				aRecord[2] = coPiList;
			
				writer.writeNext(aRecord);
			}
			writer.close();
		}catch(Exception e){e.printStackTrace();}
	}
}