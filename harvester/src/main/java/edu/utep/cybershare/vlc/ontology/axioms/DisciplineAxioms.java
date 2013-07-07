package edu.utep.cybershare.vlc.ontology.axioms;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLLiteral;

import edu.utep.cybershare.vlc.ontology.Discipline;

public class DisciplineAxioms extends AxiomSetter {
	
	public DisciplineAxioms(Discipline aDiscipline) {
		super(aDiscipline.getHasName(), Vocabulary.CLASS_IRI_Discipline, aDiscipline);
	}

	@Override
	protected void populateIndividualAxioms() {
		owlAxioms.add(this.getHasNameAssertion());
	}
	
	private OWLAxiom getHasNameAssertion(){
		OWLDataProperty hasName = bundle.getDataFactory().getOWLDataProperty(IRI.create(Vocabulary.DATA_PROPERTY_IRI_hasName));		
		OWLLiteral name = bundle.getDataFactory().getOWLLiteral(discipline.getHasName());
		OWLAxiom assertion = bundle.getDataFactory().getOWLDataPropertyAssertionAxiom(hasName, individual, name);
		return assertion;		
	}
}
