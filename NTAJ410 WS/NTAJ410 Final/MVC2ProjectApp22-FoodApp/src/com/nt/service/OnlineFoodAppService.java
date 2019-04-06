package com.nt.service;

import java.util.List;

import com.nt.dto.RestaurantDTO;

public interface OnlineFoodAppService {
	public  List<RestaurantDTO> searchByCategory(String category)throws Exception;

}
