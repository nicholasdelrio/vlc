package edu.utep.cybershare.vlc.build.source;

import java.io.File;
import java.net.URL;

public class Awards {
	static final File CACHED_AWARD_DATA = new File("./cached-award-data/");
	static final File NSF = new File(CACHED_AWARD_DATA + "/nsf/");
	static final File NASA = new File(CACHED_AWARD_DATA + "/nasa/");
		
	public static URL getAGENCY_NASA(){
		URL nasa = null;
		try{nasa = new URL("http://www.nasa.gov/");}
		catch(Exception e){e.printStackTrace();}
		return nasa;
	}
	
	public static URL getAGENCY_NSF(){
		URL nsf = null;
		try{nsf = new URL("http://www.nsf.gov/");}
		catch(Exception e){e.printStackTrace();}
		return nsf;
	}
}
