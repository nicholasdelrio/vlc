package edu.utep.cybershare.vlc.ontology.vocabulary;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;

public class RDFS extends Vocabulary {

	public static final String NAMESPACE = "http://www.w3.org/2000/01/rdf-schema";
	
	// Data Properties
	public static final String OWLDataProperty_label = NAMESPACE + "#label";
	
	public RDFS(OWLDataFactory factory){
		super(factory);
	}
	
	public OWLDataProperty getOWLDataProperty_label(){
		return factory.getOWLDataProperty(IRI.create(OWLDataProperty_label));
	}
	@Override
	public String getNamespace() {
		// TODO Auto-generated method stub
		return NAMESPACE;
	}
}
