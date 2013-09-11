package edu.utep.cybershare.vlc.builder;


import java.net.URL;
import java.util.GregorianCalendar;

public interface Builder {

	public void buildDiscipline(String name);
	public void buildSubject(String name);
	public void buildInstitution(String name, String city, String state, String zip, String street);
	public void buildCoPrincipalInvestigator(String firstName, String lastName);
	public void buildPrincipalInvestigator(String firstName, String lastName);
	public void buildProject(
			String title,
			String summary,
			GregorianCalendar startDate,
			GregorianCalendar endDate,
			int awardAmount,
			String grantIdentification,
			URL awardHomepage);
	
	public ModelProduct getResult();
}
