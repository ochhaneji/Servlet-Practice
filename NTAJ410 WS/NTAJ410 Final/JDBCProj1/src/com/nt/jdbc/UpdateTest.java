package com.nt.jdbc;
/* App to  update student name,address of student based on the given sno
 * Version: 1.0
 * author : Team-J
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		String newName=null;
		String newAddrs=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		int count=0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter existing student number::");
				no=sc.nextInt();  //gives 101
				sc.nextLine();
				System.out.println("Enter new  name::");
				newName=sc.nextLine(); //gives naya raja
				System.out.println("Enter new address::");
				newAddrs=sc.nextLine(); //gives navi hyd
			   }
			     //convert input values as required for SQL Query
			     newName="'"+newName+"'"; //gives 'naya raja'
			     newAddrs="'"+newAddrs+"'"; //gives 'navi hyd'
			
				
				  //register JDBC driver s/w
				  Class.forName("oracle.jdbc.driver.OracleDriver");
				  //establish the connection
				  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
				  //create Statement object
				  if(con!=null)
					  st=con.createStatement();
				  //prepare SQL Query
				     //UPDATE STUDENT SET SNAME='new raja' ,SADD='new hyd' WHERE SNO=103
				    query="UPDATE STUDENT SET SNAME="+newName+" ,SADD="+newAddrs+" WHERE SNO="+no;
				    System.out.println(query);
				    //send and execute SQL Query in Db s/w
				    if(st!=null)
				     count=st.executeUpdate(query);
				    //process the result
				    if(count==0)
				    	System.out.println("Record not found to update");
				    else
				    	System.out.println("Record found and updated");
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
			 //close jdbc  objs
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
