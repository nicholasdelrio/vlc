package edu.utep.cybershare.vlc.ontology.axioms;

import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class FactoryBundle {
	private OWLDataFactory dataFactory;
	private OWLOntology ontology;
	private OWLOntologyManager ontologyManager;
	private String baseIRI;
	
	public String getIndividualIRI(String individualName){
		return baseIRI + "#" + individualName;
	}
	
	public void setBaseIRI(String baseIRI){
		this.baseIRI = baseIRI;
	}
	
	public String getBaseIRI(){
		return this.baseIRI;
	}
	
	public OWLDataFactory getDataFactory() {
		return dataFactory;
	}
	public void setDataFactory(OWLDataFactory dataFactory) {
		this.dataFactory = dataFactory;
	}
	public OWLOntology getOntology() {
		return ontology;
	}
	public void setOntology(OWLOntology ontology) {
		this.ontology = ontology;
	}
	public OWLOntologyManager getOntologyManager() {
		return ontologyManager;
	}
	public void setOntologyManager(OWLOntologyManager ontologyManager) {
		this.ontologyManager = ontologyManager;
	}
}
