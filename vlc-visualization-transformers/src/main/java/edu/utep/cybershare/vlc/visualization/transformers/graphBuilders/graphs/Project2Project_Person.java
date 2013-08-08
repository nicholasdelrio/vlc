package edu.utep.cybershare.vlc.visualization.transformers.graphBuilders.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Project2Project_Person {
	
	private JSONObject graph;
	private JSONArray links;
	
	private ArrayList<ProjectNode> nodes;
	
	private Hashtable<String,ProjectLink> linksMap;
	private Hashtable<String,Integer> nodesMap;

	private int nodeCounter;
	
	public Project2Project_Person(){
		nodeCounter = 0;
		linksMap = new Hashtable<String,ProjectLink>();
		nodesMap = new Hashtable<String,Integer>();
	}
	
	
	public void addLink(ProjectLink link){
		String key = link.getKey();
		
		if(linksMap.get(key) == null)
			linksMap.put(key, link);
	}
	
	
	private void populateNodes(){
		List<ProjectLink> links = new ArrayList<ProjectLink>(linksMap.values());
		nodes = new ArrayList<ProjectNode>();
		
		ProjectLink aLink;
		
		int source;
		int target;
		
		for(int i = 0; i < links.size(); i ++){
			aLink = links.get(i);
			
			source = getIndex(aLink.getSourceProject());
			target = getIndex(aLink.getTargetProject());
		
			aLink.setSource(source);
			aLink.setTarget(target);
		}
	}
	
	private int getIndex(ProjectNode node){
		Integer index = nodesMap.get(node.getKey());
		if(index == null){
			nodesMap.put(node.getKey(), new Integer(nodeCounter ++));
			nodes.add(node);
		}
		
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
	
	private static class ProjectNode{
		private String projectName;
		
		private ProjectNode(String projectName){
			this.projectName = projectName;
		}
		
		private String getKey(){
			return projectName;
		}
	}
	
	private static class ProjectLink{
		private ProjectNode sourceProject;
		private ProjectNode targetProject;
		
		private int source;
		private int target;
		
		private ArrayList<String> people;
		
		private ProjectLink(ProjectNode sourceProject, ProjectNode targetProject){
			this.sourceProject = sourceProject;
			this.targetProject = targetProject;
			
			people = new ArrayList<String>();
		}
		
		private void addPerson(String personName){
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
		
		public void setSource(int source){
			this.source = source;
		}
		
		public void setTarget(int target){
			this.target = target;
		}
		
		public int getSource(){return source;}
		
		public int getTarget(){return target;}
		
		//since the graph is undirected, this will help us eliminate x->y and y->x redundant links
		private String getKey(){
			if(sourceProject.getKey().hashCode() > targetProject.getKey().hashCode())
				return sourceProject.getKey() + targetProject.getKey();
			else
				return targetProject.getKey() + sourceProject.getKey();
		}
	}
}
