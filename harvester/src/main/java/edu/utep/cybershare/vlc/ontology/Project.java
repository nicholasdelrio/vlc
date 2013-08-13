package edu.utep.cybershare.vlc.ontology;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

public class Project implements Concept {
	
	private Person hasPrincipalInvestigator;
	private Hashtable<String, Person> hasCoPrincipalInvestigator;
	private String hasTitle;
	private String hasAbstract;
	private GregorianCalendar hasStartDate_Funding;
	private GregorianCalendar hasEndDate_Funding;
	private ArrayList<String> hasParentCollectionIDs;
	private Hashtable<String,Project> hasRelatedProject;
	private Hashtable<String,Institution> hasHostingInstitution;
	
	public List<String> getParentCollectionIDs(){
		return hasParentCollectionIDs;
	}
	
	public void addParentCollectionID(String collectionID){
		hasParentCollectionIDs.add(collectionID);
	}
	
	public void addHasHostingInstitution(Institution institution){
		hasHostingInstitution.put(institution.getHasName(), institution);
	}
	
	public List<Institution> getHasHostingInstitution(){
		return new ArrayList<Institution>(hasHostingInstitution.values());
	}
	
	public List<Project> getHasRelatedProject() {
		return new ArrayList<Project>(hasRelatedProject.values());
	}

	public void addHasRelatedProject(Project hasRelatedProject) {
		this.hasRelatedProject.put(hasRelatedProject.getHasTitle(), hasRelatedProject);
	}

	public Project(){
		hasCoPrincipalInvestigator = new Hashtable<String, Person>();
		hasRelatedProject = new Hashtable<String, Project>();
		hasHostingInstitution = new Hashtable<String, Institution>();
		hasParentCollectionIDs = new ArrayList<String>();
	}
	
	public Person getHasPrincipalInvestigator() {
		return hasPrincipalInvestigator;
	}
	public void setHasPrincipalInvestigator(Person hasPrincipalInvestigator) {
		this.hasPrincipalInvestigator = hasPrincipalInvestigator;
	}
	
	public List<Person> getHasCoPrincipalInvestigator() {
		return new ArrayList<Person>(hasCoPrincipalInvestigator.values());
	}
		
	public void addHasCoPrincipalInvestigator(Person coPrincipalInvestigator) {
		this.hasCoPrincipalInvestigator.put(coPrincipalInvestigator.getProperName(), coPrincipalInvestigator);
	}
	public String getHasTitle() {
		if(hasTitle == null || hasTitle.isEmpty())
			return "Null Title";
		
		return hasTitle;
	}
	public void setHasTitle(String hasTitle) {
		this.hasTitle = hasTitle;
	}
	public String getHasAbstract() {
		if(hasAbstract == null || hasAbstract.isEmpty())
			return "Null Abstract";
		
		return hasAbstract;
	}
	public void setHasAbstract(String hasAbstract) {
		this.hasAbstract = hasAbstract;
	}
	public GregorianCalendar getHasStartDate_Funding() {
		return hasStartDate_Funding;
	}
	public void setHasStartDate_Funding(GregorianCalendar hasStartDate_Funding) {
		this.hasStartDate_Funding = hasStartDate_Funding;
	}
	public GregorianCalendar getHasEndDate_Funding() {
		return hasEndDate_Funding;
	}
	public void setHasEndDate_Funding(GregorianCalendar hasEndDate_Funding) {
		this.hasEndDate_Funding = hasEndDate_Funding;
	}
	
	@Override
	public String toString(){
		
		String projectString = "--- Project ---\n";
		
		projectString += "\t- hasTitle: " + hasTitle + "\n";
		projectString += "\t- hasAbstract: " + hasAbstract + "\n";
		projectString += "\t- hasPrincipalInvestigator: " + hasPrincipalInvestigator + "\n";
		
		for(Person aCOI : hasCoPrincipalInvestigator.values())
			projectString += "\t- hasCoPrincipalInvestigator: " + aCOI + "\n";
		
		projectString += "\t- hasStartDate_Funding: " + hasStartDate_Funding + "\n";
		projectString += "\t- hasEndDate_Funding: " + hasEndDate_Funding;
		
		return projectString;
	}

	public boolean isFullySpecified() {

		boolean isSpecified = true;
		for(Person person : this.getHasCoPrincipalInvestigator())
			isSpecified &= person.isFullySpecified();
					
		return isSpecified
				&& this.getHasAbstract() != null
				&& this.getHasPrincipalInvestigator().isFullySpecified()
				&& this.getHasEndDate_Funding() != null
				&& this.getHasStartDate_Funding() != null
				&& this.getHasTitle() != null;
	}	
}