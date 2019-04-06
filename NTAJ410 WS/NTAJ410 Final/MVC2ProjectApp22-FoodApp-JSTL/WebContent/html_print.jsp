<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

   <h1 style="color:red;text-align:center">Restaurants belonging to ${param.category} </h1> 
    <c:choose>
    
      <c:when test="${!empty  report }">
         <table  border="1">
            <tr>
             <th>sno</th> <th>RestuarantId</th><th>RestuarantName</th><th>Location</th><th>Rating</th><th>priceFor2</th><th>Category</th><th>status</th></tr>
            </tr>
            <c:forEach var="dto"  items="${report}">
                <tr>
                   <td>${dto.srNo}</td>
                   <td>${dto.restId}</td>
                   <td>${dto.restName}</td>
                   <td>${dto.restLocation}</td>
                   <td>${dto.rating}</td>
                   <td>${dto.priceFor2}</td>
                   <td>${dto.category}</td>
                   <td>${dto.status}</td>
                </tr>
            </c:forEach>
         </table>
         <br> <a href="JavaScript:showPrint()">print</a>
      </c:when>
      <c:otherwise>
             <h1 style="color:red;text-align:center">No Records found </h1>
      </c:otherwise>
    </c:choose> <br><br>
     <a href="search.html">home</a>

 <script language="JavaScript">
     function showPrint(){
          frames.focus();
          frames.print();
     }
 </script>
