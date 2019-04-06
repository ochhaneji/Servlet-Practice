package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LinksServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String pval=null;
		Locale locales[]=null;
		//get PrintWriter
		pw=res.getWriter();
		res.setContentType("text/html");
		//read additional req param(p1) value 
		pval=req.getParameter("p1");
		//get avaliable Locales
		locales=Locale.getAvailableLocales();
		//differentiate logics for hyperlinks
		if(pval.equalsIgnoreCase("link1")) {
			pw.println("<h3>All Languages are </h3>");
			for(Locale l:locales) {
				pw.println("<br>"+l.getDisplayLanguage());
			}
		}
		else if(pval.equalsIgnoreCase("link2")) {
			pw.println("<h3>All countries are </h3>");
			for(Locale l:locales) {
				pw.println("<br>"+l.getDisplayCountry());
			}
		}
		else {
			pw.println("<b> Date and time is :: </b>"+new java.util.Date());
		}
		//add hyperlink
		pw.println("<a href='links.html'><img src='images/home.png' width='30' height='30'/></a>");
		//close stream
		pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	  doGet(req,res);
	}//doPost(-,-)
	
}//class
