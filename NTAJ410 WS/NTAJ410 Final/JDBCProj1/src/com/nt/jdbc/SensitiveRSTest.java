package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SensitiveRSTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int cnt=0;
		try {
			//register Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish  the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement obj
			if(con!=null)
				st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						                                     ResultSet.CONCUR_UPDATABLE);
			//create Scrollable and Sensitive ResultSet obj
			if(st!=null)
			   rs=st.executeQuery("SELECT SNO,SNAME,SADD FROM STUDENT");
			//process the ResultSet obj
			if(rs!=null) {
				while(rs.next()) {
					//rs.refreshRow();
					if(cnt==1) {
						Thread.sleep(30000); //modify DB table record fromSQL Prompt
					}
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
					cnt++;
				}
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
