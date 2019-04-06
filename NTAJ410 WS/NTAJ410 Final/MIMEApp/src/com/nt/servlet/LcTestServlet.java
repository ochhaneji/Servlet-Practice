package com.nt.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class LcTestServlet extends  HttpServlet
{
	static{
		   System.out.println("LcTestServlet::static block");
	}
	public LcTestServlet(){
	      System.out.println("LcTestServlet::0-param constructor");
	}
    
	public void init(ServletConfig  cg)throws ServletException{
	       super.init(cg);
           System.out.println("LcTestServlet::init(ServletConfig cg) method");
           System.out.println("dbuser init param value::"+cg.getInitParameter("dbuser"));
	}
	
	/*@Override
	public void init() throws ServletException {
		System.out.println("LcTestServlet.init()");
		ServletConfig cg=getServletConfig();
		System.out.println("dbuser init param value::"+cg.getInitParameter("dbuser"));
	
	}*/

	public void doGet(HttpServletRequest req,
		                               HttpServletResponse res)throws ServletException,IOException{
          System.out.println("LcTestServlet::doGet(req,res)");
		PrintWriter pw=null;
             //get PrintWriter obj
			 pw=res.getWriter();
			 res.setContentType("text/html");
			 //write content to response obj
			 pw.println("<b>Date and time ::"+new Date()+"</b>");
			 
			 try {
				 ServletConfig cg=getServletConfig(); 
			pw.println("<br>dbuser init param value::"+cg.getInitParameter("dbuser"));
			 }
			 catch(Exception e) {
				 e.printStackTrace();
			 }

			 //close stream
               pw.close();
	}//service(-,-)

	public void destroy(){
		 System.out.println("LcTestServlet:: destroy() method");
	}//destroy()

	public static void main(String ...a){
          System.out.println("main(-");
	}


}//class

//cmd> javac   -d   .   LcTestServlet.java
