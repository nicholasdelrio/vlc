package edu.utep.cybershare.vlc.visualization.transformers.graphBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.utep.cybershare.vlc.sparql.SparqlEndpoint;
import edu.utep.cybershare.vlc.sparql.SparqlQueries;
import edu.utep.cybershare.vlc.sparql.SparqlResultsPicker;
import edu.utep.cybershare.vlc.visualization.transformers.graphBuilders.graphs.Graph_Projects2Projects_People;

public class Projects2Projects_People {
	
	private SparqlEndpoint endpoint;
	
	public Projects2Projects_People(SparqlEndpoint endpoint){
		this.endpoint = endpoint;
		endpoint.setQuery(SparqlQueries.getProjects2ProjectsByPeople());
	}
	
	private JSONObject getResults(){
		String jsonResults = endpoint.executeQuery();
		JSONObject results = new JSONObject();
		try{results = new JSONObject(jsonResults);}
		catch(Exception e){e.printStackTrace();}
		return results;
	}
	
	public String getJSONGraph(){
		Graph_Projects2Projects_People graph = getGraph(getResults());
		String stringGraph = "{}";
		try{stringGraph = graph.getJSONObjectGraph().toString(4);}
		catch(Exception e){e.printStackTrace();}
		return stringGraph;
	}
	
	private Graph_Projects2Projects_People getGraph(JSONObject sparqlResults){
		JSONArray bindingsArray = SparqlResultsPicker.getBindings(sparqlResults);
		JSONObject aBinding;
		
		String personName;
		String sourceProjectName;
		String targetProjectName;
		String sourceInstitutionName;
		String targetInstitutionName;

		Graph_Projects2Projects_People.ProjectNode sourceProject;
		Graph_Projects2Projects_People.ProjectNode targetProject;
		Graph_Projects2Projects_People.ProjectLink link;
		
		Graph_Projects2Projects_People graph = new Graph_Projects2Projects_People();
		try{
			for(int i = 0; i < bindingsArray.length(); i ++){
				aBinding = bindingsArray.getJSONObject(i);
				personName = aBinding.getJSONObject("person").getString("value");
				sourceProjectName = aBinding.getJSONObject("sourceProject").getString("value");
				targetProjectName = aBinding.getJSONObject("targetProject").getString("value");
				sourceInstitutionName = aBinding.getJSONObject("sourceInstitution").getString("value");
				targetInstitutionName = aBinding.getJSONObject("targetInstitution").getString("value");				
				
				sourceProject = new Graph_Projects2Projects_People.ProjectNode(sourceProjectName, sourceInstitutionName);
				targetProject = new Graph_Projects2Projects_People.ProjectNode(targetProjectName, targetInstitutionName);
				
				link = new Graph_Projects2Projects_People.ProjectLink(sourceProject, targetProject);
				link.addPerson(personName);
				
				//add link to graph
				graph.addLink(link);
			}
		}catch(Exception e){e.printStackTrace();}
		return graph;
	}	
}
