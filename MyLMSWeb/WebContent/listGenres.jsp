<%@page import="com.gcit.training.lms.entity.Genre"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.lms.service.AdministratorService"%>
<%

	List<Genre> genres = new AdministratorService().getGenres();

%>
<%@include file="index.jsp"%>
<script>
	function deleteGenre(id) {
		//document.location.href = "deleteGenre?genreId="+id;
		
		document.getElementById("genreId").value = id;
		document.deleteFrm.submit();
	}
	function EditGenre(id,name) {
	
	
		
		document.getElementById(id).innerHTML="<input type=text id='txt"+id+"'placeholder="+name+"></input>";
	
		var myBtn = document.getElementById("old"+id),
		
	    mySpan = document.createElement("span");
		var x = "'"+id+"','"+name+"'";
		
		mySpan.innerHTML = '<button class="btn btn-success" value="submit" id="sub"+id+" onclick="javascript:updateGenre('+x+')">submit</button>';
	
		myBtn.parentNode.replaceChild(mySpan, myBtn);
		 
	}
	
	function updateGenre(id,name) {
		
	
		document.getElementById("gId").value=id;
		document.getElementById("gName").value=document.getElementById("txt"+id).value;
		//alert("hi update genre");
		
		document.updatefrm.submit(); 
		

		
	}

</script>

<div style="margin-top:80px;margin-left:10px;">
<section>
<table class="table">
	<tr bgColor="gray">
		<th><font color="#fff">Genre Id</font></th>
		<th><font color="#fff">Genre Name</font></th>
		<th></th>
		<th></th>
	</tr>
	<%for(Genre a : genres) { %>	
	<tr>
		<td ><%=a.getGenre_id()%></td>
		<td id="<%=a.getGenre_id()%>"><%=a.getGenre_name()%></td>
		<td><button id ='old<%=a.getGenre_id()%>'class="btn btn-success" onclick="javascript:EditGenre('<%=a.getGenre_id()%>','<%=a.getGenre_name()%>')">Edit</button></td>
		<td><button class="btn btn-danger" onclick="javascript:deleteGenre(<%=a.getGenre_id()%>);">Delete</button></td>
	</tr>
	<% } %>
</table>
<form action="deleteGenre" method="post" name="deleteFrm">
	<input type="hidden" name="genreId" id="genreId"/>
</form>
<form action="updateGenre" method="post" name="updatefrm">
<input type="hidden" name="gId" id="gId"/>
<input type="hidden" name="gName" id="gName"/>

</form>

</section>
</div>