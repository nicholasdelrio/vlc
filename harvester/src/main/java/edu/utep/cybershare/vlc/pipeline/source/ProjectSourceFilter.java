package edu.utep.cybershare.vlc.pipeline.source;

import edu.utep.cybershare.vlc.build.ModelProduct;
import edu.utep.cybershare.vlc.build.NASABuilder;
import edu.utep.cybershare.vlc.build.NASADirector;
import edu.utep.cybershare.vlc.build.NSFBuilder;
import edu.utep.cybershare.vlc.build.NSFDirector;
import edu.utep.cybershare.vlc.build.source.NASAAwards;
import edu.utep.cybershare.vlc.build.source.NSFAwards;
import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.pipeline.Pipeline.SourceFilter;

public class ProjectSourceFilter implements SourceFilter {
	
	private NSFAwards nsfAwards;
	private NASAAwards nasaAwards;
	public ProjectSourceFilter(){
		// hard coded XML NSF awards to process
		nsfAwards = new NSFAwards();
		nasaAwards = new NASAAwards();
	}
	
	public ModelProduct getModelProduct(){

		ModelProduct product = new ModelProduct();
		
		// instantiate builders
		NSFBuilder nsfBuilder = new NSFBuilder(product);
		NASABuilder nasaBuilder = new NASABuilder(product);

		NSFDirector nsfDirector = new NSFDirector(nsfBuilder);
		nsfDirector.construct(nsfAwards);
		
		/*
		NASADirector nasaDirector = new NASADirector(nasaBuilder);
		nasaDirector.construct(nasaAwards);		
	*/
		return nasaBuilder.getResult();
	}
}
