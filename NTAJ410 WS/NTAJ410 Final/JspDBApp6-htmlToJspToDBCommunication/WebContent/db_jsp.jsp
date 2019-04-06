<%@page  import="java.sql.*"%>

<%!
    private Connection con;
    private PreparedStatement ps1=null,ps2=null; 
    private ResultSet rs=null;
     public void jspInit(){
        ServletConfig cg=null;
        String driver=null,url=null,dbuser=null,dbpwd=null;
        
      //get Access to ServletConfig object
        cg=getServletConfig();
        driver=cg.getInitParameter("driver");
        url=cg.getInitParameter("url");
        dbuser=cg.getInitParameter("dbuser");
        dbpwd=cg.getInitParameter("dbpwd");
        try{
        //register JDBC driver
        Class.forName(driver);
        //Establish the connection
        con=DriverManager.getConnection(url,dbuser,dbpwd);
        //create PreparedStatement objects
        ps1=con.prepareStatement("INSERT INTO STUDENT VALUES(SNO_SEQ.NEXTVAL,?,?)");
        ps2=con.prepareStatement("SELECT SNO,SNAME,SADD FROM STUDENT");
        }//try
        catch(SQLException se){
          se.printStackTrace();
          }
          catch(ClassNotFoundException cnf){
          cnf.printStackTrace();
          }
          catch(Exception e){
          e.printStackTrace();
          }
}//jspInit()
%>

  <%
      //read form data
      String name=null,addrs=null;
      String pval=null;
      int count=0;
      ResultSetMetaData rsmd=null;
      name=request.getParameter("sname");
      addrs=request.getParameter("sadd");
      //read s1 request param value
      pval=request.getParameter("s1");
      if(pval.equalsIgnoreCase("register")){
         //set values to Query params
          ps1.setString(1,name);
          ps1.setString(2,addrs);
          //execute the Query
          count=ps1.executeUpdate();
          if(count==0){ %>
                <h1 style='color:red;text-align:center'>Registration failed </h1>
             <% } 
              else{
             %>
                <h1 style='color:green;text-align:center'>Registration Succeded </h1>
             <% }
      }//if
      else{
          //execute SELECT SQL Query
           rs=ps2.executeQuery();
           //get ResultSetMetaDAta object
           rsmd=rs.getMetaData();
           //get and print col names
       %>
          <table border="1"  width="100" height="200">
            <tr>
          <%
             for(int i=1;i<=rsmd.getColumnCount();++i){  %>
                  <th><%=rsmd.getColumnLabel(i)%> </th>
        <%  }
           %>
          </tr>
          <%
            while(rs.next()){  %>
               <tr>
                  <%
                    for(int i=1;i<=rsmd.getColumnCount();++i){  %>
                       <td><%=rs.getString(i) %> </td>
                   <% } %> 
                   </tr>
            <%}
            }//else
           %>
           </table>
           <br><br>
           <a href="register.html">home</a>
           
  <%! public void jspDestroy(){
            //close jdbc objs
            try{
               if(rs!=null)
               rs.close();
            }
            catch(SQLException se){
               se.printStackTrace();
             }
             
              try{
               if(ps1!=null)
               ps1.close();
            }
            catch(SQLException se){
               se.printStackTrace();
             }
             
                try{
               if(ps2!=null)
               ps2.close();
            }
            catch(SQLException se){
               se.printStackTrace();
             }
             
            try{
             if(con!=null)
               con.close();
            }
            catch(SQLException se){
               se.printStackTrace();
             }
             
     }//jspDestroy() 
     %>
  
  
           
      
      
  
  