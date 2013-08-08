package edu.utep.cybershare.vlc.sparql;

import org.json.JSONArray;
import org.json.JSONObject;

public class SparqlResultsPicker {
	
	public static JSONObject emptyObject;
	public static JSONArray emptyArray;
	
	static {
		try{
			emptyObject = new JSONObject();
			emptyArray = new JSONArray();
		}catch(Exception e){e.printStackTrace();}
	}
	
	public static JSONObject getResults(JSONObject sparqlResults){
		try{return sparqlResults.getJSONObject("results");}
		catch(Exception e){e.printStackTrace();}
		return emptyObject;
	}
	
	
	public static JSONArray getBindings(JSONObject sparqlResults){
		try{return sparqlResults.getJSONArray("bindings");}
		catch(Exception e){e.printStackTrace();}
		return emptyArray;
	}
}
