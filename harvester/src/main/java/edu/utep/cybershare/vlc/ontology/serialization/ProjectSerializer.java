package edu.utep.cybershare.vlc.ontology.serialization;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.apibinding.OWLManager;

import edu.utep.cybershare.vlc.ontology.Project;

public class ProjectSerializer {
	
	public ProjectSerializer(Project aProject){
		// Get hold of an ontology manager
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        // Let's load an ontology from the web
        IRI iri = IRI.create("http://www.co-ode.org/ontologies/pizza/pizza.owl");
        OWLOntology pizzaOntology = manager.loadOntologyFromOntologyDocument(iri);
        System.out.println("Loaded ontology: " + pizzaOntology);	
	}
}
