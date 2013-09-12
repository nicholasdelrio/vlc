package edu.utep.cybershare.vlc.harvesting.filter;

import edu.utep.cybershare.vlc.builder.ModelProduct;

public interface Filter {
	
	public ModelProduct process(ModelProduct product);
}
