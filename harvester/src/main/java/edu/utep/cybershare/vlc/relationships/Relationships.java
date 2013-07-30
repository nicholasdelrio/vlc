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
	private Hashtable<String, String> projectTitleToCollectionNames;
	
	private static int semantic = 5;
	private static int biodiversity = 6;
	private static int infectionDisease = 7;
	private static int arctic = 8;
	private static int workflows = 9;
	private static int diffusion = 10;
	private static int geospatial = 11;
	private static int sensors = 12;

	
	public Relationships(){
		
		File csvFile = new File("./people-of-interest/target-vlc-projects-relationships.csv");
		
		projectTitleToRelatedProjectIDs = new Hashtable<String,String>();
		projectIDToProjectTitle = new Hashtable<Integer,String>();
		projectTitleToCollectionNames = new Hashtable<String,String>();
		
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
				
				setCollectionMapping(projectTitle, aRecord);
				
				projectIDToProjectTitle.put(new Integer(i), projectTitle);

				
				if(relatedProjectIDs != null && !relatedProjectIDs.isEmpty())
					projectTitleToRelatedProjectIDs.put(projectTitle, relatedProjectIDs);
			}
			reader.close();
		}
		catch(Exception e){e.printStackTrace();}
	}
	
	private void setCollectionMapping(String projectTitle, String[] aRecord){
		String collections = "";
		if(aRecord[Relationships.arctic] != null)
			collections += "collectionR43U52,";
		if(aRecord[Relationships.biodiversity] != null)
			collections += "collectionR41U52,";
		if(aRecord[Relationships.diffusion] != null)
			collections += "collectionR45U52,";
		if(aRecord[Relationships.geospatial] != null)
			collections += "collectionR46U52,";
		if(aRecord[Relationships.infectionDisease] != null)
			collections += "collectionR42U52,";
		if(aRecord[Relationships.semantic] != null)
			collections += "collectionR40U52,";
		if(aRecord[Relationships.sensors] != null)
			collections += "collectionR47U52,";
		if(aRecord[Relationships.workflows] != null)
			collections += "collectionR44U52,";
		
		if(!collections.isEmpty()){
			collections = collections.substring(0, collections.lastIndexOf(","));
			projectTitleToCollectionNames.put(projectTitle, collections);
		}
	}
	
	public List<String> getParentCollection(String projectTitle){
		String collectionList = projectTitleToCollectionNames.get(projectTitle);
		ArrayList<String> collections = new ArrayList<String>();
		if(collectionList != null){
			for(String collectionID : collectionList.split(","))
				collections.add(collectionID);
		}
		
		return collections;
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
