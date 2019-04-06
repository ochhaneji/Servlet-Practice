package com.nt.jdbc;
/*  App to delete student record based on the given student  number
 * version : 1.0
 *  author :team-s
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest1 {

	public static void main(String[] args) {
		Scanner sc=null;
	   String city=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		int count=0;
		try {
			//read inputs 
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("enter  city::");
				city=sc.next();
			}//if
			//convert input value as required for SQL Query
			city="'"+city+"'"; //gives 'hyd'
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establihs the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		 // create JDBC statment object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query
			query="DELETE FROM STUDENT WHERE SADD="+city;
			System.out.println(query);
			//send and execute SQL Query in DB s/w
			if(st!=null)
				count=st.executeUpdate(query);
			//process the result
			if(count==0)
				System.out.println("Record not found for deletion");
			else
				System.out.println(count+"  Record(s) found and deleted");
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
