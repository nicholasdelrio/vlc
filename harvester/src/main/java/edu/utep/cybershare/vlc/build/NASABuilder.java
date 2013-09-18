package edu.utep.cybershare.vlc.build;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.apache.commons.lang.WordUtils;

import edu.utep.cybershare.vlc.model.Agency;
import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;

public class NASABuilder implements Builder {

	private static URI dbpediaResource;
	
	private ArrayList<Person> coPrincipalInvestigators;
	private Person principalInvestigator;
	private Institution hostingInstitution;
	private ArrayList<Institution> institutions;
	private ArrayList<URI> disciplines;
	private ArrayList<URI> subjects;
	private Agency fundingAgency;
	
	private ModelProduct product;
	
	public NASABuilder(ModelProduct product){
		try{dbpediaResource = new URI("http://dbpedia.org/resource/");}
		catch(Exception e){e.printStackTrace();}
		
		this.product = product;
		
		// clear the local resources
		reset();
	}
	
	private void reset(){
		coPrincipalInvestigators = new ArrayList<Person>();
		disciplines = new ArrayList<URI>();
		subjects = new ArrayList<URI>();
		institutions = new ArrayList<Institution>();
		principalInvestigator = null;
		hostingInstitution = null;
		fundingAgency = null;
	}
	
	public void buildAgency(String name){
		fundingAgency = product.getAgency(name);
		if(fundingAgency == null){
			fundingAgency = new Agency(name);
			product.addAgency(fundingAgency);
		}
	}
	
	public void buildDiscipline(String name){
		this.disciplines.add(getDBPediaResource(name));
	}	
	public void buildSubject(String name){
		this.subjects.add(getDBPediaResource(name));
	}
	
	public void buildInstitution(String name){
		Institution institution = product.getInstitution(name);
		if(institution == null){
			institution = new Institution(name);
			product.addInstitution(institution);
		}
		this.institutions.add(institution);
	}
	
	public void buildHostingInstitution(String name){
		Institution institution = product.getInstitution(name);
		if(institution == null){
			institution = new Institution(name);
			product.addInstitution(institution);
		}
		this.hostingInstitution = institution;
	}
	
	public void buildCoPrincipalInvestigator(String firstName, String lastName, String email){
		this.coPrincipalInvestigators.add(this.getPerson(firstName, lastName, email));
	}
	
	public void buildPrincipalInvestigator(String firstName, String lastName, String email){
		this.principalInvestigator = this.getPerson(firstName, lastName, email);
	}
	
	public void buildProject(
			String title,
			String summary,
			GregorianCalendar startDate,
			GregorianCalendar endDate,
			URL awardHomepage){
		
		Project project = product.getProject(title);
		
		if(project == null){
			project = new Project(title);
			project.setAbstractText(summary);
			project.setStartDate(startDate);
			project.setEndDate(endDate);
			project.setAwardHomepage(awardHomepage);
	
			product.addProject(project);
		}
		
		populateWithBuiltParts(project);
		
		reset();
	}	
	
	public ModelProduct getResult(){
		return product;
	}
	
	private URI getDBPediaResource(String name){
		name = name.toLowerCase();
		name = WordUtils.capitalize(name);		
		name = name.replaceAll(" ", "_");
		String resourceURIString = dbpediaResource + name;
		URI dbpediaResourceURI = product.getDBPediaResource(resourceURIString);
		
		if(dbpediaResourceURI == null)
			try{
				dbpediaResourceURI = new URI(resourceURIString);
				product.addDBPediaResource(dbpediaResourceURI);
			}
			catch(Exception e){e.printStackTrace();}
		
		return dbpediaResourceURI;
	}
	
	private void populateWithBuiltParts(Project project){
		
		fundingAgency.addFundedProject(project);

		if(this.principalInvestigator != null){
			project.setPrincipalInvestigator(this.principalInvestigator);
			project.addHostingInstitution(hostingInstitution);
		}
		
		if(coPrincipalInvestigators.size() != institutions.size())
			System.out.println("nooooooooooooo --------------");
		
		for(int i = 0; i < coPrincipalInvestigators.size(); i ++){
			Person aPerson = coPrincipalInvestigators.get(0);
			Institution anInstitution = institutions.get(0);
			aPerson.addAffiliatedInstitution(anInstitution);
		}
		for(URI subject : subjects)
			project.addSubject(subject);
	}
	
	private Person getPerson(String firstName, String lastName, String email){		
		Person person = new Person(firstName, lastName); //temporary object to calculate identifier
		person = product.getPerson(person.getIdentification());

		if(person == null){
			person = new Person(firstName, lastName);			
			product.addPerson(person);
		}
		
		for(URI discipline : disciplines)
			person.addDiscipline(discipline);
					
		if(email != null)
			person.setEmail(email);
		
		return person;
	}
}