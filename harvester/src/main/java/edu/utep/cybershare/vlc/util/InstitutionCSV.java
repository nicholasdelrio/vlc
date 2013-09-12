package edu.utep.cybershare.vlc.util;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import edu.utep.cybershare.vlc.harvesting.filter.NSFSourceFilter;
import edu.utep.cybershare.vlc.model.Institution;

public class InstitutionCSV {

	private List<Institution> institutions;
	private HashMap<String,  Institution.Coordinate> institutionToCoordinates;
	
	public InstitutionCSV(List<Institution> institutions){
		this.institutions = institutions;
	}
	
	public InstitutionCSV(){
		File csvFile = new File("./output-institutions/institutions-geocoded.csv");
		
	    institutionToCoordinates = new HashMap <String, Institution.Coordinate>();
		
		try{
			CSVReader reader = new CSVReader(new FileReader(csvFile));
			List<String[]> geocodedRecords = reader.readAll();
		    String[] record;
		    Double lat;
		    Double lon;
		    String name;
		    
		    for(int i = 1; i < geocodedRecords.size(); i ++){
				record = geocodedRecords.get(i);
		    	name = record[4];
				lat = Double.valueOf(record[29]);
		    	lon = Double.valueOf(record[30]);
		    	institutionToCoordinates.put(name, new Institution.Coordinate(lon, lat));
		    }
			reader.close();
		}catch(Exception e){e.printStackTrace();}
	}
	
	public void setInstitutionCoordinates(List<Institution> institutions){
		String name;
		Institution.Coordinate coordinates;
		for(Institution anInstitution : institutions){
			name = anInstitution.getIdentification();
			coordinates = getCoordinates(name);
			if(coordinates != null)
				anInstitution.setCoordinate(coordinates);
		}
	}
	
	private Institution.Coordinate getCoordinates(String institutionName){
		String cleanedName = institutionName.replaceAll(",", " ");
		Institution.Coordinate coordinates = institutionToCoordinates.get(cleanedName);
		
		if(coordinates != null)
			return coordinates;
		
		return null;
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
			
			address = anInstitution.getAddress().replaceAll(",", " ");
			city = anInstitution.getCity().replaceAll(",", " ");
			state = anInstitution.getState().replaceAll(",",  "");
			zipCode = anInstitution.getZipCode().replaceAll(",", " ");
			institutionName = anInstitution.getIdentification().replaceAll(",", " ");
			
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
		NSFSourceFilter nsfSource = new NSFSourceFilter();
		
		InstitutionCSV csvWriter = new InstitutionCSV(nsfSource.getModelProduct().getInstitutions());
		csvWriter.dumpCSVFile();
	}
}
