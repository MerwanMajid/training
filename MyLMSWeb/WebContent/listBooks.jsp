<%@page import="com.gcit.training.lms.entity.*"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.lms.service.AdministratorService"%>
<%

	List<Book> books = new AdministratorService().getBooks();

%>

<%@include file="index.jsp"%>

<script>
	function deleteBook(id) {
		//document.location.href = "deleteAuthor?authorId="+id;
		
		document.getElementById("bookId").value = id;
		document.deleteFrm.submit();
	}
function EditBook(id) {
	
	
	
	document.getElementById("bkId").value = id;
	document.getElementById("bkTitle").value = document.getElementById("title"+id).innerHTML;


	
	document.updatefrm.submit();
	
		/* var title = "title"+id;
		var publisher = "publisher"+id;
		
		
		var pb =  document.getElementById("publisher"+id).innerHTML;
		var ti =  document.getElementById("title"+id).innerHTML;
	
		
		document.getElementById(title).innerHTML="<input type=text id ='txt"+title+"' placeholder='new book title'></input>";
		document.getElementById(publisher).innerHTML="<input type=text id='txt"+publisher+"' placeholder='new book address'></input>";
	
	
		 document.getElementById("txt"+title).value =  ti;
		 document.getElementById("txt"+publisher).value =  pb;
		
		 
		var myBtn = document.getElementById("old"+id),
		
	    mySpan = document.createElement("span");
		
		
		mySpan.innerHTML = '<button class="btn btn-success" value="submit" onclick="javascript:updateBook('+id+')">submit</button>';
	
		myBtn.parentNode.replaceChild(mySpan, myBtn); */
		 
	}
	
	function updateBook(id) {
	
		
	}

</script>

<div style="margin-top:80px;margin-left:10px;">
<section>
<table class="table">
	<tr bgcolor="gray">
		<td><font color="#fff">Book Id</font></td>
		<td><font color="#fff">Book Title</font></td>
		<td><font color="#fff">Book Publisher</font></td>
		<!-- <td><font color="#fff">Book Authors</font></td>
		<td><font color="#fff">Book Genres</font></td> -->
		<td><font color="#fff">Edit</font></td>
		<td><font color="#fff">Delete</font></td>
	</tr>
	<%for(Book a : books) { %>	
	<tr>
		<td><%=a.getBookId()%></td>
		<td id="title<%=a.getBookId()%>"><%=a.getTitle()%></td>
		<td id="publisher<%=a.getBookId()%>"><%=a.getPublisher().getPublisherName()%></td>
		
		<td><button id="old<%=a.getBookId()%>"  onclick="javascript:EditBook('<%=a.getBookId()%>')" class="btn btn-success">Edit</button></td>
		<td><button class="btn btn-danger" onclick="javascript:deleteBook(<%=a.getBookId()%>);">Delete</button></td>
	</tr>
	<% } %>
</table>
<form action="deleteBook" method="post" name="deleteFrm">
	<input type="hidden" name="bookId" id="bookId"/>
</form>

<form action="preUpdateBook" method="post" name="updatefrm">
	<input type="hidden" name="bkId" id="bkId"/>
	<input type="hidden" name="bkTitle" id="bkTitle"/>
	

</form>

</section>
</div>