package edu.utep.cybershare.vlc.ontology.axioms;

import javax.xml.bind.DatatypeConverter;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;
import edu.utep.cybershare.vlc.model.Resource;
import edu.utep.cybershare.vlc.ontology.Individuals;
import edu.utep.cybershare.vlc.ontology.OntologyToolset;
import edu.utep.cybershare.vlc.ontology.vocabulary.ARPFO;
import edu.utep.cybershare.vlc.ontology.vocabulary.DCMI;
import edu.utep.cybershare.vlc.ontology.vocabulary.FOAF;
import edu.utep.cybershare.vlc.ontology.vocabulary.PROVO;
import edu.utep.cybershare.vlc.ontology.vocabulary.VLC;
import edu.utep.cybershare.vlc.ontology.vocabulary.XSD;
import edu.utep.cybershare.vlc.util.NSFAwardsUtils;

public class ProjectAxioms extends Axioms{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Project project;
	
	public ProjectAxioms(Project aProject, OWLNamedIndividual projectIndividual, OntologyToolset bundle){
		super(projectIndividual, bundle);
		project = aProject;
	}
	
	@Override
	public void setAxioms() {
		this.setTypeAxiom(this.vocabulary_ARPFO.getOWLClass_Project());
		this.addTitleAssertion();
		this.addAbstract();
		this.addStartDate();
		this.addEndDate();
		this.addCoPrincipalInvestigatorAssertion();
		this.addPrincipalInvestigatorAssertion();
		this.addDocumentation();
		this.addHomepage();
		this.addGrantIdentification();
		this.addAwardAmount();
		this.addSubjects();
		
		//VLC Specific Axioms
		this.addHostingInstitution();
		this.addRelatedResources();
		this.addInfluencedResources();
		this.addRelatedWork();
		this.addResultantWork();
		
	}
		
	private void addTitleAssertion(){
		if(project.isSet_title()){
			OWLLiteral titleLiteral = bundle.getDataFactory().getOWLLiteral(NSFAwardsUtils.removeIllegalCharacters(project.getTitle()));
			OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(this.vocabulary_DCMI.getOWLDataProperty_title(), individual, titleLiteral);
			add(assertion);
		}
	}
	
	private void addAbstract(){
		if(project.isSet_abstractText()){
			OWLLiteral abstractLiteral = bundle.getDataFactory().getOWLLiteral(NSFAwardsUtils.removeIllegalCharacters(project.getAbstractText()));
			OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(this.vocabulary_ARPFO.getOWLDataProperty_statedPurpose(), individual, abstractLiteral);
			add(assertion);
		}
	}
	
	private void addStartDate(){
		if(project.isSet_startDate()){
			OWLLiteral startDateLiteral = bundle.getDataFactory().getOWLLiteral(DatatypeConverter.printDateTime(project.getStartDate()), this.vocabulary_XSD.getDataType_dateTime());
			OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(this.vocabulary_ARPFO.getOWLDataProperty_startDate(), individual, startDateLiteral);
			add(assertion);
		}
	}
	
	private void addEndDate(){
		if(project.isSet_endDate()){
			OWLLiteral endDateLiteral = bundle.getDataFactory().getOWLLiteral(DatatypeConverter.printDateTime(project.getEndDate()), this.vocabulary_XSD.getDataType_dateTime());
			OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(this.vocabulary_ARPFO.getOWLDataProperty_endDate(), individual, endDateLiteral);
			add(assertion);
		}
	}
	
	private void addCoPrincipalInvestigatorAssertion(){
		if(project.isSet_coPrincipalInvestigators()){
			OWLNamedIndividual personIndividual;
			OWLAxiom assertion;
			for(Person person : project.getCoPrincipalInvestigators()){
				personIndividual = Individuals.getIndividual(person, bundle);
				assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(this.vocabulary_ARPFO.getOWLObjectProperty_hasCoInvestigatar(), individual, personIndividual);
				add(assertion);
			}
		}
	}
	
