package edu.utep.cybershare.vlc.build.source;

import java.io.File;
import java.util.ArrayList;

public class XMLSet_NSF extends ArrayList<File> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
			
	public XMLSet_NSF(){
		populate();
	}
	
	private void populate(){
		File nsfCachedAwardsDir = new File(CachedAwards.NSF);
		for(File cachedAward : nsfCachedAwardsDir.listFiles())
			add(cachedAward);
	}
}