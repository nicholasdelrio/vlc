package edu.utep.cybershare.vlc.ontology.vocabulary;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;

public class VLC extends Vocabulary{
	
	public static final String NAMESPACE = "https://raw.github.com/nicholasdelrio/VLC/master/ontology/RIM.owl";

	// Classes
	
	// Object Properties
	public static final String OWLObjectProperty_hasRelatedResource = NAMESPACE + "#hasRelatedResource";
	public static final String OWLObjectProperty_hasHostingInstitution = NAMESPACE + "#hasHostingInstitution";
	public static final String OWLObjectProperty_hasRelatedWork = NAMESPACE + "#hasRelatedWork";
	public static final String OWLObjectProperty_hasResultantWork = NAMESPACE + "#hasResultantWork";
	public static final String OWLObjectProperty_affiliatedWithInstitution = NAMESPACE + "#affiliatedWithInstitution";
	
	// Data Properties
	public static final String OWLDataProperty_hasGrantAmount = NAMESPACE + "#hasGrantAmount";
	
	
	//Institution
	public static final String DATA_PROPERTY_IRI_hasName = NAMESPACE + "#hasName";
	public static final String DATA_PROPERTY_IRI_hasLatitude = NAMESPACE + "#hasLatitude";
	public static final String DATA_PROPERTY_IRI_hasLongitude = NAMESPACE + "#hasLongitude";
	public static final String DATA_PROPERTY_IRI_hasState = NAMESPACE + "#hasState";
	public static final String DATA_PROPERTY_IRI_hasCity = NAMESPACE + "#hasCity";
	public static final String DATA_PROPERTY_IRI_hasZipcode = NAMESPACE + "#hasZipcode";
	public static final String DATA_PROPERTY_IRI_hasAddress = NAMESPACE + "#hasAddress";

	
	//Discipline
	public static final String CLASS_IRI_Discipline = NAMESPACE + "#Discipline";
	
	public VLC(OWLDataFactory dataFactory) {
		super(dataFactory);
		// TODO Auto-generated constructor stub
	}
	
	public OWLObjectProperty getOWLObjectProperty_hasRelatedResource(){
		return factory.getOWLObjectProperty(IRI.create(OWLObjectProperty_hasRelatedResource));
	}
	public OWLObjectProperty getOWLObjectProperty_hasHostingInstitution(){
		return factory.getOWLObjectProperty(IRI.create(OWLObjectProperty_hasHostingInstitution));
	}
	public OWLObjectProperty getOWLObjectProperty_hasRelatedWork(){
		return factory.getOWLObjectProperty(IRI.create(OWLObjectProperty_hasRelatedWork));
	}
	public OWLObjectProperty getOWLObjectProperty_hasResultantWork(){
		return factory.getOWLObjectProperty(IRI.create(OWLObjectProperty_hasResultantWork));
	}
	public OWLObjectProperty getOWLObjectProperty_affiliatedWithInstitution(){
		return factory.getOWLObjectProperty(IRI.create(OWLObjectProperty_affiliatedWithInstitution));
	}
	
	public OWLDataProperty getOWLDataProperty_hasGrantAmount(){
		return factory.getOWLDataProperty(IRI.create(OWLDataProperty_hasGrantAmount));
	}

}
