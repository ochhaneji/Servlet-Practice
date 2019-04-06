<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,com.nt.dto.RestaurantDTO"%>
<%
     response.setContentType("text/html");
     //read request attributes
     List<RestaurantDTO> listDTO=(List<RestaurantDTO>)request.getAttribute("report");
     if(listDTO!=null ){
          if(listDTO.size()!=0){
 %>
   <h1 style="color:red;text-align:center">Restaurants belonging to <%=request.getParameter("category") %> </h1> 
        <table border="1">
          <tr> 
             <th>Sno</th> <th>Restarant Id </th> <th>Restaurant Name </th><th>Location</th><th>Rating</th><th>Price For 2</th><th>category</th><th>Status </th>
           </tr>
        <%
           for(RestaurantDTO dto:listDTO){  %>
             <tr>
                <td><%=dto.getSrNo() %> </td>
                <td><%=dto.getRestId() %> </td>
                <td><%=dto.getRestName() %> </td>
                <td><%=dto.getRestLocation() %> </td>
                <td><%=dto.getRating() %> </td>
                <td><%=dto.getPriceFor2() %> </td>
                <td><%=dto.getCategory() %> </td>
                <td><%=dto.getStatus() %> </td>
             </tr>
           
          <% }//for
         %>
         </table> <br><br>
         <a href="javascript:showPrint()">print</a>
  <%}
      else{  %>
          <h1 style="color:red;text-align:center">Records not found </h1>
      <%}
      }//if
   %> 
  <br><br>
   <a href="search.html">home</a>
 
 <script language="JavaScript">
     function showPrint(){
          frames.focus();
          frames.print();
     }
 </script>
