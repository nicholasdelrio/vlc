package edu.utep.cybershare.vlc.ontology.vocabulary;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;

/**
 * Basic Geo (WGS84 lat/long) Vocabulary (GEO)
 * <a href="http://www.w3.org/2003/01/geo/">http://www.w3.org/2003/01/geo/</a>
 * @author Nicholas Del Rio
 *
 */

public class GEO extends Vocabulary {

	private static final String NAMESPACE = "http://www.w3.org/2003/01/geo/wgs84_pos";
	
	private static final String OWLDataProperty_lat = NAMESPACE + "/lat";
	private static final String OWLDataProperty_long = NAMESPACE + "/long";

	
	public GEO(OWLDataFactory dataFactory) {
		super(dataFactory);
	}
	
	public OWLDataProperty getOWLDataProperty_lat(){
		return factory.getOWLDataProperty(IRI.create(OWLDataProperty_lat));
	}
	public OWLDataProperty getOWLDataProperty_long(){
		return factory.getOWLDataProperty(IRI.create(OWLDataProperty_long));
	}

	@Override
	public String getNamespace() {
		return NAMESPACE;
	}
}
