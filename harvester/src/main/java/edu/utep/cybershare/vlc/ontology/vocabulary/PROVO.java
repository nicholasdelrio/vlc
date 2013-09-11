package edu.utep.cybershare.vlc.ontology.vocabulary;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectProperty;

/**
 * The PROV Ontology (PROV-O)
 * <a href="http://www.w3.org/TR/prov-o/">http://www.w3.org/TR/prov-o/</>
 * @author Nicholas Del Rio
 *
 */

public class PROVO extends Vocabulary {
	
	public static final String NAMESPACE = "http://www.w3.org/ns/prov-o";
	
	// Object Properties
	public static final String OWLObjectProperty_influenced = NAMESPACE + "/influenced";

	public PROVO(OWLDataFactory dataFactory) {
		super(dataFactory);
		// TODO Auto-generated constructor stub
	}
	public OWLObjectProperty getOWLObjectProperty_influenced(){
		return factory.getOWLObjectProperty(IRI.create(OWLObjectProperty_influenced));
	}
}
