package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null,addrs=null,ms=null,qlfy=null,crs[],hb[],gender=null,dob=null;
		int age=0;
		String msg=null,dob1=null, dob2=null,time=null,color=null,income=null,email=null,url=null,phone=null,lw=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read form data
		name=req.getParameter("pname");
		age=Integer.parseInt(req.getParameter("page"));
		addrs=req.getParameter("addrs");
		gender=req.getParameter("gender");
		ms=req.getParameter("ms");
		 dob=req.getParameter("dob");
		 dob1=req.getParameter("dob1");
		 dob2=req.getParameter("dob2");
		 time=req.getParameter("dobtime");
		 url=req.getParameter("snUrl");
		 color=req.getParameter("color");
		 lw=req.getParameter("lw");
		 income=req.getParameter("income");
		 phone=req.getParameter("phone");
		 email=req.getParameter("email");
		 
		if(ms==null) {
			ms="single";
		}
		crs=req.getParameterValues("courses");
		if(crs==null) {
			crs=new String[] {"no course is selected"};
		}
		hb=req.getParameterValues("hb");
		if(hb==null) {
			hb=new String[] {"No Hobies"};
		}
		qlfy=req.getParameter("qlfy");
		//write b.logic
		if(gender.equalsIgnoreCase("M")){
			   if(age<=5)
				    msg="Master.  "+name+"  u   r  baby boy";
			   else if(age<=12)
				   msg="Master.  "+name+"  u  r small   boy";
			   else if(age<=19)
				   msg="Mr.  "+name+"  u r  teenage   boy";
			   else if(age<=35)
				   msg="Mr.  "+name+"  u r young man";
			   else if(age<=50)
				   msg="Mr.  "+name+"  u r middle-aged  man";
			   else 
				   msg="Mr.  "+name+"  u r Budda";
		 }//if
		else {
			 if(age<=5)
				    msg="Master.  "+name+"  u   r  baby girl";
			   else if(age<=12)
				   msg="Master.  "+name+"  u  r small  girl";
   			   else if(age<=19) {
   				   if(ms.equalsIgnoreCase("married")) {
				        msg="Mrs.  "+name+"  u r  teenage married girl";
   				   }
   				   else {
   					 msg="Miss.  "+name+"  u r  teenage  girl";
   				   }
   			   }//elseif
			   else if(age<=35) {
				   if(ms.equalsIgnoreCase("married")) {
				        msg="Mrs.  "+name+"  u r   young married woman";
  				   }
  				   else {
  					 msg="Miss.  "+name+"  u r  young woman ";
  				   }
			   }//else if
			   else if(age<=50) {
				   if(ms.equalsIgnoreCase("married")) {
				        msg="Mrs.  "+name+"  u r   middle-aged married woman";
 				   }
 				   else {
 					 msg="Miss.  "+name+"  u r  middle-aged woman ";
 				   }
			   }//else if
			   else {
				   if(ms.equalsIgnoreCase("married")) {
				        msg="Mrs.  "+name+"  u r   old married woman";
				   }
				   else {
					 msg="Miss.  "+name+"  u r  old woman ";
				   }
			   }//else
		}//else
			 
			 //form data
			 
			 pw.println("<br><b> Recieved form data </b>");
			 pw.println("<br><b> name :: </b>"+name);
			 pw.println("<br><b> age :: </b>"+age);
			 pw.println("<br><b> gender :: </b>"+gender);
			 pw.println("<br><b> address :: </b>"+addrs);
			 pw.println("<br><b> Marital status :: </b>"+ms);
			 pw.println("<br><b> Qualification :: </b>"+qlfy);
			 pw.println("<br><b> Courses :: </b>"+Arrays.toString(crs));
			 pw.println("<br><b> Hobies :: </b>"+Arrays.toString(hb));
			 pw.println("<br><b> DOB :: </b>"+dob);
			 pw.println("<br><b>DOB1 </b>"+dob1);
			 pw.println("<br><b>DOB2 </b>"+dob2);
			 pw.println("<br><b>time </b>"+time);
			 pw.println("<br><b>income </b>"+income);
			 pw.println("<br><b>Lucky week </b>"+lw);
			 pw.println("<br><b>Color </b>"+color);
			 pw.println("<br><b>email </b>"+email);
			 pw.println("<br><b>url </b>"+url);
			 pw.println("<br><b>phone </b>"+phone);
			
			 pw.println("<br><h1 style='color:red;text-align:center'>"+msg+"</h1>");
			 //add hyperlink
			 pw.println("<br><a href='form.html'> home </a>");
			 //close stream
			 pw.close();
			 
		}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 doGet(req,res);
	}

}
