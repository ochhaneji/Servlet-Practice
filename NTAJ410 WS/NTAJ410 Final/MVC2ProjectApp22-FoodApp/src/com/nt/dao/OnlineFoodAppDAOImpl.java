package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.nt.bo.RestaurantBO;

public class OnlineFoodAppDAOImpl implements OnlineFoodAppDAO {
	private static final String  SEARCH_RESTAURANTS_BY_CATEGORY="SELECT  RESTID,RESTNAME,LOCATION,RATING,PRICEFOR2,CATEGORY,STATUS FROM ONLINE_FOODAPP WHERE CATEGORY=?";
	
	private  Connection  getPooledConnection()throws Exception{
		InitialContext ic=null;
		DataSource ds=null;
		Connection con=null;
		//get DataSource obj from Jndi registry
		ic=new InitialContext();
		ds=(DataSource) ic.lookup("java:/comp/env/DsJndi");
		//get Pooled JDBC connection
		con=ds.getConnection();
		return con;
	}

	@Override
	public List<RestaurantBO> findByCategory(String category) throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<RestaurantBO> listBO=null;
		RestaurantBO  bo=null;
		//get Pooled Connection
		con=getPooledConnection();
		//get PreparedStatement object
		ps=con.prepareStatement(SEARCH_RESTAURANTS_BY_CATEGORY);
		//set values  to query params
		ps.setString(1, category);
		//execute the Query
		rs=ps.executeQuery();
		//copy ResultSet obj records  to ListBO 
		listBO=new ArrayList();
		while(rs.next()) {
			//copy each record to one BO class object
			bo=new RestaurantBO();
			bo.setRestId(rs.getInt(1));
			bo.setRestName(rs.getString(2));
			bo.setRestLocation(rs.getString(3));
			bo.setRating(rs.getString(4));
			bo.setPriceFor2(rs.getInt(5));
			bo.setCategory(rs.getString(6));
			bo.setStatus(rs.getString(7));
			//add Bo class objs to ListBO
			listBO.add(bo);
		}//while
		return listBO;
	}//method
}//class
