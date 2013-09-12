package edu.utep.cybershare.vlc.harvesting;

import java.io.File;

import edu.utep.cybershare.vlc.harvesting.pipeline.Pipeline;
import edu.utep.cybershare.vlc.harvesting.pipeline.dumpFilter.RDFDumpFilter;
import edu.utep.cybershare.vlc.harvesting.pipeline.sourceFilter.NSFSourceFilter;

public class Harvester {

	public static void main(String[] args){
		
		//create nsf source filter
		NSFSourceFilter nsfSourceFilter = new NSFSourceFilter();
		
		//create rdf dump filter
		RDFDumpFilter rdfDumpFilter = null;
		
		try{
			rdfDumpFilter = new RDFDumpFilter(new File(RDFDumpFilter.DUMP_DIRECTORY + RDFDumpFilter.OWL_FILENAME));
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
		
		Pipeline harvestingPipeline = new Pipeline(nsfSourceFilter, rdfDumpFilter);
		harvestingPipeline.execute();
	}
}
