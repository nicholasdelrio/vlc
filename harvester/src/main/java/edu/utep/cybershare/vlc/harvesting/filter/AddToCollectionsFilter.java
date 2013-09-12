package edu.utep.cybershare.vlc.harvesting.filter;

import java.util.List;

import edu.utep.cybershare.vlc.builder.ModelProduct;
import edu.utep.cybershare.vlc.model.Project;
import edu.utep.cybershare.vlc.util.Relationships;

public class AddToCollectionsFilter implements Filter {

	// contains collection information as well as
	private Relationships relationships;
	
	public AddToCollectionsFilter(){
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
