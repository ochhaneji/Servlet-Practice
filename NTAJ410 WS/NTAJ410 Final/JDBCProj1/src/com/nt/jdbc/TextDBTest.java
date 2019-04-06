package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TextDBTest {
   private static final String  GET_CSV_QUERY="SELECT * FROM file1";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//register JDBC driver s/w
			Class.forName("com.hxtt.sql.text.TextDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:Text:///E:/Apps/TextDB");
			//create PreparedStatement obj
			if(con!=null)
			  ps=con.prepareStatement(GET_CSV_QUERY);
			//send and execute SQL query in DB s/w
			if(ps!=null)
				rs=ps.executeQuery();
			//process the ResultSet obj
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getString(1)+" "+rs.getString(2)+"  "+rs.getString(3));
					
				}
			}
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

	}//main
}//class
