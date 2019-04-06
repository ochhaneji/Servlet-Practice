//SelectTest4.java
package com.nt.jdbc;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import oracle.jdbc.OracleDriver;


 public class SelectTest4UsingInternals
 {
	 public static void main(String ...args){
          Connection con=null;
		  Statement st=null;
		  String query=null;
		  boolean flag=false;
		  ResultSet rs=null;
		  OracleDriver driver=null;
		  Properties props=null;
		 try{
            //register JDBC driver s/w
			driver=new OracleDriver();
			//estalish the connection
			props=new Properties();
			props.put("user","system");
			props.put("password","manager");
			con=driver.connect("jdbc:oracle:thin:@localhost:1521:xe",props);
          
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			System.out.println("st obj class name::"+st.getClass());
			//prepare SQL Query
			 query="SELECT EMPNO as no,ENAME as name,JOB as job,SAL as sal FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)";
			 //send and execute SQL Query
			 if(st!=null)
				 rs=st.executeQuery(query);
			 System.out.println("rs obj class name::"+rs.getClass());
			 //process the ResultSet obj
			 if(rs!=null){
                 while(rs.next()){
                     flag=true;
					 //System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));
                     //System.out.println(rs.getInt("EMPNO")+"  "+rs.getString("ENAME")+"  "+rs.getString("JOB")+"  "+rs.getString("SAL"));
                     System.out.println(rs.getInt("no")+"  "+rs.getString("name")+"  "+rs.getString("JOB")+"  "+rs.getString("sal"));
				 }//while
			 }//if
			 if(flag==false)
				  System.out.println("records not found");
		 }//try
		 catch(SQLException se){
			 System.out.println(se);
		 }
		 catch(Exception e){
			  e.printStackTrace();
		 }
		 finally{
			 //close jdbc objs
			 try{
               if(rs!=null)
				   rs.close();
			 }
			 catch(SQLException se){
				 se.printStackTrace();
			 }

			  try{
               if(st!=null)
				   st.close();
			 }
			 catch(SQLException se){
				 se.printStackTrace();
			 }

			  try{
               if(con!=null)
				   con.close();
			 }
			 catch(SQLException se){
				 se.printStackTrace();
			 }

		 }//finally
	 }//main
 }//class
 