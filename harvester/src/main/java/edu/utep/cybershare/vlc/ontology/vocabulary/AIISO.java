package edu.utep.cybershare.vlc.ontology.vocabulary;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;

/**
 * Academic Institution Internal Structure Ontology (AIISO)
 * <a href="http://vocab.org/aiiso/schema#term-Institution">http://vocab.org/aiiso/schema#term-Institution</a>
 * @author Nicholas Del Rio
 *
 */

public class AIISO extends Vocabulary {

	private static final String NAMESPACE = "http://purl.org/vocab/aiiso/schema";

	// Classes
	private static final String OWLClass_Institution = NAMESPACE + "#Institution";
	
	public AIISO(OWLDataFactory dataFactory) {
		super(dataFactory);
	}
	
	public OWLClass getOWLClass_Institution(){
		return factory.getOWLClass(IRI.create(OWLClass_Institution));
	}

	@Override
	public String getNamespace() {
		// TODO Auto-generated method stub
		return NAMESPACE;
	}
}
