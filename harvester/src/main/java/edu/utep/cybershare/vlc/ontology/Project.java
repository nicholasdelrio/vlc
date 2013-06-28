package edu.utep.cybershare.vlc.ontology;

import java.util.GregorianCalendar;
import java.util.List;

public class Project {
	
	private Person hasPrincipalInvestigator;
	private List<Person> hasCoPrincipalInvestigator;
	private String hasTitle;
	private String hasAbstract;
	private GregorianCalendar hasStartDate_Funding;
	private GregorianCalendar hasEndDate_Funding;
	
	public Person getHasPrincipalInvestigator() {
		return hasPrincipalInvestigator;
	}
	public void setHasPrincipalInvestigator(Person hasPrincipalInvestigator) {
		this.hasPrincipalInvestigator = hasPrincipalInvestigator;
	}
	public List<Person> getHasCoPrincipalInvestigator() {
		return hasCoPrincipalInvestigator;
	}
	
	public void setHasCoPrincipalInvestigator(List<Person> coPrincipalInvestigators) {
		this.hasCoPrincipalInvestigator = coPrincipalInvestigators;
	}
	
	public void addHasCoPrincipalInvestigator(Person coPrincipalInvestigator) {
		this.hasCoPrincipalInvestigator.add(coPrincipalInvestigator);
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
}
