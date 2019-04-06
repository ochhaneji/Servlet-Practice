package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		ServletContext sc=null;
		RequestDispatcher rd1=null,rd2=null;
		//get PrintWriter
		pw=res.getWriter()	;
		res.setContentType("text/html");
		
		  //include header content
			rd1=req.getRequestDispatcher("/headerurl");
			rd1.include(req,res);
		
		//get Servletconfig obj
		 sc=getServletContext();
		 //read init param values
		 pw.println("<b> all Context Params </b>");
		 Enumeration<String> e=sc.getInitParameterNames();
		 while(e.hasMoreElements()) {
			 String name=e.nextElement();
			 String value=sc.getInitParameter(name);
			 pw.println("<br>"+name+" "+value);
		 }
		 
		 ServletConfig cg=getServletConfig();
		 pw.println("<br>dbpwd init param value::"+cg.getInitParameter("dbpwd"));
		 pw.println("<br>dbpwd context param value::"+sc.getInitParameter("dbpwd"));
		 
		 pw.println("<br> server info::"+sc.getServerInfo());
		 pw.println("<br> Context path name::"+sc.getContextPath());
		 pw.println("<br> Servlet api version::"+sc.getMajorVersion()+"."+sc.getMinorVersion());
		 pw.println("<br> Mime of type file::"+sc.getMimeType("input.html"));
		 pw.println("<br> Real path of web application::"+sc.getRealPath("/"));
		 pw.println("<br> Real Path of input.html::"+sc.getRealPath("/input.html"));
		 pw.println("<br> Servletcontext obj class name::"+sc.getClass());
		 sc.log("Todays Date::"+new Date());
		 
		//include footer
			rd2=req.getRequestDispatcher("/footer.html");
			rd2.include(req,res);
		 
		//close stream 
		 pw.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req,res);
	}
	

}
