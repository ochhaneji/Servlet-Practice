package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CheckInputsFilter implements Filter {
	
	static {
		System.out.println("CheckInputsFilter:static block");
	}
	
	public CheckInputsFilter() {
		System.out.println("CheckInputsFilter:0-param constructor");
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("CheckInputsFilter:init(-)");
	}
	

	@Override
	public void doFilter(ServletRequest req ,ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		PrintWriter pw=null;
		System.out.println("CheckInputsFilter.doFilter(req,res,chain)");
		int val1=0,val2=0;
		//get PrintWriter
		pw=res.getWriter();
		res.setContentType("text/html");
		//read input values (formd data)
		val1=Integer.parseInt(req.getParameter("t1"));
		val2=Integer.parseInt(req.getParameter("t2"));
		//check inputs
		if(val1<=0 || val2<=0) {
			System.out.println("CheckInputsFilter:: Request is block in filter itself");
			pw.println("<b> u r inputs must be positive!!!! ");
			pw.println("<br><a href='form.html'>home</a>");
			return;
		}
		else {
			System.out.println("CheckInputsFilter:: before chain.doFilter(-,-)");
			chain.doFilter(req,res);
			System.out.println("CheckInputsFilter:: after chain.doFilter(-,-)");
		}
	}
	
	@Override
	public void destroy() {
		System.out.println("CheckInputsFilter.destroy()");
	}

}
