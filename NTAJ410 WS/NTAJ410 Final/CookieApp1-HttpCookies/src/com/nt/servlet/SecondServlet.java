package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {
	@Resource(name="DsJndi")
	  private  DataSource  ds;
	 private static final String  INSERT_QUERY="INSERT INTO  COOKIE_PERSONALINFO VALUES(?,?,?,?)";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String  name=null,fname=null;
		float income=0.0f;
		int tax=0;
		Cookie  cookies[]=null;
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		
		
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form1/req1  data from Cookies (Session tracking)
		cookies=req.getCookies();
		if(cookies!=null) {
		   name=cookies[0].getValue();
		   fname=cookies[1].getValue();
		}
		//read form2/req2  data 
		income=Float.parseFloat(req.getParameter("income"));
		tax=Integer.parseInt(req.getParameter("tax"));
		//write  form1/req1 and form1/req2 data to DB table as record
		       
		      try {
		    	//get Pooled JDBC con object
				con=ds.getConnection();
				//get PreparedStatement object
				ps=con.prepareStatement(INSERT_QUERY);
				//set values to Query params
				ps.setString(1,name);
				ps.setString(2,fname);
				ps.setFloat(3,income);
				ps.setInt(4,tax);
				//execute the SQL Query
				count=ps.executeUpdate();
				//process the Result
				if(count==0)
					pw.println("<h1 style='color:red;text-align:center'>Registration Failed </h1>");
				else
					pw.println("<h1 style='color:red;text-align:center'>Registration Succeded </h1>");
				//Display both form1/req1 data
				pw.println("<br><br> <h3 style='text-align:center'>Both Forms data </h3>");
				pw.println("<b> form1/req1 data ::: </b>"+name+"....."+fname+"<br>");
				pw.println("<b> form2/req2 data ::: </b>"+income+"....."+tax+"<br>");
				pw.println("<br><br> <a href='input.html'>home </a>");
			} catch (SQLException e) {
				e.printStackTrace();
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
		    	
		    	try {
			    	if(pw!=null)
			    		pw.close();
			    	}
			    	catch(Exception ioe) {
			    		ioe.printStackTrace();
			    	}
		    }//finally
	}//doGet(-,-)

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}

}
