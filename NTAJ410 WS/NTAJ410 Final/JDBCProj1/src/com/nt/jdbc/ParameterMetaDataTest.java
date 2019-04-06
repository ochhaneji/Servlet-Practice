package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParameterMetaDataTest {
 private static final String  INSERT_QUERY="INSERt INTO STUDENT VALUES(?,?,?)";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ParameterMetaData  pmd=null;
		int count=0;
		try {
			//register JDBc driver s/w
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.jdbc.Driver");
			//establish the connection
			//con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			con=DriverManager.getConnection("jdbc:mysql:///ntaj410db1","root", "root");
			//create PreparedStatement object with type,mode values
			ps=con.prepareStatement(INSERT_QUERY);
			//create ParameterMetaData obj
			if(ps!=null) {
				pmd=ps.getParameterMetaData();
			}
			//get Parameter count
			count=pmd.getParameterCount();
			for(int i=1;i<=count;++i) {
				System.out.println("parameter number::"+i);
				System.out.println("parameter mode::"+pmd.getParameterMode(i));
				System.out.println("parameter type name::"+pmd.getParameterTypeName(i));
				System.out.println("Precision::"+pmd.getPrecision(i));
				System.out.println("Scale::"+pmd.getScale(i));
				System.out.println("Is Nullable?"+pmd.isNullable(i));
			}
		
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(ps!=null)
					ps.close();
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
		}//finally
	}//main
}//class
