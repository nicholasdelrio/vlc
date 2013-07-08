package edu.utep.cybershare.vlc.ontology.axioms;

import java.util.ArrayList;
import java.util.Hashtable;

import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import edu.utep.cybershare.vlc.ontology.Discipline;
import edu.utep.cybershare.vlc.ontology.Institution;
import edu.utep.cybershare.vlc.ontology.Person;
import edu.utep.cybershare.vlc.ontology.Project;


public abstract class AxiomSetter {

	protected static FactoryBundle bundle;
	private static Hashtable<String, OWLNamedIndividual> individuals = new Hashtable<String, OWLNamedIndividual>();
	
	protected ArrayList<OWLAxiom> owlAxioms;
	protected OWLNamedIndividual individual;
	
	protected Project project;
	protected Person person;
	protected Institution institution;
	protected Discipline discipline;
	
	public AxiomSetter(String individualName, String classIRI, Project project){
		this.project = project;
		this.setIndividual(makeURI(individualName), classIRI);
	}
	
	public AxiomSetter(String individualName, String classIRI, Person person){
		this.person = person;
		this.setIndividual(makeURI(individualName), classIRI);
	}

	public AxiomSetter(String individualName, String classIRI, Institution institution){
		this.institution = institution;
		this.setIndividual(makeURI(individualName), classIRI);
	}

	public AxiomSetter(String individualName, String classIRI, Discipline discipline){
		this.discipline = discipline;
		this.setIndividual(makeURI(individualName), classIRI);
	}

	private String makeURI(String candidateURI){
		return candidateURI.replaceAll(" ", "_");
	}
	
	
	private void setIndividual(String individualName, String classIRI){
		owlAxioms = new ArrayList<OWLAxiom>();		
		
		individual = individuals.get(individualName);
		IRI individualIRI;
		if(individual == null){
			individualIRI = IRI.create(bundle.getIndividualIRI(individualName));
			individual = bundle.getDataFactory().getOWLNamedIndividual(individualIRI);
			individuals.put(individualName, individual);

			addTypeAssertion(classIRI);
			populateIndividualAxioms();
			populateOntologyWithAxioms();
		}		
	}
	
	private void addTypeAssertion(String classIRI){
		OWLClass projectClass = bundle.getDataFactory().getOWLClass(IRI.create(classIRI));		
		OWLClassAssertionAxiom classAssertionAxiom = bundle.getDataFactory().getOWLClassAssertionAxiom(projectClass, individual);
		owlAxioms.add(classAssertionAxiom);
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