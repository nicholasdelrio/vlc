package edu.utep.cybershare.vlc.ontology.vocabulary;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;

/**
 * Academic Research Project Funding Ontology (ARPFO)
 * <a href="http://vocab.ox.ac.uk/projectfunding">http://vocab.ox.ac.uk/projectfunding</a>
 * @author Nicholas Del Rio
 *
 */

public class ARPFO extends Vocabulary {

	private static final String NAMESPACE = "http://vocab.ox.ac.uk/projectfunding";
	
	// Classes
	private static final String OWLClass_Project = NAMESPACE + "/Project";
	private static final String OWLClass_FundingBody = NAMESPACE + "/FundingBody";
	private static final String OWLClass_ProjectSection = NAMESPACE + "/ProjectSection";
	
	// Data Properties
	private static final String OWLDataProperty_endDate = NAMESPACE + "/endDate";
	private static final String OWLDataProperty_startDate = NAMESPACE + "/startDate";
	private static final String OWLDataProperty_grantNumber = NAMESPACE + "/grantNumber";
	private static final String OWLDataProperty_statedPurpose = NAMESPACE + "/statedPurpose";
	private static final String OWLDataProperty_hasDocumentation = NAMESPACE + "/hasDocumentation";
	
	// Object Properties
	private static final String OWLObjectProperty_hasCoInvestigator = NAMESPACE + "/hasCoInvestigator";
	private static final String OWLObjectProperty_hasPrincipalInvestigator = NAMESPACE + "/hasPrincipalInvestigator";
	private static final String OWLObjectProperty_contributesTo = NAMESPACE + "/contributesTo";
	
	public ARPFO(OWLDataFactory dataFactory) {
		super(dataFactory);
	}
	
	public OWLClass getOWLClass_Project(){
		return factory.getOWLClass(IRI.create(OWLClass_Project));
	}
	public OWLClass getOWLClass_FundingBody(){
		return factory.getOWLClass(IRI.create(OWLClass_FundingBody));
	}
	public OWLClass getOWLClass_ProjectSection(){
		return factory.getOWLClass(IRI.create(OWLClass_ProjectSection));
	}
	public OWLDataProperty getOWLDataProperty_endDate(){
		return factory.getOWLDataProperty(IRI.create(OWLDataProperty_endDate));
	}
	public OWLDataProperty getOWLDataProperty_startDate(){
		return factory.getOWLDataProperty(IRI.create(OWLDataProperty_startDate));
	}
	public OWLDataProperty getOWLDataProperty_grantNumber(){
		return factory.getOWLDataProperty(IRI.create(OWLDataProperty_grantNumber));
	}
	public OWLDataProperty getOWLDataProperty_statedPurpose(){
		return factory.getOWLDataProperty(IRI.create(OWLDataProperty_statedPurpose));
	}
	public OWLDataProperty getOWLDataProperty_hasDocumentation(){
		return factory.getOWLDataProperty(IRI.create(OWLDataProperty_hasDocumentation));
	}
	public OWLObjectProperty getOWLObjectProperty_hasCoInvestigatar(){
		return factory.getOWLObjectProperty(IRI.create(OWLObjectProperty_hasCoInvestigator));
	}
	public OWLObjectProperty getOWLObjectProperty_hasPrincipalInvestigator(){
		return factory.getOWLObjectProperty(IRI.create(OWLObjectProperty_hasPrincipalInvestigator));
	}
	public OWLObjectProperty getOWLObjectProperty_contributesTo(){
		return factory.getOWLObjectProperty(IRI.create(OWLObjectProperty_contributesTo));
	}
	@Override
	public String getNamespace() {
		// TODO Auto-generated method stub
		return NAMESPACE;
	}
}
