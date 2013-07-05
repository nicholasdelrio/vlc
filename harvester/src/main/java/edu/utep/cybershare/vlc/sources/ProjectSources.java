package edu.utep.cybershare.vlc.sources;

import edu.utep.cybershare.vlc.ontology.Project;
import edu.utep.cybershare.vlc.sources.nsf.NSFAwards;

public class ProjectSources {
	
	public static void main(String[] args){
		
		ProjectSource nsfSource = new NSFAwards();
		System.out.println("number of projects: " + nsfSource.getProjects().size());
		for(Project aProject : nsfSource.getProjects()){
			System.out.println(aProject.getHasTitle());
		}
	}
}
