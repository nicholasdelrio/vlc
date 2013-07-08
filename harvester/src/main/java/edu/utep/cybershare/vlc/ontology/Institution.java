package edu.utep.cybershare.vlc.ontology;

import java.awt.Point;

public class Institution implements Concept {
	
	private String hasName;
	private Point hasPoint;
	
	public Institution(){
		hasPoint = new Point();
		hasPoint.setLocation(0, 0);
	}
	
	public String getHasName() {
		return hasName;
	}
	public void setHasName(String hasName) {
		this.hasName = hasName;
	}
	public Point getHasPoint() {
		return hasPoint;
	}
	public void setHasPoint(Point hasPoint) {
		this.hasPoint = hasPoint;
	}
	
	@Override
	public String toString(){
		String institutionString = "--- Institution ---\n";
		institutionString += "\t- hasName: " + hasName + "\n";
		institutionString += "\t- hasPoint: " + hasPoint;
		
		return institutionString;
	}

	public boolean isFullySpecified() {
		return this.getHasName() != null && this.getHasPoint() != null;
	}
}
