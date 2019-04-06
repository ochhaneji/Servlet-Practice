package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCapabilitiesTest {

	public static void main(String[] args) {
		Connection con=null;
		DatabaseMetaData dbmd=null;
		try {
			//register JDBC driver 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establis the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create DatabaseMetaData obj
			dbmd=con.getMetaData();
			System.out.println("dbmd obj class name::"+dbmd.getClass());
			//get More Info DB s/w
			System.out.println("DB name::"+dbmd.getDatabaseProductName());
			System.out.println("DB Version::"+dbmd.getDatabaseProductVersion());
			System.out.println("DB Major Version::"+dbmd.getDatabaseMajorVersion());
			System.out.println("DB Minor Version::"+dbmd.getDatabaseMinorVersion());
			System.out.println("JDBC driver name::"+dbmd.getDriverName());
			System.out.println("JDBC driver version::"+dbmd.getDriverVersion());
			System.out.println("JDBC driver majaor version ::"+dbmd.getDriverMajorVersion());
			System.out.println("JDBC driver minror version ::"+dbmd.getDriverMinorVersion());
			System.out.println("Max Row Size::"+dbmd.getMaxRowSize());
			System.out.println("Max Connections::"+dbmd.getMaxConnections());
			System.out.println("Max Table name length::"+dbmd.getMaxTableNameLength());
			System.out.println("Max Cols in Group By "+dbmd.getMaxColumnsInGroupBy());
			System.out.println("Supports Procedures?"+dbmd.supportsStoredProcedures());
		}
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
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally

	}//main
}//class
