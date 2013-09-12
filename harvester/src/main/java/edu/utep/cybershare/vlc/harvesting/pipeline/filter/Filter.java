package edu.utep.cybershare.vlc.harvesting.pipeline.filter;

import edu.utep.cybershare.vlc.builder.ModelProduct;

public interface Filter {
	
	public ModelProduct process(ModelProduct product);
}
