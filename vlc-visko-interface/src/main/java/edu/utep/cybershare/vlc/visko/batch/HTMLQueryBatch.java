package edu.utep.cybershare.vlc.visko.batch;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.utep.cybershare.vlc.visko.characterization.SemanticCharacteristics;
import edu.utep.cybershare.vlc.visko.provenance.ProvenanceManager;

public class HTMLQueryBatch {

	private ArrayList<URL> webPageURLs;
	private ArrayList<JSONObject> results;

	public HTMLQueryBatch(String jsonInput) {
		webPageURLs = new ArrayList<URL>();
		results = new ArrayList<JSONObject>();
		extractInputURLs(jsonInput);
	}

	private void extractInputURLs(String jsonInput) {
		JSONObject sources;
		JSONArray sourcesArray;
		JSONObject input;
		String aWebPageURLString;
		URL aWebPageURL;
		try {
			sources = new JSONObject(jsonInput);
			sourcesArray = sources.getJSONArray("source_urls");
			for (int i = 0; i < sourcesArray.length(); i++) {
				input = sourcesArray.getJSONObject(i);
				aWebPageURLString = input.getString("source_url");
				aWebPageURL = new URL(aWebPageURLString);
				webPageURLs.add(aWebPageURL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getJSONResultString(){
		execute();

		JSONObject jsonResults = new JSONObject();
		ArrayList<JSONObject> jsonResultArray = new ArrayList<JSONObject>();

		ProvenanceManager manager = new ProvenanceManager();
		
		JSONObject aJSONResult;
		String inputDataURL;
		String outputURL;
		String provenanceURL;
		String pdfDocumentURL;
		try{
			for(JSONObject aVisKoResult : results){
				inputDataURL = extractInputDataURL(aVisKoResult);
				outputURL = extractResultURL(aVisKoResult);
				provenanceURL = extractProvenanceDataURL(aVisKoResult);
				pdfDocumentURL = manager.getIntermediatePDFDocument(provenanceURL);
				
				aJSONResult = new JSONObject();
				aJSONResult
					.put("source_url", inputDataURL)
					.put("image_url", outputURL)
					.put("pdf_version", pdfDocumentURL);

				jsonResultArray.add(aJSONResult);
			}		
			jsonResults.put("results", jsonResultArray);
			return jsonResults.toString(4);

		}catch(Exception e){e.printStackTrace();}

		return jsonResults.toString();
	}

	private String extractProvenanceDataURL(JSONObject aVisKoResult){
		String provenanceURL = null;
		JSONArray resultsArray;
		try{
			resultsArray = aVisKoResult.getJSONArray("results");
			provenanceURL = resultsArray.getJSONObject(0).getString("provenance");
		}catch(Exception e){e.printStackTrace();}
		return provenanceURL;
	}
	
	private String extractInputDataURL(JSONObject aVisKoResult){
		String inputDataURL = "null url";
		try{inputDataURL =  aVisKoResult.getString("inputURL");}
		catch(Exception e){e.printStackTrace();}

		return inputDataURL;
	}

	private String extractResultURL(JSONObject aVisKoResult){
		String resultURL = "null url";
		JSONArray resultsArray;
		try{
			resultsArray = aVisKoResult.getJSONArray("results");
			resultURL = resultsArray.getJSONObject(0).getString("outputURL");
		}catch(Exception e){e.printStackTrace();}

		return resultURL;
	}

	private String getEncodedVisKoQuery(String inputURL) {
		String viskoQuery = getVisKoQuery(inputURL);
		String encodedQuery = null;
		try {
			encodedQuery = URLEncoder.encode(viskoQuery, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodedQuery;
	}

	private String getVisKoQuery(String inputURL) {
		String inputFormatURI = SemanticCharacteristics.getCharacteristics(inputURL).getFormatURI();
		String inputTypeURI = SemanticCharacteristics.getCharacteristics(inputURL).getTypeURI();
		
		String query = "VISUALIZE "
				+ inputURL + " "
				+ "AS * "
				+ "IN http://iw.cs.utep.edu:8080/visko-web/registry/module_vlc.owl#VLC-Viewerset "
				+ "WHERE FORMAT = " + inputFormatURI + " "
				+ "AND TYPE = " + inputTypeURI;
		return query;
	}

	private void execute() {
		String encodedVisKoQuery;
		for (URL anInputURL : webPageURLs) {
			encodedVisKoQuery = getEncodedVisKoQuery(anInputURL.toString());
			results.add(executeVisKoJSON(encodedVisKoQuery));
		}
	}

	private JSONObject executeVisKoJSON(String encodedViskoQuery){
		Endpoint endpoint = Endpoint.getInstance();
		endpoint.setMaxResults(1);;
		endpoint.setProvenanceLogging(true);
			
		String jsonResultString = Endpoint.getInstance().executeVisKo(encodedViskoQuery);
		
		JSONObject result = null;
		try{result = new JSONObject(jsonResultString);}
		catch(Exception e){e.printStackTrace();}

		return result;
	}

}
