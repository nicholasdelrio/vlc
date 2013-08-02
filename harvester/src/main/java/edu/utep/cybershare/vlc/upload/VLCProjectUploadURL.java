package edu.utep.cybershare.vlc.upload;

import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.StringEscapeUtils;

public class VLCProjectUploadURL {
	
	private static final String PROTOCOL = "http";
	private static final String DOMAIN = "scidesign-test.utep.edu";
	private static final String PATH = "/createProjectServices";
	
	private static final String researchTitle = "researchTitle";
	private String inputTitle;
	
	private static final String researchAbstract = "abstract";
	private String inputAbstract;
	
	private static final String goals = "goals";
	private String inputGoals;
	
	private static final String inceptionStartDate = "inceptionStartDate";
	private String inputInceptionStartDate;
	
	private static final String fundingStartDate = "fundingStartDate";
	private String inputFundingStartDate;
	
	private static final String fundingEndDate = "fundingEndDate";
	private String inputFundingEndDate;

	private static final String fundingAgency = "fundingAgency";
	private String inputFundingAgency;
	
	private static final String pi = "pi";
	private String inputPI;
	
	private static final String coPi = "coPi";
	private List<String> inputCoPI;
	
	private static final String link = "link";
	private String inputLink;
	
	private static final String fieldSite = "fieldSite";
	private String inputFieldSite;
	
	private static final String carpTerm = "carpTerm";
	private List<String> inputCarpTerm;
	
	public VLCProjectUploadURL(){
		this.inputCoPI = new ArrayList<String>();
		this.inputCarpTerm = new ArrayList<String>();
	}
	
	public String getInputTitle() {
		return inputTitle;
	}

	public void setInputTitle(String inputTitle) {
		this.inputTitle = inputTitle;
	}

	public String getInputAbstract() {
		return inputAbstract;
	}

	public void setInputAbstract(String inputAbstract) {
		this.inputAbstract = inputAbstract;
	}

	public String getInputGoals() {
		return inputGoals;
	}

	public void setInputGoals(String inputGoals) {
		this.inputGoals = inputGoals;
	}

	public String getInputInceptionStartDate() {
		return inputInceptionStartDate;
	}

	public void setInputInceptionStartDate(String inputInceptionStartDate) {
		this.inputInceptionStartDate = inputInceptionStartDate;
	}

	public String getInputFundingStartDate() {
		return inputFundingStartDate;
	}

	public void setInputFundingStartDate(String inputFundingStartDate) {
		this.inputFundingStartDate = inputFundingStartDate;
	}

	public String getInputFundingEndDate() {
		return inputFundingEndDate;
	}

	public void setInputFundingEndDate(String inputFundingEndDate) {
		this.inputFundingEndDate = inputFundingEndDate;
	}

	public String getInputFundingAgency() {
		return inputFundingAgency;
	}

	public void setInputFundingAgency(String inputFundingAgency) {
		this.inputFundingAgency = inputFundingAgency;
	}

	public String getInputPI() {
		return inputPI;
	}

	public void setInputPI(String inputPI) {
		this.inputPI = inputPI;
	}

	public List<String> getInputCoPI() {
		return inputCoPI;
	}

	public void addInputCoPI(String inputCoPI) {
		this.inputCoPI.add(inputCoPI);
	}

	public String getInputLink() {
		return inputLink;
	}

	public void setInputLink(String inputLink) {
		this.inputLink = inputLink;
	}

	public String getInputFieldSite() {
		return inputFieldSite;
	}

	public void setInputFieldSite(String inputFieldSite) {
		this.inputFieldSite = inputFieldSite;
	}

	public List<String> getInputCarpTerm() {
		return inputCarpTerm;
	}

	public void addInputCarpTerm(String inputCarpTerm) {
		this.inputCarpTerm.add(inputCarpTerm);
	}
	
	public String getServiceURL(){
		return PROTOCOL + "://" + DOMAIN + PATH;
	}
	
	public List<NameValuePair> getParameters(){
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		
		pairs.add(new NameValuePair(VLCProjectUploadURL.carpTerm, encodeValue(this.getInputCarpTerm())));
		
		pairs.add(new NameValuePair(VLCProjectUploadURL.coPi, encodeValue(this.getInputCoPI())));
		pairs.add(new NameValuePair(VLCProjectUploadURL.fieldSite, encodeValue(this.getInputFieldSite())));
		pairs.add(new NameValuePair(VLCProjectUploadURL.fundingAgency, encodeValue(this.getInputFundingAgency())));
		pairs.add(new NameValuePair(VLCProjectUploadURL.fundingEndDate, encodeValue(this.getInputFundingEndDate())));
		pairs.add(new NameValuePair(VLCProjectUploadURL.fundingStartDate, encodeValue(this.getInputFundingStartDate())));
		pairs.add(new NameValuePair(VLCProjectUploadURL.goals, encodeValue(this.getInputGoals())));
		pairs.add(new NameValuePair(VLCProjectUploadURL.inceptionStartDate, encodeValue(this.getInputInceptionStartDate())));
		pairs.add(new NameValuePair(VLCProjectUploadURL.link, encodeValue(this.getInputLink())));
		pairs.add(new NameValuePair(VLCProjectUploadURL.pi, encodeValue(this.getInputPI())));
		pairs.add(new NameValuePair(VLCProjectUploadURL.researchAbstract, encodeValue(this.getInputAbstract())));
		pairs.add(new NameValuePair(VLCProjectUploadURL.researchTitle, encodeValue(this.getInputTitle())));
		
		return pairs;
	}
		
	private String encodeValue(String value){
		if(value != null && !value.isEmpty()){
			String text = StringEscapeUtils.escapeXml(value);
			text = StringEscapeUtils.escapeHtml(text);
			System.out.println(text);
			return text;
		}
					
		return "nullvalue";
	}
	
	private String encodeValue(List<String> list){
		String stringList = "";
		for(String item : list)
			stringList += encodeValue(item) + ",";
		
		if(stringList.isEmpty())
			stringList="nullvalue";

		return stringList;
	}
}
