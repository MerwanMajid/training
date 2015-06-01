<%@page import="com.gcit.training.lms.service.LibrarianService"%>
<%@page import="com.gcit.training.lms.service.AdministratorService"%>
<%@page import="com.gcit.training.lms.servlet.*"%>
<%@page import="com.gcit.training.lms.entity.*"%>
<%@page import="java.util.List"%>

<%@include file="index.jsp"%>

<br />
<br />
<br />
<br />
<link rel="stylesheet" href="css/myStyle.css" type="text/css" />
<script src="jquery.js" type="text/javascript"></script>

<script src="animation.js" type="text/javascript"></script>
<%
			Branch b = new LibrarianService().getBranch(Integer.parseInt(request.getParameter("brId")));
		
			
			List<Book> books = new LibrarianService().getBooks();
			
%>
<script>
function serUpdateBranch(){
	
	document.updateForm.submit();
	
}

function updateNoOfCopies(id){
	

	document.getElementById("nof"+id).innerHTML='<input type="text" placeholder="No of copies" id="newId"/>';
	var myBtn = document.getElementById("old"+id);
	
    submit = document.createElement("submit");
	
	
    submit.innerHTML = '<button  value="submit"  style="width: 100px" onclick="performUpdate('+id+')">submit</button>';
	
	<%-- document.getElementById("bookId").value=id;
	document.getElementById("branchId").value=<%=b.getBranchId()%>
	document.getElementById("noOfCopies").value= document.getElementById("nof").value; --%>
	myBtn.parentNode.replaceChild(submit, myBtn);
	
}
function test(){
	
	alert("<%=request.getParameter("brId")%>");
}
function performUpdate(id){
	
	 document.getElementById("bookId").value=""+id;
	
	 document.getElementById("branchId").value="<%=b.getBranchId()%>";
	document.getElementById("noOfCopies").value= document.getElementById("newId").value;
	
	document.noOfCopiesFrm.submit;

}


</script>

<!-- <div class="container theme-showcase" role="main" > -->

<!-- Main jumbotron for a primary marketing message or call to action -->


<div class="jumbotron" align="center" style="width: 500px">
	<h1>
		

	</h1>
	<form action="libUpdateBranch" method="post" name="updateForm">
		<ul class="ul1">

			<li class="title">Edit details</li>
			<li class="fields"><label>Branch Id</label><br />
			<input name="brId" type="text" value="<%=b.getBranchId()%>" /><br />
			<br /> <label>Branch Name</label><br />
			<input name="brName" type="text" value="<%=b.getBranchName()%>" /><br />
			<br /> <label>Branch Address</label><br />
			<input name="brAddress" type="text" value="<%=b.getBranchAddress()%>" /><br />
			<br /> <input type="hidden" name="nav" value="lib" /> <input
				type="button" onClick="serUpdateBranch()" value="Update" /></li>
		</ul>
		
	</form >

	<form action="noOfCopies" method="post" name="noOfCopiesFrm">
	<input type="hidden" name="bookId" id="bookId"/>
	<input type="hidden" name="branchId" id="branchId"/>
	<input type="hidden" name="noOfCopies" id="noOfCopies"/>
		<ul class="ul2">

			<li class="title">Add number of copies</li>
			<li class="fields">
				<table class="table">
					<tr bgcolor="gray">
						<td><font color="#fff">Book Id</font></td>
						<td><font color="#fff">Book Title</font></td>
						<td><font color="#fff"></font></td>
						<!-- <td><font color="#fff">Book Authors</font></td>
		<td><font color="#fff">Book Genres</font></td> -->
						<td><font color="#fff"></font></td>
						
					</tr>
					<%
					for (Book a : books) {
					
					%>
					<tr>
						<td><%=a.getBookId()%></td>
						<td id="title<%=a.getBookId()%>"><%=a.getTitle()%></td>
						<td id="nof<%=a.getBookId()%>"></td>

						<td><button id="old<%=a.getBookId()%>"  onclick="updateNoOfCopies('<%=a.getBookId()%>')">Add Copies</button></td>
						
					</tr>
					<%
						}
					%>
				</table>

			</li>
		</ul>
	
	
	</form>

</div>
<script>
	
	
	
	
	
	</script>
