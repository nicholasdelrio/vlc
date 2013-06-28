package edu.utep.cybershare.vlc.sources.nsf;

import java.util.GregorianCalendar;

public class NSFAwardsUtils {
	public static String getFirstName(String properName){
		return properName.split(" ")[0];
	}
	
	public static String getLastName(String properName){
		return properName.split(" ")[1];
	}
	
	public static GregorianCalendar getDate(String stringDate){
		String[] dateParts = stringDate.split("/");
		int month = Integer.valueOf(dateParts[0]);
		int day = Integer.valueOf(dateParts[1]);
		int year = Integer.valueOf(dateParts[2]);
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(year, month, day);
		return calendar;
	}
}
