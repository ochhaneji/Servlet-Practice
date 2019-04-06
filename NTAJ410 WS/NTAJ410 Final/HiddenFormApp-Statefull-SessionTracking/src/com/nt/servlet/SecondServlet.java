package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {
       private static final String  INSERT_QUERY="INSERT INTO HIDDEN_REGISTER VALUES(?,?,?,?,?)";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null,fname=null,ms=null;
		String f2Val1=null,f2Val2=null;
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		//get PrintWriter
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form1/req1 data form hidden boxes of form2
		name=req.getParameter("hname");
		fname=req.getParameter("hfname");
		ms=req.getParameter("hms");
		//read form2/req2 data
		f2Val1=req.getParameter("f2t1");
		f2Val2=req.getParameter("f2t2");
		try {
			//insert form1/req1, form2/req2 data to DB table as record
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedStatement object
			ps=con.prepareStatement(INSERT_QUERY);
			//set values to Query params
			ps.setString(1,name);
			ps.setString(2,fname);
			ps.setString(3,ms);
			ps.setString(4,f2Val1);
			ps.setString(5,f2Val2);
			//execute the SQL Query
			count=ps.executeUpdate();
			if(count==0)
				pw.println("<h1 style='color:red;text-align:center'> Registration failed </h1>");
			else
				pw.println("<h1 style='color:green;text-align:center'> Registration succeded </h1>");
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
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
		//display form1/req1 data 
		pw.println("<br><br><b> form1/req1 data :::"+name+"...."+fname+"......"+ms);
		pw.println("<br><b> form2/req2 data :::"+f2Val1+"...."+f2Val2);
		//add hyperlink
		pw.println("<br><a href='register.html'>home</a>");
		//close stream
		pw.close();
	}//doGet(-,-)

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    doGet(req,res);
	}

}
