package edu.utep.cybershare.vlc.ontology.axioms;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.FileDocumentTarget;
import org.semanticweb.owlapi.model.AddImport;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import edu.utep.cybershare.vlc.ontology.vocabulary.VLC;

public class OntologyToolset {
	private OWLDataFactory dataFactory;
	private OWLOntology ontology;
	private OWLOntologyManager ontologyManager;
	private String baseIRI;
	
	public OntologyToolset(String baseIRI){
		this.baseIRI = baseIRI;
		
		dataFactory = OWLManager.getOWLDataFactory();
		ontologyManager = OWLManager.createOWLOntologyManager();
		try{ontology = ontologyManager.createOntology(IRI.create(baseIRI));}
		catch(Exception e){e.printStackTrace();}
		
		importVLCOntology();
	}
	
	private void importVLCOntology(){
		IRI vlcOntologyIRI = IRI.create(VLC.VLC_ONTOLOGY_IRI);
		OWLImportsDeclaration vlcImportDeclaration = dataFactory.getOWLImportsDeclaration(vlcOntologyIRI);
		AddImport addVLCImport = new AddImport(ontology, vlcImportDeclaration);
		ontologyManager.applyChange(addVLCImport);
	}
	
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

	public void dumpOntology(File aFile){
		FileDocumentTarget target = new FileDocumentTarget(aFile);
		try{ontologyManager.saveOntology(ontology, target);}
		catch(Exception e){e.printStackTrace();}
	}
}