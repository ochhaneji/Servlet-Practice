package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExcelToOracleTest {
  private static final String  GET_EXCEL_RECORDS="SELECT * FROM College.Sheet1";
  private static final String  INSERT_ORA_RECORDS="INSERT INTO STUDENT VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Connection excelCon=null,oraCon=null;
		PreparedStatement ps=null;
		Statement st=null;
		ResultSet rs=null;
		int sno=0;
		String name=null,addrs=null,course=null;
		int count=0;
	  try {
		  //register JDBC drivers
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  Class.forName("com.hxtt.sql.excel.ExcelDriver");
		  //establish the connections
		  excelCon=DriverManager.getConnection("jdbc:Excel:///E:/Apps/ExcelDB");
		  oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		  //create STatement objs
		  if(excelCon!=null)
			  st=excelCon.createStatement();
		  if(oraCon!=null)
			  ps=oraCon.prepareStatement(INSERT_ORA_RECORDS);
		  //Get Records From Excel
		  if(st!=null) 
			  rs=st.executeQuery(GET_EXCEL_RECORDS);
		  //Process the ResultSet obj
		  if(rs!=null) {
			  while(rs.next()) {
				  //get each record from excel sheet
				  sno=rs.getInt(1);
				  name=rs.getString(2);
				  addrs=rs.getString(3);
				  course=rs.getString(4);
				  //set values to insert Query (oracle) params
				  ps.setInt(1,sno);
				  ps.setString(2,name);
				  ps.setString(3,addrs);
				  ps.setString(4,course);
				  //execute the Query
				  ps.executeUpdate();
				  count++;
			  }//while
			  System.out.println(count+" records are copied from Excel to ORacle");
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
		  //close all jdbc objs
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
			   if(st!=null)
				   st.close();
			  }
			  catch(SQLException se) {
				  se.printStackTrace();
			  }
		  try {
			   if(excelCon!=null)
				   excelCon.close();
			  }
			  catch(SQLException se) {
				  se.printStackTrace();
			  }
		  try {
			   if(oraCon!=null)
				   oraCon.close();
			  }
			  catch(SQLException se) {
				  se.printStackTrace();
			  }
	  }//finally
	}//main
}//class
