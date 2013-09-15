package edu.utep.cybershare.vlc.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class Resource extends Element {
	
	private HashMap<String, Resource> relatedResources;
	private HashMap<String, Resource> influencedResources;
	private HashMap<String, URI> subjects;

	public Resource(String name){
		super(name);
		relatedResources = new HashMap<String, Resource>();
		influencedResources = new HashMap<String, Resource>();
		subjects = new HashMap<String, URI>();
	}
	
	public boolean isSet_relatedResources(){return this.getRelatedResources().size() > 0;}
	public boolean isSet_influencedResources(){return this.getInfluencedResources().size() > 0;}
	public boolean isSet_subjects(){return this.getSubjects().size() > 0;}
	
	public List<Resource> getRelatedResources() {
		return new ArrayList<Resource>(relatedResources.values());
	}
	public void addRelatedResource(Resource resource){
		this.relatedResources.put(resource.getIdentification(), resource);
	}
	public List<Resource> getInfluencedResources() {
		return new ArrayList<Resource>(influencedResources.values());
	}
	public void addInfluencedResource(Resource project){
		this.influencedResources.put(project.getIdentification(), project);
	}
	public List<URI> getSubjects() {
		return new ArrayList<URI>(subjects.values());
	}
	public void addSubject(URI subject){
		this.subjects.put(subject.toASCIIString(), subject);
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
