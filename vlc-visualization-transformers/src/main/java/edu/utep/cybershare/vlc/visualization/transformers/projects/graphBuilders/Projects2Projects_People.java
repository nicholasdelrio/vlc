package edu.utep.cybershare.vlc.visualization.transformers.projects.graphBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.utep.cybershare.vlc.sparql.SparqlEndpoint;
import edu.utep.cybershare.vlc.sparql.SparqlQueries;
import edu.utep.cybershare.vlc.sparql.SparqlResultsPicker;

public class Projects2Projects_People {
	
	private SparqlEndpoint endpoint;
	
	public Projects2Projects_People(SparqlEndpoint endpoint){
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
		
		return null;
	}
}
