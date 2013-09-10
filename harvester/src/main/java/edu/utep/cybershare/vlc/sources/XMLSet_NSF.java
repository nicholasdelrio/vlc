package edu.utep.cybershare.vlc.sources;

import java.io.File;
import java.util.ArrayList;

public class XMLSet_NSF extends ArrayList<File> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private static final String CACHED_AWARD_DATA = "./cached-award-data/";
	private static final String NSF = CACHED_AWARD_DATA + "NSF/";

	private static final File[] AWARDS_NSF = new File[]{
		new File(NSF + "biodiversity-forecasting.xml"),
		new File(NSF + "data-curation.xml"),
		new File(NSF + "data-integration.xml"),
		new File(NSF + "data-management.xml"),
		new File(NSF + "geoepidemiology.xml"),
		new File(NSF + "knowledge-representation.xml"),
		new File(NSF + "ontology.xml"),
		new File(NSF + "semantic-web.xml"),
		new File(NSF + "sensor-data.xml"),
		new File(NSF + "sensor-networks.xml"),
		new File(NSF + "spatial-epidemiology.xml"),
		new File(NSF + "sustainability-science.xml"),
		new File(NSF + "visual-analytics.xml"),
		new File(NSF + "visualization.xml"),
		new File(NSF + "water-models.xml")};

	
	public XMLSet_NSF(){
		for(File cachedAwardFile : AWARDS_NSF)
			this.add(cachedAwardFile);
	}
}