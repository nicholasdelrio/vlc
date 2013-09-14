package edu.utep.cybershare.vlc.harvesting;

import java.io.File;

import edu.utep.cybershare.vlc.harvesting.pipeline.Pipeline;
import edu.utep.cybershare.vlc.harvesting.pipeline.dumpFilter.RDFDumpFilter;
import edu.utep.cybershare.vlc.harvesting.pipeline.filter.AddProjectToProjectRelationshipsFilter;
import edu.utep.cybershare.vlc.harvesting.pipeline.filter.AddToCollectionsFilter;
import edu.utep.cybershare.vlc.harvesting.pipeline.filter.InstitutionGeocodeFilter;
import edu.utep.cybershare.vlc.harvesting.pipeline.filter.PeopleOfInterestFilter;
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
		
		// create deana filter
		PeopleOfInterestFilter deanaFilter = new PeopleOfInterestFilter();
		
		// create add to collections filter
		AddToCollectionsFilter collectionsFilter = new AddToCollectionsFilter();
		
		// create project to project filter
		AddProjectToProjectRelationshipsFilter relationshipsFilter = new AddProjectToProjectRelationshipsFilter();
		
		// create geocoder filter
		InstitutionGeocodeFilter geocoderFilter = new InstitutionGeocodeFilter();
		
		Pipeline harvestingPipeline = new Pipeline(nsfSourceFilter, rdfDumpFilter);
		harvestingPipeline.addFilter(deanaFilter);
		harvestingPipeline.addFilter(geocoderFilter);
		harvestingPipeline.addFilter(collectionsFilter);
		harvestingPipeline.addFilter(relationshipsFilter);
		
		harvestingPipeline.execute();
	}
}
