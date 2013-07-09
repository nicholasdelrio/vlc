package edu.utep.cybershare.vlc.ontology.axioms;


import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import edu.utep.cybershare.vlc.ontology.Person;
import edu.utep.cybershare.vlc.ontology.Project;

public class ProjectAxioms extends AxiomSetter{
	
	public ProjectAxioms(Project aProject){
		super(aProject.getHasTitle(), Vocabulary.CLASS_IRI_Project, aProject);
	}
	
	@Override
	protected void populateIndividualAxioms() {
		this.addHasEndDate_Funding();
		this.addHasAbstract();
		this.addHasCoPrincipalInvestigatorAssertion();
		this.addHasPrincipalInvestigatorAssertion();
		this.addHasStartDate_Funding();
		this.addHasTitleAssertion();
	}
		
	private void addHasTitleAssertion(){
		OWLDataProperty hasTitle = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasTitle));		
		OWLLiteral title = bundle.getDataFactory().getOWLLiteral(cleanTitle(project.getHasTitle()));
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasTitle, individual, title);
		owlAxioms.add(assertion);	
	}
	
	private void addHasAbstract(){
		OWLDataProperty hasAbstract = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasAbstract));		
		OWLLiteral projectAbstract = bundle.getDataFactory().getOWLLiteral(project.getHasAbstract());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasAbstract, individual, projectAbstract);
		owlAxioms.add(assertion);		
	}
	
	private void addHasStartDate_Funding(){
		OWLDataProperty hasStartDate_Funding = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasStartDate_Funding));		
		OWLLiteral startDate_Funding = bundle.getDataFactory().getOWLLiteral(project.getHasStartDate_Funding().toString());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasStartDate_Funding, individual, startDate_Funding);
		owlAxioms.add(assertion);
	}
	
	private String cleanTitle(String title){
		return title.replaceAll("\\x7F", "");
	}
	
	private void addHasEndDate_Funding(){
		OWLDataProperty hasEndDate_Funding = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasEndDate_Funding));		
		OWLLiteral endDate_Funding = bundle.getDataFactory().getOWLLiteral(project.getHasEndDate_Funding().toString());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasEndDate_Funding, individual, endDate_Funding);
		owlAxioms.add(assertion);		
	}
	
	private void addHasCoPrincipalInvestigatorAssertion(){
		OWLObjectProperty hasCoPrincipalInvestigator = bundle.getDataFactory().getOWLObjectProperty(IRI.create(Vocabulary.OBJECT_PROPERTY_IRI_hasCoPrincipalInvestigator));		
		
		PersonAxioms personAxioms;
		OWLAxiom assertion;
		for(Person person : project.getHasCoPrincipalInvestigator()){
			personAxioms = new PersonAxioms(person);
			assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(hasCoPrincipalInvestigator, individual, personAxioms.getIndividual());
			owlAxioms.add(assertion);
		}
	}
	
	private void addHasPrincipalInvestigatorAssertion(){
		OWLObjectProperty hasPrincipalInvestigator = bundle.getDataFactory().getOWLObjectProperty(IRI.create(Vocabulary.OBJECT_PROPERTY_IRI_hasPrincipalInvestigator));		
		PersonAxioms personAxioms = new PersonAxioms(project.getHasPrincipalInvestigator());
		OWLAxiom assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(hasPrincipalInvestigator, individual, personAxioms.getIndividual());
		owlAxioms.add(assertion);	
	}
}