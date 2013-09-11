package edu.utep.cybershare.vlc.ontology.vocabulary;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;

/**
 * Friend of a Friend (FOAF)
 * <a href=""></a>
 * @author Nicholas Del Rio
 *
 */

public class FOAF extends Vocabulary {

	public static final String NAMESPACE = "http://xmlns.com/foaf/0.1";
	
	// Classes
	public static final String OWLClass_Person = NAMESPACE + "/Person";
	
	// Data Properties
	public static final String OWLDataProperty_homepage = NAMESPACE + "/homepage";
	public static final String OWLDataProperty_firstName = NAMESPACE + "/firstName";
	public static final String OWLDataProperty_lastName = NAMESPACE + "/lastName";
	public static final String OWLDataProperty_name = NAMESPACE + "/name";
	
	public FOAF(OWLDataFactory factory){
		super(factory);
	}
	
	public OWLClass getOWLClass_Person(){
		return factory.getOWLClass(IRI.create(OWLClass_Person));
	}
	public OWLDataProperty getOWLObjectProperty_homepage(){
		return factory.getOWLDataProperty(IRI.create(OWLDataProperty_homepage));
	}
	public OWLDataProperty getOWLDataProperty_firstName(){
		return factory.getOWLDataProperty(IRI.create(OWLDataProperty_firstName));
	}
	public OWLDataProperty getOWLDataProperty_lastName(){
		return factory.getOWLDataProperty(IRI.create(OWLDataProperty_lastName));
	}
	public OWLDataProperty getOWLDataProperty_name(){
		return factory.getOWLDataProperty(IRI.create(OWLDataProperty_name));
	}
	@Override
	public String getNamespace() {
		// TODO Auto-generated method stub
		return NAMESPACE;
	}
}