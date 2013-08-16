package edu.utep.cybershare.vlc.visko.endpoint;

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
	
	public static Endpoint getInstance(){
		if(instance == null)
			instance = new Endpoint();
		
		return instance;
	}
	
	private Endpoint(){
		client = new HttpClient();
		setViskoURL();
	}
	
	private void setViskoURL() {
		String viskoServer = "http://iw.cs.utep.edu:8080/visko-web/ViskoServletManager?requestType=execute-query-service&maxResults=1&requiredView=NULL-VIEW";
		try {
			viskoServerURL = new URL(viskoServer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String executeVisKo(String viskoQuery) {
		HttpMethod method = new GetMethod(viskoServerURL.toString() + "&query=" + viskoQuery);

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
