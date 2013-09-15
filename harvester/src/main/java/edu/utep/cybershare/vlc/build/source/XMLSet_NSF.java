package edu.utep.cybershare.vlc.build.source;

import java.io.File;
import java.util.ArrayList;

public class XMLSet_NSF extends ArrayList<File> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private static final String CACHED_AWARD_DATA = "./cached-award-data/";
	private static final String NSF = CACHED_AWARD_DATA + "NSF/";
	
	public XMLSet_NSF(){
		populate();
	}
	
	private void populate(){
		File nsfCachedAwardsDir = new File(NSF);
		for(File cachedAward : nsfCachedAwardsDir.listFiles())
			add(cachedAward);
	}
}