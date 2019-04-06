package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/testurl")
public class TestServlet  extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		Enumeration<String> e=null,e1=null;
	     //get PrintWrtier 
		pw=res.getWriter();
		res.setContentType("text/html");
		// all req param names and values are
		
		/*
		 * pw.println("<b> all req param names and values are </b>");
		 * e=req.getParameterNames(); while(e.hasMoreElements()) { String
		 * name=e.nextElement(); String value=req.getParameter(name);
		 * pw.println("<br>"+name+"---->"+value); }
		 */
		pw.println("<h1> Date and time :: "+new java.util.Date()+"</h1><br>");
		res.setHeader("refresh","10");
		// all req header names and values are
		pw.println("<br><b> all req header names and values are </b><br>");
		e1=req.getHeaderNames();
		while(e1.hasMoreElements()) {
			String name=e1.nextElement();
			String value=req.getHeader(name);
			pw.println("<br>"+name+"   "+value);
		}
		
		//Gathering misc info  about request
		//Using ServletRequest object
		pw.println("<br>  protocol ::"+req.getProtocol());
		pw.println("<br>  Scheme ::"+req.getScheme());
		pw.println("<br>browser port number"+req.getRemotePort());
		pw.println("<br>browser Machine host name"+req.getRemoteHost());
		pw.println("<br>browser Machine Ip Address"+req.getRemoteAddr());
		pw.println("<br> content length::"+req.getContentLength());
		pw.println("<br> content Type::"+req.getContentType());
		pw.println("<br> Server Port no::"+req.getServerPort());
		pw.println("<br> Server name ::"+req.getServerName());
		pw.println("<br> Servlet Path ::"+req.getServletPath());
		//Using  HttpServletRequest object
		pw.println("<br><br><br>  req method ::"+req.getMethod());
		pw.println("<br>  req queryString ::"+req.getQueryString());
		pw.println("<br>req url "+req.getRequestURL());
		pw.println("<br>req uri "+req.getRequestURI());
		
		//close stream
		pw.close();
	}//method
}//class
