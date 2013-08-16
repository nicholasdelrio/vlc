package edu.utep.cybershare.vlc.visko.batch;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONArray;
import org.json.JSONObject;

import edu.utep.cybershare.vlc.visko.endpoint.Endpoint;

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

		JSONObject aJSONResult;
		String inputDataURL;
		String outputURL;
		try{
			for(JSONObject aVisKoResult : results){
				inputDataURL = extractInputDataURL(aVisKoResult);
				outputURL = extractResultURL(aVisKoResult);
				aJSONResult = new JSONObject();
				aJSONResult.put("source_url", inputDataURL).put("image_url", outputURL);

				jsonResultArray.add(aJSONResult);
			}		
			jsonResults.put("results", jsonResultArray);
			return jsonResults.toString(4);

		}catch(Exception e){e.printStackTrace();}

		return jsonResults.toString();
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
		String queryTemplate = "VISUALIZE "
				+ inputURL
				+ " AS * IN-TYPE http://www.w3.org/2002/07/owl#Thing IN-FORMAT http://openvisko.org/rdf/pml2/formats/PNG.owl#PNG WHERE FORMAT = http://openvisko.org/rdf/pml2/formats/HTML.owl#HTML AND TYPE = http://www.w3.org/2002/07/owl#Thing";
		return queryTemplate;
	}

	private void execute() {
		String encodedVisKoQuery;
		for (URL anInputURL : webPageURLs) {
			encodedVisKoQuery = getEncodedVisKoQuery(anInputURL.toString());
			results.add(executeVisKoJSON(encodedVisKoQuery));
		}
	}

	private JSONObject executeVisKoJSON(String viskoQuery){
		String jsonResultString = Endpoint.getInstance().executeVisKo(viskoQuery);
		JSONObject result = null;
		try{result = new JSONObject(jsonResultString);}
		catch(Exception e){e.printStackTrace();}

		return result;
	}

}
