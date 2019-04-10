//EmpSearchDBServlet.java
package com.nt.servlet;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.sql.*; //jdbc api
import java.util.Enumeration;
import java.io.*;


public class  EmpSearchDBServlet extends  HttpServlet
{ 
	 private static final String GET_EMPDETAILS_BY_NO="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE EMPNO=?";


	 public  void  doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		  PrintWriter pw=null;
		  int eno=0;
		  Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
		  ServletContext sc=null;
		  String s1=null,s2=null,s3=null,s4=null;
		  RequestDispatcher rd=null,rd1=null,rd2=null;
		 
			  //include header content
			rd1=req.getRequestDispatcher("/headerurl");
			rd1.include(req,res);
           //get PrintWriter
		   pw=res.getWriter();
		   res.setContentType("text/html");
		 
		   //read form data
		   eno=Integer.parseInt(req.getParameter("eno"));
		 
			   //get Access to ServletContext obj
			   sc=getServletContext();
			   //read context param values
			   s1=sc.getInitParameter("driver");
			   s2=sc.getInitParameter("url");
			   s3=sc.getInitParameter("dbuser");
			   s4=sc.getInitParameter("dbpwd");
		   //Active JDBC driver s/w by loading  JDBC Driver class
			   try {
		   Class.forName(s1);
		   //establish the connection
               con=DriverManager.getConnection(s2,s3,s4);
           //create PreparedStatement object
		      ps=con.prepareStatement(GET_EMPDETAILS_BY_NO);
			  //set value to query param
			  ps.setInt(1,eno);
			  //execute the Query
                rs=ps.executeQuery();
				//process the ResultSet
				if(rs.next()){
					 pw.println("<table border='1' align='center'>");
					 pw.println("<tr>");
					 pw.println("<td>"+rs.getInt(1)+"</td>");
					 pw.println("<td>"+rs.getString(2)+"</td>");
					 pw.println("<td>"+rs.getString(3)+"</td>");
					 pw.println("<td>"+rs.getInt(4)+"</td>");
					 pw.println("</tr>");
					 pw.println("</table>");
				}//if
				else{
                     pw.println("<h1 style='color:red'> No records found </h1>");
				}
				  //add hyperlink
				   pw.println("<br> <a href='input.html'>home</a><br><br>");
				   pw.close();
				//include footer
				rd2=req.getRequestDispatcher("/footer.html");
				rd2.include(req,res);
		   }//try
		   catch(Exception e){
			   //forward to ErrorServlet 
			   //rd=req.getRequestDispatcher("errorurl");
			/*
			 * sc=getServletContext(); 
			 * rd=sc.getRequestDispatcher("/errorurl");
			 */
/*			   sc=getServletContext();
			   rd=sc.getNamedDispatcher("err");
*/           
			    //rd=req.getRequestDispatcher("myError.html");
			 //  rd=req.getRequestDispatcher("/myurl");
			   e.printStackTrace();
			   rd=req.getRequestDispatcher("/errorurl");
			    rd.forward(req,res);
			
		   }
		   finally{
                 //close jdbc objs
				 try{
					 if(rs!=null)
						 rs.close();
				 }
				 catch(SQLException se){
					 se.printStackTrace();
				 }
 				 try{
					 if(ps!=null)
						 ps.close();
				 }
				 catch(SQLException se){
					 se.printStackTrace();
				 }

 				 try{
					 if(con!=null)
						 con.close();
				 }
				 catch(SQLException se){
					 se.printStackTrace();
				 }
		   }//finally

		  
		   //close stream
		   pw.close();
	 }//doGet(-,-)

	 public  void  doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
           doGet(req,res);
	 }//doPost(-,-)

}//class