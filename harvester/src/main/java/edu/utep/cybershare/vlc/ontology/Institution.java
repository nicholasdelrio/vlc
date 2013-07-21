package edu.utep.cybershare.vlc.ontology;


public class Institution implements Concept {
	
	private String hasName;
	private Point hasCoordinates;
	private String hasCity;
	private String hasAddress;
	private String hasZipCode;
	private String hasState;
	
	public Institution(){
		hasCoordinates = new Point(0,0);
	}
	
	public String getHasName() {
		if(hasName == null || hasName.isEmpty())
			return "Null Name";
		
		return hasName;
	}
	public void setHasName(String hasName) {
		this.hasName = hasName;
	}
	
	public double getLatitude(){
		return hasCoordinates.getY();
	}
	
	public double getLongitude(){
		return hasCoordinates.getX();
	}
	
	public Point getHasCoordinates(){
		return hasCoordinates;
	}
	
	public void setHasCoordinates(Point coordinates) {
		hasCoordinates = coordinates;
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
		institutionString += "\t- hasCoordinates: " + hasCoordinates;
		
		return institutionString;
	}

	public boolean isFullySpecified() {
		return 
				this.getHasName() != null
				&& this.getHasCoordinates() != null
				&& this.getHasAddress() != null
				&& this.getHasCity() != null
				&& this.getHasState() != null
				&& this.getHasZipCode() != null;
	}
}
