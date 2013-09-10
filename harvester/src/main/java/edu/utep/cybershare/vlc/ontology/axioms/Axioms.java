package edu.utep.cybershare.vlc.ontology.axioms;

import java.util.ArrayList;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

public abstract class Axioms extends ArrayList<OWLAxiom> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected OntologyToolset bundle;	
	protected OWLNamedIndividual individual;
	protected OWLDatatype xsdDateTime;
		
	protected Axioms(OWLNamedIndividual individual, String classIRI, OntologyToolset bundle){
		this.individual = individual;
		this.bundle = bundle;
		addTypeAssertion(classIRI);
		
		xsdDateTime = bundle.getDataFactory().getOWLDatatype(IRI.create("http://www.w3.org/2001/XMLSchema#dateTime"));
	}
		
	private void addTypeAssertion(String classIRI){
		OWLClass projectClass = bundle.getDataFactory().getOWLClass(IRI.create(classIRI));		
		OWLClassAssertionAxiom classAssertionAxiom = bundle.getDataFactory().getOWLClassAssertionAxiom(projectClass, individual);
		this.add(classAssertionAxiom);
	}
	
	protected abstract void populateIndividualAxioms();
	
	protected String removeIllegalCharacters(String text){
		// XML 1.0
		// #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
		String xml10pattern = "[^"
		                    + "\u0009\r\n"
		                    + "\u0020-\uD7FF"
		                    + "\uE000-\uFFFD"
		                    + "\ud800\udc00-\udbff\udfff"
		                    + "]";
		return text.replaceAll(xml10pattern, "");
	}
}