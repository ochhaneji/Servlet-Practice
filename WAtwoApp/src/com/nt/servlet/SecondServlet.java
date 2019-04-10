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

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = null;
		int sVal = 0;
		int cube = 0;
		// general setting
		pw = res.getWriter();
		res.setContentType("text/html");
		// get the data from the form page
		sVal = Integer.parseInt(req.getParameter("t1"));
		cube = sVal * sVal * sVal;
		// display the output on to the browser
		pw.println("<h1>(Second Servlet) The cube of the given number is :: "+cube+"</h1>");
		// do not close stream here
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
