<%@page import="com.gcit.training.lms.entity.*"%>
<%@page import="java.util.List"%>

<%@page import="com.gcit.training.lms.service.AdministratorService"%>
<%

	List<Author> authors = new AdministratorService().getAllAuthors();
	List<Genre> genres = new AdministratorService().getGenres();
	List<Publisher> publishers = new AdministratorService().getPublishers();
    
	Book bk = new AdministratorService().getBook(Integer.parseInt(request.getAttribute("bkId").toString()));

	List<Author> actAuthors = bk.getAuthors();
	List<Genre> actGenres = bk.getGenres();
	
	
	String s = request.getAttribute("bkTitle").toString();


	 List<Author> netAuths = new AdministratorService().getAllAuthors();
	 List<Genre> netGenres = new AdministratorService().getGenres();
	 
	 
	for(int i=0;i<actAuthors.size();i++){
		
		for(int j=0;j<authors.size();j++){
			
			if(!(actAuthors.get(i).getAuthorId()==authors.get(j).getAuthorId())){
				
			 // System.out.println(actAuthors.get(i).getAuthorId()+"/"+authors.get(j).getAuthorId());
			 // System.out.println("******"+newList.get(j).getAuthorId()+"******");
			  
			}
			else{
				netAuths.remove(authors.get(j));
				
			}
		}
	}
for(int i=0;i<actGenres.size();i++){
		
		for(int j=0;j<genres.size();j++){
			
			if(!(actGenres.get(i).getGenre_id()==genres.get(j).getGenre_id())){
				
			  //System.out.println(actGenres.get(i).getGenre_id()+"/"+genres.get(j).getGenre_id());
			 // System.out.println("******"+newList.get(j).getAuthorId()+"******");
			  
			}
			else{
				netGenres.remove(genres.get(j));
				
			}
		}
	}

	/* for(Author a : authors){
		for(Author b : actAuthors){
			
			if(b.getAuthorId() == a.getAuthorId()){
				
				authors.remove(a);
			}
		}
		
	}  */
	
%>

<%@include file="index.jsp"%>


<div style="margin-top:80px;margin-left:10px;align:center">
<section>

<form method="post" action="updateBook">
	Book Title &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; publisher</br>
	<input type="text" placeholder="Enter Title" name="title" id="test" value=<%=s%>/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<select name="pubId">
		<%for(Publisher a : publishers) { if(a.getPublisherId()==bk.getPublisher().getPublisherId()){ %>	
			<option selected value="<%=a.getPublisherId()%>"><%=a.getPublisherName()%></option>
			
										<% }else %>
										
										<option value="<%=a.getPublisherId()%>"><%=a.getPublisherName()%></option>
					
		<%} %>
	</select></br></br>
	select Author(s) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;select Genre(s)</br>
	<select multiple name="authorId">
	
	  <%for(Author b : actAuthors) { %>	
			
				<option  selected value="<%=b.getAuthorId()%>"><%=b.getAuthorName()%></option>
			
		<% } %>
		<%for(Author a : netAuths) { %>	
			
				<option  value="<%=a.getAuthorId()%>"><%=a.getAuthorName()%></option>
			
		<% } %>
		
	</select>&nbsp;&nbsp;

	
	<select multiple name="gId">
		<%for(Genre a : actGenres) { %>	
			<option selected value="<%=a.getGenre_id()%> selected"><%=a.getGenre_name()%></option>
		<% } %>
		
			<%for(Genre a : netGenres) { %>	
			<option value="<%=a.getGenre_id()%> selected"><%=a.getGenre_name()%></option>
		<% } %>
	</select><br/></br>
	 <input type="hidden" name="bookId" value=${bkId} />
	<input type="submit"/>
   
</form>
</section>
</div>

