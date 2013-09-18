package edu.utep.cybershare.vlc.ontology;

import org.semanticweb.owlapi.model.OWLNamedIndividual;

import edu.utep.cybershare.vlc.model.Agency;
import edu.utep.cybershare.vlc.model.Agent;
import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.model.Organization;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;
import edu.utep.cybershare.vlc.model.Resource;
import edu.utep.cybershare.vlc.model.Visitor;
import edu.utep.cybershare.vlc.ontology.axioms.AgencyAxioms;
import edu.utep.cybershare.vlc.ontology.axioms.Axioms;
import edu.utep.cybershare.vlc.ontology.axioms.InstitutionAxioms;
import edu.utep.cybershare.vlc.ontology.axioms.PersonAxioms;
import edu.utep.cybershare.vlc.ontology.axioms.ProjectAxioms;

public class OWLVisitor implements Visitor {

	private OntologyToolset bundle;
	
	public OWLVisitor(OntologyToolset bundle){
		this.bundle = bundle;
	}
	
	public void visit(Project project) {
		OWLNamedIndividual individual = Individuals.getIndividual(project, bundle);
		Axioms axioms = new ProjectAxioms(project, individual, bundle);
		axioms.setAxioms();
		bundle.addAxioms(axioms);
	}

	public void visit(Institution institution) {
		if(Individuals.doesIndividualExist(institution, bundle)){
			OWLNamedIndividual individual = Individuals.getIndividual(institution, bundle);
			Axioms axioms = new InstitutionAxioms(institution, individual, bundle);
			axioms.setAxioms();
			bundle.addAxioms(axioms);
		}
	}

	public void visit(Person person) {
		if(Individuals.doesIndividualExist(person, bundle)){
			OWLNamedIndividual individual = Individuals.getIndividual(person, bundle);
			Axioms axioms = new PersonAxioms(person, individual, bundle);
			axioms.setAxioms();
			bundle.addAxioms(axioms);
		}
	}

	public void visit(Organization organization) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Agent agent) {
		// TODO Auto-generated method stub
		System.out.println("visiting agent");
		
	}

	public void visit(Resource resource) {
		// TODO Auto-generated method stub
		System.out.println("visiting resource");
	}

	public void visit(Agency agency) {
		OWLNamedIndividual individual = Individuals.getIndividual(agency, bundle);
		Axioms axioms = new AgencyAxioms(agency, individual, bundle);
		axioms.setAxioms();
		bundle.addAxioms(axioms);
	}
}
