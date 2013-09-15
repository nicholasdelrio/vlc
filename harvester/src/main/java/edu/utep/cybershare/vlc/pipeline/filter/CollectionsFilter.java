package edu.utep.cybershare.vlc.pipeline.filter;

import java.util.List;

import edu.utep.cybershare.vlc.build.ModelProduct;
import edu.utep.cybershare.vlc.model.Project;
import edu.utep.cybershare.vlc.pipeline.Pipeline.Filter;
import edu.utep.cybershare.vlc.util.Relationships;

public class CollectionsFilter implements Filter {

	// contains collection information as well as
	private Relationships relationships;
	
	public CollectionsFilter(){
		relationships = new Relationships();
	}
	
	public ModelProduct process(ModelProduct product) {
		List<String> collectionIDs;
		for(Project aProject : product.getProjects()){
			collectionIDs = relationships.getParentCollections(aProject);
			for(String collectionID : collectionIDs)
				aProject.addCollectionID(collectionID);
		}
		return product;
	}
}
