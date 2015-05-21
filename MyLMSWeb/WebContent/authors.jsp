<%@include file="index.jsp"%>
<div style="margin-top:80px;margin-left:10px;">
<section>
<%-- <% if(request.getAttribute("result") != null){ %>
	<%=request.getAttribute("result")%><br/>
<% } %>
 --%>
${result}<br/> 

<ul id="menu-v">
    <li><a href="addAuthor.jsp">Add Author</a></li>
    <li><a href="listAuthors.jsp">List Authors</a>
     <li><a href="listAuthors.jsp">Delete Authors</a>
      <li><a href="listAuthors.jsp">Edit Authors</a>
    </li>
  </ul>
  
</section>
</div>
<!-- this is a comment -->
