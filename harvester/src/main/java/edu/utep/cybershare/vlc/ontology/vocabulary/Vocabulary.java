package edu.utep.cybershare.vlc.ontology.vocabulary;

import org.semanticweb.owlapi.model.OWLDataFactory;

public abstract class Vocabulary {
	
	protected OWLDataFactory factory;
	
	public Vocabulary(OWLDataFactory dataFactory){
		factory = dataFactory;
	}
	
	public abstract String getNamespace();
}
