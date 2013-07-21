package edu.utep.cybershare.vlc.geocoding;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.List;

import edu.utep.cybershare.vlc.ontology.Institution;
import edu.utep.cybershare.vlc.ontology.Project;
import edu.utep.cybershare.vlc.sources.ProjectSource;
import edu.utep.cybershare.vlc.sources.nsf.NSFAwards;

public class InstitutionCSV {

	public List<Institution> institutions;
	
	public InstitutionCSV(List<Institution> institutions){
		this.institutions = institutions;
	}
	
	public void dumpCSVFile(){
		StringWriter writer = new StringWriter();
		writer.append("Address, City, State, Zip, Institution Name");
		for(Institution anInstitution : institutions){
			writer.append(anInstitution.getHasAddress() + ", ");
			writer.append(anInstitution.getHasCity() + ", ");
			writer.append(anInstitution.getHasState() + ", ");
			writer.append(anInstitution.getHasZipCode() + ", ");
			writer.append(anInstitution.getHasName());
		}

		File csvFile = new File("./output-institutions/institutions.csv");
		FileWriter fileWriter;
		try{
			fileWriter = new FileWriter(csvFile);
			fileWriter.write(writer.toString());
		}catch(Exception e){e.printStackTrace();}
	}
	
	public static void main(String[] args){
		//load set of project objects
		ProjectSource nsfSource = new NSFAwards();
		
		InstitutionCSV csvWriter = new InstitutionCSV(nsfSource.getInstitutions());
		csvWriter.dumpCSVFile();
	}
}
