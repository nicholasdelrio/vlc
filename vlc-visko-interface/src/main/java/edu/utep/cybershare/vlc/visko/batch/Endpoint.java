package edu.utep.cybershare.vlc.visko.batch;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

public class Endpoint {

	private static Endpoint instance;
	
	private HttpClient client;
	private static URL viskoServerURL;  
	private int maxResults;
	private String requiredViewURI;
	private String requiredToolkitURI;
	private boolean provenanceRecording;
	
	private static final String NULL_VIEW = "NULL-VIEW";
	
	public static Endpoint getInstance(){
		if(instance == null)
			instance = new Endpoint();
		
		return instance;
	}
	
	private Endpoint(){
		client = new HttpClient();
		setViskoBaseURL();
		maxResults = 1;
		requiredViewURI = NULL_VIEW;
	}
	
	public void setRequiredViewURI(String viewURI){
		this.requiredToolkitURI = viewURI;
	}
	
	public void setRequiredToolkitURI(String toolkitURI){
		this.requiredToolkitURI = toolkitURI;
	}
	
	public void setProvenanceLogging(boolean provenance){
		this.provenanceRecording = provenance;
	}
	
	public void setMaxResults(int max){
		if(max > 0)
			maxResults = max;
	}
		
	private void setViskoBaseURL() {
		String viskoServer = "http://iw.cs.utep.edu:8080/visko-web/ViskoServletManager";
		try {
			viskoServerURL = new URL(viskoServer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getQueryString(String encodedViskoQuery){
		String queryString = "?requestType=execute-query-service";
		
		queryString += "&query=" + encodedViskoQuery;
		queryString += "&maxResults=" + this.maxResults;
		
		if(provenanceRecording)
			queryString += "&provenance=true";
		if(this.requiredToolkitURI != null)
			queryString += "&requiredToolkit=" + this.requiredToolkitURI;
		
		if(this.requiredViewURI != null)
			queryString += "&requiredView=" + this.requiredViewURI;
		else
			queryString += "&requiredView=" + NULL_VIEW;
		
		return queryString;
	}

	public String executeVisKo(String encodedViskoQuery) {
		HttpMethod method = new GetMethod(viskoServerURL.toString() + getQueryString(encodedViskoQuery));

		String result = "{}";
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			else{
				// Read the response body.
				byte[] responseBody = method.getResponseBody();

				// Deal with the response.
				// Use caution: ensure correct character encoding and is not binary
				// data
				result = new String(responseBody);
			}

		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return result;
	}
}
