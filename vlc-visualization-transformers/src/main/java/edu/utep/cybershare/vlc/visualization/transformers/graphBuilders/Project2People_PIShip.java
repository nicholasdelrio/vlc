package edu.utep.cybershare.vlc.visualization.transformers.graphBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.utep.cybershare.vlc.sparql.SparqlEndpoint;
import edu.utep.cybershare.vlc.sparql.SparqlQueries;
import edu.utep.cybershare.vlc.sparql.SparqlResultsPicker;
import edu.utep.cybershare.vlc.visualization.transformers.graphBuilders.graphs.Graph_Projects2People_PIShip;

public class Project2People_PIShip {
	
	private SparqlEndpoint endpoint;
	
	public Project2People_PIShip(SparqlEndpoint endpoint){
		this.endpoint = endpoint;
		endpoint.setQuery(SparqlQueries.getProject2PeopleByPIShip(null));
	}
	
	private JSONObject getResults(){
		String jsonResults = endpoint.executeQuery();
		JSONObject results = new JSONObject();
		try{results = new JSONObject(jsonResults);}
		catch(Exception e){e.printStackTrace();}
		return results;
	}
	
	public String getJSONGraph(){
		Graph_Projects2People_PIShip graph = getGraph(getResults());
		String stringGraph = "{}";
		try{stringGraph = graph.getJSONObjectGraph().toString(4);}
		catch(Exception e){e.printStackTrace();}
		return stringGraph;
	}
	
	private Graph_Projects2People_PIShip getGraph(JSONObject sparqlResults){
		JSONArray bindingsArray = SparqlResultsPicker.getBindings(sparqlResults);
		JSONObject aBinding;
		
		String sourceProjectName;
		String targetPersonName;
		
		Graph_Projects2People_PIShip.ProjectNode sourceProject;
		Graph_Projects2People_PIShip.PersonNode targetPerson;
		Graph_Projects2People_PIShip.ProjectPersonLink link;
		
		Graph_Projects2People_PIShip graph = new Graph_Projects2People_PIShip();
		try{
			for(int i = 0; i < bindingsArray.length(); i ++){
				aBinding = bindingsArray.getJSONObject(i);
				sourceProjectName = aBinding.getJSONObject("project").getString("value");
				targetPersonName = aBinding.getJSONObject("person").getString("value");
				
				sourceProject = new Graph_Projects2People_PIShip.ProjectNode(sourceProjectName);
				targetPerson = new Graph_Projects2People_PIShip.PersonNode(targetPersonName);
				
				link = new Graph_Projects2People_PIShip.ProjectPersonLink(sourceProject, targetPerson);
				
				//add link to graph
				graph.addLink(link);
			}
		}catch(Exception e){e.printStackTrace();}
		return graph;
	}	
}