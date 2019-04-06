package com.nt.service;

import com.nt.bo.JobSeekerBO;
import com.nt.dao.NaukriRegistraionDAOImpl;
import com.nt.dao.NaukriRegistrationDAO;
import com.nt.dto.JobSeekerDTO;

public class NaukriRegistrationServiceImpl implements NaukriRegistrationService {
	private  NaukriRegistrationDAO dao;
	
	public NaukriRegistrationServiceImpl() {
		dao=new NaukriRegistraionDAOImpl();
	}

	@Override
	public String register(JobSeekerDTO dto) throws Exception {
		JobSeekerBO bo=null;
		int count=0;
		//convert DTO to  BO
		bo=new JobSeekerBO();
		bo.setName(dto.getName());
		bo.setAddrs(dto.getAddrs());
		bo.setAge(dto.getAge());
		bo.setExperience(dto.getExperience());
		bo.setSkill(dto.getSkill());
		bo.setLocation(dto.getLocation());
		bo.setSalary(dto.getSalary());
	   //use DAO
		count=dao.insert(bo);
		if(count==1)
			return "Registration Succeded";
		else
			return "Registration Failed";
	}

}
