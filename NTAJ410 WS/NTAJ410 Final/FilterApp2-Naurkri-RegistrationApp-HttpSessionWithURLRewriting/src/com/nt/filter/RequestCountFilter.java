package com.nt.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.GenericFilter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class RequestCountFilter extends GenericFilter {
	private  int count;
       
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		 count++;
		 ServletContext sc=null;
		 //get Servletcontext obj
		 sc=req.getServletContext();
		 //keep req count in Application Scope
		 sc.setAttribute("reqCount",count);
		 
		chain.doFilter(req, res);
	}

	
}
