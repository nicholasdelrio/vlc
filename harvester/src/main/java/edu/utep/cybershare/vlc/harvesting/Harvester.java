package edu.utep.cybershare.vlc.harvesting;

import edu.utep.cybershare.vlc.ontology.Project;
import edu.utep.cybershare.vlc.ontology.ProjectSerializer;
import edu.utep.cybershare.vlc.sources.ProjectSource;
import edu.utep.cybershare.vlc.sources.nsf.NSFAwards;

public class Harvester {

	public static void main(String[] args){
		ProjectSource nsfSource = new NSFAwards();
		System.out.println("number of projects: " + nsfSource.getProjects().size());
		
		for(Project aProject : nsfSource.getProjects()){
			System.out.println(aProject.getHasTitle());
		}
		
		ProjectSerializer serializer = new ProjectSerializer(nsfSource.getProjects());
		serializer.serializeToRDF();
	}
}
