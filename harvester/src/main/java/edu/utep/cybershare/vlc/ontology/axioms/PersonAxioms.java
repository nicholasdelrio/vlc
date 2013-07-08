package edu.utep.cybershare.vlc.ontology.axioms;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import edu.utep.cybershare.vlc.ontology.Discipline;
import edu.utep.cybershare.vlc.ontology.Institution;
import edu.utep.cybershare.vlc.ontology.Person;

public class PersonAxioms extends AxiomSetter {
	
	public PersonAxioms(Person aPerson) {
		super(aPerson.getHasLastName() + "-" + aPerson.getHasFirstName(), Vocabulary.CLASS_IRI_Person, aPerson);
	}

	@Override
	protected void populateIndividualAxioms() {
		this.addAffiliatedWithInstitution();
		this.addHasDiscipline();
		this.addHasFirstNameAssertion();
		this.addHasLastNameAssertion();
	}
	
	private void addHasDiscipline(){
		OWLObjectProperty hasDiscipline = bundle.getDataFactory().getOWLObjectProperty(IRI.create(Vocabulary.OBJECT_PROPERTY_IRI_hasDiscipline));		
		DisciplineAxioms disciplineAxioms;
		OWLAxiom assertion;
		for(Discipline discipline : person.getHasDiscipline()){
			disciplineAxioms = new DisciplineAxioms(discipline);
			assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(hasDiscipline, individual, disciplineAxioms.getIndividual());
			owlAxioms.add(assertion);
		}
	}

	private void addAffiliatedWithInstitution(){
		OWLObjectProperty affiliatedWithInstitution = bundle.getDataFactory().getOWLObjectProperty(IRI.create(Vocabulary.OBJECT_PROPERTY_IRI_affiliatedWithInstitution));		

		InstitutionAxioms institutionAxioms;
		OWLAxiom assertion;
		for(Institution institution : person.getAffiliatedWithInstitution()){
			institutionAxioms = new InstitutionAxioms(institution);
			assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(affiliatedWithInstitution, individual, institutionAxioms.getIndividual());
			owlAxioms.add(assertion);
		}
	}

	private void addHasFirstNameAssertion(){
		System.out.println(person.getHasFirstName());
		OWLDataProperty hasFirstName = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasFirstName));		
		OWLLiteral firstName = bundle.getDataFactory().getOWLLiteral(person.getHasFirstName());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasFirstName, individual, firstName);
		owlAxioms.add(assertion);		
	}
	
	private void addHasLastNameAssertion(){
		OWLDataProperty hasLastName = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasLastName));		
		OWLLiteral lastName = bundle.getDataFactory().getOWLLiteral(person.getHasFirstName());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasLastName, individual, lastName);
		owlAxioms.add(assertion);
	}
}