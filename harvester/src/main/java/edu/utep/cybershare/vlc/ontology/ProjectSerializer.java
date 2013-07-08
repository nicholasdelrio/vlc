package edu.utep.cybershare.vlc.ontology;

import java.io.File;
import java.util.List;

import org.semanticweb.owlapi.io.FileDocumentTarget;
import org.semanticweb.owlapi.io.StringDocumentTarget;

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
		
		FileDocumentTarget target = new FileDocumentTarget(new File("./output-rdf/projects.rdf"));
		try{
			bundle.getOntologyManager().saveOntology(bundle.getOntology(), target);
			System.out.println(target.toString());
			
			
			
		}catch(Exception e){e.printStackTrace();}
	}
	
	private void convertToAxioms(){
		AxiomSetter.setBundle(bundle);
		for(Project aProject : projects){
			new ProjectAxioms(aProject);
		}
	}
}