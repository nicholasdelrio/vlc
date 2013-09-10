package edu.utep.cybershare.vlc.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Person extends Agent {
	
	private HashMap<String, Institution> affiliatedInstitutions;
	private HashMap<String, Organization> affiliatedOrganizations;
	
	private String firstName;
	private String lastName;
	private String email;
		
	public Person(String firstName, String lastName){
		super(getProperName(lastName, firstName));
		
		this.lastName = lastName;
		this.firstName = firstName;
		
		affiliatedInstitutions = new HashMap<String, Institution>();
		affiliatedOrganizations = new HashMap<String, Organization>();
	}
	
	private static String getProperName(String lastName, String firstName){
		String properName = null;
		if(lastName != null && firstName != null)
			properName = lastName + ", " + firstName;
		
		return properName;
	}
	
	public List<Institution> getAffiliatedInstitutions() {
		return new ArrayList<Institution>(affiliatedInstitutions.values());
	}

	public void setAffiliatedInstitutions(
			HashMap<String, Institution> affiliatedInstitutions) {
		this.affiliatedInstitutions = affiliatedInstitutions;
	}
	
	public void addAffiliatedInstitution(Institution institution){
		this.affiliatedInstitutions.put(institution.getIdentification(), institution);
	}
	
	public String getProperName(){
		return getProperName(lastName, firstName);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Organization> getAffiliatedOrganizations() {
		return new ArrayList<Organization>(affiliatedOrganizations.values());
	}

	public void setAffiliatedOrganizations(HashMap<String, Organization> affiliatedOrganizations) {
		this.affiliatedOrganizations = affiliatedOrganizations;
	}
	
	public void addAffiliatedOrganization(Organization organization){
		this.affiliatedOrganizations.put(organization.getIdentification(), organization);
	}
}
