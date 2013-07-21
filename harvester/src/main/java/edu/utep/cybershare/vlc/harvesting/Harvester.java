package edu.utep.cybershare.vlc.harvesting;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.utep.cybershare.vlc.ontology.Project;
import edu.utep.cybershare.vlc.ontology.axioms.AxiomSetter;
import edu.utep.cybershare.vlc.ontology.axioms.OntologyToolset;
import edu.utep.cybershare.vlc.ontology.axioms.ProjectAxioms;
import edu.utep.cybershare.vlc.sources.ProjectSource;
import edu.utep.cybershare.vlc.sources.nsf.NSFAwards;

public class Harvester {
	
	private static final String OWL_FILENAME = "projects.owl";

	public static void main(String[] args){
		//load set of project objects
		ProjectSource nsfSource = new NSFAwards();
		List<Project> projects = nsfSource.getProjects();
		
		System.out.println("Unique Projects: " + projects.size());
		System.out.println("People: " + nsfSource.getPeople().size());
		System.out.println("Institutions: " + nsfSource.getInstitutions().size());
		System.out.println("Disciplines: " + nsfSource.getDisciplines().size());
		/*
		//set the tools that the axiom setters will use to populate the ontology
		OntologyToolset toolset = new OntologyToolset("http://vlc.cybershare.utep.edu/" + OWL_FILENAME);
		AxiomSetter.setToolset(toolset);
		
		//convert each project to a set of ontology axioms
		ArrayList<Project> badProjects = new ArrayList<Project>();		
		for(Project aProject : projects){
			if(!aProject.isFullySpecified()) //if project project is missing required properties, add to naughty list
				badProjects.add(aProject);
			
			else //else, convert to axioms
				new ProjectAxioms(aProject);
		}
		
		//print out the resulting ontology
		toolset.dumpOntology(new File("./output-rdf/" + OWL_FILENAME));
		
		//print out any errors
		printBadProjects(badProjects);*/
	}
	
	private static void printBadProjects(List<Project> badProjects){
		if(badProjects.size() > 0){
			System.out.println("There were some naughty projecs that were missing required properties! Punish them!!!");
			
			for(Project aBadProject : badProjects)
				System.out.println(aBadProject);
		}
	}
}