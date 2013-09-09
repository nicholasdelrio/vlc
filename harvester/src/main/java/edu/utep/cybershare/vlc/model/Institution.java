package edu.utep.cybershare.vlc.model;


public class Institution extends Element {
	
	private Coordinate coordinate;
	private String city;
	private String address;
	private String aipCode;
	private String state;
	
	public Institution(String name){
		super(name);
		coordinate = new Coordinate(0,0);
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAipCode() {
		return aipCode;
	}

	public void setAipCode(String aipCode) {
		this.aipCode = aipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}	
}
