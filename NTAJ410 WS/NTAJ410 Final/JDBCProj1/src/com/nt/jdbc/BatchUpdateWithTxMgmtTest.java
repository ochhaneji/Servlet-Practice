package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdateWithTxMgmtTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		int result[]=null;
		int sum=0;
		boolean flag=false;
		try {
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//eststablish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//Begin Tx
			if(con!=null)
				con.setAutoCommit(false);
			
			//add queries to the batch
			if(st!=null) {
				st.addBatch("insert into student values(5001,'traun','pakistan','terrorisom')");
				st.addBatch("update student set sadd='dubai' where sadd='london'");
				st.addBatch("delete from student where sno<=4000");
			}
			//execute  the batch
			if(st!=null) {
				result=st.executeBatch();
			}
			
			//perform Transaction mgmt
			for(int i=0;i<result.length;++i) {
				if(result[i]==0) {
					flag=true;
					break;
				}
			}
			
			
			
		}//try
		catch(SQLException se) {
			flag=true;
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			flag=true;
			cnf.printStackTrace();
		}
		catch(Exception e) {
			flag=true;
			e.printStackTrace();
		}
		finally {
			
			try {
			if(flag) {
				con.rollback();
				System.out.println("Tx Rolled back");
		  	 }
			else {
				con.commit();
				System.out.println("Tx Committed");
			}
			}//try
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
		}//finally

	}//main
}//class
