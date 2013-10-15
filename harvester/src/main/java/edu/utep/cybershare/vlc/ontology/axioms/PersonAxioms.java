package edu.utep.cybershare.vlc.ontology.axioms;

import java.net.URI;

import org.semanticweb.owlapi.model.OWLAxiom;

import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import edu.utep.cybershare.vlc.model.Agent;
import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Resource;
import edu.utep.cybershare.vlc.ontology.Individuals;
import edu.utep.cybershare.vlc.ontology.OntologyToolset;

public class PersonAxioms extends Axioms {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Person person;
	
	public PersonAxioms(Person aPerson, OWLNamedIndividual personIndividual, OntologyToolset bundle) {
		super(personIndividual, bundle);
		this.person = aPerson;
	}

	@Override
	public void setAxioms() {
		this.setTypeAxiom(this.vocabulary_FOAF.getOWLClass_Person());
		this.addAffiliatedInstitutions();
		this.addFirstName();
		this.addLastName();
		this.addEmailSha1Sum();
		
		this.addInfluencedAgents();
		this.addRelatedAgents();
		this.addContributedResources();
		this.addDisciplines();
	}
	
	private void addDisciplines(){
		if(person.isSet_disciplines()){
			OWLAxiom assertion;
			OWLNamedIndividual disciplineIndividual;
			for(URI discipline : person.getDisciplines()){
				disciplineIndividual = Individuals.getIndividual(discipline, bundle);
				assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(this.vocabulary_VLC.getOWLObjectProperty_hasDiscipline(), individual, disciplineIndividual);
				add(assertion);
			}
		}
	}

	private void addAffiliatedInstitutions(){
		if(person.isSet_affiliatedInstitutions()){
			OWLAxiom assertion;
			OWLNamedIndividual institutionIndividual;
			for(Institution institution : person.getAffiliatedInstitutions()){
				institutionIndividual = Individuals.getIndividual(institution, bundle);
				assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(this.vocabulary_VLC.getOWLObjectProperty_affiliatedWithInstitution(), individual, institutionIndividual);
				add(assertion);
			}
		}
	}

	private void addFirstName(){
		if(person.isSet_firstName()){
			OWLLiteral firstNameLiteral = bundle.getDataFactory().getOWLLiteral(person.getFirstName());
			OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(this.vocabulary_FOAF.getOWLDataProperty_givenName(), individual, firstNameLiteral);
			add(assertion);
		}
	}
	
	private void addLastName(){
		if(person.isSet_firstName()){
			OWLLiteral lastNameLiteral = bundle.getDataFactory().getOWLLiteral(person.getLastName());
			OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(this.vocabulary_FOAF.getOWLDataProperty_familyName(), individual, lastNameLiteral);
			add(assertion);
		}
	}
	
	public void addEmailSha1Sum(){
		if(person.isSet_email()){
			OWLLiteral shaLiteral = bundle.getDataFactory().getOWLLiteral(person.getEmailSha1Sum());
			OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(this.vocabulary_FOAF.getOWLDataProperty_mbox_sha1sum(), individual, shaLiteral);
			add(assertion);
		}
	}
	
	private void addInfluencedAgents(){
		if(person.isSet_influencedAgents()){
			OWLAxiom assertion;
			OWLNamedIndividual agentIndividual;
			for(Agent agent : person.getInfluencedAgents()){
				agentIndividual = Individuals.getIndividual(agent, bundle);
				assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(this.vocabulary_PROVO.getOWLObjectProperty_influenced(), individual, agentIndividual);
				add(assertion);
			}			
		}
	}
	
	private void addRelatedAgents(){
		if(person.isSet_relatedAgenets()){
			OWLAxiom assertion;
			OWLNamedIndividual agentIndividual;
			for(Agent agent : person.getRelatedAgents()){
				agentIndividual = Individuals.getIndividual(agent, bundle);
				assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(this.vocabulary_VLC.getOWLObjectProperty_hasRelatedAgent(), individual, agentIndividual);
				add(assertion);
			}						
		}
	}
	private void addContributedResources(){
		if(person.isSet_contributedResources()){
			OWLAxiom assertion;
			OWLNamedIndividual resourceIndividual;
			for(Resource resource : person.getContributedResources()){
				resourceIndividual = Individuals.getIndividual(resource, bundle);
				assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(this.vocabulary_PROVO.getOWLObjectProperty_contributed(), individual, resourceIndividual);
				add(assertion);
			}									
		}
	}
}