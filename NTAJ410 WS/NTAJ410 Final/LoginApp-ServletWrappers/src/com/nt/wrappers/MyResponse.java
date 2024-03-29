package com.nt.wrappers;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class MyResponse extends HttpServletResponseWrapper {
   private  HttpServletResponse response;
   private CharArrayWriter writer=null;
	public MyResponse(HttpServletResponse response) {
		super(response);
		this.response=response;
		writer=new CharArrayWriter();
	}
	
	@Override
		public PrintWriter getWriter() throws IOException {
		   PrintWriter pw=null;
		   pw=new PrintWriter(writer);
		   return pw;
		}
	
	@Override
		public String toString() {
		  return writer.toString();
		}
	
	

}
