<%@page import="com.gcit.training.lms.entity.Author"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.lms.service.AdministratorService"%>
<%

	List<Author> authors = new AdministratorService().getAllAuthors();

%>
<%@include file="index.jsp"%>
<script>
	function deleteAuthor(id) {
		//document.location.href = "deleteAuthor?authorId="+id;
		
		document.getElementById("authorId").value = id;
		document.deleteFrm.submit();
	}
	function EditAuthor(id,name) {
	
	
		
		document.getElementById(id).innerHTML="<input type=text id='txt"+id+"'placeholder="+name+"></input>";
	
		var myBtn = document.getElementById("old"+id),
		
	    mySpan = document.createElement("span");
		var x = "'"+id+"','"+name+"'";
		
		mySpan.innerHTML = '<button class="btn btn-success" value="submit" id="sub"+id+" onclick="javascript:updateAuthor('+x+')">submit</button>';
	
		myBtn.parentNode.replaceChild(mySpan, myBtn);
		 
	}
	
	function updateAuthor(id,name) {
		
	
		document.getElementById("authId").value=id;
		document.getElementById("authName").value=document.getElementById("txt"+id).value;
		//alert("hi update author");
		
		document.updatefrm.submit(); 
		

		
	}

</script>

<div style="margin-top:80px;margin-left:10px;">
<section>
<table class="table">
	<tr bgColor="gray">
		<th><font color="#fff">Author Id</font></th>
		<th><font color="#fff">Author Name</font></th>
		<th></th>
		<th></th>
	</tr>
	<%for(Author a : authors) { %>	
	<tr>
		<td ><%=a.getAuthorId()%></td>
		<td id="<%=a.getAuthorId()%>"><%=a.getAuthorName()%></td>
		<td><button id ='old<%=a.getAuthorId()%>'class="btn btn-success" onclick="javascript:EditAuthor('<%=a.getAuthorId()%>','<%=a.getAuthorName()%>')">Edit</button></td>
		<td><button class="btn btn-danger" onclick="javascript:deleteAuthor(<%=a.getAuthorId()%>);">Delete</button></td>
	</tr>
	<% } %>
</table>
<form action="deleteAuthor" method="post" name="deleteFrm">
	<input type="hidden" name="authorId" id="authorId"/>
</form>
<form action="updateAuthor" method="post" name="updatefrm">
<input type="hidden" name="authId" id="authId"/>
<input type="hidden" name="authName" id="authName"/>

</form>

</section>
</div>