package edu.utep.cybershare.vlc.visualization.transformers.servlets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.utep.cybershare.vlc.sparql.SparqlEndpoint;
import edu.utep.cybershare.vlc.visualization.transformers.graphBuilders.Institutions2Institutions_Projects;
import edu.utep.cybershare.vlc.visualization.transformers.graphBuilders.Projects2Projects_People;

public class Servlet_Institutions2Institutions_Projects extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
		String jsonGraph = getJSONGraph();
		response.setContentType("application/json");
		response.getWriter().write(jsonGraph);
	}	
	
	private String getJSONGraph(){
		SparqlEndpoint endpoint = SparqlEndpoint.getInstance();
		Institutions2Institutions_Projects transformer = new Institutions2Institutions_Projects(endpoint);
		return transformer.getJSONGraph();
	}
	
	public static void main(String[] args) throws Exception {
		SparqlEndpoint endpoint = SparqlEndpoint.getInstance();
		Institutions2Institutions_Projects transformer = new Institutions2Institutions_Projects(endpoint);
		FileWriter wtr = new FileWriter(new File("./json-output/institutions-to-institutions-projects.json"));
		wtr.write(transformer.getJSONGraph());
		wtr.close();
	}
}
