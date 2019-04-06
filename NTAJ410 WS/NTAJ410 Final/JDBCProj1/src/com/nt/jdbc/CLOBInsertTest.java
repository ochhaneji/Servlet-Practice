package com.nt.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBInsertTest {
  private static final String INSERT_JOB_SEEKER_QUERY="INSERT INTO JOBSEEKER VALUES(JOBSEEKER_REGNO_SEQ.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String name=null;
		String qlfy=null;
		String resumePath=null;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		long length=0;
		Reader reader=null;
		int count=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Job Seeker name::");
				name=sc.next();
				System.out.println("Enter Job Seeker Qualification::");
				qlfy=sc.next();
				System.out.println("Enter JobSeeker Resume Location::");
				resumePath=sc.next();
			}//if
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//estalish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(INSERT_JOB_SEEKER_QUERY);
			//Locate file
			file=new File(resumePath);
			//Get the length of the file
			length=file.length();
			//create Reader stream pointing to the file
			reader=new FileReader(file);
			//set values query params
			if(ps!=null) {
				ps.setString(1,name);
				ps.setString(2,qlfy);
				ps.setCharacterStream(3, reader,length);
			}
			//execute the Query
			if(ps!=null) {
				count=ps.executeUpdate();
			}
			//process the result
			if(count==0)
				System.out.println("JobSeeker is not registered");
			else
				System.out.println("JobSeeker is registered");
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
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(reader!=null)
					reader.close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}//finally
	}//main
}//class
