package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nt.dto.JobSeekerDTO;
import com.nt.service.NaukriRegistrationService;
import com.nt.service.NaukriRegistrationServiceImpl;

@WebServlet("/thirdurl")
public class ThirdServlet extends HttpServlet {
	 private NaukriRegistrationService service;
	public ThirdServlet() {
		service=new NaukriRegistrationServiceImpl();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		HttpSession ses=null;
		String location=null;
		int sal=0,exp=0,age=0;
		String name=null,addrs=null,skill=null;
		JobSeekerDTO dto=null;
		String result=null;
		
		//General Settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form1/req1 data
		location=req.getParameter("loc");
		sal=Integer.parseInt(req.getParameter("sal"));
		//Locate HttpSession object
		ses=req.getSession(false);
		//Gather form1/req1 data and form2/req2 data  from Session obj as Session attribute values
		name=(String) ses.getAttribute("name");
		addrs=(String) ses.getAttribute("addrs");
		skill=(String) ses.getAttribute("skill");
		age=(Integer) ses.getAttribute("age");
		exp=(Integer) ses.getAttribute("exp");
		//write form1/req1 , form2/req2  and form3/req3 data to DTO obj
		dto=new JobSeekerDTO();
		dto.setName(name);
		dto.setAddrs(addrs);
		dto.setAge(age);
		dto.setExperience(exp);
		dto.setSkill(skill);
		dto.setLocation(location);
		dto.setSalary(sal);
		try {
		//use Service
			result=service.register(dto);
			pw.println("<h1 style='color:red'>"+result+"</h1>");
		}
		catch(Exception e) {
			if(((SQLException)e).getErrorCode()==1)
				pw.println("<h1 style='color:red'>Jobseeker Alredy Registered with this number</h1>");
			else if(((SQLException)e).getErrorCode()==12899)
				pw.println("<h1 style='color:red'>Too Large values are submitted</h1>");
			else
				pw.println("<h1 style='color:red'>Unknow Internal problem</h1>");
		}
		pw.println("<br><br><b> Session Id:: "+ses.getId()+"</b>");
		//invlidate the session
		ses.invalidate();
		//add home hyperlink
		pw.println("<br><br> <a href='register.html'>home</a>");
		//close stream
		pw.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
