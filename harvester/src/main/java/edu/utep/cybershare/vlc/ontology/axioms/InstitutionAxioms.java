package edu.utep.cybershare.vlc.ontology.axioms;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLLiteral;

import edu.utep.cybershare.vlc.ontology.Institution;

public class InstitutionAxioms extends AxiomSetter {
	
	public InstitutionAxioms(Institution anInstitution) {
		super(anInstitution.getHasName(), Vocabulary.CLASS_IRI_Institution, anInstitution);
		institution = anInstitution;
	}

	@Override
	protected void populateIndividualAxioms() {
		addHasLatitude();
		addHasLongitude();
		addHasNameAssertion();
	}
	
	private void addHasLongitude(){
		OWLDataProperty hasLongitude = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasLongitude));		
		OWLLiteral longitude = bundle.getDataFactory().getOWLLiteral(institution.getLongitude());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasLongitude, individual, longitude);
		owlAxioms.add(assertion);		
	}

	private void addHasLatitude(){
		OWLDataProperty hasLatitude = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasLatitude));		
		OWLLiteral latitude = bundle.getDataFactory().getOWLLiteral(institution.getLatitude());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasLatitude, individual, latitude);
		owlAxioms.add(assertion);	
	}
	
	private void addHasNameAssertion(){
		OWLDataProperty hasName = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasName));		
		OWLLiteral name = bundle.getDataFactory().getOWLLiteral(institution.getHasName());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasName, individual, name);
		owlAxioms.add(assertion);		
	}
}
