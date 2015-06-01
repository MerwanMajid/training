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
			
			List<Book> books = new LibrarianService().getBooks();
			
			
%>
<script>
var bookId;
var branchId;
function serUpdateBranch(){
	
	document.updateForm.submit();
	
}

function updateNoOfCopies(id){
	

	document.getElementById("nof"+id).innerHTML='<input type="text" placeholder="No of copies" id="newId"/>';
	var myBtn = document.getElementById("old"+id);
	
    mySpan = document.createElement("span");
	
	
	mySpan.innerHTML = '<button  value="submit"  style="width: 100px" onclick="performUpdate('+id+')">submit</button>';
	
	<%-- document.getElementById("bookId").value=id;
	document.getElementById("branchId").value=<%=b.getBranchId()%>
	document.getElementById("noOfCopies").value= document.getElementById("nof").value; --%>
	myBtn.parentNode.replaceChild(mySpan, myBtn);
	
}

function chooseBook(id){
	
	$('#myTable').hide();
	$("#bookTable").show();
	
	$('#myTable2').hide();
	$("#bookTable2").show();
	
	branchId=id;
	

}
function returnBook(id){

	bookId = id;
	
	//$('#myTable2').show();
	$("#bookTable2").hide();
	$.ajax({
		url : 'returnBook',
		data: {cardNo: $("#txtCardNo").val(),bookId:bookId,branchId:branchId,bkLoanId:$("#bkLoanId").val()},
		dataType: 'json',
	    success: function (data) {
	    	if(data == "failed") {
		    	$("#result").css("display", "block");
		    	$("#result").html("An Error occured");
	    	} else {
	    		$("#result").css("display", "block");
	    		
	    		$("#bkLoanId").hide();
	    	
	    	}
		}
	});
	
	alert("returned successfully");
}
function checkOut(id){

	bookId = id;
	var msg="";
	//$('#myTable').show();
	$("#bookTable").hide();
	$.ajax({
		url : 'checkOut',
		data: {cardNo: $("#txtCardNo").val(),bookId:bookId,branchId:branchId},
		dataType: 'json',
	    success: function (data) {
	    	if(data == "failed") {
		    	$("#result").css("display", "block");
		    	$("#result").html("An Error occured");
	    	} else {
	    		$("#result").css("display", "block");
	    		alert("your Loan Id is "+data+", use it when return");
	    		
	    	}
		}
	});
	
}
function btnCheckCkCard(){
	
	$.ajax({
		url : 'getBorrower',
		data: {cardNo: $("#txtCardNo").val()},
		dataType: 'json',
	    success: function (data) {
	    	if(data == "failed") {
		    	$("#result").css("display", "block");
		    	$("#result").html("invalid login! try again");
	    	} else {
	    		$("#result").css("display", "block");
	    		$("#result").html("<h4>welcome "+data['name']+"</h4><br/><br/>");
	    		$('#myTable').show();
	    		$('#myTable2').show();
	    		/* $('#txtCardNo').hide();
	    		$('#btnCardCheck').hide(); */
	    	}
		}
	});

}


</script>

<!-- <div class="container theme-" role="main" > -->

<!-- Main  for a primary marketing message or call to action -->
<script>
var value;
$(document).ready(function(){
$('#bookTable').hide();
$('#bookTable2').hide();
$.ajax({
	url : 'getAllBranches',
	dataType: 'json',
    success: function (data) {
    	 for (var key in data) {
    		 
    		 var id = data[key]['branchId'];
    		 var name = data[key]['branchName'];
    		 
    		 $('#myTable').append("<tr><td>"
    				 +id+"</td><td>"+name+"</td>"
    				 +"<td><input type='button' value='Pick Branch' onClick='chooseBook("+id+")' id="+id+"/></td>"); 
    		 $('#myTable2').append("<tr><td>"
    				 +id+"</td><td>"+name+"</td>"
    				 +"<td><input type='button' value='Pick Branch' onClick='chooseBook("+id+")' id="+id+"/></td>"); 
    	  }
    	
    }
});
//for the purpose of organizing the code, a separate function has been created for the above button action.
$("#myTable").hide();
$("#myTable2").hide();

});




</script>

<div class="jumbotron" align="center" style="width: 500px">
	<input type="text" placeholder="Enter your card No" id="txtCardNo"/>
	<input type="button" value="login" id="btnCardCheck" onClick="btnCheckCkCard()"/><br/><br/>
	
	<div id="result" style="display:none"></div>
	
	<form  method="post" name="updateForm">
		<ul class="ul1">
			
			<li class="title">check out a Book</li>
			<li class="fields">
			<table class="table" id="myTable">
			<tr bgcolor="gray"><td><font color="#fff">Branch Id</font></td><td><font color="#fff">Branch Name</font></td><td></td></tr>
			</table>
			
			<table class="table" id="bookTable">
			<tr bgcolor="gray"><td><font color="#fff">Book Id</font></td><td><font color="#fff">Book Title</font></td><td></td><td></td></tr>
			<%
					for (Book a : books) {
					
					%>
					<tr>
						<td><%=a.getBookId()%></td>
						<td id="title<%=a.getBookId()%>"><%=a.getTitle()%></td>
						<td id="nof<%=a.getBookId()%>"></td>

						<td><button id="<%=a.getBookId()%>"  onclick='checkOut("<%=a.getBookId()%>")'>Choose Book</button></td>
						
					</tr>
					<%
						}
					%>
			</table>
			</li>
			
		</ul>
		
	</form >

	
	
		<ul class="ul2">

			<li class="title">return Book</li>
			<li class="fields">
			<input type="text" id="bkLoanId" placeHolder="your Book Loan Id"/></br></br>
			<table class="table" id="myTable2">
			<tr bgcolor="gray"><td><font color="#fff">Branch Id</font></td><td><font color="#fff">Branch Name</font></td><td></td></tr>
			</table>
			
			<table class="table" id="bookTable2">
			<tr bgcolor="gray"><td><font color="#fff">Book Id</font></td><td><font color="#fff">Book Title</font></td><td></td><td></td></tr>
			<%
					for (Book a : books) {
					
					%>
					<tr>
						<td><%=a.getBookId()%></td>
						<td id="title<%=a.getBookId()%>"><%=a.getTitle()%></td>
						<td id="nof<%=a.getBookId()%>"></td>

						<td><button id="<%=a.getBookId()%>"  onclick='returnBook("<%=a.getBookId()%>")'>Choose Book</button></td>
						
					</tr>
					<%
						}
					%>
			</table>
			</li>
		</ul>
	


</div>
<script>
</script>
