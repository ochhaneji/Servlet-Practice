package com.nt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dto.StudentDTO;
import com.nt.service.StudentService;
import com.nt.service.StudentServiceImpl;
import com.nt.vo.StudentVO;

public class MainControllerServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = null;
		StudentVO vo = null;
		StudentDTO dto = null;
		String sno = null, sname = null, m1 = null, m2 = null, m3 = null;
		String result = null;
		StudentService service = null;
		System.out.println("min Controller");
		// general setting
	
		pw = res.getWriter();
		// set content type
		System.out.println("befoer content tyoe");
		res.setContentType("text/html");
		// getting data from the form and insert into vo
		System.out.println("befoe vo settng");
		vo = new StudentVO();
		vo.setSno(req.getParameter("sno"));
		vo.setSname(req.getParameter("sname"));
		vo.setM1(req.getParameter("m1"));
		vo.setM2(req.getParameter("m2"));
		vo.setM3(req.getParameter("m3"));
		System.out.println("testing22");
		// convert vo into dto
		dto = new StudentDTO();
		dto.setSno(Integer.parseInt(vo.getSno()));
		dto.setSname(vo.getSname());
		dto.setM1(Integer.parseInt(vo.getM1()));
		dto.setM2(Integer.parseInt(vo.getM2()));
		dto.setM3(Integer.parseInt(vo.getM3()));
		System.out.println("testing11");
		// use service for getting result
		service = new StudentServiceImpl();
		try {
			System.out.println("result se phl");
			result = service.registerStudent(dto);
			System.out.println("result ke bad"+result);
			
			pw.println("<h2 style='color:red;text-align:center'>Result is::" + result + "</h2>");
		} catch (Exception e) {
			e.printStackTrace();
			if (((SQLException) e).getErrorCode() == 00001)
				pw.println("<h2 style='color:red;text-align:center'>Student Already registered</h2>");
			else if (((SQLException) e).getErrorCode() == 12899)
				pw.println("<h2 style='color:red;text-align:center'>Values are too Large</h2>");
			else
				pw.println("<h2 style='color:red;text-align:center'>unknow Internal Problem </h2>");
		}

		//add hyperlink 
		pw.println("<br> <br> <a href='register.html'>Register PAge</a>");
		pw.close();
		
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}
}
