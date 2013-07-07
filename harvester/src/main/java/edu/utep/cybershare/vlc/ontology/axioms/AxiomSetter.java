package edu.utep.cybershare.vlc.ontology.axioms;

import java.util.ArrayList;
import java.util.Hashtable;

import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;


public abstract class AxiomSetter {

	protected static FactoryBundle bundle;
	
	protected ArrayList<OWLAxiom> owlAxioms;
	protected OWLNamedIndividual individual;	
	private Hashtable<String, OWLNamedIndividual> individuals;
	
	public AxiomSetter(String individualName, String classIRI){
		owlAxioms = new ArrayList<OWLAxiom>();		

		individual = individuals.get(individualName);
		IRI individualIRI;
		if(individual == null){
			individualIRI = IRI.create(bundle.getIndividualIRI(individualName));
			individual = bundle.getDataFactory().getOWLNamedIndividual(individualIRI);
			individuals.put(individualName, individual);

			owlAxioms.add(getTypeAssertion(classIRI));
			populateIndividualAxioms();
			populateOntologyWithAxioms();
		}		
	}
	
	private OWLAxiom getTypeAssertion(String classIRI){
		OWLClass projectClass = bundle.getDataFactory().getOWLClass(IRI.create(classIRI));		
		OWLClassAssertionAxiom classAssertionAxiom = bundle.getDataFactory().getOWLClassAssertionAxiom(projectClass, individual);
		return classAssertionAxiom;
	}

		
	protected abstract void populateIndividualAxioms();
	
	protected void populateOntologyWithAxioms(){
		AddAxiom addAxiomChange;
		for(OWLAxiom anAxiom : owlAxioms){
			addAxiomChange = new AddAxiom(bundle.getOntology(), anAxiom);
			bundle.getOntologyManager().applyChange(addAxiomChange);
		}
	}
	
	public OWLNamedIndividual getIndividual(){return individual;}
	
	public static void setBundle(FactoryBundle aBundle){
		bundle = aBundle;
	}
}