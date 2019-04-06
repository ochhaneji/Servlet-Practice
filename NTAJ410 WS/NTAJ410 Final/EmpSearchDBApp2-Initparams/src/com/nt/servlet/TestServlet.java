package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		ServletConfig cg=null;
		//get PrintWriter
		pw=res.getWriter()	;
		res.setContentType("text/html");
		//get Servletconfig obj
		 cg=getServletConfig();
		 //read init param values
		 pw.println("<b>(TestServlet) dbuser init param values::"+cg.getInitParameter("dbuser"));
		 
		//close stream 
		 pw.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req,res);
	}
	

}
