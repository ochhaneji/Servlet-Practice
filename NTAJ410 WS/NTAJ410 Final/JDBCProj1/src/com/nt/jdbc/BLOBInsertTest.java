package com.nt.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BLOBInsertTest {
   private static final String EMPALL_INSERT_QUERY="INSERT INTO EMPALL VALUES(EMPALL_EMPNO_SEQ.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String name=null;
		int salary=0;
		String photoPath=null;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		long length=0;
		InputStream is=null;
		int result=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Emp name::");
				name=sc.next();
				System.out.println("Enter Emp salary::");
				salary=sc.nextInt();
				System.out.println("Enter Emp PhotoPath");
				photoPath=sc.next();
			}
			//register jdbc Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedSTatement object
			if(con!=null)
				ps=con.prepareStatement(EMPALL_INSERT_QUERY);
			//create java.io.File obj
			file=new File(photoPath);
			//get the length of the file
			length=file.length();
			//create InputStream
			is=new FileInputStream(file);
			//set values to query params
			if(ps!=null) {
				ps.setString(1,name);
				ps.setInt(2,salary);
				ps.setBinaryStream(3,is,length);
			}
			//exeute the SQL Query
			if(ps!=null) {
				result=ps.executeUpdate();
			}
			//process the result
			if(result==0)
				System.out.println("record not inserted");
			else
				System.out.println("Record inserted");
			
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