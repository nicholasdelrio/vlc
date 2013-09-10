package edu.utep.cybershare.vlc.ontology;

import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import edu.utep.cybershare.vlc.model.Agent;
import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.model.Organization;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;
import edu.utep.cybershare.vlc.model.Resource;
import edu.utep.cybershare.vlc.ontology.axioms.Axioms;
import edu.utep.cybershare.vlc.ontology.axioms.Individuals;
import edu.utep.cybershare.vlc.ontology.axioms.OntologyToolset;
import edu.utep.cybershare.vlc.ontology.axioms.ProjectAxioms;
import edu.utep.cybershare.vlc.visitor.Visitor;

public class OWLVisitor implements Visitor {

	private OntologyToolset bundle;
	
	public OWLVisitor(OntologyToolset bundle){
		this.bundle = bundle;
	}
	
	public void visit(Project project) {
		OWLNamedIndividual individual = Individuals.getIndividual(project.getIdentification(), bundle);
		ProjectAxioms projectAxioms = new ProjectAxioms(individual, bundle);
	}

	public void visit(Institution institution) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Person person) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Organization organization) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Agent agent) {
		// TODO Auto-generated method stub
		
	}

	public void visit(Resource resource) {
		// TODO Auto-generated method stub
		
	}
	
	private void populateOntologyWithAxioms(Axioms axioms){
		AddAxiom addAxiomChange;
		for(OWLAxiom anAxiom : axioms){
			addAxiomChange = new AddAxiom(bundle.getOntology(), anAxiom);
			bundle.getOntologyManager().applyChange(addAxiomChange);
		}
	}


}
