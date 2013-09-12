package edu.utep.cybershare.vlc.builder;

import edu.utep.cybershare.vlc.harvesting.SourceFilter;
import edu.utep.cybershare.vlc.sources.XMLSet_NSF;

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
