<%@page import="com.gcit.training.lms.service.LibrarianService"%>
<%@page import="com.gcit.training.lms.service.AdministratorService"%>
<%@page import="com.gcit.training.lms.servlet.*"%>
<%@page import="com.gcit.training.lms.entity.*"%> 



<br/><br/><br/><br/>
<link rel="stylesheet" href="css/myStyle.css" type="text/css"/>
<script src="jquery.js" type="text/javascript" ></script>

<script src="animation.js" type="text/javascript" ></script>
<script>
function serUpdateBranch(){
	
	document.testForm.submit();
}


</script>

<!-- <div class="container theme-showcase" role="main" > -->

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron" align="center" style="width: 500px">
	<h1>
	<%

	Branch b =new LibrarianService().getBranch(1);
	
	%>
	
	</h1>
		<form action="libBranch" method="post" name="testForm">
			<ul class="ul1">
				
				<li class="title">Edit details</li>
				<li class="fields">
					<label>Branch Id</label><br/><input name="brId" type="text" value="<%=b.getBranchId()%>"/><br/><br/>
					<label>Branch Name</label><br/><input name="brName" type="text" value="<%=b.getBranchName()%>" /><br/><br/>
					<label>Branch Address</label><br/><input name="brAddress" type="text" value="<%=b.getBranchAddress()%>"/><br/><br/>
					<input type="hidden" name="nav" value="lib"/>
					<input type="button" onClick="serUpdateBranch()" value="Update"/>
				</li>
			</ul>
		</form>
				
		<form>
		
			<ul class="ul2">
				
				<li class="title">Add number of copies</li>
				<li class="fields">
					<input id="1" type="text" ><br/><br/>
					<input id="2" type="text" /><br/><br/>
					<input id="3" type="text" /><br/><br/>
					<input id="4" type="text" /><br/><br/>
					
				</li>
			</ul>
			
			<input type="submit"/>
	  </form>
		
	</div>
<script>
	
	
	
	
	
	</script>
	