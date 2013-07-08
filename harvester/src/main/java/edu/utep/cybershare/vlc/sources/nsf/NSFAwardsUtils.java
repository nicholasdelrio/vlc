package edu.utep.cybershare.vlc.sources.nsf;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.w3c.dom.NodeList;

public class NSFAwardsUtils {
	public static String getFirstName(String properName){
		String firstName = null;
		String[] nameParts;
		if(properName != null){
			nameParts = properName.split(" ");
			if(nameParts.length > 1)
				firstName = nameParts[0];
		}
		return firstName;
	}
	
	public static String getLastName(String properName){
		String lastName = null;
		String[] nameParts;
		if(properName != null){
			nameParts = properName.split(" ");
			if(nameParts.length > 1)
				lastName = nameParts[1];
		}
		return lastName;
	}
	
	public static List<String> nodeListConverter(NodeList nodeList){
		ArrayList<String> stringList = new ArrayList<String>();
		for(int i = 0; i < nodeList.getLength(); i ++)
			stringList.add(nodeList.item(i).getTextContent());
		return stringList;
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
