package edu.utep.cybershare.vlc.geocoding;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.List;

import edu.utep.cybershare.vlc.ontology.Institution;
import edu.utep.cybershare.vlc.sources.ProjectSource;
import edu.utep.cybershare.vlc.sources.nsf.NSFAwards;

public class InstitutionCSV {

	public List<Institution> institutions;
	
	public InstitutionCSV(List<Institution> institutions){
		this.institutions = institutions;
	}
	
	public void dumpCSVFile(){
		StringWriter writer = new StringWriter();
		writer.append("Address, City, State, Zip, Institution Name\n");
		
		String address;
		String city;
		String state;
		String zipCode;
		String institutionName;
		for(Institution anInstitution : institutions){
			
			address = anInstitution.getHasAddress().replaceAll(",", "");
			city = anInstitution.getHasCity().replaceAll(", ", "");
			state = anInstitution.getHasState().replaceAll(",", "");
			zipCode = anInstitution.getHasZipCode().replaceAll(",", "");
			institutionName = anInstitution.getHasName().replaceAll(",", "");
			
			writer.append(address + ", ");
			writer.append(city + ", ");
			writer.append(state + ", ");
			writer.append(zipCode + ", ");
			writer.append(institutionName + "\n");
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
