package edu.utep.cybershare.vlc.visko.provenance;

import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import org.openrdf.model.Model;
import org.openrdf.model.Statement;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParser;
import org.openrdf.rio.Rio;
import org.openrdf.rio.helpers.StatementCollector;

public class ProvenanceManager {

	private Model provenanceModel;
		
	private void setUpSesameRepository(String provenanceURLString){
		try{
			URL provenanceDocumentURL = new URL(provenanceURLString);
			InputStream inputStream = provenanceDocumentURL.openStream();
			
			RDFParser rdfParser = Rio.createParser(RDFFormat.TURTLE);
			
			provenanceModel = new LinkedHashModel();
			rdfParser.setRDFHandler(new StatementCollector(provenanceModel));
			rdfParser.parse(inputStream, provenanceDocumentURL.toString());
		}
		catch(Exception e){e.printStackTrace();}
	}
		
	public String getIntermediatePDFDocument(String provenanceURLString){
		
		if(provenanceURLString != null){
			setUpSesameRepository(provenanceURLString);

		
			Iterator<Statement> statementIterator = provenanceModel.iterator();
			Statement aStatement;
			while(statementIterator.hasNext()){
				aStatement = statementIterator.next();
				if(aStatement.getObject().stringValue().equals("http://openvisko.org/rdf/pml2/formats/PDF.owl#PDF"))
					return aStatement.getSubject().stringValue();
			}
		}
		return "null url";
	}
}
