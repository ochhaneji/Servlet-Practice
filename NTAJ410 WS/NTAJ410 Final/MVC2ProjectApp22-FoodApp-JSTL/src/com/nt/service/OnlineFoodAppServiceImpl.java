package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import com.nt.bo.RestaurantBO;
import com.nt.dao.OnlineFoodAppDAO;
import com.nt.dao.OnlineFoodAppDAOImpl;
import com.nt.dto.RestaurantDTO;

public class OnlineFoodAppServiceImpl implements OnlineFoodAppService {
	 private OnlineFoodAppDAO dao;
	public OnlineFoodAppServiceImpl() {
		dao=new OnlineFoodAppDAOImpl();
	}
	
	@Override
	public List<RestaurantDTO> searchByCategory(String category) throws Exception {
		List<RestaurantBO> listBO=null;
		List<RestaurantDTO> listDTO=null;
		RestaurantDTO dto=null;
		//use DAO
		listBO=dao.findByCategory(category);
		//convert listBO to listDTO
		listDTO=new ArrayList();
		for(RestaurantBO bo:listBO) {
			//copy Each BO class obj to each DTO class object
			dto=new RestaurantDTO();
			dto.setRestId(bo.getRestId());
			dto.setRestName(bo.getRestName());
			dto.setRestLocation(bo.getRestLocation());
			dto.setRating(bo.getRating());
			dto.setPriceFor2(bo.getPriceFor2());
			dto.setCategory(bo.getCategory());
			dto.setStatus(bo.getStatus());
			dto.setSrNo(listDTO.size()+1);
			//add dto to ListDTO
			listDTO.add(dto);
		}
		
		return listDTO;
	}//method
}//class
