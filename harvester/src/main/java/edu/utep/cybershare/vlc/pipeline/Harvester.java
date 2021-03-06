package edu.utep.cybershare.vlc.pipeline;

import java.io.File;

import edu.utep.cybershare.vlc.pipeline.filter.CollectionsFilter;
import edu.utep.cybershare.vlc.pipeline.filter.InstitutionGeocodeFilter;
import edu.utep.cybershare.vlc.pipeline.filter.ProjectRelationshipsFilter;
import edu.utep.cybershare.vlc.pipeline.filter.ProjectsFilter;
import edu.utep.cybershare.vlc.pipeline.sink.RDFDumpFilter;
import edu.utep.cybershare.vlc.pipeline.sink.VLCProjectsUploader;
import edu.utep.cybershare.vlc.pipeline.source.ProjectSourceFilter;

public class Harvester {

	public static void main(String[] args){
		
		//create nsf source filter
		ProjectSourceFilter nsfSourceFilter = new ProjectSourceFilter();
		
		//create rdf dump filter
		RDFDumpFilter rdfDumpFilter = null;
		
		//create VLC upload filter
		VLCProjectsUploader vlcProjectsUploader = new VLCProjectsUploader();
		try{
			rdfDumpFilter = new RDFDumpFilter(new File(RDFDumpFilter.DUMP_DIRECTORY + RDFDumpFilter.OWL_FILENAME_FILTERED));
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
		
		// create deana filter
		ProjectsFilter deanaFilter = new ProjectsFilter();
		
		// create add to collections filter
		CollectionsFilter collectionsFilter = new CollectionsFilter();
		
		// create project to project filter
		ProjectRelationshipsFilter relationshipsFilter = new ProjectRelationshipsFilter();
		
		// create geocoder filter
		InstitutionGeocodeFilter geocoderFilter = new InstitutionGeocodeFilter();
		
		Pipeline harvestingPipeline = new Pipeline(nsfSourceFilter, rdfDumpFilter);
		//Pipeline harvestingPipeline = new Pipeline(nsfSourceFilter, vlcProjectsUploader);
		
		
		harvestingPipeline.addFilter(deanaFilter);
		harvestingPipeline.addFilter(geocoderFilter);
		harvestingPipeline.addFilter(collectionsFilter);
		harvestingPipeline.addFilter(relationshipsFilter);
		
		harvestingPipeline.execute();
	}
}
