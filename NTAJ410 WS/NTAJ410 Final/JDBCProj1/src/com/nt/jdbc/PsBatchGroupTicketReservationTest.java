package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*SQL> create table Rail_Ticket(ticketId number(6) primary key,psngrName varchar2(15),age number(3),sourcePlace varchar2(10),destPlace varchar2(10));
 SQL> create sequence tktId_seq start with 100 increment by 1; 
*/
public class PsBatchGroupTicketReservationTest {
  private static final String INSERT_RAIL_TICKET="INSERT INTO RAIL_TICKET VALUES(TKTID_SEQ.NEXTVAL,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		int groupSize=0;
		Connection con=null;
		PreparedStatement ps=null;
		String name=null,source=null,dest=null;
		int age=0;
		int result[]=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter group size::");
				groupSize=sc.nextInt();
				
				System.out.println("Enter source place");
				source=sc.next();
				System.out.println("Enter Dest place");
				dest=sc.next();
				
			}
			//register JDBC driver 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedStatement obj
			if(con!=null)
				ps=con.prepareStatement(INSERT_RAIL_TICKET);
			//read group of passegenger details and add them to batch of query params
			if(ps!=null && sc!=null) {
				for(int i=1;i<=groupSize;++i) {
					System.out.println("Enter "+i+" person details");
					System.out.println("Enter name::");
					name=sc.next();
					System.out.println("Enter age");
					age=sc.nextInt();
					//add each person info to query params
					ps.setString(1,name);
					ps.setInt(2,age);
					ps.setString(3,source);
					ps.setString(4,dest);
					//add each set of query params to batch
					ps.addBatch();
				}//for
			}//if
			
			//execute the Batch
			if(ps!=null ) {
				result=ps.executeBatch();
			}
			
			for(int i:result) {
				System.out.println(i);
			}
			
			if(result==null)
				System.out.println("Problems in Group ticket reservation");
			else
				System.out.println("No Problem is group ticket reservation");
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
		}//finally
	}//main
}//class
