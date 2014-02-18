package edu.utep.cybershare.vlc.pipeline.sink;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import edu.utep.cybershare.vlc.build.ModelProduct;
import edu.utep.cybershare.vlc.model.Person;
import edu.utep.cybershare.vlc.model.Project;
import edu.utep.cybershare.vlc.pipeline.Pipeline.DumpFilter;

public class VLCProjectsUploader implements DumpFilter  {
	
	ArrayList<VLCProjectUploadURL> uploadURLs; 
	
	private CloseableHttpClient client;
	
	public VLCProjectsUploader(){
		VLCLogin login = new VLCLogin();
		client = login.login("ndel2", "ndel2");
		uploadURLs = new ArrayList<VLCProjectUploadURL>();
	}
	
	public void setProjects(List<Project> projects){
		VLCProjectUploadURL uploadURL;
		int counter = 0;
		
		System.out.println("projects length: " + projects.size());
		
		for(Project aProject : projects){
			
			uploadURL = new VLCProjectUploadURL();
			uploadURL.setInputAbstract(aProject.getAbstractText());

			for(String collectionID : aProject.getCollectionIDs())
				uploadURL.addInputCarpTerm(collectionID);
			
			System.out.println("count: " + counter++);
			System.out.println("project: " + aProject.getIdentification());
			
			if(aProject.getPrincipalInvestigator().isSet_affiliatedInstitutions())
				uploadURL.setInputFieldSite(aProject.getPrincipalInvestigator().getAffiliatedInstitutions().get(0).getIdentification());
			else
				System.out.println("no hosting institution");
			
			uploadURL.setInputFundingAgency("NSF");
			uploadURL.setInputFundingEndDate(formatDate(aProject.getEndDate()));
			uploadURL.setInputFundingStartDate(formatDate(aProject.getStartDate()));
			
			uploadURL.setInputGoals(null);
			uploadURL.setInputInceptionStartDate(null);
			uploadURL.setInputLink(null);
			
			for(Person aPerson : aProject.getCoPrincipalInvestigators())
				uploadURL.addInputCoPI(aPerson.getFirstName() + " " + aPerson.getLastName());
			
			uploadURL.setInputPI(aProject.getPrincipalInvestigator().getFirstName() + " " + aProject.getPrincipalInvestigator().getLastName());
			uploadURL.setInputTitle(aProject.getTitle());
			
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
		HttpPost httpPost = new HttpPost(uploadURL.getServiceURL());
		
		httpPost.setEntity(new UrlEncodedFormEntity(uploadURL.getParameters(), HTTP.DEF_CONTENT_CHARSET));
		
		String result = "Failure";
		try {
			// Execute the method.
			
			System.out.println(httpPost.getURI().toString());
			
			CloseableHttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			
			if(entity != null){result = EntityUtils.toString(entity);}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Release the connection.
			httpPost.releaseConnection();
		}
		return result;
	}
	
	private String formatDate(Calendar calendar){
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		return formater.format(calendar.getTime());
	}

	public void dumpModelProduct(ModelProduct product) {
		List<Project> projects = product.getProjects();
		//List<Project> testProjects = projects.subList(0, 10);
		
		this.setProjects(projects);
		this.upload();
	}
}
