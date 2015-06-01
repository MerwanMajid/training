package com.gcit.training.lms.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.training.lms.dao.*;
import com.gcit.training.lms.entity.*;

public class BorrowerService {
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
	
	
	public Borrower getBorrower(int cardNo)throws Exception{

		return borDAO.readOne(cardNo);
				
	}
	
	public List<Branch> getBranches()throws Exception{
		
		
		return brDAO.readAll();
		
	}
	
	public List<Book> getBooks()throws Exception{
		
	
		return bookDAO.readAll();
	}

	public Book_copies getBook_copies(int bookId,int branchId)throws Exception{
		

		return bkcDAO.readOne(bookId, branchId);
		
		
	}

	public Branch getBranch(int branchId)throws Exception{
	
		return brDAO.readOne(branchId);
		
	}

	@Transactional
	public int checkOut(Book book,Branch branch,Borrower borrower)throws Exception{
		
		Book_loans bkLoans = new Book_loans();
		bkLoans.setBook(new Book());
		bkLoans.setBorrower(new Borrower());
		bkLoans.setBranch(new Branch());
		
	
		Book_copies bkCopies = getBook_copies(book.getBookId(), branch.getBranchId());
		
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		Date dateOut = new Date(cal.getTime().getTime());
		
		cal2.add(Calendar.DATE, 7);
		Date dueDate = new Date(cal2.getTime().getTime());
		
		bkLoans.setBook(book);
		bkLoans.setBranch(branch);
		bkLoans.setBorrower(borrower);
		bkLoans.setDateOut(dateOut);
		bkLoans.setDueDate(dueDate);
		
		
		bkCopies.setNoOfCopies(bkCopies.getNoOfCopies()-1);
		
		bkcDAO.update(bkCopies);
		int bkLoanId = bklDAO.addBook_loans(bkLoans);
		
		return bkLoanId;
	
	}
	
	public List<Book> getBooksByBranch(int branchId)throws Exception{
		
		
		return bookDAO.readByBranch(branchId);
	}

	public Book getBook(int bookId)throws Exception{
		
	
		return bookDAO.readOne(bookId);
	}
	
	@Transactional
	public void returnBook(int bkLoanId,Book bk,Branch br,Borrower bo)throws Exception{
	
			Book_loans bkLoans = getBook_loans(bkLoanId);

			Book_copies bc =bkcDAO.readOne(bk.getBookId(), br.getBranchId());
			
			bc.setBook(bk);
			bc.setBranch(br);
			bc.setNoOfCopies(bc.getNoOfCopies()+1);
			
			Calendar cal2 = Calendar.getInstance();
			Date dateIn = new Date(cal2.getTime().getTime());
			
			bkLoans.setDateIn(dateIn);
			
			bklDAO.update(bkLoans);
			bkcDAO.update(bc);
			
			
	}
	
	public Book_loans getBook_loans(int bkLoanId)throws Exception{
		
		return bklDAO.readOne(bkLoanId);
		
	}
}
