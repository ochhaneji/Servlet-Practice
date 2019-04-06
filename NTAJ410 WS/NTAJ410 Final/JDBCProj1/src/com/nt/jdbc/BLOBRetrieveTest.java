package com.nt.jdbc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BLOBRetrieveTest {
   private static final String EMPALL_SELECT_QUERY="SELECT * FROM EMPALL WHERE EMPNO=?";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		InputStream is=null;
         int empno=0;
         ResultSet rs=null;
         String name=null;
         int salary=0;
         OutputStream os=null;
         byte[] buffer=null;
         int bytesRead=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Emp no::");
				empno=sc.nextInt();
			}
			//register jdbc Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedSTatement object
			if(con!=null)
				ps=con.prepareStatement(EMPALL_SELECT_QUERY);
          //set values to query params		
			if(ps!=null) 
				ps.setInt(1,empno);

			//exeute the SQL Query
			if(ps!=null) 
			   rs=ps.executeQuery();
			//process the ResultSet obj
			if(rs!=null) {
				if(rs.next()) {
					name=rs.getString(2);
					salary=rs.getInt(3);
					is=rs.getBinaryStream(4);
					//create Output stream pointing to dest file
					os=new FileOutputStream("new_disha.png");
					//write buffer based streams logic to copy blob file data dest file
					if(is!=null && os!=null) {
					   buffer=new byte[4096];
					   while((bytesRead=is.read(buffer))!=-1) {
						   os.write(buffer, 0,bytesRead);
					   }//while
					}//if
					System.out.println(name+"   "+salary);
					System.out.println("image copied to new_disha.jpg");
				}
				else {
					System.out.println("Recrod not found");
				}
			}
		}////try
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
				if(is!=null)
					is.close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
			try {
				if(os!=null)
					os.close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
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

/*
SQL> create  table empAll(empno number(5) primary key,ename varchar2(20),esalary number(10),ephoto blob);

Table created.

SQL> create sequence empall_empno_seq start with 100 increment by 1;
*/