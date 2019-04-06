package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdateTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		int result[]=null;
		int sum=0;
		try {
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//eststablish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			//add queries to the batch
			if(st!=null) {
				st.addBatch("insert into student values(3001,'traun','pakistan','terrorisom')");
				st.addBatch("update student set sadd='aus' where sadd='usa'");
				st.addBatch("delete from student where sno<=200");
			}
			//execute  the batch
			if(st!=null) {
				result=st.executeBatch();
			}
			//process the Results
			for(int i=0;i<result.length;++i)
				sum=sum+result[i];
			
			System.out.println("no.of records that are effected::"+sum);
			
			
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