	private void addPrincipalInvestigatorAssertion(){
		if(project.isSet_principalInvestigator()){
			Person person = project.getPrincipalInvestigator();
			OWLNamedIndividual personIndividual = Individuals.getIndividual(person, bundle);
			OWLAxiom assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(this.vocabulary_ARPFO.getOWLObjectProperty_hasPrincipalInvestigator(), individual, personIndividual);
			add(assertion);
		}
	}
	private void addDocumentation(){
		if(project.isSet_awardHomepage()){
			OWLLiteral documentationLiteral = bundle.getDataFactory().getOWLLiteral(project.getAwardHomepage().toString(), this.vocabulary_XSD.getDataType_anyURI());
			OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(this.vocabulary_ARPFO.getOWLDataProperty_hasDocumentation(), individual, documentationLiteral);
			add(assertion);
		}
	}
	
	private void addHomepage(){
		if(project.isSet_projectHomePage()){
			OWLLiteral homepageLiteral = bundle.getDataFactory().getOWLLiteral(project.getProjectHomePage().toString(), this.vocabulary_XSD.getDataType_anyURI());
			OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(this.vocabulary_FOAF.getOWLDataProperty_homepage(), individual, homepageLiteral);
			add(assertion);
		}
	}
	
	private void addGrantIdentification(){
		if(project.isSet_grantIdentification()){
			OWLLiteral grantIdentificationLiteral = bundle.getDataFactory().getOWLLiteral(project.getGrantIdentification());
			OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(this.vocabulary_ARPFO.getOWLDataProperty_grantNumber(), individual, grantIdentificationLiteral);
			add(assertion);
		}
	}
	
	public void addAwardAmount(){
		if(project.isSet_awardAmount()){
			OWLLiteral grantAmountLiteral = bundle.getDataFactory().getOWLLiteral(project.getAwardAmount());
			OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(this.vocabulary_VLC.getOWLDataProperty_hasGrantAmount(), individual, grantAmountLiteral);
			add(assertion);			
		}
	}
	
	public void addSubjects(){
		if(project.isSet_subjects()){
			OWLNamedIndividual personIndividual;
			OWLAxiom assertion;
			for(Person person : project.getCoPrincipalInvestigators()){
				personIndividual = Individuals.getIndividual(person, bundle);
				assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(this.vocabulary_ARPFO.getOWLObjectProperty_hasCoInvestigatar(), individual, personIndividual);
				add(assertion);
			}
		}

	}
	
	// VLC Specific Axioms
	private void addHostingInstitution(){
		if(project.isSet_hostingInstitutions()){
			OWLNamedIndividual institutionIndividual;
			OWLAxiom assertion;
			for(Institution anInstitution : project.getHostingInstitutions()){
				institutionIndividual = Individuals.getIndividual(anInstitution, bundle);
				assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(this.vocabulary_VLC.getOWLObjectProperty_hasHostingInstitution(), individual, institutionIndividual);
				add(assertion);
			}
		}
	}
	
	private void addRelatedResources(){
		if(project.isSet_relatedResources()){
			OWLNamedIndividual resourceIndividual;
			OWLAxiom assertion;
			for(Resource aResource : project.getRelatedResources()){
				resourceIndividual = Individuals.getIndividual(aResource, bundle);
				assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(this.vocabulary_VLC.getOWLObjectProperty_hasRelatedResource(), individual, resourceIndividual);
				add(assertion);
			}
		}
	}
	private void addInfluencedResources(){
		if(project.isSet_influencedResources()){
			OWLNamedIndividual resourceIndividual;		
			OWLAxiom assertion;
			for(Resource aResource : project.getInfluencedResources()){
				resourceIndividual = Individuals.getIndividual(aResource, bundle);
				assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(this.vocabulary_PROVO.getOWLObjectProperty_influenced(), individual, resourceIndividual);
				add(assertion);
			}
		}
	}
	
	private void addRelatedWork(){
		if(project.isSet_relatedWorks()){
			OWLNamedIndividual resourceIndividual;	
			OWLAxiom assertion;
			for(Resource aResource : project.getRelatedWorks()){
				resourceIndividual = Individuals.getIndividual(aResource, bundle);
				assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(this.vocabulary_VLC.getOWLObjectProperty_hasRelatedWork(), individual, resourceIndividual);
				add(assertion);
			}
		}
	}
	private void addResultantWork(){
		if(project.isSet_results()){
			OWLNamedIndividual resourceIndividual;		
			OWLAxiom assertion;
			for(Resource aResource : project.getResults()){
				resourceIndividual = Individuals.getIndividual(aResource, bundle);
				assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(this.vocabulary_VLC.getOWLObjectProperty_hasResultantWork(), individual, resourceIndividual);
				add(assertion);
			}
		}
	}
}