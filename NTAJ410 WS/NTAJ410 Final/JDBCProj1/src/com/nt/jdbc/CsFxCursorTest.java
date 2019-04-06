package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*create or replace function fx_view_delete_Stud_by_No(no in number,
        count out number)return sys_refcursor
as
details sys_refcursor;
begin
open details for
select  sno,sname,sadd from student where sno=no;

delete from student where sno=no;
count:=sql%rowcount;
return details;
end;
/
*/

public class CsFxCursorTest {
  private static final String  CALL_FX="{?=call fx_view_delete_Stud_by_No(?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		int cnt=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter student number::");
				no=sc.nextInt();
			}
			//register JDBC Driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create CallableStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_FX);
			//register OUT parameters with JDBC types
			if(cs!=null) {
				cs.registerOutParameter(1, OracleTypes.CURSOR);
				cs.registerOutParameter(3,Types.INTEGER);
			}
			//set values to IN params
			if(cs!=null)
				cs.setInt(2,no);
			//call PL/SQL Function
			if(cs!=null)
				cs.execute();
			//gather results from return ,Out params
			if(cs!=null) {
				rs=(ResultSet)cs.getObject(1);
				if(rs.next()) {
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3));
					cnt=cs.getInt(3);
					if(cnt==0)
						System.out.println("Record not found");
					else
						System.out.println("record found and deleted");
				}
				else {
					System.out.println("record not found");
				}
				
			}
			
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
