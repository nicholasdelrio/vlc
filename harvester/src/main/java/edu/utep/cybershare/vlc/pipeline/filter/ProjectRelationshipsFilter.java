package edu.utep.cybershare.vlc.pipeline.filter;

import java.util.List;

import edu.utep.cybershare.vlc.build.ModelProduct;
import edu.utep.cybershare.vlc.model.Project;
import edu.utep.cybershare.vlc.pipeline.Pipeline.Filter;
import edu.utep.cybershare.vlc.util.FilterSourceData;
import edu.utep.cybershare.vlc.util.Relationships;
/**
 * Work in progress...may not be necessary
 * @author Nicholas Del Rio
 *
 */
public class ProjectRelationshipsFilter implements Filter {

	// contains collection information as well as
	private Relationships relationships;
	
	public ProjectRelationshipsFilter(){
		relationships = new Relationships(FilterSourceData.getProjectRelationships());
	}
	
	public ModelProduct process(ModelProduct product) {
		List<String> relatedProjectTitles;
		for(Project aProject : product.getProjects()){
			relatedProjectTitles = relationships.getRelatedProjectTitles(aProject);
			for(String aTitle : relatedProjectTitles){
				for(Project anotherProject : product.getProjects()){
					if(aTitle.equals(anotherProject.getIdentification())){
						aProject.addRelatedResource(anotherProject);
						System.out.println("Project: " + aProject.getIdentification() + " is related to: " + anotherProject.getIdentification());
					}
				}
			}
		}
		return product;
	}
}
