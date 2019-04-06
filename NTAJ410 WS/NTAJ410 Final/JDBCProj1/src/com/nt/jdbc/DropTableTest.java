package com.nt.jdbc;
/* App to perform DDL Operations 
 *   Version : 1.0
 *   author: Team-J
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DropTableTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
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
			  //create Statement object
			  if(con!=null)
				  st=con.createStatement();
			  //prepare SQL Query
			  query="DROP TABLE   "+tab;
			  //send and execute SQL Query in DB s/w
			  if(st!=null)
				  result=st.executeUpdate(query);
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
              if(st!=null)
				   st.close();
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
