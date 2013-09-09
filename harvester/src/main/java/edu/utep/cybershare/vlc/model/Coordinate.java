package edu.utep.cybershare.vlc.model;

public class Coordinate {
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
