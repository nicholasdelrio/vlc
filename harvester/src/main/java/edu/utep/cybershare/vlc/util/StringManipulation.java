package edu.utep.cybershare.vlc.util;

import java.net.URI;
import java.security.MessageDigest;
import java.util.Formatter;

public class StringManipulation {
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
	public static String SHAsum(byte[] convertme){
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-1"); 
			return byteArray2Hex(md.digest(convertme));
		}catch(Exception e){e.printStackTrace();}
		return null;
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
	
	public static String makeURICompliantFragment(String candidateIRIFragment, String baseURI){		
		String newIRIFragment = candidateIRIFragment
				.replaceAll("[^A-Za-z0-9\\s]", "")
				.replaceAll("\\s", "-");
		
		if(!baseURI.endsWith("/"))
			baseURI += "/";
			
		try{
			new URI(baseURI + newIRIFragment);
			return newIRIFragment;
		}
		catch(Exception e){
			System.err.println("Offending Name: " + candidateIRIFragment);
			for(int i = 0; i < candidateIRIFragment.length(); i ++)
				System.err.println("character: " + candidateIRIFragment.charAt(i) + ", value: " + candidateIRIFragment.codePointAt(i));
			e.printStackTrace();
		}
		return null;
	}
	
	 public static boolean validUTF8(byte[] input) {
		  int i = 0;
		  // Check for BOM
		  if (input.length >= 3 && (input[0] & 0xFF) == 0xEF
		    && (input[1] & 0xFF) == 0xBB & (input[2] & 0xFF) == 0xBF) {
		   i = 3;
		  }

		  int end;
		  for (int j = input.length; i < j; ++i) {
		   int octet = input[i];
		   if ((octet & 0x80) == 0) {
		    continue; // ASCII
		   }

		   // Check for UTF-8 leading byte
		   if ((octet & 0xE0) == 0xC0) {
		    end = i + 1;
		   } else if ((octet & 0xF0) == 0xE0) {
		    end = i + 2;
		   } else if ((octet & 0xF8) == 0xF0) {
		    end = i + 3;
		   } else {
		    // Java only supports BMP so 3 is max
		    return false;
		   }

		   while (i < end) {
		    i++;
		    octet = input[i];
		    if ((octet & 0xC0) != 0x80) {
		     // Not a valid trailing byte
		     return false;
		    }
		   }
		  }
		  return true;
		 }
}
