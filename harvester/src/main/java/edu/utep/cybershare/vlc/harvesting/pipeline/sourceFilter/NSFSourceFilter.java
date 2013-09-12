package edu.utep.cybershare.vlc.harvesting.pipeline.sourceFilter;

import edu.utep.cybershare.vlc.builder.ModelProduct;
import edu.utep.cybershare.vlc.builder.NSFBuilder;
import edu.utep.cybershare.vlc.builder.NSFDirector;
import edu.utep.cybershare.vlc.builder.source.XMLSet_NSF;

public class NSFSourceFilter implements SourceFilter {
	
	private XMLSet_NSF nsfAwardsXML;
	
	public NSFSourceFilter(){
		// hard coded XML NSF awards to process
		nsfAwardsXML = new XMLSet_NSF();
	}
	
	public ModelProduct getModelProduct(){
		// instantiate builder
		NSFBuilder builder = new NSFBuilder();
		
		NSFDirector director = new NSFDirector(builder);
		director.construct(nsfAwardsXML);
		
		return builder.getResult();
	}
}
