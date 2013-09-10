package edu.utep.cybershare.vlc.ontology.vocabulary;

public class VLC {
	
	public static final String NAMESPACE = "https://raw.github.com/nicholasdelrio/VLC/master/ontology/RIM.owl";
	
	// Object Properties
	public static final String OBJECT_PROPERTY_hasRelatedResource = NAMESPACE + "#hasRelatedResource";
	public static final String OBJECT_PROPERTY_hasInfluencedResource = NAMESPACE + "#hasInfluencedResource";
	public static final String OBJECT_PROPERTY_IRI_hasHostingInstitution = NAMESPACE + "#hasHostingInstitution";
	public static final String OBJECT_PROPERTY_hasRelatedWork = NAMESPACE + "#hasRelatedWork";
	public static final String OBJECT_PROPERTY_hasResultantWork = NAMESPACE + "#hasResultantWork";
	public static final String OBJECT_PROPERTY_affiliatedWithInstitution = NAMESPACE + "#affiliatedWithInstitution";
	
	//Person
	public static final String OBJECT_PROPERTY_IRI_hasDiscipline = NAMESPACE + "#hasDiscipline";
	public static final String DATA_PROPERTY_IRI_hasFirstName = NAMESPACE + "#hasFirstName";
	public static final String DATA_PROPERTY_IRI_hasLastName = NAMESPACE + "#hasLastName";

	//Institution
	public static final String CLASS_IRI_Institution = NAMESPACE + "#Institution";
	public static final String DATA_PROPERTY_IRI_hasName = NAMESPACE + "#hasName";
	public static final String DATA_PROPERTY_IRI_hasLatitude = NAMESPACE + "#hasLatitude";
	public static final String DATA_PROPERTY_IRI_hasLongitude = NAMESPACE + "#hasLongitude";
	public static final String DATA_PROPERTY_IRI_hasState = NAMESPACE + "#hasState";
	public static final String DATA_PROPERTY_IRI_hasCity = NAMESPACE + "#hasCity";
	public static final String DATA_PROPERTY_IRI_hasZipcode = NAMESPACE + "#hasZipcode";
	public static final String DATA_PROPERTY_IRI_hasAddress = NAMESPACE + "#hasAddress";




	
	//Discipline
	public static final String CLASS_IRI_Discipline = NAMESPACE + "#Discipline";	
}
