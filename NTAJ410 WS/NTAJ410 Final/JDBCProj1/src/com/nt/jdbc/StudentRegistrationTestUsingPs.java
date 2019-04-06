package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentRegistrationTestUsingPs {
	private static final String  INSERT_STUDENT_QUERY="INSERT INTO STUDENT VALUES(?,?,?)";

	public static void main(String[] args) {
		Scanner sc=null;
		int count=0;
		Connection con=null;
		PreparedStatement ps=null;
		int no=0;
		String name=null,addrs=null;
		int result=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Students count::");
				count=sc.nextInt();
			}//if
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			//create PreparedStatement object having pre-compiled SQL Query
			if(con!=null)
				ps=con.prepareStatement(INSERT_STUDENT_QUERY);
			//read multiple student details from enduser
			if(ps!=null && sc!=null) {
				for(int i=1;i<=count;++i) {
					System.out.println("Enter "+i+" studnet details::");
					System.out.println("Enter student number::");
					no=sc.nextInt();
					System.out.println("Enter student name::");
					name=sc.next();
					System.out.println("Enter student address::");
					addrs=sc.next();
					//set each student details to query param values
					ps.setInt(1, no);
					ps.setString(2,name);
					ps.setString(3,addrs);
					//execute the Query
					result=ps.executeUpdate();
					//process the result
					if(result==0)
						System.out.println(i+" student Record insertion is failed");
					else
						System.out.println(i+" student Record insertion is succeded");
				}//for
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
			try {
			//close jdbc objs
			  if(ps!=null)
				  ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				//close jdbc objs
				  if(con!=null)
					  con.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			try {
				//close jdbc objs
				  if(sc!=null)
					  sc.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
		}//finally
	}//main
}//class
