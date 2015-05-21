<%@include file="index.jsp"%>
<div style="margin-top:80px;margin-left:10px;">
<section>
<%-- <% if(request.getAttribute("result") != null){ %>
	<%=request.getAttribute("result")%><br/>
<% } %>
 --%>
${result}<br/> 

<ul id="menu-v">
    <li><a href="addGenre.jsp">Add Genre</a></li>
    <li><a href="listGenres.jsp">List Genres</a>
     <li><a href="listGenres.jsp">Delete Genres</a>
      <li><a href="listGenres.jsp">Edit Genres</a>
    </li>
  </ul>
  
</section>
</div>
<!-- this is a comment -->
