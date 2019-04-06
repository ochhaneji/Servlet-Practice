package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonAgeCalculatorJAVA {
	private static final String GET_AGE_OF_PERSON="SELECT DOB FROM PERSON_DATES WHERE PID=?";
	public static void main(String[] args) {
		 
		Scanner sc=null;
		int no=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		float age=0;
		java.sql.Date sqdob=null;
		java.util.Date udob=null;
		long  dobMs=0,sysDateMs=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Person Id::");
				no=sc.nextInt();
			}//if
		/*	//register JDBC driver s/w
			Class.forName("com.mysql.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj410db1", "root","root");*/
			
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","manager");
			
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(GET_AGE_OF_PERSON);
			//set input values to query params
			if(ps!=null)
				ps.setInt(1,no);
			//execute the Query
			if(ps!=null)
				rs=ps.executeQuery();
			//process the ResultSet
			if(rs!=null) {
				if(rs.next()) {
					sqdob=rs.getDate(1);
					udob=(java.util.Date)sqdob;
					dobMs=udob.getTime();
					sysDateMs=new java.util.Date().getTime();
					System.out.println("Person age (in Ms)"+(sysDateMs-dobMs));
					System.out.println("Person age age in Secs"+(sysDateMs-dobMs)/1000.0f);
					System.out.println("Person age  in Min"+ (sysDateMs-dobMs)/(1000.0f*60));
					System.out.println("Person age  in Hours"+ (sysDateMs-dobMs)/(1000.0f*60*60));
					System.out.println("Person age  in days"+ (sysDateMs-dobMs)/(1000.0f*60*60*24));
					System.out.println("Person age  in months"+ (sysDateMs-dobMs)/(1000.0f*60*60*24*30));
					System.out.println("Person age  in years"+ (sysDateMs-dobMs)/(1000.0f*60*60*24*365));
				}
				else {
					System.out.println("Person not found");
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
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
