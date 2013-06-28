package edu.utep.cybershare.vlc.ontology;

import java.awt.Point;

public class Institution {
	
	private String hasName;
	private Point hasPoint;
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
}
