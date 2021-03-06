package edu.utep.cybershare.vlc.pipeline.sink;

import java.io.File;

import edu.utep.cybershare.vlc.build.ModelProduct;
import edu.utep.cybershare.vlc.model.Agency;
import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;
import edu.utep.cybershare.vlc.ontology.OWLVisitor;
import edu.utep.cybershare.vlc.ontology.OntologyToolset;
import edu.utep.cybershare.vlc.pipeline.Pipeline.DumpFilter;

public class RDFDumpFilter implements DumpFilter {

	public static final String OWL_FILENAME = "projects.owl";
	public static final String OWL_FILENAME_FILTERED = "filtered-projects.owl";	
	public static final String DUMP_DIRECTORY = "../ontology.cybershare.utep.edu/VLC/data/";
	
	private File dumpFile;
	
	public RDFDumpFilter(File dumpFile){
		if(dumpFile == null)
			throw new IllegalArgumentException("Specified file is null!");
		
		this.dumpFile = dumpFile;
	}
	
	public void dumpModelProduct(ModelProduct product) {	//set the tools that the axiom setters will use to populate the ontology
		OntologyToolset toolset = new OntologyToolset("http://vlc.cybershare.utep.edu/" + dumpFile.getName());
	
		OWLVisitor axiomGenerator = new OWLVisitor(toolset);
		
		//visit all projects
		System.out.println("Transforming " + product.getProjects().size() + " projects into owl axioms...");
		for(Project aProject : product.getProjects())
			aProject.accept(axiomGenerator);
		
		//visit all people
		for(Person aPerson : product.getPeople())
			aPerson.accept(axiomGenerator);
		
		//visit all institutions
		for(Institution anInstitution : product.getInstitutions())
			anInstitution.accept(axiomGenerator);
		
		for(Agency anAgency : product.getAgencies())
			anAgency.accept(axiomGenerator);
				
		//convert each project to a set of ontology axioms
		//print out the resulting ontology
		System.out.println("Dumping owl axioms as RDF...");
		toolset.dumpOntology(dumpFile);		
	}
}
