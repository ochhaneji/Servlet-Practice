package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadFile;

@WebServlet("/uploadurl")
public class UploadServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		MultipartFormDataRequest nreq=null;
		UploadBean  bean=null;
		Hashtable<String,UploadFile> ht=null;
		Enumeration<UploadFile> e=null; 
		UploadFile file=null;
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		try {
		//Create Special Request object
		nreq=new MultipartFormDataRequest(req);
		//Perform File Uploading
		bean=new UploadBean();
		bean.setFolderstore("e:/uploads");
		bean.setMaxfiles(6);
		
		bean.setFilesizelimit(10*1024);
		bean.setOverwrite(false);
		bean.store(nreq);  //completes file uploading
		pw.println("<h1 style='color:red;text-align:center'> Files are Uploaded Successfully..The file names are </h1>");
		//get List of Uploaded files
		ht=nreq.getFiles();
		e=ht.elements();
		while(e.hasMoreElements()) {
			file=e.nextElement();
			pw.println("<br><b>"+file.getFileName()+"......"+file.getContentType()+"...."+file.getFileSize()+"</b>");
		}
		}//try
		catch(Exception  ex) {
			pw.println("<h3> Problem in File Uploading ...</h3>");
			ex.printStackTrace();
		}
		//add hyperlink
		pw.println("<br> <a href='upload.html'>home</a>");
		//close stream
		pw.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req,res);
	}

}
