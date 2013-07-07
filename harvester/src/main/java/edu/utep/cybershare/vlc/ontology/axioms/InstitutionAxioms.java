package edu.utep.cybershare.vlc.ontology.axioms;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLLiteral;

import edu.utep.cybershare.vlc.ontology.Institution;

public class InstitutionAxioms extends AxiomSetter {

	private Institution institution;
	
	public InstitutionAxioms(Institution anInstitution) {
		super(anInstitution.getHasName(), Vocabulary.CLASS_IRI_Institution);
		institution = anInstitution;
	}

	@Override
	protected void populateIndividualAxioms() {
		owlAxioms.add(this.getHasLatitude());
		owlAxioms.add(this.getHasLongitude());
		owlAxioms.add(this.getHasNameAssertion());
	}
	
	private OWLAxiom getHasLongitude(){
		OWLDataProperty hasLongitude = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasLongitude));		
		OWLLiteral longitude = bundle.getDataFactory().getOWLLiteral(institution.getHasPoint().getX());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasLongitude, individual, longitude);
		return assertion;		
	}

	private OWLAxiom getHasLatitude(){
		OWLDataProperty hasLatitude = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasLatitude));		
		OWLLiteral latitude = bundle.getDataFactory().getOWLLiteral(institution.getHasPoint().getY());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasLatitude, individual, latitude);
		return assertion;		
	}
	
	private OWLAxiom getHasNameAssertion(){
		OWLDataProperty hasName = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasName));		
		OWLLiteral name = bundle.getDataFactory().getOWLLiteral(institution.getHasName());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasName, individual, name);
		return assertion;		
	}
}
