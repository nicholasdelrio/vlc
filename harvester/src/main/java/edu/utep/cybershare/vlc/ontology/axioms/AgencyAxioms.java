package edu.utep.cybershare.vlc.ontology.axioms;

import java.net.URI;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import edu.utep.cybershare.vlc.model.Agency;
import edu.utep.cybershare.vlc.model.Agent;
import edu.utep.cybershare.vlc.model.Resource;
import edu.utep.cybershare.vlc.ontology.Individuals;
import edu.utep.cybershare.vlc.ontology.OntologyToolset;

public class AgencyAxioms extends Axioms {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Agency agency;
	
	public AgencyAxioms(Agency anAgency, OWLNamedIndividual agencyIndividual, OntologyToolset bundle) {
		super(agencyIndividual, bundle);
		this.agency = anAgency;
	}

	@Override
	public void setAxioms() {
		this.setTypeAxiom(this.vocabulary_ARPFO.getOWLClass_FundingBody());
		this.addDisciplines();
		this.addInfluencedAgents();
		this.addRelatedAgents();
		this.addContributedResources();
		this.addDisciplines();
		
		this.addFundedProjects();
	}
	
	private void addDisciplines(){
		if(agency.isSet_disciplines()){
			OWLAxiom assertion;
			OWLNamedIndividual disciplineIndividual;
			for(URI discipline : agency.getDisciplines()){
				disciplineIndividual = Individuals.getIndividual(discipline, bundle);
				assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(this.vocabulary_VLC.getOWLObjectProperty_hasDiscipline(), individual, disciplineIndividual);
				add(assertion);
			}
		}
	}
	
	
	private void addInfluencedAgents(){
		if(agency.isSet_influencedAgents()){
			OWLAxiom assertion;
			OWLNamedIndividual agentIndividual;
			for(Agent agent : agency.getInfluencedAgents()){
				agentIndividual = Individuals.getIndividual(agent, bundle);
				assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(this.vocabulary_PROVO.getOWLObjectProperty_influenced(), individual, agentIndividual);
				add(assertion);
			}			
		}
	}
	
	private void addRelatedAgents(){
		if(agency.isSet_relatedAgenets()){
			OWLAxiom assertion;
			OWLNamedIndividual agentIndividual;
			for(Agent agent : agency.getRelatedAgents()){
				agentIndividual = Individuals.getIndividual(agent, bundle);
				assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(this.vocabulary_VLC.getOWLObjectProperty_hasRelatedAgent(), individual, agentIndividual);
				add(assertion);
			}						
		}
	}
	private void addContributedResources(){
		if(agency.isSet_contributedResources()){
			OWLAxiom assertion;
			OWLNamedIndividual resourceIndividual;
			for(Resource resource : agency.getContributedResources()){
				resourceIndividual = Individuals.getIndividual(resource, bundle);
				assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(this.vocabulary_PROVO.getOWLObjectProperty_contributed(), individual, resourceIndividual);
				add(assertion);
			}									
		}
	}
	
	private void addFundedProjects(){
		if(agency.isSet_FundedProjects()){
			OWLAxiom assertion;
			OWLNamedIndividual projectIndividual;
			for(Resource resource : agency.getFundedProjects()){
				projectIndividual = Individuals.getIndividual(resource, bundle);
				assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(this.vocabulary_ARPFO.getOWLObjectProperty_contributesTo(), individual, projectIndividual);
				add(assertion);
			}									
		}
	}
}