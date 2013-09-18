package edu.utep.cybershare.vlc.model;



public class Institution extends Element {
	
	private Coordinate coordinate;
	private String city;
	private String address;
	private String zipCode;
	private String state;
	
	public Institution(String name){
		super(name);
		coordinate = new Coordinate(0,0);
	}

	public boolean isSet_coordinate(){return this.getCoordinate().getLongitude() != 0 && this.getCoordinate().getLatitude() != 0;}
	public boolean isSet_city(){return this.getCity() != null;}
	public boolean isSet_address(){return this.getAddress() != null;}
	public boolean isSet_zipCode(){return this.getZipCode() != null;}
	public boolean isSet_state(){return this.getState() != null;}
	
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	public static class Coordinate {
		private double lon;
		private double lat;
		
		public Coordinate(double longitude, double latitude){
			this.lon = longitude;
			this.lat = latitude;
		}
		
		public Coordinate(){
			this.lon = 0;
			this.lat = 0;
		}
		
		public void setLongitude(double longitude){
			this.lon = longitude;
		}
		
		public void setLatitude(double latitude){
			this.lat = latitude;
		}
		
		public double getLongitude(){
			return lon;
		}
		
		public double getLatitude(){
			return lat;
		}
	}
}
