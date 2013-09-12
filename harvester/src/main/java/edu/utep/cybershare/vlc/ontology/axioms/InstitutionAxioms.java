package edu.utep.cybershare.vlc.ontology.axioms;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.ontology.OntologyToolset;

public class InstitutionAxioms extends Axioms {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Institution institution;
	
	public InstitutionAxioms(Institution anInstitution, OWLNamedIndividual individual, OntologyToolset toolset) {
		super(individual, toolset);
		institution = anInstitution;
	}

	@Override
	public void setAxioms() {
		addLatitude();
		addLongitude();
		addAddress();
		addCity();
		addState();
		addZipcode();
	}
	
	private void addLongitude(){
		if(institution.isSet_coordinate()){
			OWLLiteral longitudeLiteral = bundle.getDataFactory().getOWLLiteral(institution.getCoordinate().getLongitude());
			OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(this.vocabulary_VLC.getOWLDataProperty_hasLongitude(), individual, longitudeLiteral);
			add(assertion);		
		}
	}

	private void addLatitude(){
		if(institution.isSet_coordinate()){
			OWLLiteral latitudeLiteral = bundle.getDataFactory().getOWLLiteral(institution.getCoordinate().getLatitude());
			OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(this.vocabulary_VLC.getOWLDataProperty_hasLatitude(), individual, latitudeLiteral);
			add(assertion);	
		}
	}
		
	private void addAddress(){
		OWLLiteral addressLiteral = bundle.getDataFactory().getOWLLiteral(institution.getAddress());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(this.vocabulary_VLC.getOWLDataProperty_hasAddress(), individual, addressLiteral);
		add(assertion);		
	}
	
	private void addCity(){
		if(institution.isSet_city()){
			OWLLiteral cityLiteral = bundle.getDataFactory().getOWLLiteral(institution.getCity());
			OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(this.vocabulary_VLC.getOWLDataProperty_hasCity(), individual, cityLiteral);
			add(assertion);
		}
	}
	
	private void addState(){
		if(institution.isSet_state()){
			OWLLiteral stateLiteral = bundle.getDataFactory().getOWLLiteral(institution.getState());
			OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(this.vocabulary_VLC.getOWLDataProperty_hasState(), individual, stateLiteral);
			add(assertion);
		}
	}

	private void addZipcode(){
		if(institution.isSet_zipCode()){
			OWLLiteral zipcodeLiteral = bundle.getDataFactory().getOWLLiteral(institution.getZipCode());
			OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(this.vocabulary_VLC.getOWLDataProperty_hasZipcode(), individual, zipcodeLiteral);
			add(assertion);
		}
	}
}