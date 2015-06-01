package com.gcit.training.lms.service;

import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.training.lms.dao.*;
import com.gcit.training.lms.entity.*;

public class LibrarianService {
	
	@Autowired
	public BasicDataSource ds;

	@Autowired
	private AuthorDAO authorDAO;
	
	@Autowired
	private Book_copiesDAO bkcDAO;
	
	@Autowired
	private Book_loansDAO bklDAO;
	
	@Autowired
	private BookDAO bookDAO;
	
	@Autowired
	private BorrowerDAO borDAO;
	
	@Autowired
	private BranchDAO brDAO;
	
	@Autowired
	private GenreDAO genDAO;
	
	@Autowired
	private PublisherDAO pubDAO;

	public List<Branch> getBranches()throws Exception{
		
		return brDAO.readAll();
		
	}
	
	public Branch getBranch(int branchId)throws Exception{
		
		
		return brDAO.readOne(branchId);
		
	}
	
	@Transactional
	public void updateBranch(Branch branch) throws Exception {
	
			
			brDAO.update(branch);
	
	}
	
	public List<Book> getBooksByBranch(int branchId)throws Exception{
		
	
		return bookDAO.readByBranch(branchId);
	}

	public Book getBook(int bookId)throws Exception{
		
		
		return bookDAO.readOne(bookId);
	}

	public List<Book> getBooks()throws Exception{
		
	
		return bookDAO.readAll();
	}

	public Book readBook(int bookId)throws Exception{
		
		return  bookDAO.readOne(bookId);
	}

	public  List<Book> readAllBooks()throws Exception{
		
		return bookDAO.readAll();
		
		
	}

	@Transactional
	public void updateNoOfCopies(Book_copies book_copies)throws Exception{
		
			
			bkcDAO.update(book_copies);
		
	}
	
	public Book_copies getBook_copies(int bookId,int branchId)throws Exception{
		

		return bkcDAO.readOne(bookId, branchId);
	}

	@Transactional
	public void addBook_Copies(Book_copies book_copies) throws Exception {
	
			bkcDAO.addBook_copies(book_copies);
			
	}
 
}
