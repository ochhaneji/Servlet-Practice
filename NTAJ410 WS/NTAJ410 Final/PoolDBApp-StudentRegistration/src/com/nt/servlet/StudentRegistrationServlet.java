package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/regurl")
public class StudentRegistrationServlet extends HttpServlet {
	private static final String  INSERT_STUDENT_QUERY="INSERT INTO STUDENT VALUES(SNO_SEQUENCE.NEXTVAL,?,?,?)";
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null,addrs=null,course=null;
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		  //get PrintWriter 
		pw=res.getWriter();
		//set response content type
		res.setContentType("text/html");
		//read form data
		name=req.getParameter("sname");
		addrs=req.getParameter("sadd");
		course=req.getParameter("course");
		try {
		//get Pooled JDBC connection 
		con=getPooledConnection();
		//create JDBC PreparedStatement object
		ps=con.prepareStatement(INSERT_STUDENT_QUERY);
		//set values to query params
		ps.setString(1,name);
		ps.setString(2,addrs);
		ps.setString(3,course);
		//execute the Query
		result=ps.executeUpdate();
		//process the result
		if(result==0)
			pw.println("<h1 style='color:red;text-align:center'> Registration Failed </h1>");
		else
			pw.println("<h1 style='color:green;text-align:center'> Registration sucessful </h1>");
		//add home hyperlink
		pw.println("<br><a href='register.html'>home</a>");
		//close stream 
		pw.close();
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			pw.println("<h1 style='color:red'>Internal DB Problem</h1>");
			pw.println("<br><a href='register.html'>home</a>");
		}
		catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1 style='color:red'>Internal  Problem</h1>");
			pw.println("<br><a href='register.html'>home</a>");
		}
		finally {
			//close jdbc objs
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
	}//doGet(-,-)
	

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    doGet(req,res);
	}
	
	//helper
	private  Connection   getPooledConnection()throws Exception {
		  InitialContext ic=null;
		  DataSource ds=null;
		  Connection con=null;
		  //perform Jndi lookup operation on Jndi registry
		  ic=new InitialContext();
		  //ds=(DataSource)ic.lookup("DsJndi");
		  ds=(DataSource)ic.lookup("java:/comp/env/DsJndi");  //only in Tomcat server
		  //get Pooled JDBC connection object
		  con=ds.getConnection();
		  return con;
	}

}
