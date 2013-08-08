package edu.utep.cybershare.vlc.sparql;

public class SparqlQueries {
	
	private static String prefixDeclaration_RIM = "prefix rim: <https://raw.github.com/nicholasdelrio/VLC/master/ontology/RIM.owl#>";
	private static String prefixDeclaration_OWL = "";
	
	private static String newline = "\n";
	
	public static String getProjectsByPeople(){
		String query = prefixDeclaration_RIM;
		
		query += "select distinct(?person) ?sourceProject ?targetProject" 				+ newline;
		query += "from <http://vlc.cybershare.utep.edu/filtered-projects.owl>"	+ newline;
		query += "where"														+ newline;
		query += "{{"															+ newline;
		query += "?sourceProject rim:hasPrincipalInvestigator ?person ."		+ newline;
		query += "?targetProject rim:hasPrincipalInvestigator ?person ."		+ newline;

		query += "?sourceProject rim:hasCoPrincipalInvestigator ?person1 ." 	+ newline;
		query += "?targetProject rim:hasPrincipalInvestigator ?person1 ."		+ newline;
		query += "}"															+ newline;
		query += "union"														+ newline;
		query += "{"															+ newline;
		query += "?sourceProject rim:hasPrincipalInvestigator ?person ."		+ newline;
		query += "?targetProject rim:hasPrincipalInvestigator ?person ."		+ newline;

		query += "?sourceProject rim:hasCoPrincipalInvestigator ?person1 ."		+ newline;
		query += "?targetProject rim:hasCoPrincipalInvestigator ?person1 ."		+ newline;
		query += "}"															+ newline;
		query += "filter(?sourceProject != ?targetProject)"						+ newline;
		query += "filter(?person != ?person1)"									+ newline;
			
		return query;
	}
}
