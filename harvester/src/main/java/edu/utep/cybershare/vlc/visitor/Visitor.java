package edu.utep.cybershare.vlc.visitor;

import edu.utep.cybershare.vlc.model.Agent;
import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.model.Organization;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;
import edu.utep.cybershare.vlc.model.Resource;

public interface Visitor {
	public void visit(Project project);
	public void visit(Institution institution);
	public void visit(Person person);
	public void visit(Organization organization);
	public void visit(Agent agent);
	public void visit(Resource resource);
}
