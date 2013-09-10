package edu.utep.cybershare.vlc.model;
import edu.utep.cybershare.vlc.visitor.Visitor;
public class Organization extends Agent {

	public Organization(String name){
		super(name);
	}
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
