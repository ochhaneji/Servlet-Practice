package com.nt.jdbc;

import java.io.FileWriter;

import javax.sql.rowset.WebRowSet;

import oracle.jdbc.rowset.OracleWebRowSet;

public class WebRowSetDemo {

	public static void main(String[] args) {
		
		try(WebRowSet wrowset=new OracleWebRowSet()) {
			wrowset.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			wrowset.setUsername("system");
			wrowset.setPassword("manager");
			wrowset.setCommand("select * from student");
			wrowset.execute();
			while(wrowset.next()) {
				System.out.println(wrowset.getInt(1)+"  "+wrowset.getString(2)+"  "+wrowset.getString(3));
			}
			System.out.println("......................................");
			wrowset.writeXml(System.out);
			System.out.println("............................");
			 try(FileWriter writer=new FileWriter("d:/student.xml")) {
				 wrowset.writeXml(writer);
			 }
			
		}//try
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main
}//class
