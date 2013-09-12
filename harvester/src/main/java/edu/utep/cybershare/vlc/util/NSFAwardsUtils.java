package edu.utep.cybershare.vlc.util;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
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
	
	public static URL getAwardHomepageURL(String awardID){
		URL homepageURL = null;
		try{homepageURL = new URL("http://www.nsf.gov/awardsearch/showAward?HistoricalAwards=false&AWD_ID=" + awardID);}
		catch(Exception e){e.printStackTrace();}
		return homepageURL;
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
	
	public static String removeIllegalCharacters(String text){
		// XML 1.0
		// #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
		String xml10pattern = "[^"
		                    + "\u0009\r\n"
		                    + "\u0020-\uD7FF"
		                    + "\uE000-\uFFFD"
		                    + "\ud800\udc00-\udbff\udfff"
		                    + "]";
		return text.replaceAll(xml10pattern, "");
	}
	public static String SHAsum(byte[] convertme) throws NoSuchAlgorithmException{
	    MessageDigest md = MessageDigest.getInstance("SHA-1"); 
	    return byteArray2Hex(md.digest(convertme));
	}

	private static String byteArray2Hex(final byte[] hash) {
	    Formatter formatter = new Formatter();
	    for (byte b : hash) {
	        formatter.format("%02x", b);
	    }
	    String hex = formatter.toString();
	    formatter.close();
	    return hex;
	}
}
