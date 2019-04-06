package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsTest1 {
  private static final String  CALL_P_FIRST_PRO_QUERY="{CALL P_FIRST_PRO(?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		int fno=0,sno=0;
		Connection con=null;
		CallableStatement cs=null;
		float result=0;
		
		try {
    	   //read inputs
    	   sc=new Scanner(System.in);
    	   if(sc!=null) {
    		   System.out.println("Enter first value::");
    		   fno=sc.nextInt();
    		   System.out.println("Enter Second value::");
    		   sno=sc.nextInt();
    	   }
    	   //register JDBC driver s/w
    	   Class.forName("oracle.jdbc.driver.OracleDriver");
    	   //establish the connection
    	   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
    	   //create CallableStatment  object
    	   if(con!=null)
    		   cs=con.prepareCall(CALL_P_FIRST_PRO_QUERY);
    	   //register OUT params of Query with JDBC ttypes
    	   if(cs!=null)
    		  cs.registerOutParameter(3,Types.FLOAT);
    	   //set values to IN params
    	   if(cs!=null) {
    		   cs.setInt(1,fno);
    		   cs.setInt(2,sno);
    	   }
    	   //call /execute PL/SQL Procure
    	   if(cs!=null) {
    		   cs.execute();
    	   }
    	   //gather results from Out params
    	   if(cs!=null)
    		   result=cs.getFloat(3);
    	   System.out.println("Result is :::"+result);
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
				if(cs!=null)
					cs.close();
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
	}//method
}//class
