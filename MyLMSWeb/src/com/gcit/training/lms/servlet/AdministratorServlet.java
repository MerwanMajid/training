package com.gcit.training.lms.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.training.lms.entity.*;
import com.gcit.training.lms.service.AdministratorService;

/**
 * Servlet implementation class AdministratorServlet
 */
@WebServlet({ "/addAuthor","/updateAuthor", "/deleteAuthor" , "/addPublisher",
	         "/deletePublisher","/updatePublisher","/addBorrower","/deleteBorrower",
	         "/updateBorrower","/addBranch","/updateBranch","/deleteBranch","/addGenre",
	         "/updateGenre","/deleteGenre","/addBook","/updateBook","/preUpdateBook"})
public class AdministratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministratorServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String function = request.getRequestURI().substring(
				request.getContextPath().length(),
				request.getRequestURI().length());

		//System.out.println(function);

		switch (function) {
		case "/addAuthor": {
			addAuthor(request, response);
			break;
		}
		case "/updateAuthor":{
			
			updateAuthor(request, response);
			
			break;
		}
		case "/deleteAuthor": {
			deleteAuthor(request, response);
			break;
		}
		case "/addPublisher":{
			addPublisher(request, response);
			
			break;
		}
		
		case "/updatePublisher":{
			
			
			updatePublisher(request, response);
			break;
		}
		case "/deletePublisher":{
			
			deletePublisher(request, response);
			break;
		}
		case "/addBorrower":{
			
			addBorrower(request, response);
			break;

		}
		case "/updateBorrower":{
			
			updateBorrower(request, response);
			break;
		}
		case "/deleteBorrower":{
			
			deleteBorrower(request, response);
			break;

		}
		case "/addBranch":{
			
			addBranch(request, response);
			break;

		}
		case "/updateBranch":{
			
			updateBranch(request, response);
			break;

		}
		case "/deleteBranch":{
			
			deleteBranch(request, response);
			break;

		}
		case "/addGenre":{
			
			addGenre(request, response);
			break;

		}
	case "/updateGenre":{
			
		updateGenre(request, response);
			
			break;
		}
		case "/deleteGenre": {
			deleteGenre(request, response);
			break;
		}
		case "/addBook": {
			
			addBook(request, response);
			
			break;
		}
		case "/updateBook": {
			
			updateBook(request, response);
			
			break;
		}
		case "/preUpdateBook": {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/updateBook.jsp");
			int id = Integer.parseInt(request.getParameter("bkId"));
			String title = request.getParameter("bkTitle");
			
			request.setAttribute("bkId", id);
			request.setAttribute("bkTitle", title);
			rd.forward(request, response);
			
			break;
		}
		default:
			break;
		}

	}

	private void updatePublisher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/listPublisher.jsp");
		try {
			
		
			
		Publisher pub = new Publisher();
		
		pub.setPublisherId(Integer.parseInt(request.getParameter("pubId")));
		pub.setPublisherName(request.getParameter("pubName"));
		pub.setPublisherAddress(request.getParameter("pubAddress"));
		pub.setPublisherPhone(request.getParameter("pubPhone"));
		
			
			new AdministratorService().updatePublisher(pub);
			request.setAttribute("result", "Publisher updated Succesfully!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rd.forward(request, response);
	}
	
	private void updateBorrower(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/listBorrowers.jsp");
		try {
			
		
			
		Borrower br = new Borrower();
		
		
		
		int cardNo = Integer.parseInt(request.getParameter("ucardNo"));
		
		br.setCardNo(cardNo);
		br.setName(request.getParameter("name"));
		br.setAddress(request.getParameter("address"));
		br.setPhone(request.getParameter("phone"));
		
			
			new AdministratorService().updateBorrower(br);
			request.setAttribute("result", "Borrower updated Succesfully!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rd.forward(request, response);
	}

	private void addBorrower(HttpServletRequest request,
			
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/borrowers.jsp");
		
		Borrower bo = new Borrower();
		bo.setAddress(request.getParameter("address"));
		bo.setName(request.getParameter("name"));
		bo.setPhone(request.getParameter("phone"));
		try{
			
			new AdministratorService().AddBorrower(bo);
			request.setAttribute("result", "borrower added Succesfully!");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		rd.forward(request, response);
	}

	private void updateAuthor(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Author auth = new Author();
		auth.setAuthorId(Integer.parseInt(request.getParameter("authId")));
		auth.setAuthorName(request.getParameter("authName"));
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/listAuthors.jsp");
		
		try {
			
			new AdministratorService().updateAuthor(auth);
			request.setAttribute("result", "Author updated Succesfully!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rd.forward(request, response);
	}

	private void updateGenre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Genre auth = new Genre();
		auth.setGenre_id(Integer.parseInt(request.getParameter("gId")));
		auth.setGenre_name(request.getParameter("gName"));
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/listGenres.jsp");
		
		try {
			
			new AdministratorService().updateGenre(auth);
			request.setAttribute("result", "Genre updated Succesfully!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rd.forward(request, response);
	}

	private void deletePublisher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pubId = request.getParameter("branchId");
		Publisher publisher = new Publisher();
		publisher.setPublisherId(Integer.parseInt(pubId));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listPublisher.jsp");
		try {
			new AdministratorService().deletePublisher((publisher));

			request.setAttribute("result", "Publisher Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Publisher Delete Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
	}
	private void deleteBorrower(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String cardNo = request.getParameter("cardNo");
		Borrower borrower = new Borrower();
		borrower.setCardNo(Integer.parseInt(cardNo));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listBorrowers.jsp");
		try {
			new AdministratorService().deleteBorrower((borrower));

			request.setAttribute("result", "Borrower Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Borrower Delete Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
	}

	private void addPublisher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pubName = request.getParameter("publisherName");
		String pubAddress = request.getParameter("publisherAddress");
		String pubPhone = request.getParameter("publisherPhone");
		
		Publisher pub = new Publisher();
		pub.setPublisherName(pubName);
		pub.setPublisherAddress(pubAddress);
		pub.setPublisherPhone(pubPhone);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/publishers.jsp");
		try {
			
			new AdministratorService().AddPublisher(pub);
			request.setAttribute("result", "publisher added Succesfully!");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rd.forward(request, response);
	}

	private void deleteAuthor(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String authorId = request.getParameter("authorId");
		Author author = new Author();
		author.setAuthorId(Integer.parseInt(authorId));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listAuthors.jsp");
		try {
			new AdministratorService().deleteAuthor(author);

			request.setAttribute("result", "Author Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Author Delete Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
	}

	private void addAuthor(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String authorName = request.getParameter("authorName");
		Author author = new Author();
		author.setAuthorName(authorName);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/authors.jsp");
		try {
			new AdministratorService().addAuthor(author);

			request.setAttribute("result", "Author Added Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Author Add Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
	}
	private void addGenre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String genreName = request.getParameter("genreName");
		Genre genre = new Genre();
		genre.setGenre_name(genreName);

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/genres.jsp");
		try {
			new AdministratorService().addGenre(genre);

			request.setAttribute("result", "Genre Added Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Genre Add Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
	}
	private void addBranch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String brName = request.getParameter("branchName");
		String brAddress = request.getParameter("branchAddress");
		
		
		Branch br = new Branch();
		br.setBranchName(brName);
		br.setBranchAddress(brAddress);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/branches.jsp");
		try {
			
			new AdministratorService().AddBranch(br);
			request.setAttribute("result", "branch added Succesfully!");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rd.forward(request, response);
	}

	private void updateBranch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=getServletContext().getRequestDispatcher("/listBranches.jsp");
		
		
		try {
			
		
			
		Branch br = new Branch();
		
		br.setBranchId(Integer.parseInt(request.getParameter("brId")));
		br.setBranchName(request.getParameter("brName"));
		br.setBranchAddress(request.getParameter("brAddress"));
		
		
			
			new AdministratorService().updateBranch(br);
			request.setAttribute("result", "Branch updated Succesfully!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rd.forward(request, response);
	}
	private void deleteBranch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String branchId = request.getParameter("branchId");
		Branch branch = new Branch();
		branch.setBranchId(Integer.parseInt(branchId));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listBranches.jsp");
		try {
			new AdministratorService().deleteBranch(branch);

			request.setAttribute("result", "Branch Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Branch Delete Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
	}
	private void deleteGenre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String genreId = request.getParameter("genreId");
		Genre genre = new Genre();
		genre.setGenre_id(Integer.parseInt(genreId));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/listGenres.jsp");
		try {
			new AdministratorService().deleteGenre(genre);

			request.setAttribute("result", "Genre Deleted Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Genre Delete Failed because: " + e.getMessage());
		}
		
		rd.forward(request, response);
	}
	
	private void addBook(HttpServletRequest request,
			HttpServletResponse response)  throws ServletException, IOException {
		
		String title = request.getParameter("title");
		int pubId = Integer.parseInt(request.getParameter("pubId"));
		String[] authorIds = request.getParameterValues("authorId");
		String[] genreIds = request.getParameterValues("gId");
		
		Publisher pub = new Publisher();
		try {
			pub = new AdministratorService().getPublisher(pubId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		Book bk = new Book();
		bk.setTitle(title);
		bk.setPublisher(pub);
		
		if(authorIds != null && authorIds.length > 0) {
			bk.setAuthors(new ArrayList<Author>());
			for(String s : authorIds) {
				Author author;
				try {
					author = new AdministratorService().getAuthor(Integer.parseInt(s));
					bk.getAuthors().add(author);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			}
		}
		if(genreIds != null && genreIds.length > 0) {
			bk.setGenres(new ArrayList<Genre>());
			for(String s : genreIds) {
				Genre genre;
				try {
					genre = new AdministratorService().getGenres(Integer.parseInt(s));
					bk.getGenres().add(genre);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/books.jsp");
		try {
			new AdministratorService().addBook(bk);

			request.setAttribute("result", "Book Added Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Book Add Failed because: " + e.getMessage());
		}

		rd.forward(request, response);
	}
	private void updateBook(HttpServletRequest request,
			HttpServletResponse response)  throws ServletException, IOException {
		
		String title = request.getParameter("title");
		int pubId = Integer.parseInt(request.getParameter("pubId"));
		String[] authorIds = request.getParameterValues("authorId");
		String[] genreIds = request.getParameterValues("gId");
		
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		
		Publisher pub = new Publisher();
		try {
			pub = new AdministratorService().getPublisher(pubId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		Book bk = new Book();
		bk.setBookId(bookId);
		bk.setTitle(title);
		bk.setPublisher(pub);
		
		if(authorIds != null && authorIds.length > 0) {
			bk.setAuthors(new ArrayList<Author>());
			for(String s : authorIds) {
				Author author;
				try {
					author = new AdministratorService().getAuthor(Integer.parseInt(s));
					bk.getAuthors().add(author);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			}
		}
		if(genreIds != null && genreIds.length > 0) {
			bk.setGenres(new ArrayList<Genre>());
			for(String s : genreIds) {
				Genre genre;
				try {
					genre = new AdministratorService().getGenres(Integer.parseInt(s));
					bk.getGenres().add(genre);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/books.jsp");
		try {
			new AdministratorService().updateBook(bk);

			request.setAttribute("result", "Book updated Succesfully!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result",
					"Book update Failed because: " + e.getMessage());
		}

		rd.forward(request, response);
	}

}
