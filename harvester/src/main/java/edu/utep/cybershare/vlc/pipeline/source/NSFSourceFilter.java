package edu.utep.cybershare.vlc.pipeline.source;

import edu.utep.cybershare.vlc.build.ModelProduct;
import edu.utep.cybershare.vlc.build.NASABuilder;
import edu.utep.cybershare.vlc.build.NASADirector;
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

		ModelProduct product = new ModelProduct();
		
		// instantiate builders
		NSFBuilder nsfBuilder = new NSFBuilder(product);
		NASABuilder nasaBuilder = new NASABuilder(product);
		
		NSFDirector nsfDirector = new NSFDirector(nsfBuilder);
		nsfDirector.construct(nsfAwardsXML);

		NASADirector nasaDirector = new NASADirector(nasaBuilder);
		nasaDirector.construct();
		
		return nasaBuilder.getResult();
	}
}
