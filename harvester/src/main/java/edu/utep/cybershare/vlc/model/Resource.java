package edu.utep.cybershare.vlc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import edu.utep.cybershare.vlc.visitor.Visitor;
public class Resource extends Element {
	
	private HashMap<String, Resource> relatedResources;
	private HashMap<String, Resource> influencedResources;
	
	public Resource(String name){
		super(name);
		relatedResources = new HashMap<String, Resource>();
		influencedResources = new HashMap<String, Resource>();
	}
	public List<Resource> getRelatedResources() {
		return new ArrayList<Resource>(relatedResources.values());
	}
	public void setRelatedResources(HashMap<String, Resource> relatedResources) {
		this.relatedResources = relatedResources;
	}
	public void addRelatedResource(Resource resource){
		this.relatedResources.put(resource.getIdentification(), resource);
	}
	public List<Resource> getInfluencedResources() {
		return new ArrayList<Resource>(influencedResources.values());
	}
	public void setInfluencedResources(HashMap<String, Resource> influencedResources) {
		this.influencedResources = influencedResources;
	}
	public void addInfluencedResource(Resource project){
		this.influencedResources.put(project.getIdentification(), project);
	}
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
