package edu.utep.cybershare.vlc.sources.nsf;

public class NSFAwardsUtils {
	public static String getFirstName(String properName){
		return properName.split(" ")[0];
	}
	
	public static String getLastName(String properName){
		return properName.split(" ")[1];
	}

}
