package com.nt.service;

import com.nt.bo.StudentBO;
import com.nt.dao.StudentDAO;
import com.nt.dao.StudentDAOImpl;
import com.nt.dto.StudentDTO;

public class StudentServiceImpl implements StudentService {

	@Override
	public String registerStudent(StudentDTO dto) throws Exception {
		int total = 0;
		float avg = 0.0f;
		String result = "fail";
		StudentBO bo = null;
		int count = 0;
		StudentDAO dao = null;

		// generate the b.logic
		total = dto.getM1() + dto.getM2() + dto.getM3();
		avg = total / 3.0f;
		System.out.println("avg :: "+avg);
		if(dto.getM1() > 33 && dto.getM2() > 33 && dto.getM3() > 33)
			result = "pass";
		// convert dto into bo
		System.out.println("result h service me "+result);
		bo = new StudentBO();
		bo.setSno(dto.getSno());
		bo.setSname(dto.getSname());
		bo.setTotal(total);
		bo.setAvg(avg);
		bo.setResult(result);
		System.out.println("after bo conversion ::"+bo);
		//use DAO 
		dao = new StudentDAOImpl();
		count = dao.insert(bo);
		System.out.println("count h "+count);
		if(count == 0)
			return "Student Registation failed :: Result is - "+result;
		return "Student Registation success :: Result is - "+result;
	}

}
