package edu.utep.cybershare.vlc.visualization.transformers.graphBuilders;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.utep.cybershare.vlc.sparql.SparqlEndpoint;
import edu.utep.cybershare.vlc.sparql.SparqlQueries;
import edu.utep.cybershare.vlc.sparql.SparqlResultsPicker;
import edu.utep.cybershare.vlc.visualization.transformers.graphBuilders.graphs.Graph_Project2Project_People;

public class Projects2Projects_People {
	
	private SparqlEndpoint endpoint;
	private Hashtable<String,LinkAndPeople> edgesToPeople;
	
	public Projects2Projects_People(SparqlEndpoint endpoint){
		edgesToPeople = new Hashtable<String,LinkAndPeople>();
		endpoint.setQuery(getSparqlQuery());
	}
	
	private String getSparqlQuery(){
		return SparqlQueries.getProjectsByPeople();
	}

	public Graph_Project2Project_People getGraph(JSONObject sparqlResults){
	
		JSONArray bindingsArray = SparqlResultsPicker.getBindings(sparqlResults);
		JSONObject aBinding;
		String person;
		String sourceProject;
		String targetProject;
		LinkAndPeople link;
		try{
			for(int i = 0; i < bindingsArray.length(); i ++){
				aBinding = bindingsArray.getJSONObject(i);
				person = aBinding.getJSONObject("person").getString("value");
				sourceProject = aBinding.getJSONObject("sourceProject").getString("value");
				targetProject = aBinding.getJSONObject("targetProject").getString("value");
				
				link = new LinkAndPeople(sourceProject, targetProject);
				link.addPerson(person);
			}
		}catch(Exception e){e.printStackTrace();}
		
		return null;
	}
	
	private void addLink(){
		
	}

}
