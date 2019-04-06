package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdatableRsTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//register Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish  the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement obj
			if(con!=null)
				st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						                                     ResultSet.CONCUR_READ_ONLY);
			//create Scrollable and Sensitive ResultSet obj
			if(st!=null)
			   rs=st.executeQuery("SELECT SNO,SNAME,SADD FROM STUDENT");
			//Process the ResultSet obj
			if(rs!=null) {
				while(rs.next()) {
				   System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3));
				}
			}
		/*	//insert operation
			if(rs!=null) {
				rs.moveToInsertRow();
				rs.updateInt(1,1001);
				rs.updateString(2, "rajesh");
				rs.updateString(3,"vizag");
				rs.insertRow();
				System.out.println("record inserted");
			}*/
			
		/*	//update operation
			if(rs!=null) {
				rs.absolute(4);
				rs.updateString(3,"bengaluru");
				rs.updateRow();
				System.out.println("Record updated");
			}*/
			
			if(rs!=null) {
				rs.absolute(4);
				rs.deleteRow();
				System.out.println("Record deleted");
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
