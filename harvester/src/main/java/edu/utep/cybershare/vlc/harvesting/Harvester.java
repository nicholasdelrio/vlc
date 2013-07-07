package edu.utep.cybershare.vlc.harvesting;

import java.util.List;

import edu.utep.cybershare.vlc.ontology.Project;
import edu.utep.cybershare.vlc.ontology.ProjectSerializer;
import edu.utep.cybershare.vlc.sources.ProjectSource;
import edu.utep.cybershare.vlc.sources.nsf.NSFAwards;

public class Harvester {

	public static void main(String[] args){
		ProjectSource nsfSource = new NSFAwards();
		List<Project> projects = nsfSource.getProjects();
		System.out.println("number of projects: " + projects.size());
		
		for(Project aProject : projects){
			System.out.println(aProject.getHasTitle());
		}
		
		ProjectSerializer serializer = new ProjectSerializer(projects);
		serializer.serializeToRDF();
	}
}
