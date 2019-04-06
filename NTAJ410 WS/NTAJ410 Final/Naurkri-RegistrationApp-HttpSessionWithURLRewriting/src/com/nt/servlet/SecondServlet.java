package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String skill=null;
		int exp=0;
		HttpSession ses=null;
		//General Settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form1/req1 data
		skill=req.getParameter("skill");
		exp=Integer.parseInt(req.getParameter("exp"));
		//Locate HttpSession object
		ses=req.getSession(false);
		//keep form1/req1 data in Session obj as Session attribute values
		ses.setAttribute("skill",skill);
		ses.setAttribute("exp",exp);
		//generate form3 dynamcally form here
		pw.println("<h1 style='color:red;text-align:center'>Naukri :: Registration-Form3 </h1>");
		pw.println("<form action='"+res.encodeURL("thirdurl")+"' method='GET'>");
		pw.println(" Expected salary :: <input type='text' name='sal'><br>"); 
		pw.println("<br> Location:: <input type='text' name='loc'><br>");
		pw.println("<input type='submit' value='submit'>");
		pw.println("</form>");
		pw.println("<br><br><b> Session Id:: "+ses.getId()+"</b>");
		//close stream
		pw.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
