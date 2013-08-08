package edu.utep.cybershare.vlc.visualization.transformers.graphBuilders.graphs;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Graph_Project2People_PIShip {
		
	private ArrayList<Node> nodes;
	private Hashtable<String,Integer> nodesMap;
	private int nodeCounter;
	
	private Hashtable<String,ProjectPersonLink> linksMap;
	
	public Graph_Project2People_PIShip(){
		nodeCounter = 0;
		linksMap = new Hashtable<String,ProjectPersonLink>();
		nodesMap = new Hashtable<String,Integer>();
	}
		
	public void addLink(ProjectPersonLink link){
		String key = link.getKey();
		
		ProjectPersonLink existingLink = linksMap.get(key);
		if(existingLink == null)
			linksMap.put(key, link);			
	}
	
	private void populateNodes(){
		List<ProjectPersonLink> links = new ArrayList<ProjectPersonLink>(linksMap.values());
		nodes = new ArrayList<Node>();
		
		ProjectPersonLink aLink;
		ProjectNode sourceProject;
		PersonNode targetPerson;
				
		for(int i = 0; i < links.size(); i ++){
			aLink = links.get(i);
			sourceProject = aLink.getSourceProject();
			targetPerson = aLink.getTargetPerson();
			
			sourceProject.setIndex(getIndex(aLink.getSourceProject()));
			targetPerson.setIndex(getIndex(aLink.getTargetPerson()));		
		}
	}
	
	private int getIndex(Node node){
		Integer index = nodesMap.get(node.getKey());
		if(index == null){
			nodesMap.put(node.getKey(), new Integer(nodeCounter ++));
			nodes.add(node);
			index = nodeCounter;
		}
		
		return index;
	}
	
	private JSONArray getJSONArrayNodes(){
		JSONArray jsonNodes = new JSONArray();
		JSONObject jsonNode;
		Node node;
		for(int i = 0; i < nodes.size(); i ++){
			node = nodes.get(i);
			
			jsonNode = new JSONObject();
			try{
				jsonNode.put("name", node.getNodeName());
				jsonNode.put("type", node.getType());
				jsonNodes.put(i, jsonNode);
			}catch(Exception e){e.printStackTrace();}
		}
		return jsonNodes;
	}
	
	private JSONArray getJSONArrayLinks(){
		ArrayList<ProjectPersonLink> links = new ArrayList<ProjectPersonLink>(linksMap.values());
		ProjectPersonLink projectPersonLink;
		JSONArray jsonLinks = new JSONArray();
		JSONObject jsonLink;
		for(int i = 0; i < links.size(); i ++){
			projectPersonLink = links.get(i);
			jsonLink = new JSONObject();
			try{
				jsonLink.put("source", projectPersonLink.getSourceProject().getIndex());
				jsonLink.put("target", projectPersonLink.getTargetPerson().getIndex());
				
				jsonLinks.put(i, jsonLink);
			}catch(Exception e){e.printStackTrace();}
		}
		return jsonLinks; 
	}
	
	public JSONObject getJSONObjectGraph(){
		populateNodes();
		
		JSONObject graph = new JSONObject();
		JSONArray links = getJSONArrayLinks();
		JSONArray nodes = getJSONArrayNodes();
		
		try{		
			graph.put("links", links);
			graph.put("nodes", nodes);
			return graph;
		}
		catch(Exception e){e.printStackTrace();}
		return graph;
	}
	
	private static interface Node {
		public String getNodeName();
		public String getKey();
		public int getIndex();
		public void setIndex(int index);
		public int getType();
	}
	
	public static class ProjectNode implements Node{
		private String projectName;
		private int index;
		
		public ProjectNode(String projectName){
			this.projectName = projectName;
			index = -1;
		}
		
		public int getType(){
			return 2;
		}
		
		public String getNodeName(){
			return projectName;
		}
		
		public void setIndex(int index){
			this.index = index;
		}
		
		public int getIndex(){
			return index;
		}
			
		public String getKey(){
			return projectName;
		}
	}
	
	public static class PersonNode implements Node {
		private String personName;
		private int index;
		
		public PersonNode(String projectName){
			this.personName = projectName;
			index = -1;
		}
		
		public int getType(){
			return 1;
		}
		
		public String getNodeName(){
			return personName;
		}
		
		public void setIndex(int index){
			this.index = index;
		}
		
		public int getIndex(){
			return index;
		}
		
		public String getKey(){
			return personName;
		}
	}
	
	public static class ProjectPersonLink{
		private ProjectNode sourceProject;
		private PersonNode targetPerson;
						
		public ProjectPersonLink(ProjectNode sourceProject, PersonNode targetPerson){
			this.sourceProject = sourceProject;
			this.targetPerson = targetPerson;			
		}
		
		public ProjectNode getSourceProject(){
			return sourceProject;
		}
		
		public PersonNode getTargetPerson(){
			return targetPerson;
		}
					
		//since the graph is undirected, this will help us eliminate x->y and y->x redundant links
		private String getKey(){
			if(sourceProject.getKey().hashCode() > targetPerson.getKey().hashCode())
				return sourceProject.getKey() + targetPerson.getKey();
			else
				return targetPerson.getKey() + sourceProject.getKey();
		}
	}
}