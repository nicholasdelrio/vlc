package edu.utep.cybershare.vlc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Agency extends Organization{
	
	private HashMap<String, Project> fundedProjects;
	
	public Agency(String agencyName){
		super(agencyName);
		fundedProjects = new HashMap<String, Project>();
	}
	public boolean isSet_FundedProjects(){
		return !fundedProjects.isEmpty();
	}
	public void addFundedProject(Project fundedProject){
		this.fundedProjects.put(fundedProject.getIdentification(), fundedProject);
	}
	
	public List<Project> getFundedProjects(){
		return new ArrayList<Project>(fundedProjects.values());
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
