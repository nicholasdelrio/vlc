package edu.utep.cybershare.vlc.ontology;

import org.semanticweb.owlapi.model.OWLNamedIndividual;

import edu.utep.cybershare.vlc.model.Agent;
import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.model.Organization;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;
import edu.utep.cybershare.vlc.model.Resource;
import edu.utep.cybershare.vlc.model.Visitor;
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
		OWLNamedIndividual individual = Individuals.getIndividual(project.getIdentification(), bundle);
		Axioms axioms = new ProjectAxioms(project, individual, bundle);
		axioms.setAxioms();
		bundle.addAxioms(axioms);
	}

	public void visit(Institution institution) {
		OWLNamedIndividual individual = Individuals.getIndividual(institution.getIdentification(), bundle);
		Axioms axioms = new InstitutionAxioms(institution, individual, bundle);
		axioms.setAxioms();
		bundle.addAxioms(axioms);		
	}

	public void visit(Person person) {
		OWLNamedIndividual individual = Individuals.getIndividual(person.getIdentification(), bundle);
		Axioms axioms = new PersonAxioms(person, individual, bundle);
		axioms.setAxioms();
		bundle.addAxioms(axioms);		
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
}
