package edu.utep.cybershare.vlc.model;

import java.net.URL;
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
	private URL awardHomepage;

	// VLC specific properties
	private HashMap<String, Project> relatedProjects;
	private HashMap<String, Project> influencedProjects;
	private HashMap<String, Resource> relatedWorks;
	private HashMap<String, Resource> results;
	
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
	public URL getAwardHomepage() {
		return awardHomepage;
	}
	public void setAwardHomepage(URL awardHomepage) {
		this.awardHomepage = awardHomepage;
	}
	public List<Resource> getRelatedWorks() {
		return new ArrayList<Resource>(relatedWorks.values());
	}
	public void setRelatedWorks(HashMap<String, Resource> relatedWorks) {
		this.relatedWorks = relatedWorks;
	}
	public void addRelatedWork(Resource resource){
		this.relatedWorks.put(resource.getIdentification(), resource);
	}
	public List<Resource> getResults() {
		return new ArrayList<Resource>(results.values());
	}
	public void setResults(HashMap<String, Resource> results) {
		this.results = results;
	}
	public void addResult(Resource resource){
		this.results.put(resource.getIdentification(), resource);
	}
}