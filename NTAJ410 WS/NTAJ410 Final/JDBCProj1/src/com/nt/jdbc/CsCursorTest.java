package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.internal.OracleTypes;

/*create or replace procedure p_getEmpDetailsByNameChars(initchars in varchar,
        details out sys_refcursor)as
begin
open details for
select empno,ename,job,sal from emp where ename like initchars;
end;
/
*/
public class CsCursorTest {
   private static final String  CALL_PROCEDURE="{ CALL P_GETEMPDETAILSBYNAMECHARS(?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
	    String initChars=null;
	    Connection con=null;
	    CallableStatement cs=null;
	    ResultSet rs=null;
	    boolean flag=false;
		try {
	    //read inputs 
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter initial chars of emp name::");
				initChars=sc.next();
			}
			//register JDBC Driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create CallableStatement obj
			if(con!=null)
				cs=con.prepareCall(CALL_PROCEDURE);
			//register OUT params with JDBC types/Oracle Types
			if(cs!=null)
				cs.registerOutParameter(2,OracleTypes.CURSOR);
			//set values to IN Params
			if(cs!=null)
				cs.setString(1,initChars+"%");
			//call PL/SQL Procedure
			if(cs!=null)
				cs.execute();
			//get ResultSet obj from cursor type OUT param
			if(cs!=null)
			  rs=(ResultSet) cs.getObject(2);
			//process the ResultSet obj
			if(rs!=null) {
				while(rs.next()) {
					 flag=true;
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
				}//while
				if(flag==false)
					System.out.println("Recrods not found");
					
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
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(cs!=null) 
					cs.close();
				}//try
				catch(SQLException se) {
					se.printStackTrace();
				}
			try {
				if(con!=null) 
					con.close();
				}//try
				catch(SQLException se) {
					se.printStackTrace();
				}
			try {
				if(sc!=null) 
					sc.close();
				}//try
				catch(Exception e) {
					e.printStackTrace();
				}
		}//finally
	}//main
}//class
