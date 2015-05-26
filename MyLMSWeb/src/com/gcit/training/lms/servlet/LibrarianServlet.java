package com.gcit.training.lms.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.training.lms.entity.*;
import com.gcit.training.lms.service.LibrarianService;

/**
 * Servlet implementation class LibrarianServlet
 */
@WebServlet("/libBranch")
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
			case "/libBranch": {
		
				testMethod(request, response);
			break;
			}
		}

	}

	private void testMethod(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Branch b = new Branch();
		b.setBranchId(Integer.parseInt(request.getParameter("brId")));
		b.setBranchName(request.getParameter("brName"));
		b.setBranchAddress(request.getParameter("brAddress"));
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/libBranch.jsp");
		try{
			new LibrarianService().updateBranch(b);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		rd.forward(request, response);
	}

}
