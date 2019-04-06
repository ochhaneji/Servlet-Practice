package com.nt.jdbc;
/* App to perform DDL Operations 
 *   Version : 1.0
 *   author: Team-J
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		String query=null;
		int result=0;
		try {
			  //register JDBC driver s/w
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  //establish the connection
			  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			  //create Statement object
			  if(con!=null)
				  st=con.createStatement();
			  //prepare SQL Query
			  query="CREATE TABLE  TEMP(COL1 number(5))";
			  //send and execute SQL Query in DB s/w
			  if(st!=null)
				  result=st.executeUpdate(query);
			  //process the Result
			  if(result!=0)
				  System.out.println("Table not created");
			  else
				  System.out.println("Table created");
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
			  
			  

		 }//finally

	}

}
