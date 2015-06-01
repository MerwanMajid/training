package com.gcit.training.lms.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcit.training.lms.entity.*;
import com.gcit.training.lms.service.BorrowerService;
import com.gcit.training.lms.service.LibrarianService;

/**
 * Servlet implementation class LibrarianServlet
 */
@WebServlet({ "/libUpdateBranch", "/noOfCopies", "/getAllBranches",
		"/getBorrower","/checkOut","/returnBook"})
public class LibrarianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LibrarianServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

		switch (function) {

		case "/libUpdateBranch": {

			libUpdateBranch(request, response);
			break;
		}
		case "/checkOut": {
			
			checkOut(request, response);
			
			break;
		}
		case "/returnBook": {
			Book bk = new Book();
			Borrower bo = new Borrower();
			Branch br = new Branch();
			int cardNo = Integer.parseInt(request.getParameter("cardNo"));
			
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			int branchId = Integer.parseInt(request.getParameter("branchId"));
			
			int bkLoanId =  Integer.parseInt(request.getParameter("bkLoanId"));
			
			try{
			bk = new BorrowerService().getBook(bookId);
			br = new BorrowerService().getBranch(branchId);
			bo = new BorrowerService().getBorrower(cardNo);
			
			new BorrowerService().returnBook(bkLoanId, bk, br, bo);
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
			break;
		}
		case "/noOfCopies": {

			noOfCopies(request, response);
			break;
		}

		case "/getAllBranches": {

			getAllBooks(response);

		}
		case "/getBorrower": {

			getBorrower(request,response);
			
			break;

		}

		}

	}

	private void checkOut(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			JsonGenerationException, JsonMappingException {
		Branch br = new Branch();
		Book bk = new Book();
		Borrower bo = new Borrower();
		int brId = Integer.parseInt(request.getParameter("branchId"));
		int bkId = Integer.parseInt(request.getParameter("bookId"));
		int cardNo = Integer.parseInt(request.getParameter("cardNo"));
		ObjectMapper om = new ObjectMapper();
		try{
			br = new BorrowerService().getBranch(brId);
			bk = new BorrowerService().getBook(bkId);
			bo = new BorrowerService().getBorrower(cardNo);
			
			int bkLoanId = new BorrowerService().checkOut(bk, br, bo);
			System.out.println(bkLoanId);
			om.writeValue(response.getWriter(), bkLoanId+"");
		}
		catch(Exception e){
			e.printStackTrace();
			om.writeValue(response.getWriter(), "failed");
		}
	}

	private void getBorrower(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			JsonGenerationException, JsonMappingException {
		
		ObjectMapper om = new ObjectMapper();
		int cardNo = Integer.parseInt(request.getParameter("cardNo"));
		try {

			Borrower borrower = new BorrowerService().getBorrower(cardNo);
			borrower.getCardNo();
			om.writeValue(response.getWriter(), borrower);

		} catch (Exception e) {
			e.printStackTrace();
			om.writeValue(response.getWriter(), "failed");
		}
	}

	private void getAllBooks(HttpServletResponse response) throws IOException,
			JsonGenerationException, JsonMappingException {
		ObjectMapper om = new ObjectMapper();

		try {

			List<Branch> list = new LibrarianService().getBranches();

			om.writeValue(response.getWriter(), list);

		} catch (Exception e) {
			e.printStackTrace();
			om.writeValue(response.getWriter(), "failed");
		}
	}

	private void noOfCopies(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int brId = Integer.parseInt(request.getParameter("branchId"));
		int noc = Integer.parseInt(request.getParameter("noOfCopies"));

		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/libBranch.jsp?brId=" + brId);
		try {

			Book_copies bkc = new LibrarianService().getBook_copies(bookId,
					brId);
			bkc.setNoOfCopies(noc);
			new LibrarianService().updateNoOfCopies(bkc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rd.forward(request, response);
	}

	private void libUpdateBranch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Branch b = new Branch();
		b.setBranchId(Integer.parseInt(request.getParameter("brId")));
		b.setBranchName(request.getParameter("brName"));
		b.setBranchAddress(request.getParameter("brAddress"));
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/home.jsp");
		try {
			new LibrarianService().updateBranch(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		rd.forward(request, response);
	}

}
