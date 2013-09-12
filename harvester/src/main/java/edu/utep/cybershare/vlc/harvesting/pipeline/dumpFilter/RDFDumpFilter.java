package edu.utep.cybershare.vlc.harvesting.pipeline.dumpFilter;

import java.io.File;

import edu.utep.cybershare.vlc.builder.ModelProduct;
import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;
import edu.utep.cybershare.vlc.ontology.OWLVisitor;
import edu.utep.cybershare.vlc.ontology.OntologyToolset;

public class RDFDumpFilter implements DumpFilter {

	public static final String OWL_FILENAME = "projects.owl";
	public static final String OWL_FILENAME_FILTERED = "filtered-projects.owl";	
	public static final String DUMP_DIRECTORY = "./output-rdf/";
	
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
		for(Project aProject : product.getProjects())
			aProject.accept(axiomGenerator);
		
		//visit all institutions
		for(Institution anInstitution : product.getInstitutions())
			anInstitution.accept(axiomGenerator);
		
		//visit all people
		for(Person aPerson : product.getPeople())
			aPerson.accept(axiomGenerator);
						
		//convert each project to a set of ontology axioms
		//print out the resulting ontology
		toolset.dumpOntology(dumpFile);		
	}
}
