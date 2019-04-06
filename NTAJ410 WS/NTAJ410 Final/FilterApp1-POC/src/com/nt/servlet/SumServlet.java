package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SumServlet extends HttpServlet {
	
	static {
		System.out.println("SumServlet: static block");
	}
	
	public SumServlet() {
		System.out.println("SumServlet: 0-param constructor");
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("SumServlet: init(-)");
	}
	
   @Override
public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	  System.out.println("SumServlet.doGet(-,-)");
	   PrintWriter pw=null;
	   int val1=0,val2=0;
	   //general settings
	   pw=res.getWriter();
	   res.setContentType("text/html");
	   //read form data
	   val1=Integer.parseInt(req.getParameter("t1"));
	   val2=Integer.parseInt(req.getParameter("t2"));
	   //perform addition
	   pw.println("<h1 style='color:red;text-align:center'> Sum :: "+(val1+val2)+"</h1>");
	   //add hyperlink
	   pw.println("<br><br><a href='form.html'>home </a>");
	   //close stream
	   pw.close();
} //doGet(-,-)
   
   @Override
public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   System.out.println("SumServlet:doPost(-,-)");
   doGet(req,res);
}
   
   @Override
public void destroy() {
   System.out.println("SumServlet::destroy()");
}
	

}
