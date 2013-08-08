package edu.utep.cybershare.vlc.visualization.transformers.projects.graph;

import java.util.Hashtable;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class JSONGraph {
	
	private JSONObject graph;
	private JSONArray links;
	private JSONArray nodes;
	
	private Hashtable<String,JSONObject> linksMap;
	private Hashtable<String,JSONObject> nodesMap;
	
	public JSONGraph(){
		linksMap = new Hashtable<String,JSONObject>();
		nodesMap = new Hashtable<String,JSONObject>();
	}
	
	public void addNode(String name){
		JSONObject node;
		try{
			node = new JSONObject().put("name", name);
			addNode(node);
		}	
		catch(Exception e){e.printStackTrace();}
	}
	
	public void addLink(int source, int target){
		JSONObject link;
		try{
			link = new JSONObject()
				.put("source", source)
				.put("target", target);
			
			addLink(link);
		}	
		catch(Exception e){e.printStackTrace();}
	}
	
	protected boolean addLink(JSONObject link){
		String linkString = link.toString();
		
		if(linksMap.get(linkString) == null){
			linksMap.put(linkString, link);
			return true;
		}
		return false;
	}
	
	protected boolean addNode(JSONObject node){
	String nodeString = node.toString();
		
		if(nodesMap.get(nodeString) == null){
			nodesMap.put(nodeString, node);
			return true;
		}
		return false;
	}
	
	public JSONObject getGraph(){
		graph = new JSONObject();
		try{			
			links = new JSONArray(linksMap.values());
			nodes = new JSONArray(nodesMap.values());
			
			graph.put("links", links);
			graph.put("nodes", nodes);
			return graph;
		}
		catch(Exception e){e.printStackTrace();}
		return graph;
	}
}
