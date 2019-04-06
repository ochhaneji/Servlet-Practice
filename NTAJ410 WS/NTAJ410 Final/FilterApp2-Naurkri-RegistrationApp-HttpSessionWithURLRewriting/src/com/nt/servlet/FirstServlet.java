package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null,addrs=null;
		int age=0;
		HttpSession ses=null;
		//General Settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form1/req1 data
		name=req.getParameter("name");
		addrs=req.getParameter("addrs");
		age=Integer.parseInt(req.getParameter("age"));
		//create HttpSession object
		ses=req.getSession(true);
		//keep form1/req1 data in Session obj as Session attribute values
		ses.setAttribute("name",name);
		ses.setAttribute("addrs",addrs);
		ses.setAttribute("age", age);
		//set Max Inactive Interval
		//ses.setMaxInactiveInterval(60);
		//generate form2 dynamcally form here
		pw.println("<h1 style='color:red;text-align:center'>Naukri :: Registration-Form2 </h1>");
		pw.println("<form action='"+res.encodeURL("secondurl")+"' method='GET'>");
		pw.println(" Skill :: <select name='skill'>");
		pw.println("<option value='java'>Java Domain </option>");
		pw.println("<option value='.net'>.Net Domain </option>");
		pw.println("<option value='php'>PHP Domain </option>");
		pw.println("<option value='ui'>UI Domain </option>");
		pw.println("</select>");
		pw.println("<br> Experience:: <input type='text' name='exp'><br>");
		pw.println("<input type='submit' value='continue'>");
		pw.println("</form>");
		pw.println("<br><br><b> Session Id:: "+ses.getId()+"</b>");
		pw.println("<br> <span style='color:red'>Req count::"+getServletContext().getAttribute("reqCount")+"</span>");
		//close stream
		pw.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
