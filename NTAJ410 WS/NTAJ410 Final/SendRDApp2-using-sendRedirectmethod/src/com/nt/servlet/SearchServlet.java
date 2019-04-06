package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/searchurl")
public class SearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String ss=null;
		String engine=null;
		String url=null;
		RequestDispatcher rd=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		ss=req.getParameter("ss");
		engine=req.getParameter("engine");
		//prepare URL for SendRedirection
		if(engine.equalsIgnoreCase("google"))
			url="https://www.google.com/search?q="+ss;
		else if(engine.equalsIgnoreCase("bing"))
			url="https://www.bing.com/search?q="+ss;
		else
			url="https://in.search.yahoo.com/search?p="+ss;
		//perform sendRedirection
		System.out.println("Before res.sendRedirect(-)");
		pw.println("Before res.sendRedirect(-)");
		res.sendRedirect(url);
		//res.sendRedirect("abc.html");
		rd=req.getRequestDispatcher("/abc.html");
		rd.include(req,res);
		System.out.println("after res.sendRedirect(-)");
		pw.println("After res.sendRedirect(-)");
	}//doGet(-,-)

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
