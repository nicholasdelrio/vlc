package edu.utep.cybershare.vlc.ontology.vocabulary;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;


/**
 * Dublin Core Metadata Initiative (DCMI)
 * <a href="http://dublincore.org/">http://dublincore.org/</a>
 * @author Nicholas Del Rio
 *
 */

public class DCMI extends Vocabulary {

	public static final String NAMESPACE = "http://purl.org/dc/terms";
	

	// Object Properties
	public static final String OWLObjectProperty_subject = NAMESPACE + "/subject";
	public static final String OWLObjectProperty_isPartOf = NAMESPACE + "/isPartOf";
	
	// Data Properties
	public static final String OWLDataProperty_title = NAMESPACE + "/title";

	public DCMI(OWLDataFactory dataFactory) {
		super(dataFactory);
		// TODO Auto-generated constructor stub
	}
	
	public OWLObjectProperty getOWLObjectProperty_subject(){
		return factory.getOWLObjectProperty(IRI.create(OWLObjectProperty_subject));
	}
	public OWLDataProperty getOWLDataProperty_title(){
		return factory.getOWLDataProperty(IRI.create(OWLDataProperty_title));
	}

	@Override
	public String getNamespace() {
		// TODO Auto-generated method stub
		return NAMESPACE;
	}	
}
