package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {
       

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null,fname=null,ms=null;
		String f2Val1=null,f2Val2=null;
		//get PrintWriter
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form1/req1 data
		name=req.getParameter("pname");
		fname=req.getParameter("fname");
		ms=req.getParameter("ms");
		//read form2/req2 data
		f2Val1=req.getParameter("f2t1");
		f2Val2=req.getParameter("f2t2");
		//display form1/req1 data 
		pw.println("<b> form1/req1 data :::"+name+"...."+fname+"......"+ms);
		pw.println("<br><b> form2/req2 data :::"+f2Val1+"...."+f2Val2);
		//add hyperlink
		pw.println("<br><a href='register.html'>home</a>");
		//close stream
		pw.close();
	}//doGet(-,-)

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    doGet(req,res);
	}

}
