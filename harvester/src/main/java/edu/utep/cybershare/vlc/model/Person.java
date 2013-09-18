package edu.utep.cybershare.vlc.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.utep.cybershare.vlc.util.StringManipulation;
public class Person extends Agent {
	
	private HashMap<String, Institution> affiliatedInstitutions;
	private HashMap<String, Organization> affiliatedOrganizations;
	
	private String firstName;
	private String lastName;
	private String email;
		
	public Person(String firstName, String lastName){
		super(getIdentification(firstName, lastName));
		
		this.lastName = lastName;
		this.firstName = firstName;
		
		affiliatedInstitutions = new HashMap<String, Institution>();
		affiliatedOrganizations = new HashMap<String, Organization>();
	}
	
	public boolean isSet_affiliatedInstitutions(){return this.getAffiliatedInstitutions().size() > 0;}
	public boolean isSet_affiliatedOrganizations(){return this.getAffiliatedOrganizations().size() > 0;}
	public boolean isSet_firstName(){return this.getFirstName() != null;}
	public boolean isSet_lastName(){return this.getLastName() != null;}
	public boolean isSet_email(){return this.getEmail() != null;}
	
	public static String getIdentification(String firstName, String lastName){
		String properName = null;
		if(lastName != null && firstName != null)
			properName = lastName + ", " + firstName;
		
		return properName;
	}
	
	public List<Institution> getAffiliatedInstitutions() {
		return new ArrayList<Institution>(affiliatedInstitutions.values());
	}

	public void addAffiliatedInstitution(Institution institution){
		this.affiliatedInstitutions.put(institution.getIdentification(), institution);
	}
	
	public String getProperName(){
		return getIdentification(firstName, lastName);
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

	public String getEmailSha1Sum(){
		String shaSum = null;
		if(this.isSet_email()){
			try{shaSum = StringManipulation.SHAsum(this.getEmail().getBytes("UTF-8"));}
			catch(Exception e){e.printStackTrace();}
		}
		return shaSum;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Organization> getAffiliatedOrganizations() {
		return new ArrayList<Organization>(affiliatedOrganizations.values());
	}
	
	public void addAffiliatedOrganization(Organization organization){
		this.affiliatedOrganizations.put(organization.getIdentification(), organization);
	}
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}