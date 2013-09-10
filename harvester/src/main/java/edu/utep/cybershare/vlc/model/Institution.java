package edu.utep.cybershare.vlc.model;

import edu.utep.cybershare.vlc.visitor.Visitor;


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

	public void seTZipCode(String aipCode) {
		this.zipCode = aipCode;
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
		private double x;
		private double y;
		
		public Coordinate(double x, double y){
			this.x = x;
			this.y = y;
		}
		
		public Coordinate(){
			this.x = 0;
			this.y = 0;
		}
		
		public void setX(double x){
			this.x = x;
		}
		
		public void setY(double y){
			this.y = y;
		}
		
		public double getX(){
			return x;
		}
		
		public double getY(){
			return y;
		}
		
		public String toString(){
			return "x = " + x + ", y = " + y;
		}
	}
}
