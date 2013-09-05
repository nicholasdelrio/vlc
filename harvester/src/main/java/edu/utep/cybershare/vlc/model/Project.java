package edu.utep.cybershare.vlc.model;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class Project extends Collection {

	private String grantIdentification;
	private Person principalInvestigator;
	private HashMap<String, Person> coPrincipalInvestigators;	
	private String title;
	private String summary;
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	private HashMap<String, Institution> hostingInstitutions;
	private int awardAmount;

	// VLC specific properties
	private HashMap<String, Project> relatedProjects;
	private HashMap<String, Project> influencedProjects;
	
	public String getGrantIdentification() {
		return grantIdentification;
	}
	public void setGrantIdentification(String grantIdentification) {
		this.grantIdentification = grantIdentification;
	}
	public Person getPrincipalInvestigator() {
		return principalInvestigator;
	}
	public void setPrincipalInvestigator(Person principalInvestigator) {
		this.principalInvestigator = principalInvestigator;
	}
	public List<Person> getCoPrincipalInvestigators() {
		return new ArrayList<Person>(coPrincipalInvestigators.values());
	}
	public void setCoPrincipalInvestigators(HashMap<String, Person> coPrincipalInvestigators) {
		this.coPrincipalInvestigators = coPrincipalInvestigators;
	}
	public void addCoPrincipalInvestigator(Person person){
		this.coPrincipalInvestigators.put(person.getProperName(), person);
	}
	public String getTitle() {
		if(title == null || title.isEmpty())
			return "Null Title";
		
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		if(summary == null || summary.isEmpty())
			return "Null Summary";
		
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public GregorianCalendar getStartDate() {
		return startDate;
	}
	public void setStartDate(GregorianCalendar startDate) {
		this.startDate = startDate;
	}
	public GregorianCalendar getEndDate() {
		return endDate;
	}
	public void setEndDate(GregorianCalendar endDate) {
		this.endDate = endDate;
	}
	public List<Institution> getHostingInstitutions() {
		return new ArrayList<Institution>(hostingInstitutions.values());
	}
	public void setHostingInstitutions(HashMap<String, Institution> hostingInstitutions) {
		this.hostingInstitutions = hostingInstitutions;
	}
	public void addHostingInstitution(Institution institution){
		this.hostingInstitutions.put(institution.getHasName(), institution);
	}
	public int getAwardAmount() {
		return awardAmount;
	}
	public void setAwardAmount(int awardAmount) {
		this.awardAmount = awardAmount;
	}
	public List<Project> getRelatedProjects() {
		return new ArrayList<Project>(relatedProjects.values());
	}
	public void setRelatedProjects(HashMap<String, Project> relatedProjects) {
		this.relatedProjects = relatedProjects;
	}
	public void addRelatedProject(Project project){
		this.relatedProjects.put(project.getTitle(), project);
	}
	public List<Project> getInfluencedProjects() {
		return new ArrayList<Project>(influencedProjects.values());
	}
	public void setInfluencedProjects(HashMap<String, Project> influencesProjects) {
		this.influencedProjects = influencesProjects;
	}
	public void addInfluencedProject(Project project){
		this.influencedProjects.put(project.getTitle(), project);
	}
}