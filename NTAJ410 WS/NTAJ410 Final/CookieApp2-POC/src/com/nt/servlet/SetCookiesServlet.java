package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/scurl")
public class SetCookiesServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		Cookie ck1=null,ck2=null,ck3=null,ck4=null;
		//get PrintWrtier 
		pw=res.getWriter();
		res.setContentType("text/html");
		//create Cookies
		   //InMemory Cookies
		ck1=new Cookie("ap","amaravathi");
		ck2=new Cookie("mh","mumbai");
		 res.addCookie(ck1); res.addCookie(ck2);
		 //Persistent Cookies
		 ck3=new Cookie("mp","bhopal");
		 ck4=new Cookie("od","Bhuvaneshwar");
		 ck3.setMaxAge(120);
		 ck4.setMaxAge(120);
		 res.addCookie(ck3); res.addCookie(ck4);
		 
		 pw.println("<b>Cookies are added </b>");
		 //close stream
		 pw.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}

}
