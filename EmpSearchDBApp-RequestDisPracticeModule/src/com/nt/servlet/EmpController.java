package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmpController extends HttpServlet {
	private static final String GET_EMPLOYEE_BY_ID = "SELECT EMPNO, ENAME, JOB, SAL FROM EMP WHERE EMPNO = ?";

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext sc = null;
		RequestDispatcher rd = null, rd1 = null;
		String driverClass = null, url = null, dbuser = null, dbpwd = null;
		PrintWriter pw = null;
		int eid = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		//general seting
		pw = res.getWriter();
		res.setContentType("text/html");
		//include a header page
		rd = req.getRequestDispatcher("/headerurl");
		rd.include(req, res);
		// get data from servlet context
		sc = getServletContext();
		driverClass = sc.getInitParameter("driver");
		url = sc.getInitParameter("url");
		dbuser = sc.getInitParameter("dbuser");
		dbpwd = sc.getInitParameter("dbpwd");
		
		// get data from form page
		eid = Integer.parseInt(req.getParameter("eid"));
		
		
		try {
			// load the driver (optional )
			Class.forName(driverClass);
			// get the connection from DriverManager
			con = DriverManager.getConnection(url, dbuser, dbpwd);
			// prepare the query
			ps = con.prepareStatement(GET_EMPLOYEE_BY_ID);
			// set the parameter of the query
			ps.setInt(1, eid);
			// execute the query
			rs = ps.executeQuery();
			// getting data from ResultSet()
			if(rs.next()) {
				pw.println("<table border=1>");
					pw.println("<th>");
					pw.println("<td>"+rs.getInt(1)+"</td>");
					pw.println("<td>"+rs.getString(2)+"</td>");
					pw.println("<td>"+rs.getString(3)+"</td>");
					pw.println("<td>"+rs.getFloat(4)+"</td>");
					pw.println("</th>");
				pw.println("</table>");
			}
			else {
				pw.print("<b><h2>Record not found for this "+eid+" id</h2><b>");
			}
			
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			//rd1 = req.getRequestDispatcher("errorurl");
			//rd1 = sc.getRequestDispatcher("/errorurl");
			rd1 = sc.getNamedDispatcher("err");
			rd1.forward(req, res);
			
		}
		
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
