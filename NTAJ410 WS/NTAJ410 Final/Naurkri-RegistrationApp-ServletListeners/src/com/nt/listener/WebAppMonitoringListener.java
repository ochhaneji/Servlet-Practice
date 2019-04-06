package com.nt.listener;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebAppMonitoringListener implements ServletContextListener {
     private long start,end;
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		start=System.currentTimeMillis();
		//write log message
		sce.getServletContext().log("Web application is deployed/reloaded/restarted at::"+new Date());
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		end=System.currentTimeMillis();
		//write log message
		sce.getServletContext().log("Web application is stopped/undeployed at::"+new Date());
		sce.getServletContext().log("Web application   "+sce.getServletContext().getContextPath()+" executed smoothly for"+(end-start)+" ms");
	}
	
}
