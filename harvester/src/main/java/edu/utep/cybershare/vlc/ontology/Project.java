package edu.utep.cybershare.vlc.ontology;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Hashtable;

public class Project implements Concept {
	
	private Person hasPrincipalInvestigator;
	private Hashtable<String, Person> hasCoPrincipalInvestigator;
	private String hasTitle;
	private String hasAbstract;
	private GregorianCalendar hasStartDate_Funding;
	private GregorianCalendar hasEndDate_Funding;
	
	public Project(){
		hasCoPrincipalInvestigator = new Hashtable<String, Person>();
	}
	
	public Person getHasPrincipalInvestigator() {
		return hasPrincipalInvestigator;
	}
	public void setHasPrincipalInvestigator(Person hasPrincipalInvestigator) {
		this.hasPrincipalInvestigator = hasPrincipalInvestigator;
	}
	public Collection<Person> getHasCoPrincipalInvestigator() {
		return hasCoPrincipalInvestigator.values();
	}
		
	public void addHasCoPrincipalInvestigator(Person coPrincipalInvestigator) {
		this.hasCoPrincipalInvestigator.put(coPrincipalInvestigator.getProperName(), coPrincipalInvestigator);
	}
	public String getHasTitle() {
		return hasTitle;
	}
	public void setHasTitle(String hasTitle) {
		this.hasTitle = hasTitle;
	}
	public String getHasAbstract() {
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