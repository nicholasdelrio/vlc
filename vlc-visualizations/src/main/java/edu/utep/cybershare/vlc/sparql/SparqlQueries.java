package edu.utep.cybershare.vlc.sparql;

public class SparqlQueries {
	
	private static String prefixDeclaration_RIM = "prefix rim: <https://raw.github.com/nicholasdelrio/VLC/master/ontology/RIM.owl#>";
	private static String namedGraphDeclaration = "from <http://vlc.cybershare.utep.edu/filtered-projects.owl>";
	private static String rimRuleSetDeclaration = "define input:inference 'https://raw.github.com/nicholasdelrio/VLC/master/ontology/RIM-Rules.owl'";
	private static String newline = "\n";
	
	public static String getInstitutions2InstitutionsByProjects(){
		/*
		prefix rim: <https://raw.github.com/nicholasdelrio/VLC/master/ontology/RIM.owl#>
		select distinct ?sourceInstitution ?sourceLat ?sourceLon ?targetInstitution ?targetLat ?targetLon ?project
		from <http://vlc.cybershare.utep.edu/filtered-projects.owl>
		where {
			?project rim:hasHostingInstitution ?sourceInstitutionURI .
			?project rim:hasHostingInstitution ?targetInstitutionURI .

			?sourceInstitutionURI rim:hasLatitude ?sourceLat .
			?sourceInstitutionURI rim:hasLongitude ?sourceLon .
			?sourceInstitutionURI rim:hasName ?sourceInstitution .

			?targetInstitutionURI rim:hasLatitude ?targetLat .
			?targetInstitutionURI rim:hasLongitude ?targetLon .
			?targetInstitutionURI rim:hasName ?targetInstitution .
	
			filter(?sourceInstitution != ?targetInstitution)
		}		
		*/
		String query = prefixDeclaration_RIM									+ newline;
		query += "select distinct ?sourceInstitution ?sourceLat ?sourceLon ?targetInstitution ?targetLat ?targetLon ?project"
																				+ newline;
		query += namedGraphDeclaration											+ newline;
		query += "where {"														+ newline;
		query += "?project rim:hasHostingInstitution ?sourceInstitutionURI ."	+ newline; 
		query += "?project rim:hasHostingInstitution ?targetInstitutionURI ."	+ newline;

		query += "?sourceInstitutionURI rim:hasLatitude ?sourceLat ."			+ newline;
		query += "?sourceInstitutionURI rim:hasLongitude ?sourceLon ."			+ newline;
		query += "?sourceInstitutionURI rim:hasName ?sourceInstitution ."		+ newline;

		query += "?targetInstitutionURI rim:hasLatitude ?targetLat ."			+ newline;
		query += "?targetInstitutionURI rim:hasLongitude ?targetLon ."			+ newline;
		query += "?targetInstitutionURI rim:hasName ?targetInstitution ."		+ newline;

		query += "filter(?sourceInstitution != ?targetInstitution)"				+ newline;
		query += "}";

		return query;
	}
	
	public static String getProjects2ProjectsByPeople(){
		/*
		define input:inference "https://raw.github.com/nicholasdelrio/VLC/master/ontology/RIM-Rules.owl"
		prefix rim: <https://raw.github.com/nicholasdelrio/VLC/master/ontology/RIM.owl#>
		select ?person ?sourceProject ?targetProject
		from <http://vlc.cybershare.utep.edu/filtered-projects.owl>
		where {
			{
				?sourceProject rim:hasProjectMember ?person .
				?sourceProject rim:hasHostingInstitution ?sourceInstitution . 
				?targetProject rim:hasProjectMember ?person .
				?targetProject rim:hasHostingInstitution ?targetInstitution .
				filter(?sourceProject != ?targetProject)
				filter(?person != <http://vlc.cybershare.utep.edu/filtered-projects.owl#nullnamenullname>)
			}
			union
			{
				?sourceProject rim:hasProjectMember ?person .
				?sourceProject rim:hasHostingInstitution ?sourceInstitution . 
				?targetProject rim:hasProjectMember ?person .
				?targetProject rim:hasHostingInstitution ?targetInstitution .
				filter(?sourceInstitution != ?targetInstitution)
			}
			filter(?person != <http://vlc.cybershare.utep.edu/filtered-projects.owl#nullnamenullname>)
		}
		*/
		
		String query = rimRuleSetDeclaration 										+ newline;
		query += prefixDeclaration_RIM												+ newline;
		query += "select distinct ?person ?sourceProject ?sourceInstitution ?targetProject ?targetInstitution" 																					+ newline;
		query += namedGraphDeclaration												+ newline;
		query += "where"															+ newline;
		query += "{"																+ newline;
		query += "{"																+ newline;
		query += "?sourceProject rim:hasProjectMember ?person ."					+ newline;
		query += "?sourceProject rim:hasHostingInstitution ?sourceInstitution ." 	+ newline;
		query += "?targetProject rim:hasProjectMember ?person ."					+ newline;
		query += "?targetProject rim:hasHostingInstitution ?targetInstitution ."	+ newline;
		query += "filter(?sourceProject != ?targetProject)"							+ newline;
		query += "}"																+ newline;
		query += "union"															+ newline;
		query += "{"																+ newline;		
		query += "?sourceProject rim:hasProjectMember ?person ."					+ newline;
		query += "?sourceProject rim:hasHostingInstitution ?sourceInstitution ." 	+ newline;
		query += "?targetProject rim:hasProjectMember ?person ."					+ newline;
		query += "?targetProject rim:hasHostingInstitution ?targetInstitution ."	+ newline;
		query += "filter(?sourceInstitution != ?targetInstituiton)"					+ newline;
		query += "}"																+ newline;
		query += "filter(?person != <http://vlc.cybershare.utep.edu/filtered-projects.owl#nullnamenullname>)"
																					+ newline;
		query += "}"																+ newline;
		return query;
	}
}