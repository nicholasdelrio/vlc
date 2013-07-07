package edu.utep.cybershare.vlc.ontology;

import java.util.List;

import edu.utep.cybershare.vlc.ontology.axioms.AxiomSetter;
import edu.utep.cybershare.vlc.ontology.axioms.FactoryBundle;
import edu.utep.cybershare.vlc.ontology.axioms.ProjectAxioms;

public class ProjectSerializer {
	
	private List<Project> projects;
	private FactoryBundle bundle;
	
	public ProjectSerializer(List<Project> someProjects){
		projects = someProjects;
		
		bundle = new FactoryBundle("http://test-uri");		
		convertToAxioms();
	}
	
	public void serializeToRDF(){
		bundle.getOntology().getAxiomCount();
	}
	
	private void convertToAxioms(){
		AxiomSetter.setBundle(bundle);
		for(Project aProject : projects){
			new ProjectAxioms(aProject);
		}
	}
}