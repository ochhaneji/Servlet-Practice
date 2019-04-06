package com.nt.jdbc;

/*  App to get Dept details based on given Dept no 
 *  verison : 1.0
 *  author : Team-s
 * */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest5 {

	public static void main(String[] args) {
		Scanner sc = null;
		int no = 0;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = null;
		// System.out.println("main(-) method");
		System.out.println("SelectTest5.main()");
		// read inputs
		try {
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("Enter Depart Number::");
				no = sc.nextInt();
			}
			// register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
//establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
			// create Statement obj
			if (con != null)
				st = con.createStatement();
			// prepare SQL Query
			// SELECT * FROM DEPT WHERE DEPTNO=10
			query = "SELECT * FROM DEPT WHERE DEPTNO=" + no;
			// send and execute SQL Query in DB s/w
			if (st != null)
				rs = st.executeQuery(query);
			// process the ResultSet obj
			if (rs != null) {
				if (rs.next()) {
					System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
				} else {
					System.out.println("Records not found");
				}
			} // if
		} // try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close jdbc objs
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (st != null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (sc != null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // finally
	}// main
}// class
