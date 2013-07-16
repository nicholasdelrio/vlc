package edu.utep.cybershare.vlc.ontology;

public class Discipline implements Concept {
	
	private String hasName;

	public String getHasName() {
		if(hasName == null || hasName.isEmpty())
			return "Null Discipline Name";
		
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

	public boolean isFullySpecified() {
		return this.getHasName() != null;
	}
}
