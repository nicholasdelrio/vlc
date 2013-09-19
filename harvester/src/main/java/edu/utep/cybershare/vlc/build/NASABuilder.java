package edu.utep.cybershare.vlc.build;

import java.net.URI;

import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;

public class NASABuilder extends Builder {

	public NASABuilder(ModelProduct aProduct) {
		super(aProduct);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void populateWithBuiltParts(Project project){
		
		fundingAgency.addFundedProject(project);

		if(this.principalInvestigator != null){
			project.setPrincipalInvestigator(this.principalInvestigator);
			project.setHostingInstitution(hostingInstitution);
		}
		
		for(int i = 0; i < coPrincipalInvestigators.size(); i ++){
			Person aPerson = coPrincipalInvestigators.get(i);
			project.addCoPrincipalInvestigator(aPerson);
			
			// add affiliated institution to co pi
			Institution anInstitution = institutions.get(i);
			aPerson.addAffiliatedInstitution(anInstitution);
		}
		for(URI subject : subjects)
			project.addSubject(subject);
	}
}
