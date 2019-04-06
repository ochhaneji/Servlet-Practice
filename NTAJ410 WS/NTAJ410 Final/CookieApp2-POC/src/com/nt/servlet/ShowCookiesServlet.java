package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/shurl")
public class ShowCookiesServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		Cookie cookies[]=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read cookies
		cookies=req.getCookies();
		pw.println("<h1 style='color:red;text-align:center'>The Cookies are </h1>");
		if(cookies!=null) {
			pw.println("<table border='1' bgcolor='cyan'>");
			pw.println("<tr><th>Cookie name </th><th>Cookie value </th><th>Domain name </th></tr>");
			for(Cookie ck:cookies) {
				pw.println("<tr><td>"+ck.getName()+"</td><td>"+ck.getValue()+"</td><td>"+ck.getDomain()+"</td></tr>");
			}
			pw.println("</table>");
		}//if
		//close stream
		pw.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}

}
