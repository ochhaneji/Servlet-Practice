package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter("/*")
public class BrowserCheckingFilter extends GenericFilter {
       
  
  
	

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		String brName=null;
		PrintWriter pw=null;
		//get PrintWrtier
		pw=res.getWriter();
		res.setContentType("text/html");
		//get Browser name
		brName=((HttpServletRequest)req).getHeader("user-agent");
		System.out.println(brName);
		if(!brName.contains("Chrome")) {
			pw.println("<h1 style='color:red'> Request must be given from Chrome browser </h1>");
			pw.println("<br><br> <a href='register.html'>home </a>");
			return;
		}
		else {
			chain.doFilter(req, res);
		}
		//close stream 
		pw.close();
	}

	

}
