package edu.utep.cybershare.vlc.ontology;

public class Discipline {
	
	private String hasName;

	public String getHasName() {
		return hasName;
	}

	public void setHasName(String hasName) {
		this.hasName = hasName;
	}

	@Override
	public String toString(){
		String disciplineString = "--- Discipline ---\n";
		disciplineString += "\t- hasName: " + hasName;
		
		return disciplineString;
	}
}
