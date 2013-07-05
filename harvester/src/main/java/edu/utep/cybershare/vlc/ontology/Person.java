package edu.utep.cybershare.vlc.ontology;

import java.util.ArrayList;
import java.util.List;

public class Person {
	
	private List<Institution> affiliatedWithInstitution;
	private	List<Discipline> hasDiscipline;
	private String hasFirstName;
	private String hasLastName;
	
	public Person(){
		affiliatedWithInstitution = new ArrayList<Institution>();
		hasDiscipline = new ArrayList<Discipline>();
	}
	
	public List<Institution> getAffiliatedWithInstitution() {
		return affiliatedWithInstitution;
	}
	public void addAffiliatedWithInstitution(Institution institution) {
		this.affiliatedWithInstitution.add(institution);
	}
	public List<Discipline> getHasDiscipline() {
		return hasDiscipline;
	}
	public void addHasDiscipline(Discipline discipline) {
		this.hasDiscipline.add(discipline);
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
	
	@Override
	public String toString(){
		String personString = "--- Person ---\n";
		personString += "\t- hasFirstName: " + hasFirstName + "\n";
		personString += "\t- hasLastName: " + hasLastName + "\n";
		
		for(Institution anInstitution : affiliatedWithInstitution)
			personString += "\t- affiliatedWithInstitution: " + anInstitution + "\n";
		
		for(Discipline aDiscipline : hasDiscipline)
			personString += "\t- hasDiscipline: " + aDiscipline + "\n";
		
		return personString.substring(0, personString.length() - 1);
	}
}