package com.nt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(value="/*")
public class PerformanceTestFilter extends GenericFilter {
	

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		   long start=0,end=0;
		   ServletContext sc=null;
		//get Start time
		   start=System.currentTimeMillis();
		   chain.doFilter(req,res);
		   end=System.currentTimeMillis();
		   //get ServletContext object
		   sc=getServletContext();
		   sc.log(((HttpServletRequest)req).getRequestURI()+" has taken "+(end-start)+"ms");
		   
		   
		   
	}

}
