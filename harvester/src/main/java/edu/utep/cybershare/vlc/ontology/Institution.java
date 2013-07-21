package edu.utep.cybershare.vlc.ontology;

import java.awt.Point;

public class Institution implements Concept {
	
	private String hasName;
	private Point hasPoint;
	private String hasCity;
	private String hasAddress;
	private String hasZipCode;
	private String hasState;
	
	public Institution(){
		hasPoint = new Point();
		hasPoint.setLocation(0, 0);
	}
	
	public String getHasName() {
		if(hasName == null || hasName.isEmpty())
			return "Null Name";
		
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
	public void setHasCity(String cityName){
		this.hasCity = cityName;
	}
	public String getHasCity(){
		if(this.hasCity == null || this.hasCity.isEmpty())
			return "NULL CITY NAME";
		return this.hasCity;
	}
	public void setHasAddress(String address){
		this.hasAddress = address;
	}
	public String getHasAddress(){
		if(this.hasAddress == null || this.hasAddress.isEmpty())
			return "NULL ADDRESS";
		return this.hasAddress;
	}
	public void setHasState(String state){
		this.hasState = state;
	}
	public String getHasState(){
		if(this.hasState == null || this.hasState.isEmpty())
			return "NULL STATE NAME";
		return this.hasState;
	}
	
	public void setHasZipCode(String zipCode){
		this.hasZipCode = zipCode;
	}
	
	public String getHasZipCode(){
		if(this.hasZipCode == null || this.hasZipCode.isEmpty())
			return "NULL ZIP CODE";
		return this.hasZipCode;
	}
	
	@Override
	public String toString(){
		String institutionString = "--- Institution ---\n";
		institutionString += "\t- hasName: " + hasName + "\n";
		institutionString += "\t- hasPoint: " + hasPoint;
		
		return institutionString;
	}

	public boolean isFullySpecified() {
		return 
				this.getHasName() != null
				&& this.getHasPoint() != null
				&& this.getHasAddress() != null
				&& this.getHasCity() != null
				&& this.getHasState() != null
				&& this.getHasZipCode() != null;
	}
}
