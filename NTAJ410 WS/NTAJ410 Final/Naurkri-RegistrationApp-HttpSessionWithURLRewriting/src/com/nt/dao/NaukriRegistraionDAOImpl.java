package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.nt.bo.JobSeekerBO;

public class NaukriRegistraionDAOImpl implements NaukriRegistrationDAO {
	private static final String  INSERT_QUERY="INSERT INTO  NAUKRI_INFO VALUES(JSID_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
	private Connection  getPooledConnection()throws Exception {
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		//get DataSource obj ref from underlying Jndi registry 
		ic=new InitialContext();
		ds=(DataSource) ic.lookup("java:/comp/env/DsJndi");
		//get Pooled jdbc con object
		con=ds.getConnection();
		return con;
	}

	@Override
	public int insert(JobSeekerBO bo) throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		//get Pooled Connection obj
		con=getPooledConnection();
		//create PreparedStatement object
		ps=con.prepareStatement(INSERT_QUERY);
		//set values to query params
		ps.setString(1,bo.getName());
		ps.setString(2,bo.getAddrs());
		ps.setInt(3,bo.getAge());
		ps.setString(4,bo.getSkill());
		ps.setInt(5,bo.getExperience());
		ps.setInt(6,bo.getSalary());
		ps.setString(7,bo.getLocation());
		//execuite the the query
		count=ps.executeUpdate();
		//close jdbc objs
		ps.close();
		con.close();
		return count;
	}

}
