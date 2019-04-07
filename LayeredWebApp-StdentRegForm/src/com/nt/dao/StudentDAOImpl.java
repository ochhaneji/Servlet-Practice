package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.nt.bo.StudentBO;

public class StudentDAOImpl implements StudentDAO{
private static final String STUDENT_INSERT_QUERY = "insert into student values(?,?,?,?,?)";
	private Connection getPooledConnection() throws Exception{
		InitialContext ic = null;
		DataSource ds  = null;
		Connection con = null;

		
		ic = new InitialContext();
		ds = (DataSource) ic.lookup("java:/comp/env/DsJndi");
		//ds=(DataSource) ic.lookup("java:/comp/env/DsJndi");
		con = ds.getConnection();
		
		return con;
		
	}
	
	@Override
	public int insert(StudentBO bo) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;
		
		//get connection object
		con = getPooledConnection();
		//prepare the query
		ps = con.prepareStatement(STUDENT_INSERT_QUERY);
		//set the query params
		ps.setInt(1, bo.getSno());
		ps.setString(2, bo.getSname());
		ps.setInt(3, bo.getTotal());
		ps.setFloat(4, bo.getAvg());
		ps.setString(5, bo.getResult());
		//execute the query
		count = ps.executeUpdate();
		
		
		//close the connections
		ps.close();
		con.close();
		//return the result
		return count;
		

	}

}
