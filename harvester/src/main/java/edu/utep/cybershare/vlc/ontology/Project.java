package edu.utep.cybershare.vlc.ontology;

import java.util.Date;
import java.util.List;

public class Project {
	
	private Person hasPrincipalInvestigator;
	private List<Person> hasCoPrincipalInvestigator;
	private String hasTitle;
	private String hasAbstract;
	private Date hasStartDate_Funding;
	private Date hasEndDate_Funding;
	
	public Person getHasPrincipalInvestigator() {
		return hasPrincipalInvestigator;
	}
	public void setHasPrincipalInvestigator(Person hasPrincipalInvestigator) {
		this.hasPrincipalInvestigator = hasPrincipalInvestigator;
	}
	public List<Person> getHasCoPrincipalInvestigator() {
		return hasCoPrincipalInvestigator;
	}
	public void setHasCoPrincipalInvestigator(
			List<Person> hasCoPrincipalInvestigator) {
		this.hasCoPrincipalInvestigator = hasCoPrincipalInvestigator;
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
	public Date getHasStartDate_Funding() {
		return hasStartDate_Funding;
	}
	public void setHasStartDate_Funding(Date hasStartDate_Funding) {
		this.hasStartDate_Funding = hasStartDate_Funding;
	}
	public Date getHasEndDate_Funding() {
		return hasEndDate_Funding;
	}
	public void setHasEndDate_Funding(Date hasEndDate_Funding) {
		this.hasEndDate_Funding = hasEndDate_Funding;
	}
}
