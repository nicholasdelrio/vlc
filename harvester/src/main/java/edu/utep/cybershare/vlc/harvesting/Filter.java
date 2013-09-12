package edu.utep.cybershare.vlc.harvesting;

import edu.utep.cybershare.vlc.builder.ModelProduct;

public interface Filter {
	
	public ModelProduct process(ModelProduct product);
}
