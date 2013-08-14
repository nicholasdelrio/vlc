package edu.utep.cybershare.vlc.visualization.transformers.graphBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.utep.cybershare.vlc.sparql.SparqlEndpoint;
import edu.utep.cybershare.vlc.sparql.SparqlQueries;
import edu.utep.cybershare.vlc.sparql.SparqlResultsPicker;
import edu.utep.cybershare.vlc.visualization.transformers.graphBuilders.graphs.Graph_Institutions2Institutions_Projects;

public class Institutions2Institutions_Projects {
	
	private SparqlEndpoint endpoint;
	
	public Institutions2Institutions_Projects(SparqlEndpoint endpoint){
		this.endpoint = endpoint;
		endpoint.setQuery(SparqlQueries.getInstitutions2InstitutionsByProjects());
	}
	
	private JSONObject getResults(){
		String jsonResults = endpoint.executeQuery();
		JSONObject results = new JSONObject();
		try{results = new JSONObject(jsonResults);}
		catch(Exception e){e.printStackTrace();}
		return results;
	}
	
	public String getJSONGraph(){
		Graph_Institutions2Institutions_Projects graph = getGraph(getResults());
		String stringGraph = "{}";
		try{stringGraph = graph.getJSONObjectGraph().toString(4);}
		catch(Exception e){e.printStackTrace();}
		return stringGraph;
	}
	
	private Graph_Institutions2Institutions_Projects getGraph(JSONObject sparqlResults){
		JSONArray bindingsArray = SparqlResultsPicker.getBindings(sparqlResults);
		JSONObject aBinding;
		
		String projectName;
		String sourceInstitutionName;
		String targetInstitutionName;
		double sourceLat;
		double sourceLon;
		
		double targetLat;
		double targetLon;

		Graph_Institutions2Institutions_Projects.InstitutionNode sourceInstitution;
		Graph_Institutions2Institutions_Projects.InstitutionNode targetInstitution;
		Graph_Institutions2Institutions_Projects.InstitutionLink link;
		
		Graph_Institutions2Institutions_Projects graph = new Graph_Institutions2Institutions_Projects();
		try{
			for(int i = 0; i < bindingsArray.length(); i ++){
				aBinding = bindingsArray.getJSONObject(i);
				projectName = aBinding.getJSONObject("project").getString("value");

				sourceInstitutionName = aBinding.getJSONObject("sourceInstitution").getString("value");
				targetInstitutionName = aBinding.getJSONObject("targetInstitution").getString("value");
				
				sourceLat = aBinding.getJSONObject("sourceLat").getDouble("value");
				sourceLon = aBinding.getJSONObject("sourceLon").getDouble("value");			

				targetLat = aBinding.getJSONObject("targetLat").getDouble("value");
				targetLon = aBinding.getJSONObject("targetLon").getDouble("value");			
				
				sourceInstitution = new Graph_Institutions2Institutions_Projects.InstitutionNode(sourceInstitutionName, sourceLat, sourceLon);
				targetInstitution = new Graph_Institutions2Institutions_Projects.InstitutionNode(targetInstitutionName, targetLat, targetLon);
				
				link = new Graph_Institutions2Institutions_Projects.InstitutionLink(sourceInstitution, targetInstitution);
				link.addCommonProjectName(projectName);
				
				//add link to graph
				graph.addLink(link);
			}
		}catch(Exception e){e.printStackTrace();}
		return graph;
	}	
}