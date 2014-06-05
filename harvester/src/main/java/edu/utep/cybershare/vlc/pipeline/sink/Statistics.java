package edu.utep.cybershare.vlc.pipeline.sink;

import edu.utep.cybershare.vlc.build.ModelProduct;
import edu.utep.cybershare.vlc.pipeline.Pipeline.DumpFilter;

public class Statistics implements DumpFilter {

	public void dumpModelProduct(ModelProduct product) {
		int insts = product.getInstitutions().size();
		int agencies = product.getAgencies().size();
		int people = product.getPeople().size();
		int projects = product.getProjects().size();
		
		System.out.println("instituions: " + insts);
		System.out.println("agencies: " + agencies);
		System.out.println("people: " + people);
		System.out.println("projects: " + projects);
	}
}
