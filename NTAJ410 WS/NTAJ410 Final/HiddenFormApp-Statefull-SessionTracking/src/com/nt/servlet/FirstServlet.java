package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    PrintWriter pw=null;
	    String pname=null,fname=null,ms=null;
	    //get PrintWriter
	    pw=res.getWriter();
	    res.setContentType("text/html");
	    //read form1/req1 data...
	    pname=req.getParameter("pname");
	    fname=req.getParameter("fname");
	    ms=req.getParameter("ms");
	 
	    if(ms.equalsIgnoreCase("married")) {
	    	//generate form2 dynamically here
	    	pw.println("<form action='secondurl' method='POST'>");
	    	pw.println("spouse name :: <input type='text' name='f2t1'><br>");
	    	pw.println("no.of kids :: <input type='text' name='f2t2'><br>");
	    	pw.println("<input type='hidden' name='hname' value="+pname+">");
	    	pw.println("<input type='hidden' name='hfname' value="+fname+">");
	    	pw.println("<input type='hidden' name='hms' value="+ms+">");
	    	pw.println("<input type='submit' value='submit'>");
	    	pw.println("</form>");
	    }
	    else {
	    	//generate form2 dynamically here
	    	pw.println("<form action='secondurl' method='POST'>");
	    	pw.println("When do u want to marry? :: <input type='text' name='f2t1'><br>");
	    	pw.println("Why do u want to marry? :: <input type='text' name='f2t2'><br>");
	    	pw.println("<input type='hidden' name='hname' value="+pname+">");
	    	pw.println("<input type='hidden' name='hfname' value="+fname+">");
	    	pw.println("<input type='hidden' name='hms' value="+ms+">");
	    	pw.println("<input type='submit' value='submit'>");
	    	pw.println("</form>");
	    }
	    //close stream
	    pw.close();
	}//doGet(-,-)

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	      doGet(req,res);
	}

}
