package com.nt.listener;

import java.util.Date;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionDurationTracker implements HttpSessionListener {
	private long start,end;
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		start=System.currentTimeMillis();
		//write to log file
		se.getSession().getServletContext().log("Session started at ::"+new Date());
	
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
	  end=System.currentTimeMillis();
	  se.getSession().getServletContext().log("Session ended at::"+new Date());
	  se.getSession().getServletContext().log("Session duration ::"+(end-start)+"  ms");
	}

}
