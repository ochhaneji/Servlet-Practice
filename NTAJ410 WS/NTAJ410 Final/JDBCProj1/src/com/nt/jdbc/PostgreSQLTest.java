package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
		//register JDBC driver
		//Class.forName("org.postgresql.Driver");
		//establish the connection
		con=DriverManager.getConnection("jdbc:postgresql:NTAJ410DB", "postgres", "tiger");
		con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/NTAJ410DB", "postgres", "tiger");
		//create Statement object
		if(con!=null)
			st=con.createStatement();
		//send and execute SQL Query
		if(st!=null)
			rs=st.executeQuery("select * from Product");
		//gather and process the Results
		if(rs!=null) {
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getFloat(3));
			}//while
		}//if
	}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		/*catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}*/
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
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
