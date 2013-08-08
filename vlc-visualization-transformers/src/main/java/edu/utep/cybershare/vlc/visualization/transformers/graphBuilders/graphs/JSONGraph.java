package edu.utep.cybershare.vlc.visualization.transformers.graphBuilders.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class JSONGraph {
	
	private JSONObject graph;
	private JSONArray links;
	private ArrayList<JSONObject> nodes;

	private Hashtable<String,Link> linksMap;
	private Hashtable<String,Integer> nodesMap;

	private int nodeCounter;
	
	public JSONGraph(){
		nodeCounter = 0;
		linksMap = new Hashtable<String,Link>();
		nodesMap = new Hashtable<String,Integer>();
	}
	
	public void addLink(String sourceName, String targetName){
		Link link;
		try{
			link = new Link(sourceName, targetName);			
			addLink(link);
		}	
		catch(Exception e){e.printStackTrace();}
	}
	
	protected boolean addLink(Link link){
		String key = link.getKey();
		
		if(linksMap.get(key) == null){
			linksMap.put(key, link);
			return true;
		}
		return false;
	}
	
	private void populateNodes(){
		List<Link> links = new ArrayList<Link>(linksMap.values());
		nodes = new ArrayList<JSONObject>();
		
		Link aLink;
		String sourceName;
		String targetName;
		
		int source;
		int target;
		
		for(int i = 0; i < links.size(); i ++){
			aLink = links.get(i);
			sourceName = aLink.getSource();
			targetName = aLink.getTarget();
			
			
				
			
			aLink.get
		}
	}
	
	private int getIndex(String nodeName){
		Integer index = nodesMap.get(nodeName);
		if(index == null)
			nodesMap.put(nodeName, new Integer(nodeCounter ++));
		
		return index;
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
