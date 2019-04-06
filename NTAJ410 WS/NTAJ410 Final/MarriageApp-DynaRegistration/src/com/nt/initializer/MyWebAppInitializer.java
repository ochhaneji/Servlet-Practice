package com.nt.initializer;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import com.nt.servlet.MarriageServlet;

public class MyWebAppInitializer implements ServletContainerInitializer {
	
	static {
		System.out.println("MyWebAppInitializer::static block");
	}
	
	public MyWebAppInitializer() {
		System.out.println("MyWebAppInitializer::0-param constructor");
	}

	@Override
	public void onStartup(Set<Class<?>> set, ServletContext sc) throws ServletException {
	  System.out.println("MyWebAppInitializer.onStartup()");
	    MarriageServlet servlet=null;
	    ServletRegistration.Dynamic reg=null;
	    //register servlet
	    servlet=new MarriageServlet();
	    reg=sc.addServlet("ms", servlet);
	    reg.addMapping("/marriageurl");
	    reg.setLoadOnStartup(2);
	    reg.setInitParameter("p1","val1");
	    reg.setInitParameter("p2","val2");
	}

}
