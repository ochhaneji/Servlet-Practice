package com.nt.dao;

import java.util.List;

import com.nt.bo.RestaurantBO;

public interface OnlineFoodAppDAO {
	public  List<RestaurantBO>  findByCategory(String category)throws Exception;

}
