<%@page import="com.gcit.training.lms.entity.*"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.lms.service.AdministratorService"%>
<%

	List<Author> authors = new AdministratorService().getAllAuthors();
	List<Genre> genres = new AdministratorService().getGenres();
	List<Publisher> publishers = new AdministratorService().getPublishers();

%>

<%@include file="index.jsp"%>

<div style="margin-top:80px;margin-left:10px;align:center">
<section>
<form method="post" action="addBook">
	Book Title &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; publisher</br>
	<input type="text" placeholder="Enter Title" name="title"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<select name="pubId">
		<%for(Publisher a : publishers) { %>	
			<option value="<%=a.getPublisherId()%>"><%=a.getPublisherName()%></option>
		<% } %>
	</select></br></br>
	
	select Author(s) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;select Genre(s)</br>
	<select multiple name="authorId">
		<%for(Author a : authors) { %>	
			<option value="<%=a.getAuthorId()%>"><%=a.getAuthorName()%></option>
		<% } %>
	</select>&nbsp;&nbsp;

	
	<select multiple name="gId">
		<%for(Genre a : genres) { %>	
			<option value="<%=a.getGenre_id()%>"><%=a.getGenre_name()%></option>
		<% } %>
	</select><br/></br>
	<input type="submit"/>

</form>
</section>
</div>