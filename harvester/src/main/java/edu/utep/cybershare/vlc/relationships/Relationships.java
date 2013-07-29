package edu.utep.cybershare.vlc.relationships;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.util.Hashtable;

import au.com.bytecode.opencsv.CSVReader;

public class Relationships {
	
	private Hashtable<String, String> projectTitleToRelatedProjectIDs;
	private Hashtable<Integer,String> projectIDToProjectTitle;
	
	public Relationships(){
		
		File csvFile = new File("./people-of-interest/target-vlc-projects-relationships.csv");
		
		projectTitleToRelatedProjectIDs = new Hashtable<String,String>();
		projectIDToProjectTitle = new Hashtable<Integer,String>();
		
		try{
			CSVReader reader = new CSVReader(new FileReader(csvFile));	
			List<String[]> records = reader.readAll();
			
			String projectTitle;
			String relatedProjectIDs;
			String[] aRecord;
			
			for(int i = 1; i < records.size(); i ++){
				aRecord = records.get(i);
				projectTitle = aRecord[1];
				relatedProjectIDs = aRecord[4].trim();
				
				projectIDToProjectTitle.put(new Integer(i), projectTitle);
				
				if(relatedProjectIDs != null && !relatedProjectIDs.isEmpty())
					projectTitleToRelatedProjectIDs.put(projectTitle, relatedProjectIDs);
			}
			reader.close();
		}
		catch(Exception e){e.printStackTrace();}
	}
	
	public List<String> getRelatedProjectTitles(String projectTitle){
		String relatedProjectIDs = projectTitleToRelatedProjectIDs.get(projectTitle);
		ArrayList<String> relatedProjectTitles = new ArrayList<String>();
		String[] projectIDs;
		String relatedProjectTitle;
		if(relatedProjectIDs != null){
			projectIDs = relatedProjectIDs.split(",");
			for(String projectID : projectIDs){
				relatedProjectTitle = projectIDToProjectTitle.get(Integer.valueOf(projectID.trim()));
				relatedProjectTitles.add(relatedProjectTitle);
			}
		}
		return relatedProjectTitles;
	}
}
