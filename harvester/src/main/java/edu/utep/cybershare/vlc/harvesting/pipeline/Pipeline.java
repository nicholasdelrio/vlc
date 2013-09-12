package edu.utep.cybershare.vlc.harvesting.pipeline;

import java.util.ArrayList;

import edu.utep.cybershare.vlc.builder.ModelProduct;
import edu.utep.cybershare.vlc.harvesting.filter.DumpFilter;
import edu.utep.cybershare.vlc.harvesting.filter.Filter;
import edu.utep.cybershare.vlc.harvesting.filter.SourceFilter;

public class Pipeline {
	
	private SourceFilter modelSource;
	private ArrayList<Filter> modelFilters;
	private DumpFilter modelDump;
	
	public Pipeline(SourceFilter source, DumpFilter dump){
		modelSource = source;
		modelDump = dump;
		
		modelFilters = new ArrayList<Filter>();
	}

	public void addFilter(Filter aFilter){
		modelFilters.add(aFilter);
	}
	
	public void execute(){
		ModelProduct product = modelSource.getModelProduct();
		for(Filter aFilter : modelFilters)
			product = aFilter.process(product);
		
		modelDump.dumpModelProduct(product);
	}
}
