package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//register JDBc driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "manager");
			//create STatement object with type,mode values
			/*st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					                                      ResultSet.CONCUR_READ_ONLY);*/
			//st=con.createStatement(1004, 1007);
			st=con.createStatement();
			//create Scrollable ResultSet object
			rs=st.executeQuery("SELECT * FROM STUDENT");
			//display records (top-bottom)
			System.out.println("Top---Bottom");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
			}
			System.out.println("Bottom To Top");
			while(rs.previous()) {
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
			}
			rs.first();
			System.out.println(rs.getRow()+"-->"+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3));
			System.out.println("......................");
			rs.last();
			System.out.println(rs.getRow()+"-->"+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3));
			System.out.println("......................");
			rs.relative(-3);
			System.out.println(rs.getRow()+"-->"+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3));
			System.out.println("......................");
			rs.relative(2);
			System.out.println(rs.getRow()+"-->"+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3));
			System.out.println("......................");
			rs.absolute(2);
			System.out.println(rs.getRow()+"-->"+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3));
			System.out.println("......................");
			rs.absolute(-4);
			System.out.println(rs.getRow()+"-->"+rs.getInt(1)+" "+rs.getString(2)+"  "+rs.getString(3));
			System.out.println("......................");
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
				if(st!=null)
					st.close();
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
