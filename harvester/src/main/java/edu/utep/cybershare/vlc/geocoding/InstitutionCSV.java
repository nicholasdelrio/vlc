package edu.utep.cybershare.vlc.geocoding;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.Hashtable;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

import edu.utep.cybershare.vlc.ontology.Institution;
import edu.utep.cybershare.vlc.sources.ProjectSource;
import edu.utep.cybershare.vlc.sources.nsf.NSFAwards;

public class InstitutionCSV {

	private List<Institution> institutions;
	private Hashtable<String,Point> institutionToCoordinates;
	
	public InstitutionCSV(List<Institution> institutions){
		this.institutions = institutions;
	}
	
	public InstitutionCSV(File csvFile){
	    institutionToCoordinates = new Hashtable <String, Point>();
		
		try{
			CSVReader reader = new CSVReader(new FileReader(csvFile));
			List<String[]> geocodedRecords = reader.readAll();
		    String lat;
		    String lon;
		    String name;
		    Point location;
			for(String[] entry : geocodedRecords){
		    	name = entry[4];
				lat = entry[29];
		    	lon = entry[30];
		    	location = new Point();
		    	location.setLocation(Double.valueOf(lon), Double.valueOf(lat));
		    	institutionToCoordinates.put(name, location);
		    }
			reader.close();
		}catch(Exception e){e.printStackTrace();}
	}
	
	public Point getCoordinates(String institutionName){
		String cleanedName = institutionName.replaceAll(",", "");
		return this.institutionToCoordinates.get(cleanedName);
	}
	
	public void dumpCSVFile(){
		StringWriter writer = new StringWriter();
		writer.append("Address, City, State, Zip, Name\n");
		
		String address;
		String city;
		String state;
		String zipCode;
		String institutionName;
		for(Institution anInstitution : institutions){
			
			address = anInstitution.getHasAddress().replaceAll(",", " ");
			city = anInstitution.getHasCity().replaceAll(",", " ");
			state = anInstitution.getHasState().replaceAll(",",  "");
			zipCode = anInstitution.getHasZipCode().replaceAll(",", " ");
			institutionName = anInstitution.getHasName().replaceAll(",", " ");
			
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
