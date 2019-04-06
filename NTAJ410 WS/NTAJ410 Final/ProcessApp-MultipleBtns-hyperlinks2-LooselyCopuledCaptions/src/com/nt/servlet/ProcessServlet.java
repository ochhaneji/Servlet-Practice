package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doGet(-,-)");
		PrintWriter pw=null;
		String pval=null;
		int val1=0,val2=0;
		Properties props=null;
		//get PrintWriter
		pw=res.getWriter();
		//set response content type
		res.setContentType("text/html");
		//read special req param (s1) value
		pval=req.getParameter("s1");
		if(!pval.equalsIgnoreCase("link1") && !pval.equalsIgnoreCase("link2")) {
			val1=Integer.parseInt(req.getParameter("t1"));
			val2=Integer.parseInt(req.getParameter("t2"));
			System.out.println(pval+"  "+val1+" "+val2);
		}
		//differentiate logics
		if(pval.equalsIgnoreCase("add")) {
			pw.println("<h1 style='color:red;text-align:center'>Add:::"+(val1+val2)+"</h1>");
		}
		else if(pval.equalsIgnoreCase("sub")) {
			pw.println("<h1 style='color:red;text-align:center'>Sub:::"+(val1-val2)+"</h1>");
		}
		else if(pval.equalsIgnoreCase("mul")) {
			pw.println("<h1 style='color:red;text-align:center'>Mul:::"+(val1*val2)+"</h1>");
		}
		else if(pval.equalsIgnoreCase("link1")) {
			pw.println("<h1> System properties are </h1>");
			props=System.getProperties();
			pw.println(props);
		}
		else {
			pw.println("<b> Date and time ::</b>"+new java.util.Date());
		}
      //add hyperlink
		pw.println("<br> <a href='form.html'>home</a>");
      //close stream
		pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}//doPost(-,-)
}//class
