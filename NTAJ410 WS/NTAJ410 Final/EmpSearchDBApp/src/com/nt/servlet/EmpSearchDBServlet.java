//EmpSearchDBServlet.java
package com.nt.servlet;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.sql.*; //jdbc api
import java.io.*;

@WebServlet("/empurl")
public class  EmpSearchDBServlet extends  HttpServlet
{ 
	 private static final String GET_EMPDETAILS_BY_NO="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE EMPNO=?";


	 public  void  doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		  PrintWriter pw=null;
		  int eno=0;
		  Connection con=null;
		  PreparedStatement ps=null;
		  ResultSet rs=null;
           //get PrintWriter
		   pw=res.getWriter();
		   res.setContentType("text/html");
		   //read form data
		   eno=Integer.parseInt(req.getParameter("eno"));
		   try{
		   //Active JDBC driver s/w by loading  JDBC Driver class
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   //establish the connection
               con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
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
		   }//try
		   catch(SQLException se){
			   se.printStackTrace();
		   }
		   catch(ClassNotFoundException cnf){
			   cnf.printStackTrace();
		   }
		   catch(Exception e){
			   e.printStackTrace();
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

		   //add hyperlink
		   pw.println("<br> <a href='input.html'>home</a>");
		   //close stream
		   pw.close();
	 }//doGet(-,-)

	 public  void  doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
           doGet(req,res);
	 }//doPost(-,-)

}//class