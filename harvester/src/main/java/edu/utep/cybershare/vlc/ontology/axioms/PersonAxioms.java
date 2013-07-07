package edu.utep.cybershare.vlc.ontology.axioms;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLLiteral;

import edu.utep.cybershare.vlc.ontology.Person;
import edu.utep.cybershare.vlc.ontology.Vocabulary;

public class PersonAxioms extends VLCAxiomSetter {

	private Person person;
	
	public PersonAxioms(Person aPerson) {
		super(aPerson.getHasLastName() + "-" + aPerson.getHasFirstName(), Vocabulary.CLASS_IRI_Person);
		person = aPerson;
	}

	@Override
	protected void populateIndividualAxioms() {
		person.getAffiliatedWithInstitution();
		person.getHasDiscipline();
		person.getHasFirstName();
		person.getHasLastName();
	}
	
	private OWLAxiom getHasFirstNameAssertion(){
		OWLDataProperty hasFirstName = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasFirstName));		
		OWLLiteral firstName = bundle.getDataFactory().getOWLLiteral(person.getHasFirstName());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasFirstName, individual, firstName);
		return assertion;		
	}
	
	private OWLAxiom getHasLastNameAssertion(){
		OWLDataProperty hasLastName = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasLastName));		
		OWLLiteral lastName = bundle.getDataFactory().getOWLLiteral(person.getHasFirstName());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasLastName, individual, lastName);
		return assertion;
	}
}
