package com.nt.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class RequestPerformanceAnalyzer implements ServletRequestListener {
	private long start,end;
	
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		   start=System.currentTimeMillis();
		}
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		ServletContext sc=null;
		end=System.currentTimeMillis();
		//get ServletContext obj
		sc=sre.getServletContext();
		//write log message
		sc.log(((HttpServletRequest)sre.getServletRequest()).getRequestURL()+"has taken   "+(end-start)+"  ms to process the request ");
	}
	

}
