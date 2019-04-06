package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/errorurl",name="err")
public class ErrorServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("ErrorServlet:doGet(-,-)");
		PrintWriter pw=null;
		//get PrintWriter
		pw=res.getWriter();
		res.setContentType("text/html");
		//Display Non-Technical guiding message to enduser
		pw.println("<h1 style='color:red;text-align:center'>Internal Problem </h1>");
		pw.println("<br> <a href='input.html'>try agin</a>");
		//close stream 
		pw.close();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	     doGet(req,res);
	}

}
