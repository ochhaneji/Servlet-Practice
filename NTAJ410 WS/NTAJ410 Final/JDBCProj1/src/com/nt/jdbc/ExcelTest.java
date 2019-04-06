package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExcelTest {
  private static final String  GET_EXCEL_RECORDS="SELECT * FROM College.Sheet1 where sno>=?";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//register JDBC driver s/w (optional)
			Class.forName("com.hxtt.sql.excel.ExcelDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:Excel:///e:/apps/ExcelDB");
			//create PreparedStatement object
			if(con!=null)
			   ps=con.prepareStatement(GET_EXCEL_RECORDS);
			//set query param values
			if(ps!=null)
				ps.setInt(1,100);
			//execute the Query
			if(ps!=null)
				rs=ps.executeQuery();
			
			
			//process the ResultSet
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
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
