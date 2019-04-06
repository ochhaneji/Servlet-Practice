package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class DateRetrievTest {
  private static final String DATE_RETRIEVE_QUERY="SELECT PID,PNAME,DOB,DOJ,DOM FROM PERSON_DATES";
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int pid=0;
		String pname=null;
		java.sql.Date sqdob=null,sqdoj=null,sqdom=null;
		java.util.Date udob=null,udoj=null,udom=null;
		SimpleDateFormat sdf=null;
		String sdob=null,sdoj=null,sdom=null;
		try {
		/*	//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");*/
			
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql:///ntaj410db1","root","root");
			
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//send and execute SQL query in DB s/w
			if(st!=null)
				rs=st.executeQuery(DATE_RETRIEVE_QUERY);
			//process the ResultSet object
			if(rs!=null) {
				while(rs.next()) {
					pid=rs.getInt(1);
					pname=rs.getString(2);
					sqdob=rs.getDate(3);
					sqdoj=rs.getDate(4);
					sqdom=rs.getDate(5);
					//converting java.sql.Date class objs to java.util.Date class objs
					udob=(java.util.Date)sqdob;
					udoj=(java.util.Date)sqdoj;
					udom=(java.util.Date)sqdom;
					//Convert java.util.Date class objs to String date values..
					sdf=new SimpleDateFormat("dd-MM-yyyy");
					sdob=sdf.format(udob);
					sdoj=sdf.format(udoj);
					sdom=sdf.format(udom);
					System.out.println(pid+"  "+pname+"  "+sdob+"   "+sdoj+"  "+sdom);
				}//while
			}//if
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
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
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

	}//main
}//class
