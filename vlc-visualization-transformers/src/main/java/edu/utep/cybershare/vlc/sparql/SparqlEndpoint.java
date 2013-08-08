package edu.utep.cybershare.vlc.sparql;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;


public class SparqlEndpoint {
	
	private static SparqlEndpoint instance;
	
	private URL sparqlEndpoint;
	private String encodedQuery;
	private HttpClient client;
	
	public static SparqlEndpoint getInstance(){
		if(instance == null)
			instance = new SparqlEndpoint();
		
		return instance;
	}
	
	private SparqlEndpoint(){
		setEndpoint();
		client = new HttpClient();
	}
	
	private void setEndpoint(){
		String endpoint = "http://129.108.156.52:8890/sparql";
		try{sparqlEndpoint = new URL(endpoint);}
		catch(Exception e){e.printStackTrace();}
	}
	
	public void setQuery(String sparqlQuery){
		try{encodedQuery = URLEncoder.encode(sparqlQuery, "UTF-8");}
		catch(Exception e){e.printStackTrace();}
	}
	
	public String executeQuery(){
		String queryRequestURL = sparqlEndpoint.toString() + getQueryString();
		return httpGetRequest(queryRequestURL);
	}
	
	public String httpGetRequest(String requestURL){
		GetMethod method = new GetMethod(requestURL);
		
		String result = "Failure";
		try {			
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK)
				System.err.println("Method failed: " + method.getStatusLine());
		
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
	
	private String getQueryString(){
		String queryString = "?&query=" + encodedQuery + "&format=" + "text%2Fjson";
		return queryString;
	}
}
