package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MarriageServlet extends HttpServlet {
	
	
	public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("MarriageServlet:process(-,-)");
		 final PrintWriter pw=res.getWriter();
		String name=null,gender=null,tage=null;
		int age=0;
		List<String> errsList=null;
		String vstatus=null;
		//set response content type
		  res.setContentType("application/vnd.ms-word");
		  res.addHeader("Content-Disposition","attachment;fileName=status.doc");
		//read form data (req param values)
		name=req.getParameter("pname");
		tage=req.getParameter("page");
		gender=req.getParameter("gender");
		vstatus=req.getParameter("vflag");
		if(vstatus.equalsIgnoreCase("no")) {
		//write form validation logics (server side)
		System.out.println("Server side form validations.......");
		errsList=new ArrayList();
		if(name==null || name.length()==0 || name.equals("")) {  //required rule
			errsList.add("name is required ");
		}
		
		if(tage==null || tage.length()==0 || tage.equals("")) {  //required rule
			errsList.add("age is required");
		}
		else {
			try {
			age=Integer.parseInt(tage);
			    if(age<1 || age>125) {  // must be the there in the range 1 through 125
			    	errsList.add("age must be in the range of 1 through 125");
			    }
			}
			catch(NumberFormatException nfe) {  //must be numeric value
				errsList.add("Age must be a number");
			}
		}
		
		//display Error Messages
	 if(errsList.size()>0) {
		for(String msg:errsList) {
		    pw.println(" <h4 style='color:red'><li> "+msg+"</li></h4> ");	
		}
		return;
	 }
	/*	if(errsList.size()>0) {
			errsList.forEach(msg->{
				  pw.println(" <h4 style='color:red'><li> "+msg+"</li></h4> ");	
			});
			return;
		}*/
		}//if
		else {
			age=Integer.parseInt(tage);
		}
		
		pw.println("<br> request122 obj class name::"+req.getClass());
		pw.println("<br> response122 obj class name::"+res.getClass());
		
		//write b.logic or request processing logic
		if(gender.equalsIgnoreCase("M")) {
			 if(age<21)
				 pw.println("<h1 style='color:cyan;text-align:center'>Mr. "+name+" u not eligle for marriage ..Enjoy u r life </h1> ");
			 else
				 pw.println("<h1 style='color:green;text-align:center'>Mr. "+name+" u  eligle for marriage ..But think Thrice </h1> ");
		}
		else {
			 if(age<18)
				 pw.println("<h1 style='color:red;text-align:center'>Miss. "+name+" u not eligle for marriage ..Be Happy and make Others Happy </h1> ");
			 else
				 pw.println("<h1 style='color:green;text-align:center'>Miss. "+name+" u  elgible for marriage ..But think Once </h1> ");
		}
		pw.println("<br><a href='input.html'><img src='home.png' width='50' height='50'></a>");
		
		
		
		
		//close stream /Commit the response
		pw.close();
	}//doPost(-,-)
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("MarriageServlet.doGet(-,-)");
		  process(req,res);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("MarriageServlet.doPost(-,-)");
		  process(req,res);
	}

}//class
