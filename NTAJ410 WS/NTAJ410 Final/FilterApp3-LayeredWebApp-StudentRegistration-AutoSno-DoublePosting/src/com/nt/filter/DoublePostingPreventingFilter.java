package com.nt.filter;

import java.io.IOException;
import java.util.Random;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("*.jsp")
public class DoublePostingPreventingFilter extends GenericFilter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		  HttpSession ses=null;
		  HttpServletRequest hreq=null;
		  int cTkn=0,sTkn=0;
		  RequestDispatcher rd=null;
		  //get Request obj
		  hreq=(HttpServletRequest)req;
		  //get Session
		  ses=hreq.getSession();
		  if(hreq.getMethod().equalsIgnoreCase("GET")) { //for form launching
			  //crete Server side Session Token as ses attribute
			  ses.setAttribute("sToken",new Random().nextInt(10000));
			  //deleage to form launching
			  chain.doFilter(req,res);
		  }
		  else {  // for form submission
			  cTkn=Integer.parseInt(req.getParameter("cToken"));
			  sTkn=(Integer)ses.getAttribute("sToken");
			  if(cTkn==sTkn) {
				  ses.setAttribute("sToken",new Random().nextInt(10000));
				  chain.doFilter(req,res);
			  }
			  else {
				  //display error page
				  rd=req.getRequestDispatcher("/dbl_post.html");
				  rd.forward(req,res);
			  }//else
			  
		  }//else
		

	}//doFilter(req,res)

}//class
