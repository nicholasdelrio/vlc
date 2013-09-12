package edu.utep.cybershare.vlc.harvesting.filter;

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
	
	private String dir;
	private String fileName;
	
	public RDFDumpFilter(String directory, String fileName){
		this.dir = directory;
		this.fileName = fileName;
	}
	
	public void dumpModelProduct(ModelProduct product) {	//set the tools that the axiom setters will use to populate the ontology
		OntologyToolset toolset = new OntologyToolset("http://vlc.cybershare.utep.edu/" + fileName);
	
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
		
		String outputFilePath = dir + fileName;
				
		//convert each project to a set of ontology axioms
		//print out the resulting ontology
		toolset.dumpOntology(new File(outputFilePath));		
	}
}
