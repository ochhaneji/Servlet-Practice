package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Type5DriverTest {

	public static void main(String[] args) {
		try {
			Class.forName("com.ddtek.jdbc.oracle.OracleDriver");
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try(Connection con=DriverManager.getConnection("jdbc:datadirect:oracle://localhost:1521;ServiceName=xe","system","manager")){
			try(Statement st=con.createStatement()){
				try(ResultSet rs=st.executeQuery("SELECT * FROM EMPLOYEE")){
					//process the ResultSet obj
					while(rs.next()) {
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
					}//while
				}
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main
}//class
