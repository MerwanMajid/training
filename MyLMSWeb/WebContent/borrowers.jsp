<%@include file="index.jsp"%>
<div style="margin-top:80px;margin-left:10px;">
<section>
<%-- <% if(request.getAttribute("result") != null){ %>
	<%=request.getAttribute("result")%><br/>
<% } %>
 --%>
${result}<br/> 

<ul id="menu-v">
    <li><a href="addBorrower.jsp">Add Borrower</a></li>
    <li><a href="listBorrowers.jsp">List Borrowers</a>
     <li><a href="listAuthors.jsp">Delete Borrower</a>
      <li><a href="listAuthors.jsp">Edit Borrower</a>
    </li>
  </ul>
  
</section>
</div>
<!-- this is a comment -->
