package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/balurl")
public class CheckBalanceServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    PrintWriter pw=null;
	    Random rad=null;
	    
		//general settings
	    pw=res.getWriter();
	    res.setContentType("text/html");
	    //get Closing balnace
	    rad=new Random();
	    pw.println("<h1 style='color:red;text-align:center'> Balance:: "+rad.nextInt(10000000)+"</h1>");
	    pw.println("<br> <a href='index.html'>home</a>");
	    //close stream 
	    pw.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	  doGet(req,res);
	}

}
