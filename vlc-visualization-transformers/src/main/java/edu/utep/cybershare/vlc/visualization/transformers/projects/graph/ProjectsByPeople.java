package edu.utep.cybershare.vlc.visualization.transformers.projects.graph;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.utep.cybershare.vlc.sparql.SparqlEndpoint;
import edu.utep.cybershare.vlc.sparql.SparqlQueries;
import edu.utep.cybershare.vlc.sparql.SparqlResultsPicker;

public class ProjectsByPeople {
	
	private SparqlEndpoint endpoint;
	
	public ProjectsByPeople(SparqlEndpoint endpoint){
		endpoint.setQuery(getSparqlQuery());
	}
	
	private String getSparqlQuery(){
		return SparqlQueries.getProjectsByPeople();
	}

	public JSONObject transform(JSONObject sparqlResults){
	
		JSONArray bindingsArray = SparqlResultsPicker.getBindings(sparqlResults);
		
		JSONObject aResult;
		try{
			for(int i = 0; i < bindingsArray.length(); i ++){
				aResult = bindingsArray.getJSONObject(i);
				
			}
		}catch(Exception e){e.printStackTrace();}
	}
}
