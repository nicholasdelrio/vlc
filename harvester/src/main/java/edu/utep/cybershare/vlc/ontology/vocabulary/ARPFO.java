package edu.utep.cybershare.vlc.ontology.vocabulary;

/**
 * Academic Research Project Funding Ontology (ARPFO)
 * <a href=""></>
 * @author Nicholas Del Rio
 *
 */

public class ARPFO {

	public static final String NAMESPACE = "http://vocab.ox.ac.uk/projectfunding/";
	
	// Classes
	public static final String Project = NAMESPACE + "Project";
	
	// Data Properties
	public static final String DATA_PROPERTY_endDate = NAMESPACE + "endDate";
	public static final String DATA_PROPERTY_startDate = NAMESPACE + "startDate";
	public static final String DATA_PROPERTY_grantNumber = NAMESPACE + "grantNumber";
	public static final String DATA_PROPERTY_statedPurpose = NAMESPACE + "statedPurpose";
	
	
	// Object Properties
	public static final String hasCoInvestigator = NAMESPACE + "hasCoInvestigator";
	public static final String hasPrincipalInvestigator = NAMESPACE + "hasPrincipalInvestigator";	
}
