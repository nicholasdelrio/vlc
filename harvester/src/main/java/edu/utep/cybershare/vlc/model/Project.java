package edu.utep.cybershare.vlc.model;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class Project extends Resource {

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
	private URI subject;

	// VLC specific properties
	private HashMap<String, Resource> relatedWorks;
	private HashMap<String, Resource> results;
	
	public Project(String title) {
		super(title);
		this.title = title;
		coPrincipalInvestigators = new HashMap<String, Person>();
		hostingInstitutions = new HashMap<String, Institution>(); 
	}
	
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
		this.hostingInstitutions.put(institution.getIdentification(), institution);
	}
	public int getAwardAmount() {
		return awardAmount;
	}
	public void setAwardAmount(int awardAmount) {
		this.awardAmount = awardAmount;
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

	public URI getSubject() {
		return subject;
	}

	public void setSubject(URI subject) {
		this.subject = subject;
	}
}