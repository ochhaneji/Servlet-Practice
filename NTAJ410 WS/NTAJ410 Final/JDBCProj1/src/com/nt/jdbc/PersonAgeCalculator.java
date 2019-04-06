package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonAgeCalculator {
	private static final String GET_AGE_OF_PERSON="SELECT (SYSDATE-DOB)/365.0 FROM PERSON_DATES WHERE PID=?";
	public static void main(String[] args) {
		 
		Scanner sc=null;
		int no=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		float age=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Person Id::");
				no=sc.nextInt();
			}//if
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
					age=rs.getFloat(1);
					System.out.println("Person Age is:: "+age);
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
