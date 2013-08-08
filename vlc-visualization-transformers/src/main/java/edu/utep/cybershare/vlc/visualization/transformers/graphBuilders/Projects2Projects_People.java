package edu.utep.cybershare.vlc.visualization.transformers.graphBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.utep.cybershare.vlc.sparql.SparqlEndpoint;
import edu.utep.cybershare.vlc.sparql.SparqlQueries;
import edu.utep.cybershare.vlc.sparql.SparqlResultsPicker;
import edu.utep.cybershare.vlc.visualization.transformers.graphBuilders.graphs.Graph_Project2Project_People;

public class Projects2Projects_People {
	
	private SparqlEndpoint endpoint;
	
	public Projects2Projects_People(SparqlEndpoint endpoint){
		endpoint.setQuery(SparqlQueries.getProjectsByPeople());
	}
	
	private JSONObject getResults(){
		String jsonResults = endpoint.executeQuery();
		JSONObject results = new JSONObject();
		try{results = new JSONObject(jsonResults);}
		catch(Exception e){e.printStackTrace();}
		return results;
	}
	
	public String getJSONGraph(){
		Graph_Project2Project_People graph = getGraph(getResults());
		String stringGraph = graph.getGraph().toString();
		try{stringGraph = graph.getGraph().toString(4);}
		catch(Exception e){e.printStackTrace();}
		return stringGraph;
	}
	
	private Graph_Project2Project_People getGraph(JSONObject sparqlResults){
		JSONArray bindingsArray = SparqlResultsPicker.getBindings(sparqlResults);
		JSONObject aBinding;
		
		String personName;
		String sourceProjectName;
		String targetProjectName;
		
		Graph_Project2Project_People.ProjectNode sourceProject;
		Graph_Project2Project_People.ProjectNode targetProject;
		Graph_Project2Project_People.ProjectLink link;
		
		Graph_Project2Project_People graph = new Graph_Project2Project_People();
		try{
			for(int i = 0; i < bindingsArray.length(); i ++){
				aBinding = bindingsArray.getJSONObject(i);
				personName = aBinding.getJSONObject("person").getString("value");
				sourceProjectName = aBinding.getJSONObject("sourceProject").getString("value");
				targetProjectName = aBinding.getJSONObject("targetProject").getString("value");
				
				sourceProject = new Graph_Project2Project_People.ProjectNode(sourceProjectName);
				targetProject = new Graph_Project2Project_People.ProjectNode(targetProjectName);
				
				link = new Graph_Project2Project_People.ProjectLink(sourceProject, targetProject);
				link.addPerson(personName);
				
				//add link to graph
				graph.addLink(link);
			}
		}catch(Exception e){e.printStackTrace();}
		return graph;
	}	
}
