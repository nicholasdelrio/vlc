package edu.utep.cybershare.vlc.pipeline.filter;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import edu.utep.cybershare.vlc.build.ModelProduct;
import edu.utep.cybershare.vlc.model.Institution;
import edu.utep.cybershare.vlc.model.Institution.Coordinate;
import edu.utep.cybershare.vlc.pipeline.Pipeline.Filter;

public class InstitutionGeocodeFilter implements Filter {

	private HashMap<String, Coordinate> institutionToCoordinate;

	public InstitutionGeocodeFilter(){
		institutionToCoordinate = new HashMap<String, Coordinate>();
		populateMappings(FilterSourceData.getGeocodedInstitutions());
	}
	
	private void populateMappings(File mappingsFile){
		try{
			CSVReader reader = new CSVReader(new FileReader(mappingsFile));
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
		    	institutionToCoordinate.put(name, new Institution.Coordinate(lon, lat));
		    }
			reader.close();
		}catch(Exception e){e.printStackTrace();}
	}
	
	private void setInstitutionCoordinates(List<Institution> institutions){
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
		Institution.Coordinate coordinates = institutionToCoordinate.get(cleanedName);
		
		if(coordinates != null)
			return coordinates;
		
		return null;
	}		
	
	public ModelProduct process(ModelProduct product) {
		setInstitutionCoordinates(product.getInstitutions());
		return product;
	}
}
