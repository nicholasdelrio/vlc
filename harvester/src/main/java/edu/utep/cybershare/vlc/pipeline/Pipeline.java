package edu.utep.cybershare.vlc.pipeline;

import java.util.ArrayList;

import edu.utep.cybershare.vlc.build.ModelProduct;

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
	
	public static interface SourceFilter{
		public ModelProduct getModelProduct();
	}
	
	public static interface DumpFilter {	
		public void dumpModelProduct(ModelProduct product);
	}
	
	public static interface Filter {
		public ModelProduct process(ModelProduct product);
	}
}
