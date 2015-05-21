<%@page import="com.gcit.training.lms.entity.*"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.lms.service.AdministratorService"%>
<%

	List<Borrower> borrowers = new AdministratorService().getBorrowers();

%>

<%@include file="index.jsp"%>

<script>
	function deleteBorrower(id) {
		//document.location.href = "deleteAuthor?authorId="+id;
		
		document.getElementById("cardNo").value = id;
		document.deleteFrm.submit();
	}
function EditBorrower(id) {
	
		var name = "name"+id;
		var address = "address"+id;
		var phone = "phone"+id;
		
		var ad =  document.getElementById("address"+id).innerHTML;
		var na =  document.getElementById("name"+id).innerHTML;
		var ph =  document.getElementById("phone"+id).innerHTML;
		
		document.getElementById(name).innerHTML="<input type=text id ='txt"+name+"' placeholder='new borrower name'></input>";
		document.getElementById(address).innerHTML="<input type=text id='txt"+address+"' placeholder='new borrower address'></input>";
		document.getElementById(phone).innerHTML="<input type=text id='txt"+phone+"' placeholder='new borrower phone'></input>";
	   
		 document.getElementById("txt"+name).value =  na;
		 document.getElementById("txt"+address).value =  ad;
		 document.getElementById("txt"+phone).value =  ph;
		 
		
		var myBtn = document.getElementById("old"+id),
		
	    mySpan = document.createElement("span");
		
		
		mySpan.innerHTML = '<button class="btn btn-success" value="submit" onclick="javascript:updateBorrower('+id+')">submit</button>';
	
		myBtn.parentNode.replaceChild(mySpan, myBtn);
		 
	}
	
	function updateBorrower(id) {
		
		
		document.getElementById("ucardNo").value=id;
		document.getElementById("name").value=document.getElementById("txtname"+id).value;
		document.getElementById("address").value=document.getElementById("txtaddress"+id).value;
		document.getElementById("phone").value=document.getElementById("txtphone"+id).value;
		
		document.updatefrm.submit();
	
		 

		
	}

</script>

<div style="margin-top:80px;margin-left:10px;">
<section>
<table class="table">
	<tr>
		<td>Borrower Id</td>
		<td>Borrower Name</td>
		<td>Borrower Address</td>
		<td>Borrower Phone</td>
		<td>Edit</td>
		<td>Delete</td>
	</tr>
	<%for(Borrower a : borrowers) { %>	
	<tr>
		<td><%=a.getCardNo()%></td>
		<td id="name<%=a.getCardNo()%>"><%=a.getName()%></td>
		<td id="address<%=a.getCardNo()%>"><%=a.getAddress()%></td>
		<td id="phone<%=a.getCardNo()%>"><%=a.getPhone()%></td>
		<td><button id="old<%=a.getCardNo()%>"  onclick="javascript:EditBorrower('<%=a.getCardNo()%>')" class="btn btn-success">Edit</button></td>
		<td><button class="btn btn-danger" onclick="javascript:deleteBorrower(<%=a.getCardNo()%>);">Delete</button></td>
	</tr>
	<% } %>
</table>
<form action="deleteBorrower" method="post" name="deleteFrm">
	<input type="hidden" name="cardNo" id="cardNo"/>
</form>

<form action="updateBorrower" method="post" name="updatefrm">
	<input type="hidden" name="ucardNo" id="ucardNo"/>
	<input type="hidden" name="name" id="name"/>
	<input type="hidden" name="address" id="address"/>
	<input type="hidden" name="phone" id="phone"/>
</form>

</section>
</div>