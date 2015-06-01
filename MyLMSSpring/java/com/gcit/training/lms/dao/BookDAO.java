package com.gcit.training.lms.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gcit.training.lms.dao.PublisherDAO;
import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.Genre;




public class BookDAO extends BaseDAO<List<Book>> implements Serializable, ResultSetExtractor<List<Book>>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	PublisherDAO pDAO;
	
	
	public void addBook(Book book) throws Exception {

		Integer pubId = null;
		if (book.getPublisher() != null)
			pubId = book.getPublisher().getPublisherId();
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		template.update("insert into tbl_book (title, pubId) values (?,?)",
				new Object[] { book.getTitle(), pubId },keyHolder);
		
		int bookId = keyHolder.getKey().intValue();
		
		for(Author a : book.getAuthors()) {
			template.update("insert into tbl_book_authors (bookId, authorId) values (?,?)",
					new Object[] { bookId, a.getAuthorId() });
		}

		for(Genre g : book.getGenres()) {
			template.update("insert into tbl_book_genres (bookId, genre_Id) values (?,?)",
					new Object[] { bookId, g.getGenre_id() });
		}
	}

	public void update(Book book) throws Exception {
		template.update("update tbl_book set title=?, pubId=? where bookId=?",
				new Object[] { book.getTitle() ,book.getPublisher().getPublisherId(),book.getBookId()});
		 
		template.update("delete from tbl_book_authors where bookId="+book.getBookId());
		 
		 for(Author a : book.getAuthors()) {
			 template.update("insert into tbl_book_authors (bookId, authorId) values (?,?)",
						new Object[] { book.getBookId(), a.getAuthorId() });
			}
		 	
		 template.update("delete from tbl_book_genres where bookId="+book.getBookId());
			for(Genre g : book.getGenres()) {
				template.update("insert into tbl_book_genres (bookId, genre_Id) values (?,?)",
						new Object[] {book.getBookId(), g.getGenre_id() });
			}
		 
	}
	

	public void delete(Book book) throws Exception {
		template.update("delete from tbl_book where bookId = ?",
				new Object[] { book.getBookId() });
	}

	public List<Book> readByBranch(int branchId) throws Exception {
		return (List<Book>) template.query("select bk.bookId,bk.title,bk.pubId,a.authorId,g.genre_id from tbl_book bk,tbl_book_authors a,tbl_book_genres g join tbl_book_copies bc on bk.bookId=bc.bookId where bk.bookId=a.bookId and bk.bookId=g.bookId  and bc.branchId="+branchId, this);
	}
	
	public List<Book> readAll() throws SQLException {
		return (List<Book>) template.query("select * from tbl_book", this);
	}

	public Book readOne(int bookId) throws SQLException {
		
		List<Book> list = (List<Book>) template.query("select * from tbl_book where bookId="+bookId, this);
		
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		
		while (rs.next()) {
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			b.setPublisher(pDAO.readOne(rs.getInt("pubId")));
			books.add(b);
		}
		return books;
	}
	
	public List<Book> searchBookByTitle(String searchString) throws SQLException {
		searchString = "%" + searchString + "%";
		return (List<Book>) template.query("select * from tbl_book where title like ?", new Object[]{searchString}, this);
	}


}
