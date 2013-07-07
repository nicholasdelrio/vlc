package edu.utep.cybershare.vlc.ontology.axioms;


import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import edu.utep.cybershare.vlc.ontology.Project;

public class ProjectAxioms extends AxiomSetter{
	
	public ProjectAxioms(Project aProject){
		super(aProject.getHasTitle(), Vocabulary.CLASS_IRI_Project, aProject);
	}
	
	@Override
	protected void populateIndividualAxioms() {
		owlAxioms.add(this.getHasEndDate_Funding());
		owlAxioms.add(this.getHasAbstract());
		owlAxioms.add(this.getHasCoPrincipalInvestigatorAssertion());
		owlAxioms.add(this.getHasPrincipalInvestigatorAssertion());
		owlAxioms.add(this.getHasStartDate_Funding());
		owlAxioms.add(this.getHasTitleAssertion());
	}
		
	private OWLAxiom getHasTitleAssertion(){
		OWLDataProperty hasTitle = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasTitle));		
		OWLLiteral title = bundle.getDataFactory().getOWLLiteral(project.getHasTitle());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasTitle, individual, title);
		return assertion;		
	}
	
	private OWLAxiom getHasAbstract(){
		OWLDataProperty hasAbstract = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasAbstract));		
		OWLLiteral projectAbstract = bundle.getDataFactory().getOWLLiteral(project.getHasAbstract());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasAbstract, individual, projectAbstract);
		return assertion;		
	}
	
	private OWLAxiom getHasStartDate_Funding(){
		OWLDataProperty hasStartDate_Funding = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasStartDate_Funding));		
		OWLLiteral startDate_Funding = bundle.getDataFactory().getOWLLiteral(project.getHasStartDate_Funding().toString());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasStartDate_Funding, individual, startDate_Funding);
		return assertion;		
	}
	
	private OWLAxiom getHasEndDate_Funding(){
		OWLDataProperty hasEndDate_Funding = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasEndDate_Funding));		
		OWLLiteral endDate_Funding = bundle.getDataFactory().getOWLLiteral(project.getHasEndDate_Funding().toString());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasEndDate_Funding, individual, endDate_Funding);
		return assertion;		
	}
	
	private OWLAxiom getHasCoPrincipalInvestigatorAssertion(){
		OWLObjectProperty hasCoPrincipalInvestigator = bundle.getDataFactory().getOWLObjectProperty(IRI.create(Vocabulary.OBJECT_PROPERTY_IRI_hasCoPrincipalInvestigator));		
		PersonAxioms personAxioms = new PersonAxioms(project.getHasCoPrincipalInvestigator().get(0));
		OWLAxiom assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(hasCoPrincipalInvestigator, individual, personAxioms.getIndividual());
		return assertion;				
	}
	
	private OWLAxiom getHasPrincipalInvestigatorAssertion(){
		OWLObjectProperty hasPrincipalInvestigator = bundle.getDataFactory().getOWLObjectProperty(IRI.create(Vocabulary.OBJECT_PROPERTY_IRI_hasPrincipalInvestigator));		
		PersonAxioms personAxioms = new PersonAxioms(project.getHasCoPrincipalInvestigator().get(0));
		OWLAxiom assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(hasPrincipalInvestigator, individual, personAxioms.getIndividual());
		return assertion;				
	}
}