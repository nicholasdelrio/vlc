package edu.utep.cybershare.vlc.harvesting.pipeline.filter;

import java.util.List;

import edu.utep.cybershare.vlc.builder.ModelProduct;
import edu.utep.cybershare.vlc.model.Project;
import edu.utep.cybershare.vlc.util.Relationships;
/**
 * Work in progress...may not be necessary
 * @author Nicholas Del Rio
 *
 */
public class AddProjectToProjectRelationshipsFilter implements Filter {

	// contains collection information as well as
	private Relationships relationships;
	
	public AddProjectToProjectRelationshipsFilter(){
		relationships = new Relationships();
	}
	
	public ModelProduct process(ModelProduct product) {
		List<String> relatedProjectTitles;
		for(Project aProject : product.getProjects()){
			relatedProjectTitles = relationships.getRelatedProjectTitles(aProject);
		}
		return product;
	}
}