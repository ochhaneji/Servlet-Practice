package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FetchCountriesServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String capitals[]= {"New Delhi","Islmabad","Melborne","Bejing"};
		int countryIndex=0;
		//get PrintWriter 
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		countryIndex=Integer.parseInt(req.getParameter("country"));
		//write data 
		pw.println("<h1 style='color:red;text-align:center'>Capital is ::"+capitals[countryIndex]+"</h1>");
		//add home hyperlink
		pw.println("<br><a href='form.html'>home</a>");
		//close stream
		pw.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req, res);
	}

}
