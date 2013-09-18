package edu.utep.cybershare.vlc.model;


public interface Visitor {
	public void visit(Project project);
	public void visit(Institution institution);
	public void visit(Person person);
	public void visit(Organization organization);
	public void visit(Agent agent);
	public void visit(Resource resource);
	public void visit(Agency agency);
}
