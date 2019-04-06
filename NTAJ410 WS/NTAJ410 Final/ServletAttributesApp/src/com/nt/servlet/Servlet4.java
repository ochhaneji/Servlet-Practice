package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/s4url")
public class Servlet4 extends HttpServlet {
	
    @Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	RequestDispatcher rd=null;
    	PrintWriter pw=null;
    	HttpSession  ses=null;
    	ServletContext sc=null;
    	//get PrintWriter
    	pw=res.getWriter();
    	res.setContentType("text/html");
    	//read and display request attribute value
    	pw.println("servlet4--> attr1(req) value::"+req.getAttribute("attr1"));
    	//read and display session attribute values
    	ses = req.getSession();
    	pw.println("<br>Servlet4---> attr2(ses) value::"+ses.getAttribute("attr2"));
    	//read and display ServletContext attribute values
    	sc=getServletContext();
    	pw.println("<br>Servlet4---> attr3(sc) value::"+sc.getAttribute("attr3"));

    	///close stream
    	pw.close();
	}//doGet(-,-)	
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	doGet(req,res);
    }//doPost(-,-)

}
