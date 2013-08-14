package edu.utep.cybershare.vlc.sparql;

public class SparqlQueries {
	
	private static String prefixDeclaration_RIM = "prefix rim: <https://raw.github.com/nicholasdelrio/VLC/master/ontology/RIM.owl#>";
	private static String namedGraphDeclaration = "from <http://vlc.cybershare.utep.edu/filtered-projects.owl>";
	
	private static String newline = "\n";
	
	public static String getProject2PeopleByPIShip(String project){
		String query = prefixDeclaration_RIM									+ newline;

		String projectReplacement;
		
		query += "select ?project ?person"								 		+ newline;
		query += namedGraphDeclaration											+ newline;
		query += "where{"														+ newline;
		query += "{?project rim:hasPrincipalInvestigator ?person}"				+ newline;
		query += "union"														+ newline;
		query += "{?project rim:hasCoPrincipalInvestigator ?person}"			+ newline;
		query += "filter(?person != <http://vlc.cybershare.utep.edu/filtered-projects.owl#nullnamenullname>)}";
		
		return query;
	}
	
	public static String getProjects2ProjectsByPeople(){
		String query = prefixDeclaration_RIM									+ newline;
		
		query += "select distinct ?person ?sourceProject ?sourceInstitution ?targetProject ?targetInstitution" 		+ newline;
		query += namedGraphDeclaration											+ newline;
		query += "where"														+ newline;
		query += "{"															+ newline;
		query += "{"															+ newline;
		query += "?sourceProject rim:hasPrincipalInvestigator ?person ."		+ newline;
		query += "?targetProject rim:hasPrincipalInvestigator ?person ."		+ newline;
		query += "?sourceProject rim:hasHostingInstitution ?sourceInstitution ."+ newline;
		query += "?targetProject rim:hasHostingInstitution ?targetInstitution ."+ newline;
		query += "filter(?sourceProject != ?targetProject)"						+ newline;
		query += "}"															+ newline;
		query += "union"														+ newline;
		query += "{"															+ newline;
		query += "?sourceProject rim:hasCoPrincipalInvestigator ?person ."		+ newline;
		query += "?targetProject rim:hasCoPrincipalInvestigator ?person ."		+ newline;
		query += "?sourceProject rim:hasHostingInstitution ?sourceInstitution ."+ newline;
		query += "?targetProject rim:hasHostingInstitution ?targetInstitution ."+ newline;
		query += "filter(?sourceProject != ?targetProject)"						+ newline;
		query += "}"															+ newline;
		query += "union"														+ newline;
		query += "{"															+ newline;
		query += "?sourceProject rim:hasPrincipalInvestigator ?person ."		+ newline;
		query += "?targetProject rim:hasCoPrincipalInvestigator ?person ."		+ newline;
		query += "?sourceProject rim:hasHostingInstitution ?sourceInstitution ."+ newline;
		query += "?targetProject rim:hasHostingInstitution ?targetInstitution ."+ newline;
		query += "filter(?sourceProject != ?targetProject)"						+ newline;
		query += "}"															+ newline;
		query += "union"														+ newline;
		query += "{"															+ newline;
		query += "?sourceProject rim:hasCoPrincipalInvestigator ?person ."		+ newline;
		query += "?targetProject rim:hasPrincipalInvestigator ?person ."		+ newline;
		query += "?sourceProject rim:hasHostingInstitution ?sourceInstitution ."+ newline;
		query += "?targetProject rim:hasHostingInstitution ?targetInstitution ."+ newline;
		query += "filter(?sourceProject != ?targetProject)"						+ newline;
		query += "}"															+ newline;		
		query += "filter(?person != <http://vlc.cybershare.utep.edu/filtered-projects.owl#nullnamenullname>)" + newline;
		query += "}"															+ newline;
			
		return query;
	}
}