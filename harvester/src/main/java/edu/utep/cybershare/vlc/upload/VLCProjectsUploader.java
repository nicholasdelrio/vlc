package edu.utep.cybershare.vlc.upload;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import edu.utep.cybershare.vlc.ontology.Person;
import edu.utep.cybershare.vlc.ontology.Project;

public class VLCProjectsUploader {
	
	ArrayList<VLCProjectUploadURL> uploadURLs; 
	
	private HttpClient client;
	
	public VLCProjectsUploader(){
		client = new HttpClient();
		uploadURLs = new ArrayList<VLCProjectUploadURL>();
	}
	
	public void setProjects(List<Project> projects){
		VLCProjectUploadURL uploadURL;
		for(Project aProject : projects){
			
			uploadURL = new VLCProjectUploadURL();
			uploadURL.setInputAbstract(aProject.getHasAbstract());

			for(String collectionID : aProject.getParentCollectionIDs())
				uploadURL.addInputCarpTerm(collectionID);
	
			uploadURL.setInputFieldSite(aProject.getHasPrincipalInvestigator().getAffiliatedWithInstitution().get(0).getHasName());
			uploadURL.setInputFundingAgency("NSF");
			uploadURL.setInputFundingEndDate(formatDate(aProject.getHasEndDate_Funding()));
			uploadURL.setInputFundingStartDate(formatDate(aProject.getHasStartDate_Funding()));
			uploadURL.setInputGoals(null);
			uploadURL.setInputInceptionStartDate(null);
			uploadURL.setInputLink(null);
			
			for(Person aPerson : aProject.getHasCoPrincipalInvestigator())
				uploadURL.addInputCoPI(aPerson.getHasFirstName() + " " + aPerson.getHasLastName());
			
			uploadURL.setInputPI(aProject.getHasPrincipalInvestigator().getHasFirstName() + " " + aProject.getHasPrincipalInvestigator().getHasLastName());
			uploadURL.setInputTitle(aProject.getHasTitle());
			
			//add uploadURL to list
			uploadURLs.add(uploadURL);
		}
	}
	
	public void upload(){		
		VLCProjectUploadURL uploadURL;
		for(int i = 0; i < this.uploadURLs.size(); i ++){
			uploadURL = uploadURLs.get(i);
			System.out.println(uploadSingleProject(uploadURL));
		}
	}
		
	private String uploadSingleProject(VLCProjectUploadURL uploadURL){
		PostMethod method = new PostMethod(uploadURL.getServiceURL());
		
		for(NameValuePair pair : uploadURL.getParameters())
			method.addParameter(pair);
		
		String result = "Failure";
		try {
			// Execute the method.
			
			System.out.println(method.getURI().toString());
			
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
	
	private String formatDate(Calendar calendar){
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		return formater.format(calendar.getTime());
	}
}
