package com.nt.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/downloadurl")
public class FileDownloadServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String fname=null;
		File file=null;
		ServletContext sc=null;
		InputStream is=null;
		ServletOutputStream sos=null;
		long length=0;
		byte[] buffer=null;
		int bytesRead=0;
		//get Servletcontext obj
		sc=getServletContext();
	    //read file name as req param value
		fname=req.getParameter("file");
		//Locate the file in web application
		file=new File(sc.getRealPath("/")+"/"+fname);
		//Create InputStream pointing to the file
		is=new FileInputStream(file);
		//create OutputStream pointing to response object
		sos=res.getOutputStream();
		//get File length and make it as response content length
		res.setContentLengthLong(file.length());
		// get MIME type of  the file and make it  as response content type
		res.setContentType(sc.getMimeType(fname));
		//add "Content-Disposition" response header
		res.addHeader("Content-Disposition","attachment;fileName="+fname);
		//write streams based logic to wrtie file content to response object
		buffer=new byte[4096];
		while((bytesRead=is.read(buffer))!=-1) {
			sos.write(buffer, 0,bytesRead);
	     }
	    //close streams
	    sos.close();
	    is.close();
	}//doGet(-,-)
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		  doGet(req,res);
	}

}
