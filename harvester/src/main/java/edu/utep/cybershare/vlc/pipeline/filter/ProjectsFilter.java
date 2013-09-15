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

public class ProjectsFilter implements Filter {
	private Hashtable<String, Boolean> projectsOfInterest;

	public ProjectsFilter(){
		projectsOfInterest = new Hashtable<String, Boolean>();
		
		File csvFile = FilterSourceData.getWaterSustainabilityProjects();
		
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
	
	public ModelProduct process(ModelProduct product) {
		for(Project aProject : product.getProjects()){
			if(!isTargetProject(aProject))
				product.removeProject(aProject);
		}		
		return product;
	}
	
	private boolean isTargetProject(Project aProject){
		return this.projectsOfInterest.get(aProject.getIdentification()) != null;
	}
	

	private String getProperName(String firstName, String lastName){
		return lastName + ", " + firstName;
	}
}
