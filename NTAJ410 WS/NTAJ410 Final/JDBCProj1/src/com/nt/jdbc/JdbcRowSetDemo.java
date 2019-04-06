package com.nt.jdbc;

import javax.sql.rowset.JdbcRowSet;

import oracle.jdbc.rowset.OracleJDBCRowSet;

public class JdbcRowSetDemo {

	public static void main(String[] args) {
		
		try(JdbcRowSet jrowset=new OracleJDBCRowSet()) {
			jrowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			jrowset.setUsername("system");
			jrowset.setPassword("manager");
			jrowset.setCommand("select * from emp");
			jrowset.execute();
			while(jrowset.next()) {
				System.out.println(jrowset.getInt(1)+"  "+jrowset.getString(2)+"  "+jrowset.getString(3));
			}
			jrowset.absolute(4);
			System.out.println(jrowset.getRow()+"-->"+jrowset.getInt(1)+"  "+jrowset.getString(2)+"  "+jrowset.getString(3));
			
		}//try
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main
}//class
