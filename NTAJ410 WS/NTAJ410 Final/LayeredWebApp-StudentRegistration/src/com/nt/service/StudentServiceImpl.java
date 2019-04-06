package com.nt.service;

import com.nt.bo.StudentBO;
import com.nt.dao.StudentDAO;
import com.nt.dao.StudentDAOImpl;
import com.nt.dto.StudentDTO;

public class StudentServiceImpl implements StudentService {
	private StudentDAO dao;
	
	public  StudentServiceImpl() {
		dao=new StudentDAOImpl();
	}

	@Override
	public String generateResult(StudentDTO dto) throws Exception {
	    int total=0;
	    float avg=0.0f;
	    String result=null;
	    StudentBO bo=null;
	    int  count=0;
		//Write b.logic calculating  total,avg and result
		total=dto.getM1()+dto.getM2()+dto.getM3();
		avg=total/3.0f;
		if(avg<35)
			result="Fail";
		else
			result="pass";
		//create BO class obj
		bo=new StudentBO();
		bo.setSno(dto.getSno());
		bo.setSname(dto.getSname());
		bo.setTotal(total);
		bo.setAvg(avg);
		bo.setResult(result);
		//use DAO
		count=dao.insert(bo);
		if(count==0)
			return "Registration failed :: Result is:::"+result;
		else
			return "Registration Succeded :: Result is:::"+result;
	}//method
}//class
