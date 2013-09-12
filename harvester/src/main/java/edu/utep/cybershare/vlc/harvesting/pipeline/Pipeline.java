package edu.utep.cybershare.vlc.harvesting.pipeline;

import java.util.ArrayList;

import edu.utep.cybershare.vlc.builder.ModelProduct;
import edu.utep.cybershare.vlc.harvesting.pipeline.dumpFilter.DumpFilter;
import edu.utep.cybershare.vlc.harvesting.pipeline.filter.Filter;
import edu.utep.cybershare.vlc.harvesting.pipeline.sourceFilter.SourceFilter;

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
		System.out.println("Constructing model...");
		ModelProduct product = modelSource.getModelProduct();

		System.out.println("Applying filters...");
		for(Filter aFilter : modelFilters)
			product = aFilter.process(product);
		
		System.out.println("Dumping model...");
		modelDump.dumpModelProduct(product);
	}
}
