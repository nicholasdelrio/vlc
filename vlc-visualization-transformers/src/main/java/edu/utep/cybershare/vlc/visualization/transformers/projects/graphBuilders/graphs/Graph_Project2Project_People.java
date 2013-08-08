package edu.utep.cybershare.vlc.visualization.transformers.projects.graphBuilders.graphs;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class Graph_Project2Project_People extends JSONGraph {

	public void addLink(int source, int target, List<String> people) {
		JSONObject link = new JSONObject();
		JSONArray peopleList = new JSONArray();
		try{
			link.put("source", source);
			link.put("target", target);
			link.put("people", peopleList);
			
			addLink(link);
			
		}catch(Exception e){e.printStackTrace();}
	}
}
