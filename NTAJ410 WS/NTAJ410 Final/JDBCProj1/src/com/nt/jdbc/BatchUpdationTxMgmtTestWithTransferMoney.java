package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
  SQL> desc Account;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 ACNO                                      NOT NULL NUMBER(10)
 HOLDERNAME                                         VARCHAR2(20)
 BALANCE                                            NUMBER(10,2)
 
 SQL> select * from Account;

      ACNO HOLDERNAME              BALANCE
----------        --------------------           ----------
      1001             raja                        9000
      1002             ravi                        8000
      1003              rani                       6000
 
 */

public class BatchUpdationTxMgmtTestWithTransferMoney {

	public static void main(String[] args) {
		Scanner sc=null;
		int srcNo=0,destNo=0;
		float amt=0.0f;
		Connection con=null;
		Statement st=null;
		int result[]=null;
		boolean flag=false;
		//read inputs
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Source Account number::");
				srcNo=sc.nextInt();
				System.out.println("Enter Dest Account number::");
				destNo=sc.nextInt();
				System.out.println("Enter Amount to transfer::");
				amt=sc.nextFloat();
			}//if
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//begin Tx 
			if(con!=null) 
				con.setAutoCommit(false);
			
			//create sTatement object
			if(con!=null)
				st=con.createStatement();
			//add withdraw,deposite operations related Queries to the Batch
			//for Batch Processing
			if(st!=null) {
				st.addBatch("UPDATE ACCOUNT SET BALANCE=BALANCE-"+amt+" WHERE ACNO="+srcNo);
				st.addBatch("UPDATE ACCOUNT SET BALANCE=BALANCE+"+amt+" WHERE ACNO="+destNo);
			}
			//execute the Batch
			if(st!=null)
			  result=st.executeBatch();
			//perform Tx mgmtn
			for(int i=0;i<result.length;++i) {
				if(result[i]==0) {
					flag=true;
					break;
				}
			}
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			flag=true;
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
			flag=true;
		}
		catch(Exception e) {
			e.printStackTrace();
			flag=true;
		}
		finally {
			try {
				if(flag) {
					con.rollback();
					System.out.println("Tx rolledback (Money not Transffered)");
				}
				else {
					con.commit();
					System.out.println("Tx Committed (Money  Transffered)");
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			//close jdbc objs
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
