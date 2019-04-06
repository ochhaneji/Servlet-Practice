package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.nt.bo.StudentBO;

public class StudentDAOImpl implements StudentDAO {
	private static final String  STUD_INSERT_QUERY="INSERT INTO LAYERED_STUDENT VALUES(?,?,?,?,?)";
	
	
	private  Connection  getPooledConnection()throws Exception{
		Connection con=null;
		InitialContext ic=null;
		DataSource ds=null;
		//create InitialContext obj
		ic=new InitialContext();
		ds=(DataSource) ic.lookup("java:/comp/env/DsJndi");
		//get Pooled JDBc connection
		con=ds.getConnection();
		return con;
		
	}

	@Override
	public int insert(StudentBO bo) throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		int  count=0;
		//get Pooled Connection
		con=getPooledConnection();
		//create PreparedStatement object
		ps=con.prepareStatement(STUD_INSERT_QUERY);
		//set values to Query params
		ps.setInt(1,bo.getSno());
		ps.setString(2,bo.getSname());
		ps.setInt(3,bo.getTotal());
		ps.setFloat(4,bo.getAvg());
		ps.setString(5,bo.getResult());
		//execute the Query
		count=ps.executeUpdate();
		//close objs
		ps.close();
		con.close();
		return count;
	}

}
