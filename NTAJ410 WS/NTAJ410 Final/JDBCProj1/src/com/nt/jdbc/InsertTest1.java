package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/* App to insert the record by collecting values from Enduser
 * Version : 1.0
 *  Author :Team-J
 */
public class InsertTest1 {

	public static void main(String[] args) {
	  Scanner sc=null;
	  int sno=0;
	  String sname=null,sadd=null;
	  Connection con=null;
	  Statement st=null;
	  String query=null;
	  int count=0;
	  try {
		  //read inputs
		  sc=new Scanner(System.in);
		  if(sc!=null) {
			  System.out.println("Enter Student number::");
			  sno=sc.nextInt();  //gives 567
			  System.out.println("Enter Student name::");
			  sname=sc.next(); //gives bigB 
			  System.out.println("Enter  student address::");
			  sadd=sc.next(); // gives mumbai
		  }
		  //convert given input values as required for the SQL Query
		  sname="'"+sname+"'"; //gives 'bigB'
		  sadd="'"+sadd+"'"; //gives 'mumbai'
		  //register JDBC driver s/w
		 // Class.forName("oracle.jdbc.driver.OracleDriver");
		  //establish the connection
		  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
		  //con=DriverManager.getConnection("jdbc:oracle:oci8:@xe","system","manager");
		  //create Statement object
		  if(con!=null)
			  st=con.createStatement();
		  //prepare SQL Query
		         // insert into student values(567,'bigB','mumbai')
		  query=" insert into student values("+sno+	","+sname+","+sadd+")";
		  System.out.println(query);
		  //send and execute SQL Query in DB s/w
		  if(st!=null)
			  count=st.executeUpdate(query);
		  if(count==0)
			  System.out.println("Record not inserted");
		  else
			  System.out.println("Record inserted");
		  System.out.println("con object class name::"+con.getClass());
		  System.out.println("st obj class name::"+st.getClass());
		  
		  
	  }//try
	  catch(SQLException se) {
		  if(se.getErrorCode()==1)
			  System.out.println(sno+" student number  already registered with us::"+se.getMessage());
		  else if(se.getErrorCode()==12899)
			  System.out.println("can not more than col size data::"+se.getMessage());
		  else
			  System.out.println("Unknown DB problem"+se.getMessage());
	  }
	/*  catch(ClassNotFoundException cnf) {
		  cnf.printStackTrace();
	  }*/
	  catch(Exception e) {
		  e.printStackTrace();
	  }
	  finally {
		  //close jdbc  objs
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
