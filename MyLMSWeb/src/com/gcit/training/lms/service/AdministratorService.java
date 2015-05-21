package com.gcit.training.lms.service;

import java.sql.Connection;
import java.util.List;

import com.gcit.training.lms.dao.*;
import com.gcit.training.lms.entity.*;

public class AdministratorService {

	public void addBook(Book book)throws Exception{
		Connection c = ConnectionUtil.getConnection();
		try {
			if (book == null || book.getTitle() == null
					|| book.getTitle().length() == 0) {
				throw new Exception("Book cannot be null");
			}
			BookDAO aDAO = new BookDAO(c);
			aDAO.create(book);
			c.commit();
			System.out.println("Book added Succesfully: "
					+ book.getTitle());
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}
	public void addAuthor(Author author) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			if (author == null || author.getAuthorName() == null
					|| author.getAuthorName().length() == 0
					|| author.getAuthorName().length() > 45) {
				throw new Exception(
						"Author cannot be null and Name should be 1-45 characters");
			}
			AuthorDAO aDAO = new AuthorDAO(c);
			aDAO.create(author);
			c.commit();
			System.out.println("Author added Succesfully: "
					+ author.getAuthorName());
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}

	public void AddPublisher(Publisher publisher) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			if (publisher == null || publisher.getPublisherName() == null
					|| publisher.getPublisherName().length() == 0 ||

					publisher.getPublisherAddress() == null
					|| publisher.getPublisherAddress().length() == 0
					|| publisher.getPublisherPhone() == null
					|| publisher.getPublisherPhone().length() == 0) {

				throw new Exception("publisher information can't br blank");
			}
			PublisherDAO pDAO = new PublisherDAO(c);
			pDAO.create(publisher);
			c.commit();
			System.out.println("publisher added successfully: "
					+ publisher.getPublisherName());
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}

	public void AddBranch(Branch branch) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			if (branch == null || branch.getBranchName() == null
					|| branch.getBranchName().length() == 0 ||

					branch.getBranchAddress() == null
					|| branch.getBranchAddress().length() == 0) {

				throw new Exception("branch information can't br blank");
			}
			BranchDAO pDAO = new BranchDAO(c);
			pDAO.create(branch);
			c.commit();
			System.out.println("branch added successfully: "
					+ branch.getBranchName());
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}

	}

	public void AddBorrower(Borrower borrower) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			if (borrower == null || borrower.getName() == null
					|| borrower.getName().length() == 0 ||

					borrower.getAddress() == null
					|| borrower.getAddress().length() == 0
					|| borrower.getPhone() == null
					|| borrower.getPhone().length() == 0) {

				throw new Exception("borrower information can't br blank");
			}
			BorrowerDAO bDAO = new BorrowerDAO(c);
			bDAO.create(borrower);
			c.commit();
			System.out.println("borrower added successfully: "
					+ borrower.getName());
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}
 
	public void addGenre(Genre genre)throws Exception{
		Connection c = ConnectionUtil.getConnection();
		try {
			if (genre == null || genre.getGenre_name() == null
					|| genre.getGenre_name().length() == 0
					|| genre.getGenre_name().length() > 45) {
				throw new Exception(
						"Genre cannot be null and Name should be 1-45 characters");
			}
			GenreDAO aDAO = new GenreDAO(c);
			aDAO.create(genre);
			c.commit();
			System.out.println("Genre added Succesfully: "
					+ genre.getGenre_name());
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
		
	}
	
	public void updateAuthor(Author author) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			if (author == null || author.getAuthorName() == null
					|| author.getAuthorName().length() == 0
					|| author.getAuthorName().length() > 45) {
				throw new Exception(
						"Author cannot be null and Name should be 1-45 characters");
			}
			AuthorDAO aDAO = new AuthorDAO(c);
			aDAO.update(author);
			c.commit();
			System.out.println("Author updated Succesfully: "
					+ author.getAuthorName());
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}
	public void updateGenre(Genre genre) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			if (genre == null || genre.getGenre_name() == null
					|| genre.getGenre_name().length() == 0
					|| genre.getGenre_name().length() > 45) {
				throw new Exception(
						"Genre cannot be null and Name should be 1-45 characters");
			}
			GenreDAO aDAO = new GenreDAO(c);
			aDAO.update(genre);
			c.commit();
			System.out.println("Genre updated Succesfully: "
					+ genre.getGenre_name());
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}

	public void updatePublisher(Publisher publisher) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			if (publisher == null || publisher.getPublisherName() == null
					|| publisher.getPublisherName().length() == 0 ||

					publisher.getPublisherAddress() == null
					|| publisher.getPublisherAddress().length() == 0
					|| publisher.getPublisherPhone() == null
					|| publisher.getPublisherPhone().length() == 0) {

				throw new Exception("publisher information can't br blank");
			}
			PublisherDAO pDAO = new PublisherDAO(c);
			pDAO.update(publisher);
			c.commit();
			System.out.println("publisher updated successfully: "
					+ publisher.getPublisherName());
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
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
	
	public void updateBorrower(Borrower borrower) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			if (borrower == null || borrower.getName() == null
					|| borrower.getName().length() == 0 ||

					borrower.getAddress() == null
					|| borrower.getAddress().length() == 0
					|| borrower.getPhone() == null
					|| borrower.getPhone().length() == 0) {

				throw new Exception("borrower information can't br blank");
			}
			BorrowerDAO bDAO = new BorrowerDAO(c);
			bDAO.update(borrower);
			c.commit();
			System.out.println("borrower updated successfully: "
					+ borrower.getName());
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}

	public void updateBook_loans(Book_loans book_loans)throws Exception{
		Connection c = ConnectionUtil.getConnection();
		try {
			if (book_loans == null || book_loans.getDateOut()==null) {

				throw new Exception("borrower information can't br blank");
			}
			
			Book_loansDAO bDAO = new Book_loansDAO(c);
			bDAO.update(book_loans);
			c.commit();
			System.out.println("due date updated successfully");
					
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
		
	}

	public void updateBook(Book book)throws Exception{
		Connection c = ConnectionUtil.getConnection();
		try {
			if (book == null || book.getTitle()==null) {

				throw new Exception("book information can't br blank");
			}
			
			BookDAO bDAO = new BookDAO(c);
			bDAO.update(book);
			c.commit();
			System.out.println("book updated successfully");
					
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
		
	}
	
	public void deleteAuthor(Author author) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			if (author == null ){
					
				throw new Exception(
						"Author cannot be null");
			}
			AuthorDAO aDAO = new AuthorDAO(c);
			aDAO.delete(author);
			c.commit();
			System.out.println("Author deleted Succesfully: "
					+ author.getAuthorName());
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}

	public void deleteGenre(Genre genre) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			if (genre == null ){
					
				throw new Exception(
						"Genre cannot be null");
			}
			GenreDAO aDAO = new GenreDAO(c);
			aDAO.delete(genre);
			c.commit();
			System.out.println("Genre deleted Succesfully: "
					+ genre.getGenre_name());
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}
	public void deletePublisher(Publisher publisher) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			if (publisher == null ) {

				throw new Exception("publisher information can't br blank");
			}
			PublisherDAO pDAO = new PublisherDAO(c);
			pDAO.delete(publisher);
			c.commit();
			System.out.println("publisher deleted successfully: "
					+ publisher.getPublisherName());
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}

	public void deleteBranch(Branch branch) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			if (branch == null) {

				throw new Exception("branch information can't br blank");
			}
			BranchDAO pDAO = new BranchDAO(c);
			pDAO.delete(branch);
			c.commit();
			System.out.println("branch deleted successfully: "
					+ branch.getBranchName());
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}

	}

	public void deleteBorrower(Borrower borrower) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			if (borrower == null) {

				throw new Exception("borrower information can't br blank");
			}
			BorrowerDAO bDAO = new BorrowerDAO(c);
			bDAO.delete(borrower);
			c.commit();
			System.out.println("borrower deleted successfully: "
					+ borrower.getName());
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}

	public void deleteBook(Book book) throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			if (book == null) {

				throw new Exception("book information can't br blank");
			}
			BookDAO bDAO = new BookDAO(c);
			bDAO.delete(book);
			c.commit();
			System.out.println("book deleted successfully: "
					+ book.getTitle());
		} catch (Exception e) {
			c.rollback();
			throw e;
		} finally {
			c.close();
		}
	}
	
	public Author getAuthor(int authorId)throws Exception{
		Connection c = ConnectionUtil.getConnection();
		AuthorDAO aDAO = new AuthorDAO(c);
		
		return aDAO.readOne(authorId);
		
	}
	
	public List<Author> getAuthors()throws Exception{
		Connection c = ConnectionUtil.getConnection();
		AuthorDAO aDAO = new AuthorDAO(c);
		return aDAO.readAll();
		
	}
	public List<Publisher> getPublishers()throws Exception{
		Connection c = ConnectionUtil.getConnection();
		PublisherDAO aDAO = new PublisherDAO(c);
		return aDAO.readAll();
		
	}

	public Publisher getPublisher(int pubId)throws Exception{
		Connection c = ConnectionUtil.getConnection();
		PublisherDAO pDAO = new PublisherDAO(c);
		
		return pDAO.readOne(pubId);
		
	}

	public Branch getBranch(int branchId)throws Exception{
		Connection c = ConnectionUtil.getConnection();
		BranchDAO bDAO = new BranchDAO(c);
		
		return bDAO.readOne(branchId);
		
	}
	public List<Branch> getBranches()throws Exception{
		Connection c = ConnectionUtil.getConnection();
		BranchDAO bDAO = new BranchDAO(c);
		
		return bDAO.readAll();
		
	}

	public Borrower getBorrower(int cardNo)throws Exception{
		Connection c = ConnectionUtil.getConnection();
		BorrowerDAO brDAO = new BorrowerDAO(c);
		
		return brDAO.readOne(cardNo);
		
	}
	public List<Borrower> getBorrowers()throws Exception{
		Connection c = ConnectionUtil.getConnection();
		BorrowerDAO brDAO = new BorrowerDAO(c);
		
		return brDAO.readAll();
		
	}

	public Book_loans getBook_loans(int bkLoanId)throws Exception{
		
		Connection c = ConnectionUtil.getConnection();
		Book_loansDAO bklDAO = new Book_loansDAO(c);
		
		return bklDAO.readOne(bkLoanId);
		
	}
	
	public List<Genre> getGenres()throws Exception{
		
		Connection c = ConnectionUtil.getConnection();
		GenreDAO gDAO = new GenreDAO(c);
		return gDAO.readAll();
	}
	public Genre getGenres(int genreId)throws Exception{
	
		Connection c = ConnectionUtil.getConnection();
		GenreDAO gDAO = new GenreDAO(c);
		return gDAO.readOne(genreId);
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
	public List<Author> getAllAuthors() throws Exception {
		Connection c = ConnectionUtil.getConnection();
		try {
			AuthorDAO aDAO = new AuthorDAO(c);
			return aDAO.readAll();
		} finally {
			c.close();
		}
	}
}
