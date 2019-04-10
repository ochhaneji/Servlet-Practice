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

@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = null;
		int[] kiwi;
		int val = 0;
		int square = 0;
		RequestDispatcher rd = null;
		ServletContext localContext = null, foreignContext = null;
		// general settings
		pw = res.getWriter();
		res.setContentType("text/html");
		// read data from form
		val = Integer.parseInt(req.getParameter("t1"));
		// calculate the square
		square = val * val;
		// send the output to the browser from first servlet
		pw.println("<h1>(First SErvlet) Square of the Number is :: " + square + "</h1>");
		// set and get data from second servlet
		// get servlet context object of current web application
		localContext = getServletContext();
		// get servlet context object of WAtwoApp web application
		foreignContext = localContext.getContext("/WAtwoApp");
		// get request dispatcher oject pointing to second servlet
		rd = foreignContext.getRequestDispatcher("/secondurl");
		rd.include(req, res);

		// close stream
		pw.close();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
