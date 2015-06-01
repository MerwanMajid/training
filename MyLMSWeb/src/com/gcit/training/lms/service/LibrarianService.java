package com.gcit.training.lms.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.dao.*;
import com.gcit.training.lms.entity.*;

public class LibrarianService {
	
	

	public List<Branch> getBranches()throws Exception{
		Connection c = ConnectionUtil.getConnection();
		BranchDAO bDAO = new BranchDAO(c);
		
		return bDAO.readAll();
		
	}
	
	public Branch getBranch(int branchId)throws Exception{
		Connection c = ConnectionUtil.getConnection();
		BranchDAO bDAO = new BranchDAO(c);
		
		return bDAO.readOne(branchId);
		
	}
	
	public void updateBranch(Branch branch) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			if (branch == null || branch.getBranchName() == null
					|| branch.getBranchName().length() == 0 ||

					branch.getBranchAddress() == null
					|| branch.getBranchAddress().length() == 0) {

				throw new Exception("branch information can't br blank");
			}
			BranchDAO pDAO = new BranchDAO(c);
			pDAO.update(branch);
			c.commit();
			System.out.println("branch updated successfully: "
					+ branch.getBranchName());
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
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

	public List<Book> getBooks()throws Exception{
		
		Connection c = ConnectionUtil.getConnection();
		BookDAO bDAO = new BookDAO(c);
		return bDAO.readAllBooks();
	}
	public Book readBook(int bookId)throws Exception{
		Connection c = ConnectionUtil.getConnection();
		BookDAO b = new BookDAO(c);
		
		return  b.readOneBook(bookId);
	}
	public  List<Book> readAllBooks()throws Exception{
		Connection c = ConnectionUtil.getConnection();
		BookDAO b = new BookDAO(c);
		b.readAllBooks();
		
		return  b.readAllBooks();
	}

	public void updateNoOfCopies(Book_copies book_copies)throws Exception{
		Connection c = ConnectionUtil.getConnection();
		try {
			if (book_copies == null || book_copies.getBook() == null
					|| book_copies.getBranch() == null) {

				throw new Exception("book_copies information can't br blank");
			}
			Book_copiesDAO bcDAO = new Book_copiesDAO(c);
			
			bcDAO.update(book_copies);
			c.commit();
			System.out.println("book_copies updated successfully");
				
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}

		
		
	}
	
	public Book_copies getBook_copies(int bookId,int branchId)throws Exception{
		Connection c = ConnectionUtil.getConnection();
		Book_copiesDAO bcDAO = new Book_copiesDAO(c);


		return bcDAO.readOne(bookId, branchId);
	}

	public void addBook_Copies(Book_copies book_copies) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			if (book_copies == null || book_copies.getBook() == null
					|| book_copies.getBranch() == null) {

				throw new Exception("book_copies information can't br blank");
			}
			Book_copiesDAO bcDAO = new Book_copiesDAO(c);
			
			bcDAO.create(book_copies);
			c.commit();
			System.out.println("book_copies updated successfully: ");
					
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}
 
}
