
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.lms.service.AdministratorService"%>
<%@page import="com.gcit.training.lms.entity.Branch"%>

<%

List<Branch> branches =null;
	AdministratorService admin  = new AdministratorService();
	branches = (List<Branch>)admin.getBranches();

%>

<%@include file="index.jsp"%>

<script>
	function deleteBranch(id) {
		
		
		document.getElementById("branchId").value = id;
		document.deleteFrm.submit();
	}
function EditBranch(id) {
	
		var name = "name"+id;
		var address = "address"+id;
	
		
		var ad =  document.getElementById("address"+id).innerHTML;
		var na =  document.getElementById("name"+id).innerHTML;
		
		
		document.getElementById(name).innerHTML="<input type=text id ='txt"+name+"' placeholder='new branch name'></input>";
		document.getElementById(address).innerHTML="<input type=text id='txt"+address+"' placeholder='new branch address'></input>";
		
	
		 document.getElementById("txt"+name).value =  na;
		 document.getElementById("txt"+address).value =  ad;
		
		 
		var myBtn = document.getElementById("old"+id),
		
	    mySpan = document.createElement("span");
		
		
		mySpan.innerHTML = '<button class="btn btn-success" value="submit" onclick="javascript:updateBranch('+id+')">submit</button>';
	
		myBtn.parentNode.replaceChild(mySpan, myBtn);
		 
	}
	
	function updateBranch(id) {
		
		document.getElementById("brId").value=id;
		document.getElementById("brName").value=document.getElementById("txtname"+id).value;
		document.getElementById("brAddress").value=document.getElementById("txtaddress"+id).value;
		
		document.updatefrm.submit();
	
		

		
	}

</script>

<div style="margin-top:80px;margin-left:10px;">
<section>
<table class="table">
	<tr bgcolor="gray" >
		<td><font color="#fff">Branch Id</font></td>
		<td><font color="#fff">Branch Name</font></td>
		<td><font color="#fff">Branch Address</font></td>
		<td><font color="#fff">Edit</font></td>
		<td><font color="#fff">Delete</font></td>
		
	</tr>
	<%for(Branch a : branches) { %>	
	<tr>
		<td><%=a.getBranchId()%></td>
		<td id="name<%=a.getBranchId()%>"><%=a.getBranchName()%></td>
		<td id="address<%=a.getBranchId()%>"><%=a.getBranchAddress()%></td>
		
		<td><button id="old<%=a.getBranchId()%>"  onclick="javascript:EditBranch('<%=a.getBranchId()%>')" class="btn btn-success">Edit</button></td>
		<td><button class="btn btn-danger" onclick="javascript:deleteBranch(<%=a.getBranchId()%>);">Delete</button></td>
	</tr>
	<% } %>
</table>
<form action="deleteBranch" method="post" name="deleteFrm">
	<input type="hidden" name="branchId" id="branchId"/>
</form>

<form action="LibrarianServlet" method="post" name="updatefrm">
	<input type="hidden" name="brId" id="brId"/>
	<input type="hidden" name="brName" id="brName"/>
	<input type="hidden" name="brAddress" id="brAddress"/>
	<input type="hidden" name="brPhone" id="brPhone"/>
	<input type="hidden" name="nav" value="admin"/>
</form>

</section>
</div>