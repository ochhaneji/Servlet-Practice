package com.nt.wrappers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyRequest extends HttpServletRequestWrapper {
   private HttpServletRequest request;
	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request=request;
	}
	
	@Override
		public String getParameter(String name) {
		     String pval=null;
		     //read request parameter value
		     pval=request.getParameter(name);
		     if(name.equals("uname")) {
		    	  if(!pval.endsWith("@gmail.com"))
		    		  pval=pval+"@gmail.com";
		     }
		     return pval;
		}

}
