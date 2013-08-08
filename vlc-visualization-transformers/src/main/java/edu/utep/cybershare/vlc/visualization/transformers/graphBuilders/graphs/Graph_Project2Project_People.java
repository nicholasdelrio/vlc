package edu.utep.cybershare.vlc.visualization.transformers.graphBuilders.graphs;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Graph_Project2Project_People {
		
	private ArrayList<ProjectNode> nodes;
	private Hashtable<String,Integer> nodesMap;
	private int nodeCounter;
	
	private Hashtable<String,ProjectLink> linksMap;
	
	public Graph_Project2Project_People(){
		nodeCounter = 0;
		linksMap = new Hashtable<String,ProjectLink>();
		nodesMap = new Hashtable<String,Integer>();
	}
		
	public void addLink(ProjectLink link){
		String key = link.getKey();
		
		ProjectLink existingLink = linksMap.get(key);
		if(existingLink == null)
			linksMap.put(key, link);
		else
			for(String personName : link.getPeople())
				existingLink.addPerson(personName);
			
	}
	
	private void populateNodes(){
		List<ProjectLink> links = new ArrayList<ProjectLink>(linksMap.values());
		nodes = new ArrayList<ProjectNode>();
		
		ProjectLink aLink;
		ProjectNode sourceProject;
		ProjectNode targetProject;
				
		for(int i = 0; i < links.size(); i ++){
			aLink = links.get(i);
			sourceProject = aLink.getSourceProject();
			targetProject = aLink.getTargetProject();
			
			sourceProject.setIndex(getIndex(aLink.getSourceProject()));
			targetProject.setIndex(getIndex(aLink.getTargetProject()));		
		}
	}
	
	private int getIndex(ProjectNode node){
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
		ProjectNode node;
		for(int i = 0; i < nodes.size(); i ++){
			node = nodes.get(i);
			
			jsonNode = new JSONObject();
			try{
				jsonNode.put("project", node.getProjectName());
				jsonNodes.put(i, jsonNode);
			}catch(Exception e){e.printStackTrace();}
		}
		return jsonNodes;
	}
	
	private JSONArray getJSONArrayLinks(){
		ArrayList<ProjectLink> links = new ArrayList<ProjectLink>(linksMap.values());
		ProjectLink projectLink;
		JSONArray jsonLinks = new JSONArray();
		JSONObject jsonLink;
		for(int i = 0; i < links.size(); i ++){
			projectLink = links.get(i);
			jsonLink = new JSONObject();
			try{
				jsonLink.put("source", projectLink.getSourceProject().getIndex());
				jsonLink.put("target", projectLink.getTargetProject().getIndex());
				jsonLink.put("people", new JSONArray(projectLink.getPeople()));
				
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
	
	public static class ProjectNode{
		private String projectName;
		private int index;
		
		public ProjectNode(String projectName){
			this.projectName = projectName;
			index = -1;
		}
		
		private String getProjectName(){
			return projectName;
		}
		
		private void setIndex(int index){
			this.index = index;
		}
		
		private int getIndex(){
			return index;
		}
		
		private String getKey(){
			return projectName;
		}
	}
	
	public static class ProjectLink{
		private ProjectNode sourceProject;
		private ProjectNode targetProject;
				
		private ArrayList<String> people;
		
		public ProjectLink(ProjectNode sourceProject, ProjectNode targetProject){
			this.sourceProject = sourceProject;
			this.targetProject = targetProject;
			
			people = new ArrayList<String>();
		}
		
		public void addPerson(String personName){
			if(!people.contains(personName))
				people.add(personName);
		}
		
		private List<String> getPeople(){
			return people;
		}
		
		public ProjectNode getSourceProject(){
			return sourceProject;
		}
		
		public ProjectNode getTargetProject(){
			return targetProject;
		}
					
		//since the graph is undirected, this will help us eliminate x->y and y->x redundant links
		private String getKey(){
			if(sourceProject.getKey().hashCode() > targetProject.getKey().hashCode())
				return sourceProject.getKey() + targetProject.getKey();
			else
				return targetProject.getKey() + sourceProject.getKey();
		}
	}
}
