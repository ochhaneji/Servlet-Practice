package com.nt.controller;

import java.io.IOException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dto.RestaurantDTO;
import com.nt.service.OnlineFoodAppService;
import com.nt.service.OnlineFoodAppServiceImpl;

@WebServlet("/controller")
public class MainControllerServlet extends HttpServlet {
	private  OnlineFoodAppService  service=null;
	
	@Override
	public void init()  {
		service=new OnlineFoodAppServiceImpl();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    String category=null;
	    String source=null;
	    List<RestaurantDTO>  listDTO=null;
	    RequestDispatcher rd=null;
	    String target=null;
	    
		//read form data
	    category=req.getParameter("category");
	    source=req.getParameter("source");
	    try {
	    //use service class
	    listDTO=service.searchByCategory(category);
	    //keep results in request scope
	    req.setAttribute("report",listDTO);
	    }
	    catch(Exception e) {
	    	e.printStackTrace();
	    	rd=req.getRequestDispatcher("/error.html");
	    	rd.forward(req,res);
	    	return;
	    }
	    //decide view comp to display results
	    if(source.equalsIgnoreCase("html"))
	    	target="/html_print.jsp";
	    else
	    	target="/excel_screen.jsp";
	    //forward to View comp
	    rd=req.getRequestDispatcher(target);
	    rd.forward(req,res);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	@Override
	public void destroy() {
	     service=null;
	}

}
