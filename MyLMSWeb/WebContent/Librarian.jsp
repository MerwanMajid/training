<%@page import="com.gcit.training.lms.entity.*"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.lms.service.AdministratorService"%>
<%

	List<Branch> branches = new AdministratorService().getBranches();

%>

<%@include file="index.jsp"%>
<script>

function navigate(brId){
	
	window.location.href="libBranch.jsp?brId="+brId;
	
}
</script>

<div style="margin-top:80px;margin-left:10px;">
<section>
<table class="table">
	<tr bgcolor="gray" ><font color:red>
		<td><font color="#fff">Branch Id</font></td>
		<td><font color="#fff">Branch Name</font></td>
		<td><font color="#fff">Branch Address</font></td>
		<td><font color="#fff"></font></td>
	</tr>
	<%for(Branch a : branches) { %>	
	<tr>
		<td><%=a.getBranchId()%></td>
		<td id="name<%=a.getBranchId()%>"><%=a.getBranchName()%></td>
		<td id="address<%=a.getBranchId()%>"><%=a.getBranchAddress()%></td>
		
		<td><button id="old<%=a.getBranchId()%>"  class="btn btn-success" onClick="navigate('<%=a.getBranchId()%>')">Enter</button></td>
		
	</tr>
	<% } %>
</table>


</section>
</div>