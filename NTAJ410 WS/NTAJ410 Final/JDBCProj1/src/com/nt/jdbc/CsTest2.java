package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*create or replace procedure p_get_empDetailsByNo(eno in number, 
        name out varchar,
        desg out varchar,
         salary out number)
as 
begin
select ename,job,sal into name,desg,salary from emp where empno=eno;
end;
/
*/
public class CsTest2 {
  private static final String CALL_PROCUDRE="{CALL P_GET_EMPDETAILSBYNO(?,?,?,?) }";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter employee number::");
				no=sc.nextInt();
			}
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create STatement object
			if(con!=null)
				cs=con.prepareCall(CALL_PROCUDRE);
			//register OUT params with JDBC types
			if(cs!=null) {
				cs.registerOutParameter(2,Types.VARCHAR);
				cs.registerOutParameter(3,Types.VARCHAR);
				cs.registerOutParameter(4,Types.INTEGER);
			}
			//set values to IN params
			if(cs!=null)
				cs.setInt(1,no);
			//execute the Query
			if(cs!=null)
				cs.execute();
			//gather results from OutParams
			if(cs!=null) {
				System.out.println("Emp name ::"+cs.getString(2));
				System.out.println("Emp Job ::"+cs.getString(3));
				System.out.println("Emp Salary::"+cs.getInt(4));
			}
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()==1403)
				System.out.println("Employee Not found");
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(cs!=null)
					cs.close();
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
