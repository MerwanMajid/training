package com.gcit.training.lms.service;


import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.training.lms.dao.*;
import com.gcit.training.lms.entity.*;

public class AdministratorService {

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
	
	@Transactional
	public void addAuthor(Author author) throws Exception {
		authorDAO.addAuthor(author);
	}

	@Transactional
	public void deleteAuthor(Author author) throws Exception {
		authorDAO.delete(author);
	}
	
	@Transactional
	public void updateAuthor(Author author) throws Exception {
		authorDAO.update(author);
	}
	
	public Author getAuthor(int authorId) throws Exception {
		return authorDAO.readOne(authorId);
	}
	
	public List<Author> getAuthors(int pageNo, int pageSize) throws Exception {
		authorDAO.setPageNo(pageNo);
		authorDAO.setPageSize(pageSize);
		return authorDAO.readAll();
	}
	
	@Transactional
	public void addBook_copies(Book_copies bkc) throws Exception {
		bkcDAO.addBook_copies(bkc);
	}

	@Transactional
	public void updateBook_copies(Book_copies bkc) throws Exception {
		bkcDAO.update(bkc);
	}

	@Transactional
	public void deleteBook_copies(Book_copies bkc) throws Exception {
		bkcDAO.delete(bkc);
	}
	
	public void getBook_copies(int bookId, int branchId) throws Exception {
		bkcDAO.readOne(bookId, branchId);
	}
	
	public List<Book_copies> getAllBook_copies() throws Exception {
		
		return bkcDAO.readAll();
	}
	
	@Transactional
	public void addBook_loans(Book_loans bkl) throws Exception {
		bklDAO.addBook_loans(bkl);
	}

	@Transactional
	public void updateBook_loans(Book_loans bkl) throws Exception {
		bklDAO.update(bkl);
	}

	@Transactional
	public void deleteBook_loans(Book_loans bkl) throws Exception {
		bklDAO.delete(bkl);
	}
	
	public Book_loans getBook_loans(int bkLoanId) throws Exception {
		return bklDAO.readOne(bkLoanId);
	}
	
	public List<Book_loans> getAllBook_loans() throws Exception {
		
		return bklDAO.readAll();
	}

	@Transactional
	public void addBook(Book b) throws Exception {
			bookDAO.addBook(b);
	}
	
	@Transactional
	public void updateBook(Book b) throws Exception {
			bookDAO.update(b);
	}
	
	@Transactional
	public void deleteBook(Book b) throws Exception {
			bookDAO.delete(b);
	}

	public Book getBook(int bookId) throws Exception {
		
			return bookDAO.readOne(bookId);
	}

	public List<Book> getBooks() throws Exception {
		
			return bookDAO.readAll();
	}

	public List<Book> searchBooks(String searchString) throws Exception {
		return bookDAO.searchBookByTitle(searchString);
	}
	
	@Transactional
	public void addBorrwer(Borrower borrower) throws Exception {
			borDAO.addBorrower(borrower);
	}
	
	@Transactional
	public void updateBorrower(Borrower borrower) throws Exception {
		borDAO.update(borrower);
	}
	
	@Transactional
	public void deleteBorrower(Borrower borrower) throws Exception {
			borDAO.delete(borrower);
	}

	public Borrower getBorrower(int cardNo) throws Exception {
		
			return borDAO.readOne(cardNo);
	}

	public List<Borrower> getBorrowers() throws Exception {
		
			return borDAO.readAll();
	}
	
	@Transactional
	public void addBranch(Branch branch) throws Exception {
			brDAO.addBranch(branch);
	}
	
	@Transactional
	public void updateBranch(Branch branch) throws Exception {
		brDAO.update(branch);
	}
	
	@Transactional
	public void deleteBranch(Branch branch) throws Exception {
			brDAO.delete(branch);
	}

	public Branch getBranch(int branchId) throws Exception {
		
			return brDAO.readOne(branchId);
	}
	
	public List<Branch> getBranches() throws Exception {
		
			return brDAO.readAll();
	}
	
	
	@Transactional
	public void addGenre(Genre genre) throws Exception {
			genDAO.addGenre(genre);
	}
	
	@Transactional
	public void updateGenre(Genre genre) throws Exception {
		genDAO.update(genre);
	}
	
	@Transactional
	public void deleteGenre(Genre genre) throws Exception {
			genDAO.delete(genre);
	}

	public Genre getGenre(int genreId) throws Exception {
		
			return genDAO.readOne(genreId);
	}
	
	public List<Genre> getGenrees() throws Exception {
		
			return genDAO.readAll();
	}
	
	
	
	@Transactional
	public void addPublisher(Publisher p) throws Exception {
			pubDAO.addPublisher(p);
	}
	
	@Transactional
	public void deletePublisher(Publisher p) throws Exception {
			pubDAO.delete(p);
	}
	
	@Transactional
	public void updatePublisher(Publisher p) throws Exception {
			pubDAO.update(p);
	}


	public Publisher getPublisher(int publisherId) throws Exception {
		return pubDAO.readOne(publisherId);
	}

	public List<Publisher> getPublishers() throws Exception {
		return pubDAO.readAll();
	}

	
	
	}
