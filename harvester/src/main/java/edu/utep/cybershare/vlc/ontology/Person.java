package edu.utep.cybershare.vlc.ontology;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

public class Person implements Concept {
	
	private Hashtable<String, Institution> affiliatedWithInstitution;
	private	Hashtable<String, Discipline> hasDiscipline;
	private String hasFirstName;
	private String hasLastName;
	
	public Person(){
		affiliatedWithInstitution = new Hashtable<String, Institution>();
		hasDiscipline = new Hashtable<String, Discipline>();
	}
	
	public List<Institution> getAffiliatedWithInstitution() {
		return new ArrayList<Institution>(affiliatedWithInstitution.values());
	}
	public void addAffiliatedWithInstitution(Institution institution) {
		this.affiliatedWithInstitution.put(institution.getHasName(), institution);
	}
	public Collection<Discipline> getHasDiscipline() {
		return hasDiscipline.values();
	}
	public void addHasDiscipline(Discipline discipline) {
		this.hasDiscipline.put(discipline.getHasName(), discipline);
	}
	public String getHasFirstName() {
		if(hasFirstName == null || hasFirstName.isEmpty())
			return "Null First Name";
		return hasFirstName;
	}
	public void setHasFirstName(String hasFirstName) {
		this.hasFirstName = hasFirstName;
	}
	public String getHasLastName() {
		if(hasLastName == null || hasLastName.isEmpty())
			return "Null Last Name";
		
		return hasLastName;
	}
	public void setHasLastName(String hasLastName) {
		this.hasLastName = hasLastName;
	}
	
	public String getProperName(){
		return this.hasLastName + ", " + this.getHasFirstName();
	}
	
	@Override
	public String toString(){
		String personString = "--- Person ---\n";
		personString += "\t- hasFirstName: " + hasFirstName + "\n";
		personString += "\t- hasLastName: " + hasLastName + "\n";
		
		for(Institution anInstitution : affiliatedWithInstitution.values())
			personString += "\t- affiliatedWithInstitution: " + anInstitution + "\n";
		
		for(Discipline aDiscipline : hasDiscipline.values())
			personString += "\t- hasDiscipline: " + aDiscipline + "\n";
		
		return personString.substring(0, personString.length() - 1);
	}

	public boolean isFullySpecified() {
		boolean isSpecified = true;
		for(Institution institution : this.getAffiliatedWithInstitution())
			isSpecified &= institution.isFullySpecified();
		
		for(Discipline discipline : this.getHasDiscipline())
			isSpecified &= discipline.isFullySpecified();
		
		return isSpecified
				&& this.getHasFirstName() != null
				&& this.getHasLastName() != null;
	}
}
