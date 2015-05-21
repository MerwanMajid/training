<%@page import="com.gcit.training.lms.entity.*"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.lms.service.AdministratorService"%>
<%

	List<Publisher> publishers = new AdministratorService().getPublishers();

%>

<%@include file="index.jsp"%>

<script>
	function deletePublisher(id) {
		//document.location.href = "deleteAuthor?authorId="+id;
		
		document.getElementById("publisherId").value = id;
		document.deleteFrm.submit();
	}
function EditPublisher(id) {
	
		var name = "name"+id;
		var address = "address"+id;
		var phone = "phone"+id;
		
		var ad =  document.getElementById("address"+id).innerHTML;
		var na =  document.getElementById("name"+id).innerHTML;
		var ph =  document.getElementById("phone"+id).innerHTML;
		
		document.getElementById(name).innerHTML="<input type=text id ='txt"+name+"' placeholder='new publisher name'></input>";
		document.getElementById(address).innerHTML="<input type=text id='txt"+address+"' placeholder='new publisher address'></input>";
		document.getElementById(phone).innerHTML="<input type=text id='txt"+phone+"' placeholder='new publisher phone'></input>";
	
		 document.getElementById("txt"+name).value =  na;
		 document.getElementById("txt"+address).value =  ad;
		 document.getElementById("txt"+phone).value =  ph;
		 
		var myBtn = document.getElementById("old"+id),
		
	    mySpan = document.createElement("span");
		
		
		mySpan.innerHTML = '<button class="btn btn-success" value="submit" onclick="javascript:updatePublisher('+id+')">submit</button>';
	
		myBtn.parentNode.replaceChild(mySpan, myBtn);
		 
	}
	
	function updatePublisher(id) {
		
		document.getElementById("pubId").value=id;
		document.getElementById("pubName").value=document.getElementById("txtname"+id).value;
		document.getElementById("pubAddress").value=document.getElementById("txtaddress"+id).value;
		document.getElementById("pubPhone").value=document.getElementById("txtphone"+id).value;
		
		document.updatefrm.submit();
	
		

		
	}

</script>

<div style="margin-top:80px;margin-left:10px;">
<section>
<table class="table">
	<tr bgcolor="gray">
		<td><font color="#fff">Publisher Id</font></td>
		<td><font color="#fff">Publisher Name</font></td>
		<td><font color="#fff">Publisher Address</font></td>
		<td><font color="#fff">Publisher phone</font></td>
		<td><font color="#fff">Edit</font></td>
		<td><font color="#fff">Delete</font></td>
	</tr>
	<%for(Publisher a : publishers) { %>	
	<tr>
		<td><%=a.getPublisherId()%></td>
		<td id="name<%=a.getPublisherId()%>"><%=a.getPublisherName()%></td>
		<td id="address<%=a.getPublisherId()%>"><%=a.getPublisherAddress()%></td>
		<td id="phone<%=a.getPublisherId()%>"><%=a.getPublisherPhone()%></td>
		<td><button id="old<%=a.getPublisherId()%>"  onclick="javascript:EditPublisher('<%=a.getPublisherId()%>')" class="btn btn-success">Edit</button></td>
		<td><button class="btn btn-danger" onclick="javascript:deletePublisher(<%=a.getPublisherId()%>);">Delete</button></td>
	</tr>
	<% } %>
</table>
<form action="deletePublisher" method="post" name="deleteFrm">
	<input type="hidden" name="publisherId" id="publisherId"/>
</form>

<form action="updatePublisher" method="post" name="updatefrm">
	<input type="hidden" name="pubId" id="pubId"/>
	<input type="hidden" name="pubName" id="pubName"/>
	<input type="hidden" name="pubAddress" id="pubAddress"/>
	<input type="hidden" name="pubPhone" id="pubPhone"/>
</form>

</section>
</div>