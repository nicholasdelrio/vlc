package edu.utep.cybershare.vlc.ontology.axioms;

import java.util.ArrayList;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import edu.utep.cybershare.vlc.ontology.OntologyToolset;
import edu.utep.cybershare.vlc.ontology.vocabulary.AIISO;
import edu.utep.cybershare.vlc.ontology.vocabulary.ARPFO;
import edu.utep.cybershare.vlc.ontology.vocabulary.DCMI;
import edu.utep.cybershare.vlc.ontology.vocabulary.FOAF;
import edu.utep.cybershare.vlc.ontology.vocabulary.PROVO;
import edu.utep.cybershare.vlc.ontology.vocabulary.VLC;
import edu.utep.cybershare.vlc.ontology.vocabulary.VOID;
import edu.utep.cybershare.vlc.ontology.vocabulary.XSD;

public abstract class Axioms extends ArrayList<OWLAxiom> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected OntologyToolset bundle;	
	protected OWLNamedIndividual individual;
	
	protected AIISO vocabulary_AIISO;
	protected ARPFO vocabulary_ARPFO;
	protected DCMI vocabulary_DCMI;
	protected FOAF vocabulary_FOAF;
	protected PROVO vocabulary_PROVO;
	protected VLC vocabulary_VLC;
	protected VOID vocabulary_VOID;
	protected XSD vocabulary_XSD;
		
	protected Axioms(OWLNamedIndividual individual, OntologyToolset bundle){
		this.individual = individual;
		this.bundle = bundle;
		initializeVocabularies();
	}
	
	private void initializeVocabularies(){
		OWLDataFactory dataFactory = bundle.getDataFactory();
		vocabulary_AIISO = new AIISO(dataFactory);
		vocabulary_ARPFO = new ARPFO(dataFactory);
		vocabulary_DCMI = new DCMI(dataFactory);
		vocabulary_FOAF = new FOAF(dataFactory);
		vocabulary_PROVO = new PROVO(dataFactory);
		vocabulary_VLC = new VLC(dataFactory);
		vocabulary_VOID = new VOID(dataFactory);
		vocabulary_XSD = new XSD(dataFactory);
	}
		
	protected void setTypeAxiom(OWLClass owlClass){
		OWLClassAssertionAxiom classAssertionAxiom = bundle.getDataFactory().getOWLClassAssertionAxiom(owlClass, individual);
		add(classAssertionAxiom);
	}	
	
	public abstract void setAxioms();
}