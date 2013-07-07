package edu.utep.cybershare.vlc.ontology.axioms;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import edu.utep.cybershare.vlc.ontology.Person;

public class PersonAxioms extends AxiomSetter {

	private Person person;
	
	public PersonAxioms(Person aPerson) {
		super(aPerson.getHasLastName() + "-" + aPerson.getHasFirstName(), Vocabulary.CLASS_IRI_Person);
		person = aPerson;
	}

	@Override
	protected void populateIndividualAxioms() {
		owlAxioms.add(this.getAffiliatedWithInstitution());
		owlAxioms.add(this.getHasDiscipline());
		owlAxioms.add(this.getHasFirstNameAssertion());
		owlAxioms.add(this.getHasLastNameAssertion());
	}
	
	private OWLAxiom getHasDiscipline(){
		OWLObjectProperty hasDiscipline = bundle.getDataFactory().getOWLObjectProperty(IRI.create(Vocabulary.OBJECT_PROPERTY_IRI_hasDiscipline));		
		DisciplineAxioms disciplineAxioms = new DisciplineAxioms(person.getHasDiscipline().get(0));
		OWLAxiom assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(hasDiscipline, individual, disciplineAxioms.getIndividual());
		return assertion;				
	}

	private OWLAxiom getAffiliatedWithInstitution(){
		OWLObjectProperty affiliatedWithInstitution = bundle.getDataFactory().getOWLObjectProperty(IRI.create(Vocabulary.OBJECT_PROPERTY_IRI_affiliatedWithInstitution));		
		InstitutionAxioms institutionAxioms = new InstitutionAxioms(person.getAffiliatedWithInstitution().get(0));
		OWLAxiom assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(affiliatedWithInstitution, individual, institutionAxioms.getIndividual());
		return assertion;				
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