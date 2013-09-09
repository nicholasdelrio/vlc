package edu.utep.cybershare.vlc.model;

import java.util.ArrayList;

public class Element {
	
	private static ArrayList<String> names = new ArrayList<String>(); 
	
	private String identification;

	public Element(String identification){
		if(names.contains(identification))
			throw new IllegalArgumentException("Element ID must be unique. Given: " + identification);
		if(identification == null)
			throw new IllegalArgumentException("Element ID must not be null.");
		
		this.identification = identification;
	}
	
	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}
}
