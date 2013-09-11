package edu.utep.cybershare.vlc.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.utep.cybershare.vlc.visitor.Visitor;

public class Agent extends Element{

	private HashMap<String, Agent> influencedAgents;
	private HashMap<String, Agent> relatedAgents;
	private HashMap<String, Resource> contributedResources;
	private	HashMap<String, URI> disciplines;
	
	public Agent(String name){
		super(name);
		influencedAgents = new HashMap<String, Agent>();
		contributedResources = new HashMap<String, Resource>();
		relatedAgents = new HashMap<String, Agent>();
		disciplines = new HashMap<String, URI>();
	}

	public boolean isSet_influencedAgents(){return this.getInfluencedAgents().size() > 0;}
	public boolean isSet_relatedAgenets(){return this.getRelatedAgents().size() > 0;}
	public boolean isSet_contributedResources(){return this.getContributedResources().size() > 0;}
	public boolean isSet_disciplines(){return this.getDisciplines().size() > 0;}
	
	public List<Agent> getInfluencedAgents() {
		return new ArrayList<Agent>(influencedAgents.values());
	}

	public void setInfluencedAgents(HashMap<String, Agent> influencedAgents) {
		this.influencedAgents = influencedAgents;
	}
	
	public void addInfluencedAgent(Agent agent){
		this.influencedAgents.put(agent.getIdentification(), agent);
	}

	public List<Agent> getRelatedAgents() {
		return new ArrayList<Agent>(relatedAgents.values());
	}

	public void setRelatedAgents(HashMap<String, Agent> relatedAgents) {
		this.relatedAgents = relatedAgents;
	}

	public void addRelatedAgent(Agent agent){
		this.relatedAgents.put(agent.getIdentification(), agent);
	}
	
	public List<Resource> getContributedResources() {
		return new ArrayList<Resource>(contributedResources.values());
	}

	public void setContributedResources(HashMap<String, Resource> contributedResources) {
		this.contributedResources = contributedResources;
	}
	
	public void addContributedResource(Resource resource){
		this.contributedResources.put(resource.getIdentification(), resource);
	}
	
	public void addContributedResources(Resource resource){
		this.contributedResources.put(resource.getIdentification(), resource);
	}
	
	public List<URI> getDisciplines() {
		return new ArrayList<URI>(disciplines.values());
	}

	public void setDisciplines(HashMap<String, URI> disciplines) {
		this.disciplines = disciplines;
	}
	
	public void addDiscipline(URI uri){
		this.disciplines.put(uri.toASCIIString(), uri);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}