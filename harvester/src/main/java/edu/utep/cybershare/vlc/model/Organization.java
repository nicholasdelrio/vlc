package edu.utep.cybershare.vlc.model;

public class Organization extends Agent {

	public Organization(String key){
		super(key);
	}
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
