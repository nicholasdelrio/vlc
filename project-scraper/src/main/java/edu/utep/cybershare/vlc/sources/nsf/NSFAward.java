package edu.utep.cybershare.vlc.sources.nsf;

import java.net.URL;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
 
public class NSFAward extends DefaultHandler { 

	public NSFAward(String projectXML) throws Exception {
		SAXParserFactory spf = SAXParserFactory.newInstance();
	    spf.setNamespaceAware(true);
	    SAXParser saxParser = spf.newSAXParser();
	    XMLReader xmlReader = saxParser.getXMLReader();
	    xmlReader.setContentHandler(this);
	    InputSource is = new InputSource();
	    
	    xmlReader.parse(projectXML); //this is wrong
	}
}