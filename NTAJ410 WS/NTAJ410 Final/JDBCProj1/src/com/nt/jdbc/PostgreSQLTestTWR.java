package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLTestTWR {

	public static void main(String[] args) {
		
		try(Connection con=DriverManager.getConnection("jdbc:postgresql:NTAJ410DB","postgres","tiger")) {
			try(Statement st=con.createStatement()){
				try(ResultSet rs=st.executeQuery("SELECT * FROM PRODUCT")){
					while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3));
					}//while
				}//try3
			}//try2
			
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
