package edu.utep.cybershare.vlc.visko;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.utep.cybershare.vlc.visko.batch.HTMLQueryBatch;

public class HTMLQueryBatchService extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
		String jsonSources = request.getParameter("jsonSources");

		HTMLQueryBatch queryBatch = new HTMLQueryBatch(jsonSources);
		String jsonResult = queryBatch.getJSONResultString();
		response.setContentType("application/json");
		response.getWriter().write(jsonResult);
	}
	
	private static String sampleInputJSON =
			"{"
			+ "\"source_urls\":"
			+ "["
			+ "{\"source_url\" : \"http://www.google.com\"},"
			+ "{\"source_url\" : \"http://www.nsf.gov\"}"
			+ "]"
			+ "}";
	
	public static void main(String[] args){
		HTMLQueryBatch queryBatch = new HTMLQueryBatch(sampleInputJSON);
		String jsonResult = queryBatch.getJSONResultString();
		System.out.println(jsonResult);
	}	
}
