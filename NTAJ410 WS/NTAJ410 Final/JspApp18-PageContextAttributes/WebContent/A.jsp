
<b>From A.jsp</b> <br>
  <!--create all the 4 scopes of attributes using pageContext object -->
  <%
    pageContext.setAttribute("attr1","val1",pageContext.PAGE_SCOPE);
    pageContext.setAttribute("attr2","val2",pageContext.REQUEST_SCOPE);
    pageContext.setAttribute("attr3","val3",pageContext.SESSION_SCOPE);
    pageContext.setAttribute("attr4","val4",pageContext.APPLICATION_SCOPE);
  %>
   <jsp:forward page="B.jsp"/>
   
  
  