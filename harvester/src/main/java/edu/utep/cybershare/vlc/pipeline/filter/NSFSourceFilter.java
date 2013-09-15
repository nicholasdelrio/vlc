package edu.utep.cybershare.vlc.pipeline.filter;

import edu.utep.cybershare.vlc.build.ModelProduct;
import edu.utep.cybershare.vlc.build.NSFBuilder;
import edu.utep.cybershare.vlc.build.NSFDirector;
import edu.utep.cybershare.vlc.build.source.XMLSet_NSF;
import edu.utep.cybershare.vlc.pipeline.Pipeline.SourceFilter;

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
