package edu.utep.cybershare.vlc.sparql;

public class SparqlQueries {
	
	private static String prefixDeclaration_RIM = "prefix rim: <https://raw.github.com/nicholasdelrio/VLC/master/ontology/RIM.owl#>";
	private static String namedGraphDeclaration = "from <http://vlc.cybershare.utep.edu/filtered-projects.owl>";
	private static String rimRuleSetDeclaration = "define input:inference 'https://raw.github.com/nicholasdelrio/VLC/master/ontology/RIM-Rules.owl'";
	private static String newline = "\n";
	
	public static String getInstitutions2InstitutionsByProjects(){
		String query = prefixDeclaration_RIM									+ newline;
		query += "select distinct ?sourceInstitution ?sourceLat ?sourceLon ?targetInstitution ?targetLat ?targetLon ?project"
																				+ newline;
		query += namedGraphDeclaration											+ newline;
		query += "where {"														+ newline;
		query += "?projectURI rim:hasHostingInstitution ?sourceInstitutionURI ."+ newline; 
		query += "?projectURI rim:hasHostingInstitution ?targetInstitutionURI ."+ newline;
		query += "?projectURI rim:hasTitle ?project ."							+ newline;	
		
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
		String query = rimRuleSetDeclaration 										+ newline;
		query += prefixDeclaration_RIM												+ newline;
		query += "select distinct ?person ?sourceProject ?sourceInstitution ?targetProject ?targetInstitution" 																					+ newline;
		query += namedGraphDeclaration												+ newline;
		query += "where"															+ newline;
		query += "{"																+ newline;
		query += "{"																+ newline;
		query += "?sourceProjectURI rim:hasProjectMember ?personURI ."					+ newline;
		query += "?sourceProjectURI rim:hasHostingInstitution ?sourceInstitutionURI ." 	+ newline;
		query += "?targetProjectURI rim:hasProjectMember ?personURI ."					+ newline;
		query += "?targetProjectURI rim:hasHostingInstitution ?targetInstitutionURI ."	+ newline;

		query += "?sourceProjectURI rim:hasTitle ?sourceProject ."					+ newline;
		query += "?targetProjectURI rim:hasTitle ?targetProject ."					+ newline;
		query += "?sourceInstitutionURI rim:hasName ?sourceInstitution ."			+ newline;
		query += "?targetInstitutionURI rim:hasName ?targetInstitution ."			+ newline;
		query += "?personURI rim:hasLastName ?person ."								+ newline;
		
		query += "filter(?sourceProjectURI != ?targetProjectURI)"							+ newline;
		query += "}"																+ newline;
		query += "union"															+ newline;
		query += "{"																+ newline;		
		query += "?sourceProjectURI rim:hasProjectMember ?personURI ."					+ newline;
		query += "?sourceProjectURI rim:hasHostingInstitution ?sourceInstitutionURI ." 	+ newline;
		query += "?targetProjectURI rim:hasProjectMember ?personURI ."					+ newline;
		query += "?targetProjectURI rim:hasHostingInstitution ?targetInstitutionURI ."	+ newline;

		query += "?sourceProjectURI rim:hasTitle ?sourceProject ."					+ newline;
		query += "?targetProjectURI rim:hasTitle ?targetProject ."					+ newline;
		query += "?sourceInstitutionURI rim:hasName ?sourceInstitution ."			+ newline;
		query += "?targetInstitutionURI rim:hasName ?targetInstitution ."			+ newline;
		query += "?personURI rim:hasLastName ?person ."								+ newline;
		
		query += "filter(?sourceInstitutionURI != ?targetInstitutionURI)"					+ newline;
		query += "}"																+ newline;
		query += "filter(?personURI != <http://vlc.cybershare.utep.edu/filtered-projects.owl#nullnamenullname>)"
																					+ newline;
		query += "}"																+ newline;
		return query;
	}
	
	public static void main(String[] args){
		System.out.println(SparqlQueries.getProjects2ProjectsByPeople());
		System.out.println(SparqlQueries.getInstitutions2InstitutionsByProjects());
	}
}