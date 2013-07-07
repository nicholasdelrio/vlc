package edu.utep.cybershare.vlc.ontology;

public class Vocabulary {
	
	public static final String VLC_ONTOLOGY_IRI = "https://raw.github.com/nicholasdelrio/VLC/master/ontology/RIM.owl";
	
	//project
	public static final String CLASS_IRI_Project = VLC_ONTOLOGY_IRI + "#Project";
	public static final String OBJECT_PROPERTY_IRI_hasPrincipalInvestigator = VLC_ONTOLOGY_IRI + "#hasPrincipalInvestigator";
	public static final String OBJECT_PROPERTY_IRI_hasCoPrincipalInvestigator = VLC_ONTOLOGY_IRI + "#hasCoPrincipalInvestigator";
	public static final String DATA_PROPERTY_IRI_hasTitle = VLC_ONTOLOGY_IRI + "#hasTitle";
	public static final String DATA_PROPERTY_IRI_hasAbstract = VLC_ONTOLOGY_IRI + "#hasAbstract";
	public static final String DATA_PROPERTY_IRI_hasStartDate_Funding = VLC_ONTOLOGY_IRI + "#hasStartDate_Funding";
	public static final String DATA_PROPERTY_IRI_hasEndDate_Funding = VLC_ONTOLOGY_IRI + "#hasEndDate_Funding";

	//Person
	public static final String CLASS_IRI_Person = VLC_ONTOLOGY_IRI + "#Person";
	public static final String OBJECT_PROPERTY_IRI_affiliatedWithInstitution = VLC_ONTOLOGY_IRI + "#affiliatedWithInstitution";
	public static final String OBJECT_PROPERTY_IRI_hasDiscipline = VLC_ONTOLOGY_IRI + "#hasDiscipline";
	public static final String DATA_PROPERTY_IRI_hasFirstName = VLC_ONTOLOGY_IRI + "#hasFirstName";
	public static final String DATA_PROPERTY_IRI_hasLastName = VLC_ONTOLOGY_IRI + "#hasLastName";

	//Institution
	public static final String CLASS_IRI_Institution = VLC_ONTOLOGY_IRI + "#Institution";
	public static final String DATA_PROPERTY_IRI_hasName = VLC_ONTOLOGY_IRI + "#hasName";
	public static final String DATA_PROPERTY_IRI_hasLatitude = VLC_ONTOLOGY_IRI + "#hasLatitude";
	public static final String DATA_PROPERTY_IRI_hasLongitude = VLC_ONTOLOGY_IRI + "#hasLongitude";
	
	//Discipline
	public static final String CLASS_IRI_Discipline = VLC_ONTOLOGY_IRI + "#Discipline";	
}
