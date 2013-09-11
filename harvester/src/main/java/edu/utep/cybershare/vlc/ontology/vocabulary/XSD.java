package edu.utep.cybershare.vlc.ontology.vocabulary;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDatatype;

/**
 * XML Schema (FOAF)
 * <a href="http://www.w3.org/XML/Schema">http://www.w3.org/XML/Schema</a>
 * @author Nicholas Del Rio
 *
 */

public class XSD extends Vocabulary {
	
	public static final String NAMESPACE = "http://www.w3.org/2001/XMLSchema";
	
	// Data Types
	public static final String Datatype_dateTime = NAMESPACE + "#dateTime";
	public static final String Datatype_anyURI = NAMESPACE + "#anyURI";
	public static final String Datatype_integer = NAMESPACE + "#integer";
	public static final String Datatype_string = NAMESPACE + "#string";

	public XSD(OWLDataFactory dataFactory) {
		super(dataFactory);
		// TODO Auto-generated constructor stub
	}	
	public OWLDatatype getDataType_dateTime(){
		return factory.getOWLDatatype(IRI.create(Datatype_dateTime));
	}	
	public OWLDatatype getDataType_anyURI(){
		return factory.getOWLDatatype(IRI.create(Datatype_anyURI));
	}
	public OWLDatatype getDataType_integer(){
		return factory.getOWLDatatype(IRI.create(Datatype_integer));
	}
	public OWLDatatype getDataType_string(){
		return factory.getOWLDatatype(IRI.create(Datatype_string));
	}
}
