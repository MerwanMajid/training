package com.gcit.training.lms.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.gcit.training.lms.dao.BookDAO;
import com.gcit.training.lms.dao.Book_copiesDAO;
import com.gcit.training.lms.dao.Book_loansDAO;
import com.gcit.training.lms.dao.BorrowerDAO;
import com.gcit.training.lms.dao.BranchDAO;
import com.gcit.training.lms.entity.*;

public class BorrowerService {
	
	
	public Borrower getBorrower(int cardNo)throws Exception{
		
		Connection c = ConnectionUtil.getConnection();
		
		BorrowerDAO brDAO = new BorrowerDAO(c);
		
		
		
		return brDAO.readOne(cardNo);
	}
	
	public List<Branch> getBranches()throws Exception{
		Connection c = ConnectionUtil.getConnection();
		BranchDAO bDAO = new BranchDAO(c);
		
		return bDAO.readAll();
		
	}
	
	public List<Book> getBooks()throws Exception{
		
		Connection c = ConnectionUtil.getConnection();
		BookDAO bDAO = new BookDAO(c);
		return bDAO.readAllBooks();
	}

	public Book_copies getBook_copies(int bookId,int branchId)throws Exception{
		Connection c = ConnectionUtil.getConnection();
		Book_copiesDAO bcDAO = new Book_copiesDAO(c);

		Book_copies out = bcDAO.readOne(bookId, branchId);
		
		return out;
	}

	public Branch getBranch(int branchId)throws Exception{
		Connection c = ConnectionUtil.getConnection();
		BranchDAO bDAO = new BranchDAO(c);
		
		return bDAO.readOne(branchId);
		
	}

	public void checkOut(Book book,Branch branch,Borrower borrower)throws Exception{
		Connection c = ConnectionUtil.getConnection();
		try{
		Book_loans bkLoans = new Book_loans();
		bkLoans.setBook(new Book());
		bkLoans.setBorrower(new Borrower());
		bkLoans.setBranch(new Branch());
		
	
		Book_copies bkCopies = getBook_copies(book.getBookId(), branch.getBranchId());
		
		
		Book_loansDAO bkLoansDao = new Book_loansDAO(c);
		Book_copiesDAO copDao = new Book_copiesDAO(c);
		
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
		
		copDao.update(bkCopies);
		bkLoansDao.create(bkLoans);
		c.commit();
		}
	catch(Exception e){
		c.rollback();
		throw e;
	}
		finally{
			c.close();
		}
		
	}
	
	public List<Book> getBooksByBranch(int branchId)throws Exception{
		
		Connection c = ConnectionUtil.getConnection();
		BookDAO bDAO = new BookDAO(c);
		return bDAO.readByBranch(branchId);
	}

	public Book getBook(int bookId)throws Exception{
		
		Connection c = ConnectionUtil.getConnection();
		BookDAO bDAO = new BookDAO(c);
		return bDAO.readOneBook(bookId);
	}
	
	public void returnBook(int bkLoanId,Book bk,Branch br,Borrower bo)throws Exception{
		Connection c = ConnectionUtil.getConnection();
		try{
			Book_loans bkLoans = getBook_loans(bkLoanId);
			
			
			Book_loansDAO bkLDAO = new Book_loansDAO(c);
			Book_copiesDAO bkCDAO = new Book_copiesDAO(c);

			Book_copies bc =bkCDAO.readOne(bk.getBookId(), br.getBranchId());
			
			bc.setBook(bk);
			bc.setBranch(br);
			bc.setNoOfCopies(bc.getNoOfCopies()+1);
			
			Calendar cal2 = Calendar.getInstance();
			Date dateIn = new Date(cal2.getTime().getTime());
			
			bkLoans.setDateIn(dateIn);
			
			bkLDAO.update(bkLoans);
			bkCDAO.update(bc);
			
			c.commit();
			System.out.println("returned successfully");
		}
	catch(Exception e){
		c.rollback();
		throw e;
	}
		finally{
			c.close();
		}
		
	}
	
	public Book_loans getBook_loans(int bkLoanId)throws Exception{
		
		Connection c = ConnectionUtil.getConnection();
		Book_loansDAO bklDAO = new Book_loansDAO(c);
		
		return bklDAO.readOne(bkLoanId);
		
	}
}
