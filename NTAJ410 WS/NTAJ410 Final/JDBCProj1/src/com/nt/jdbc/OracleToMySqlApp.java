package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleToMySqlApp {
  private static final String INSERT_MYSQL_QUERY="INSERT INTO ACCOUNT VALUES(?,?,?)";
  private static final String SELECT_ORACLE_QUERY="SELECT * FROM ACCOUNT";
	public static void main(String[] args) {
		Connection oraCon=null,mysqlCon=null;
		Statement  st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int acno=0;
		String holder=null;
		int balance=0;
		try {
			//register JDBC driver s/ws
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.jdbc.Driver");
			//Establish the connections
			oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			mysqlCon=DriverManager.getConnection("jdbc:mysql:///ntaj410db1", "root","root");
			//create Statement objs
			if(oraCon!=null)
				st=oraCon.createStatement();
			if(mysqlCon!=null)
				ps=mysqlCon.prepareStatement(INSERT_MYSQL_QUERY);
			//get Records from Oracle DB table
			if(st!=null)
				rs=st.executeQuery(SELECT_ORACLE_QUERY);
			//process ORacle Records (ResultSet)
			if(rs!=null && ps!=null) {
				while(rs.next()) {
					//get Each record from ResultSet obj (oracle)
					acno=rs.getInt(1);
					holder=rs.getString(2);
					balance=rs.getInt(3);
					//set each above record values to Mysql Insert Query
					ps.setInt(1,acno);
					ps.setString(2,holder);
					ps.setInt(3,balance);
					//execute the Query
					ps.executeUpdate();
				}//while
			}//if
			System.out.println("Records are copied from ORacle DB table to mysql");
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
				if(st!=null)
					st.close();
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
			try {
				if(mysqlCon!=null)
					mysqlCon.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
