package edu.utep.cybershare.vlc.ontology.axioms;

import java.net.URI;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.ontology.vocabulary.VLC;

public class PersonAxioms extends Axioms {
	
	private Person person;
	
	public PersonAxioms(Person person, OWLNamedIndividual personIndividual, OntologyToolset bundle) {
		super(personIndividual, bundle);
	}

	@Override
	protected void addAxioms() {
		this.addTypeAssertion(this.vocabulary_FOAF.getOWLClass_Person());
		this.addAffiliatedWithInstitution();
		this.addHasDiscipline();
		this.addHasFirstNameAssertion();
		this.addHasLastNameAssertion();
		
		this.addInflencedAgents();
		this.addRelatedAgents();
		this.contributedResources();
		this.addDisciplines();
	}
	
	private void addHasDiscipline(){
		if(person.isSet_disciplines()){
			OWLAxiom assertion;
			OWLNamedIndividual disciplineIndividual;
			for(URI discipline : person.getDisciplines()){
				disciplineIndividual = Individuals.getIndividual(discipline.toASCIIString(), bundle);
				assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(this.vocabulary_VLC.getOWLObjectProperty_hasDiscipline(), individual, disciplineIndividual);
				add(assertion);
			}
		}
	}

	private void addAffiliatedWithInstitution(){
		OWLObjectProperty affiliatedWithInstitutionProperty = bundle.getDataFactory().getOWLObjectProperty(IRI.create(VLC.OBJECT_PROPERTY_affiliatedWithInstitution));		
		OWLNamedIndividual institutionIndividual;
		OWLAxiom assertion;
		for(Institution institution : person.getAffiliatedInstitutions()){
			institutionIndividual = Individuals.getIndividual(institution.getIdentification(), bundle);
			assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(affiliatedWithInstitutionProperty, individual, institutionIndividual);
			add(assertion);
		}
	}

	private void addHasFirstNameAssertion(){
		OWLDataProperty hasFirstName = bundle.getDataFactory().getOWLDataProperty(IRI.create(VLC.DATA_PROPERTY_IRI_hasFirstName));		
		OWLLiteral firstName = bundle.getDataFactory().getOWLLiteral(person.getHasFirstName());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasFirstName, individual, firstName);
		owlAxioms.add(assertion);		
	}
	
	private void addHasLastNameAssertion(){
		OWLDataProperty hasLastName = bundle.getDataFactory().getOWLDataProperty(IRI.create(VLC.DATA_PROPERTY_IRI_hasLastName));		
		OWLLiteral lastName = bundle.getDataFactory().getOWLLiteral(person.getHasLastName());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasLastName, individual, lastName);
		owlAxioms.add(assertion);
	}
}