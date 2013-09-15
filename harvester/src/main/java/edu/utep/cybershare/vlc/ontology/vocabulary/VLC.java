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
	public static final String OWLObjectProperty_hasRelatedAgent = NAMESPACE + "#hasRelatedAgent";
	public static final String OWLObjectProperty_hasHostingInstitution = NAMESPACE + "#hasHostingInstitution";
	public static final String OWLObjectProperty_hasRelatedWork = NAMESPACE + "#hasRelatedWork";
	public static final String OWLObjectProperty_hasResultantWork = NAMESPACE + "#hasResultantWork";
	public static final String OWLObjectProperty_affiliatedWithInstitution = NAMESPACE + "#affiliatedWithInstitution";
	public static final String OWLObjectProperty_hasDiscipline = NAMESPACE + "#hasDiscipline";

	// Data Properties
	public static final String OWLDataProperty_hasGrantAmount = NAMESPACE + "#hasGrantAmount";

	// To be replaced by VCARD and others...
	public static final String OWLDataProperty_hasState = NAMESPACE + "#hasState";
	public static final String OWLDataProperty_hasCity = NAMESPACE + "#hasCity";
	public static final String OWLDataProperty_hasZipcode = NAMESPACE + "#hasZipcode";
	public static final String OWLDataProperty_hasAddress = NAMESPACE + "#hasAddress";

	public VLC(OWLDataFactory dataFactory) {
		super(dataFactory);
		// TODO Auto-generated constructor stub
	}
	
	public OWLObjectProperty getOWLObjectProperty_hasRelatedResource(){
		return factory.getOWLObjectProperty(IRI.create(OWLObjectProperty_hasRelatedResource));
	}
	public OWLObjectProperty getOWLObjectProperty_hasRelatedAgent(){
		return factory.getOWLObjectProperty(IRI.create(OWLObjectProperty_hasRelatedAgent));
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
	public OWLObjectProperty getOWLObjectProperty_hasDiscipline(){
		return factory.getOWLObjectProperty(IRI.create(OWLObjectProperty_hasDiscipline));
	}
	
	public OWLDataProperty getOWLDataProperty_hasGrantAmount(){
		return factory.getOWLDataProperty(IRI.create(OWLDataProperty_hasGrantAmount));
	}
	public OWLDataProperty getOWLDataProperty_hasState(){
		return factory.getOWLDataProperty(IRI.create(OWLDataProperty_hasState));
	}
	public OWLDataProperty getOWLDataProperty_hasCity(){
		return factory.getOWLDataProperty(IRI.create(OWLDataProperty_hasCity));
	}
	public OWLDataProperty getOWLDataProperty_hasZipcode(){
		return factory.getOWLDataProperty(IRI.create(OWLDataProperty_hasZipcode));
	}
	public OWLDataProperty getOWLDataProperty_hasAddress(){
		return factory.getOWLDataProperty(IRI.create(OWLDataProperty_hasAddress));
	}

	@Override
	public String getNamespace() {
		// TODO Auto-generated method stub
		return NAMESPACE;
	}

}
