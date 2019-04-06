//WordServlet.java
package com.nt.servlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class WordServlet extends HttpServlet
{
	static{
		   System.out.println("WordServlet::static block");
	}
	public WordServlet(){
	      System.out.println("WordServlet::0-param constructor");
	}

	public void init(ServletConfig  cg)throws ServletException{
           System.out.println("WordServlet::init(ServletConfig cg) method");
	}
	protected void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		System.out.println("WordServlet::service(-,-) method");
		PrintWriter pw=null;
		//set response content type
		res.setContentType("application/msword");
		//get PrintWriter object
		pw=res.getWriter();
		//write output to the response object
		pw.println("<table border='1' bgcolor='cyan'>");
		pw.println("<tr> <th>Party Name</th><th>Party Symbol</th><th>Party Leader </th> <th>role </th></tr>");
		pw.println("<tr><td> BJP </td> <td> Lotus </td><td>Amit Shah </td><td> president</td></tr>");
		pw.println("<tr><td> BJP </td> <td> Lotus </td><td>Modi </td><td> Every thing</td></tr>");
		pw.println("<tr><td> Congress </td> <td> Hand </td><td>Pappu </td><td>  President</td></tr>");
		pw.println("<tr><td> AAp </td> <td> Jhadu </td><td> Mental kejriwal </td><td>  Every thing</td></tr>");
		pw.println("</table>");
		//close streams
		pw.close();
	}//service(-,-)
}//class
