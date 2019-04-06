package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*create or replace function fx_getEmpDetailsByNo(no in number,
        name out varchar,
        desg out varchar)
return number  as
bsal number(10);
begin
select  ename,job,sal into name,desg,bsal from emp where empno=no;
return bsal;
end;
/
*/
public class CsFxTest {
   private static final String  CALL_FX_QUERY="{?=call FX_GETEMPDETAILSBYNO(?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		CallableStatement cs=null;
		int no=0;
		try {
		  sc=new Scanner(System.in);
		  if(sc!=null) {
			  System.out.println("Enter employee number::");
			  no=sc.nextInt();
		  }//if
		  //register JDBC Driver class
			//register JDBC Driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create CallableStatement obj
			if(con!=null)
				cs=con.prepareCall(CALL_FX_QUERY);
			//register return,Out params with JDBC types
			  if(cs!=null) {
				  cs.registerOutParameter(1,Types.INTEGER); //return param
				  cs.registerOutParameter(3,Types.VARCHAR); //out param
				  cs.registerOutParameter(4,Types.VARCHAR); //out param
			  }
			  //set values to IN params
			  if(cs!=null){
				  cs.setInt(2,no);
			  }
			  //call PL/SQL function
			  if(cs!=null) {
				  cs.execute();
			  }
			  //gather results from return,OUT params
			  if(cs!=null) {
				  System.out.println("emp name::"+cs.getString(3));
				  System.out.println("emp desg ::"+cs.getString(4));
				  System.out.println("Emp salary::"+cs.getInt(1));
			  }
		}//try
		catch(SQLException  | ClassNotFoundException  se) {
			se.printStackTrace();
			if(((SQLException) se).getErrorCode()==1403)
				System.out.println("employee not found");
		}
		finally {
			//close jdbc objs
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
