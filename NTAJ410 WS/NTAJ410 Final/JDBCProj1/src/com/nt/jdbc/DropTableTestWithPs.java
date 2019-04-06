package com.nt.jdbc;
/* App to perform DDL Operations 
 *   Version : 1.0
 *   author: Team-J
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DropTableTestWithPs {

	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		String query=null;
		int result=0;
		Scanner sc=null;
		String tab=null;
		try {
			// read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter db table name ");
				tab=sc.next();
			}
			  //register JDBC driver s/w
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  //establish the connection
			  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			  //prepare SQL Query
			 // query="DROP TABLE   ?"; //dynamic query
			  query="DROP TABLE "+tab; //static query
			  //create Statement object
			  if(con!=null)
				  ps=con.prepareStatement(query);
			
			  //send and execute SQL Query in DB s/w
			  if(ps!=null)
				  result=ps.executeUpdate();
			  //process the Result
			  if(result!=0)
				  System.out.println("Table not dropped");
			  else
				  System.out.println("Table dropped");
			  System.out.println(result);
		}//try
		catch(SQLException se){
			 System.out.println(se);
		 }
		 catch(ClassNotFoundException cnf){
			  cnf.printStackTrace();
		 }
		 catch(Exception e){
			  e.printStackTrace();
		 }
		 finally{
			 //close jdbc objs
			
			  try{
              if(ps!=null)
				   ps.close();
			 }
			 catch(SQLException se){
				 se.printStackTrace();
			 }

			  try{
              if(con!=null)
				   con.close();
			 }
			 catch(SQLException se){
				 se.printStackTrace();
			 }
			  
			  try{
	              if(sc!=null)
					   sc.close();
				 }
				 catch(Exception e){
					 e.printStackTrace();
				 }
		 }//finally
	}//main
}//class
