package edu.utep.cybershare.vlc.visualization.transformers.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.utep.cybershare.vlc.sparql.SparqlEndpoint;
import edu.utep.cybershare.vlc.visualization.transformers.graphBuilders.Projects2Projects_People;

public class Servlet_Projects2Projects_People extends HttpServlet {
	
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
		Projects2Projects_People transformer = new Projects2Projects_People(endpoint);
		return transformer.getJSONGraph();
	}
	
	public static void main(String[] args){
		SparqlEndpoint endpoint = SparqlEndpoint.getInstance();
		Projects2Projects_People transformer = new Projects2Projects_People(endpoint);
		System.out.println(transformer.getJSONGraph());
	}
}
