package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ResultSetMEtaDataTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ResultSetMetaData rsmd=null;
	     int colCount=0;
	     Scanner sc=null;
	     String tabName=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter DB table name::");
				tabName=sc.next();
			}
			//register Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish  the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement obj
			if(con!=null)
				st=con.createStatement();
			//create Scrollable and Sensitive ResultSet obj
			if(st!=null)
			   rs=st.executeQuery("SELECT * FROM "+tabName);
			//create ResultSetMetaData obj
			if(rs!=null) {
				rsmd=rs.getMetaData();
			}
			//get column count;
			if(rsmd!=null)
				colCount=rsmd.getColumnCount();
			//print col names
			   for(int i=1;i<=colCount;++i) {
				   System.out.print(rsmd.getColumnLabel(i)+"   ");
			   }
			   System.out.println();
			   
			 //print col data types
			   for(int i=1;i<=colCount;++i) {
				   System.out.print(rsmd.getColumnTypeName(i)+"   ");
			   }
			   System.out.println();
			
			
			//process the ResultSet obj
			if(rs!=null) {
				while(rs.next()) {
	                for(int i=1;i<=colCount;++i) {
	                	System.out.print(rs.getString(i)+"  ");
	                }
	                System.out.println();
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
