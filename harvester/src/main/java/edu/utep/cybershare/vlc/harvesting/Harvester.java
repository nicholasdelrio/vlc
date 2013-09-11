package edu.utep.cybershare.vlc.harvesting;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.utep.cybershare.vlc.geocoding.InstitutionCSV;
import edu.utep.cybershare.vlc.model.Project;
import edu.utep.cybershare.vlc.ontology.axioms.AxiomSetter;
import edu.utep.cybershare.vlc.ontology.axioms.OntologyToolset;
import edu.utep.cybershare.vlc.ontology.axioms.ProjectAxioms;
import edu.utep.cybershare.vlc.relationships.Relationships;
import edu.utep.cybershare.vlc.sources.ProjectSource;
import edu.utep.cybershare.vlc.sources.nsf.NSFAwards;
import edu.utep.cybershare.vlc.subsetting.FilteredProjects;
import edu.utep.cybershare.vlc.upload.VLCProjectsUploader;

public class Harvester {
	
	private static final String OWL_FILENAME = "projects.owl";
	private static final String OWL_FILENAME_FILTERED = "filtered-projects.owl";
	
	public static void main(String[] args){
		//load set of project objects
		ProjectSource nsfSource = new NSFAwards();
		List<Project> projects = nsfSource.getProjects();

		// add relationships between projects as identified by Deana Pennington, including parent collections
		Relationships relationships = new Relationships();
		nsfSource.setProjectRelationships(relationships);
		
		System.out.println("Unique Projects: " + projects.size());
		System.out.println("People: " + nsfSource.getPeople().size());
		System.out.println("Institutions: " + nsfSource.getInstitutions().size());
		System.out.println("Disciplines: " + nsfSource.getDisciplines().size());
		
		//filter projects based on Deana Pennington's identified persons
		FilteredProjects filter = new FilteredProjects();
		projects = filter.filter(projects);
		System.out.println("number of projects after filtering: " + projects.size());
		
		//aggregate lat/lon coordinates with each institution
		InstitutionCSV coordinates = new InstitutionCSV();
		coordinates.setInstitutionCoordinates(nsfSource.getInstitutions());		
		
		//either dump or upload to VLC
		//publishToVLC(projects);
		dumpRDF(projects, OWL_FILENAME_FILTERED);
		
	}
	
	private static void printBadProjects(List<Project> badProjects){
		if(badProjects.size() > 0){
			System.out.println("There were some naughty projects that were missing required properties! Punish them!!!");
			
			for(Project aBadProject : badProjects)
				System.out.println(aBadProject);
		}
	}
	
	private static void publishToVLC(List<Project> projects){
		VLCProjectsUploader uploader = new VLCProjectsUploader();
		uploader.setProjects(projects);
		uploader.upload();
	}	
	
	private static void dumpRDF(List<Project> projects, String fileName){
		
		//set the tools that the axiom setters will use to populate the ontology
		OntologyToolset toolset = new OntologyToolset("http://vlc.cybershare.utep.edu/" + fileName);
		AxiomSetter.setToolset(toolset);
		
		String outputFilePath = "./output-rdf/" + fileName;
		
		
		//convert each project to a set of ontology axioms
		ArrayList<Project> badProjects = new ArrayList<Project>();		
		for(Project aProject : projects){
			if(!aProject.isFullySpecified()) //if project project is missing required properties, add to naughty list
				badProjects.add(aProject);
			
			else //else, convert to axioms
				new ProjectAxioms(aProject);
		}
		
		//print out the resulting ontology
		toolset.dumpOntology(new File(outputFilePath));
		
		//print out any errors
		printBadProjects(badProjects);
	}
}