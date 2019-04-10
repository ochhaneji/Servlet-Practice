package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet (value="/errorurl",name="err")
public class ErrorServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = null;
		// general setting
		pw = res.getWriter();
		res.setContentType("text/html");

		// printing the non technical msg for the non techincal users
		pw.println("Internal Problem hbhai kuch abi <b>Work in Progress</b> wait kar");
		pw.println("<h1><a href='EmployeeFind.html'>Try Again</a></h1>");
		// close the stream
		pw.close();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}
}
