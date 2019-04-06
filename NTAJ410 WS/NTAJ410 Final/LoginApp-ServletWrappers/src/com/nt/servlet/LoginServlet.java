package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginurl")
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String user=null,pwd=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		user=req.getParameter("uname");
		pwd=req.getParameter("pwd");
		//validate username,password
		if(user.equals("raja@gmail.com") && pwd.equals("rani"))
			pw.println("<h1 style='color:red;text-align:center'>VALID CREDENTIALS </h1>");
		else
			pw.println("<h1 style='color:red;text-align:center'>INVALID CREDENTIALS </h1>");
		//add hyperlink
		pw.println("<br><br> <a href='login.html'>home</a>");
		//close stream
		pw.close();
	}//doGet(-,-)

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
