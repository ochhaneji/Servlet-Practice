package com.nt.jdbc;

import javax.sql.rowset.CachedRowSet;

import oracle.jdbc.rowset.OracleCachedRowSet;

public class CachedRowSetDemo {

	public static void main(String[] args) {
		
		try(CachedRowSet crowset=new OracleCachedRowSet()) {
			crowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			crowset.setUsername("system");
			crowset.setPassword("manager");
			crowset.setCommand("select * from student");
			crowset.execute();
			while(crowset.next()) {
				System.out.println(crowset.getInt(1)+"  "+crowset.getString(2)+"  "+crowset.getString(3));
			}
			System.out.println("Stop the DB s/w");
			Thread.sleep(50000);
			//modify 4th record
			crowset.absolute(4);
			crowset.updateString(3,"pakistan");
			crowset.updateRow();
			System.out.println("Start the DB s/w");
			Thread.sleep(60000);
			crowset.acceptChanges(); //sync the changes to DB s/w
			while(crowset.next()) {
				System.out.println(crowset.getInt(1)+"  "+crowset.getString(2)+"  "+crowset.getString(3));
			}
			
			
		}//try
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main
}//class
