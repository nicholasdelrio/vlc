package edu.utep.cybershare.vlc.ontology.axioms;

import javax.xml.bind.DatatypeConverter;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import edu.utep.cybershare.vlc.ontology.Institution;
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
		this.addHasRelatedProject();
		this.addHasHostingInstitution();
	}
		
	private void addHasTitleAssertion(){
		OWLDataProperty hasTitle = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasTitle));		
		OWLLiteral title = bundle.getDataFactory().getOWLLiteral(removeIllegalCharacters(project.getHasTitle()));
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasTitle, individual, title);
		owlAxioms.add(assertion);	
	}
	
	private void addHasAbstract(){
		OWLDataProperty hasAbstract = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasAbstract));		
		OWLLiteral projectAbstract = bundle.getDataFactory().getOWLLiteral(removeIllegalCharacters(project.getHasAbstract()));
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasAbstract, individual, projectAbstract);
		owlAxioms.add(assertion);		
	}

	private void addHasHostingInstitution(){
		OWLObjectProperty hasHostingInstitution = bundle.getDataFactory().getOWLObjectProperty(IRI.create(Vocabulary.OBJECT_PROPERTY_IRI_hasHostingInstitution));		
		
		InstitutionAxioms institutionAxioms;
		OWLAxiom assertion;
		for(Institution anInstitution : project.getHasHostingInstitution()){
			institutionAxioms = new InstitutionAxioms(anInstitution);
			assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(hasHostingInstitution, individual, institutionAxioms.getIndividual());
			owlAxioms.add(assertion);
		}
	}
	
	private void addHasRelatedProject(){
		OWLObjectProperty hasRelatedProject = bundle.getDataFactory().getOWLObjectProperty(IRI.create(Vocabulary.OBJECT_PROPERTY_IRI_hasRelatedProject));		
		
		ProjectAxioms projectAxioms;
		OWLAxiom assertion;
		for(Project aProject : project.getHasRelatedProject()){
			projectAxioms = new ProjectAxioms(aProject);
			assertion = bundle.getDataFactory().getOWLObjectPropertyAssertionAxiom(hasRelatedProject, individual, projectAxioms.getIndividual());
			owlAxioms.add(assertion);
		}
	}
	
	private void addHasStartDate_Funding(){
		OWLDataProperty hasStartDate_Funding = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasStartDate_Funding));		
		OWLLiteral startDate_Funding = bundle.getDataFactory().getOWLLiteral(DatatypeConverter.printDateTime(project.getHasStartDate_Funding()), xsdDateTime);
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasStartDate_Funding, individual, startDate_Funding);
		owlAxioms.add(assertion);
	}
	
	private String removeIllegalCharacters(String text){
		// XML 1.0
		// #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
		String xml10pattern = "[^"
		                    + "\u0009\r\n"
		                    + "\u0020-\uD7FF"
		                    + "\uE000-\uFFFD"
		                    + "\ud800\udc00-\udbff\udfff"
		                    + "]";
		return text.replaceAll(xml10pattern, "");
	}
	
	private void addHasEndDate_Funding(){
		OWLDataProperty hasEndDate_Funding = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasEndDate_Funding));		
		OWLLiteral endDate_Funding = bundle.getDataFactory().getOWLLiteral(DatatypeConverter.printDateTime(project.getHasEndDate_Funding()), xsdDateTime);
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