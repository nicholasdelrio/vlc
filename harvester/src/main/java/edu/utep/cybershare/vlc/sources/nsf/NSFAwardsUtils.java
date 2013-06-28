package edu.utep.cybershare.vlc.sources.nsf;

import java.util.GregorianCalendar;

public class NSFAwardsUtils {
	public static String getFirstName(String properName){
		String firstName = null;
		String[] nameParts;
		if(properName != null){
			nameParts = properName.split(" ");
			if(nameParts.length == 2)
				firstName = nameParts[0];
		}
		return firstName;
	}
	
	public static String getLastName(String properName){
		String lastName = null;
		String[] nameParts;
		if(properName != null){
			nameParts = properName.split(" ");
			if(nameParts.length == 2)
				lastName = nameParts[1];
		}
		return lastName;
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
