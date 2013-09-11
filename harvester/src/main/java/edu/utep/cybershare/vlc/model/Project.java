package edu.utep.cybershare.vlc.model;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import edu.utep.cybershare.vlc.visitor.Visitor;
public class Project extends Resource {

	private String grantIdentification;
	private Person principalInvestigator;
	private HashMap<String, Person> coPrincipalInvestigators;	
	private String title;
	private String abstractText;
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	private HashMap<String, Institution> hostingInstitutions;
	private int awardAmount;
	private URL awardHomepage;
	private URL projectHomePage;

	// VLC specific properties
	private HashMap<String, Resource> relatedWorks;
	private HashMap<String, Resource> results;
	
	public Project(String title) {
		super(title);
		this.title = title;
		
		coPrincipalInvestigators = new HashMap<String, Person>();
		hostingInstitutions = new HashMap<String, Institution>(); 
		relatedWorks = new HashMap<String, Resource>();
		results = new HashMap<String, Resource>();
	}
	
	public boolean isSet_grantIdentification(){return this.getGrantIdentification() != null;}
	public boolean isSet_principalInvestigator(){return this.getPrincipalInvestigator() != null;}
	public boolean isSet_coPrincipalInvestigators(){return this.getCoPrincipalInvestigators().size() > 0;}
	public boolean isSet_title(){return this.getTitle() != null;}
	public boolean isSet_abstractText(){return this.getAbstractText() != null;}
	public boolean isSet_startDate(){return this.getStartDate() != null;}
	public boolean isSet_endDate(){return this.getEndDate() != null;}
	public boolean isSet_hostingInstitutions(){return this.getHostingInstitutions().size() > 0;}
	public boolean isSet_awardAmount(){return this.getAwardAmount() != 0;}
	public boolean isSet_awardHomepage(){return this.awardHomepage != null;}
	public boolean isSet_projectHomePage(){return this.projectHomePage != null;}
	public boolean isSet_relatedWorks(){return this.getRelatedWorks().size() > 0;}
	public boolean isSet_results(){return this.getResults().size() > 0;}
	
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
	public String getAbstractText() {
		if(abstractText == null || abstractText.isEmpty())
			return "Null Abstract";
		
		return abstractText;
	}
	public void setAbstractText(String summary) {
		this.abstractText = summary;
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
	public URL getProjectHomePage() {
		return projectHomePage;
	}

	public void setProjectHomePage(URL projectHomePage) {
		this.projectHomePage = projectHomePage;
	}
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}