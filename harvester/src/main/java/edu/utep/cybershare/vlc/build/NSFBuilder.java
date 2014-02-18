package edu.utep.cybershare.vlc.build;

import java.net.URI;

import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;

public class NSFBuilder extends Builder {

	public NSFBuilder(ModelProduct aProduct) {
		super(aProduct);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void populateWithBuiltParts(Project project) {

		fundingAgency.addFundedProject(project);
		
		if(this.principalInvestigator != null){
			project.setPrincipalInvestigator(this.principalInvestigator);
			project.setHostingInstitution(institutions.get(0)); //should be only one institution per award xml document for NSF
			
			this.principalInvestigator.addAffiliatedInstitution(institutions.get(0));
		}
		
		for(Person coPrincipalInvestigator : this.coPrincipalInvestigators)
			project.addCoPrincipalInvestigator(coPrincipalInvestigator);
		
		for(URI subject : subjects)
			project.addSubject(subject);
	}
}