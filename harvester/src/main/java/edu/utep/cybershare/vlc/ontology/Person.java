package edu.utep.cybershare.vlc.ontology;

import java.util.List;

public class Person {
	
	private List<Institution> affiliatedWithInstitution;
	private	List<Discipline> hasDiscipline;
	private String hasFirstName;
	private String hasLastName;
	
	public List<Institution> getAffiliatedWithInstitution() {
		return affiliatedWithInstitution;
	}
	public void setAffiliatedWithInstitution(
			List<Institution> affiliatedWithInstitution) {
		this.affiliatedWithInstitution = affiliatedWithInstitution;
	}
	public List<Discipline> getHasDiscipline() {
		return hasDiscipline;
	}
	public void setHasDiscipline(List<Discipline> hasDiscipline) {
		this.hasDiscipline = hasDiscipline;
	}
	public String getHasFirstName() {
		return hasFirstName;
	}
	public void setHasFirstName(String hasFirstName) {
		this.hasFirstName = hasFirstName;
	}
	public String getHasLastName() {
		return hasLastName;
	}
	public void setHasLastName(String hasLastName) {
		this.hasLastName = hasLastName;
	}
	
	
}
