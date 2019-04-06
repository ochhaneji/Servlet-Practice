package com.nt.jdbc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CLOBRetrieveMySQLTest {
  private static final String  GET_JOBSEEKER_DETAILS="SELECT NAME,QLFY,RESUME FROM JOBSEEKER WHERE REGNO=?"; 
	public static void main(String[] args) {
		Scanner sc=null;
		int regNo=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String name=null,qlfy=null;
		Reader reader=null;
		Writer writer=null;
		char[] buffer=null;
		int charsRead=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
			System.out.println("Enter Job Seeker's Registration number::");
			regNo=sc.nextInt();
			}
			//register JDBC driver s/w
			Class.forName("com.mysql.jdbc.Driver");
			//estalish the connection
			con=DriverManager.getConnection("jdbc:mysql:///ntaj410db1","root","root");
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(GET_JOBSEEKER_DETAILS);
			//set values to Query params
			if(ps!=null) 
				ps.setInt(1,regNo);
			//execute the SQL Query
			if(ps!=null)
				rs=ps.executeQuery();
			//process the ResultSet obj
			if(rs!=null) {
				if(rs.next()) {
					name=rs.getString(1);
					qlfy=rs.getString(2);
					reader=rs.getCharacterStream(3);
					//create Writer Stream pointing to any empty Dest file
					writer=new FileWriter("new_resume.txt");
					//write buffer and streams based logic to complete file copy activity
					if(reader!=null && writer!=null) {
						buffer=new char[1024];
						while((charsRead=reader.read(buffer))!=-1) {
							writer.write(buffer,0,charsRead);
						}//while
						System.out.println(name+"  "+qlfy);
						System.out.println("Resume downloaded...");
					}//if
				}//if
					else {
						System.out.println("Record not found");
					}//else
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
				try {
					if(writer!=null)
						writer.close();
				}
				catch(IOException ioe) {
					ioe.printStackTrace();
				}
			}//finally
	}//main
}//class
