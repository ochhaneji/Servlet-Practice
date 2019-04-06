package com.nt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dto.StudentDTO;
import com.nt.service.StudentService;
import com.nt.service.StudentServiceImpl;
import com.nt.vo.StudentVO;

@WebServlet("/controller")
public class MainControllerServlet extends HttpServlet {
	private StudentService service;
	public  void init() {
		service=new StudentServiceImpl();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String sno=null;
		String sname=null;
		String m1=null,m2=null,m3=null;
		StudentVO vo=null;
		StudentDTO dto=null;
		String result=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		sname=req.getParameter("sname");
		m1=req.getParameter("m1");
		m2=req.getParameter("m2");
		m3=req.getParameter("m3");
		//Store formdata in to StudentVO class object
		vo=new StudentVO();
		vo.setSname(sname);vo.setM1(m1);
		vo.setM2(m2);vo.setM3(m3);
		//convert VO class obj to DTO class object
		dto=new StudentDTO();
		dto.setSname(vo.getSname());
		dto.setM1(Integer.parseInt(vo.getM1()));
		dto.setM2(Integer.parseInt(vo.getM2()));
		dto.setM3(Integer.parseInt(vo.getM3()));
		//create and use Service class object
		try {
		result=service.generateResult(dto);
		pw.println("<h2 style='color:red;text-align:center'>Result is::"+result+"</h2>");
		}
		catch(Exception e) {
			e.printStackTrace();
			if(((SQLException)e).getErrorCode()==00001)
                      pw.println("<h2 style='color:red;text-align:center'>Student Already registered</h2>");
			else if(((SQLException)e).getErrorCode()==12899)
			      pw.println("<h2 style='color:red;text-align:center'>Values are too Large</h2>");
			else
				pw.println("<h2 style='color:red;text-align:center'>unknow Internal Problem </h2>");
		}
		//add hyperlink
		pw.println("<br><a href='register.html'>home</a>");
		//close stream
		pw.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
	
	@Override
	public void destroy() {
	  service=null;	
	}

}
