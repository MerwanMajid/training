package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gcit.training.lms.entity.Author;
import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.Genre;
import com.gcit.training.lms.entity.Publisher;



public class BookDAO extends BaseDAO<List<Book>>{
	
	public BookDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(Book book) throws Exception {
		int bookId = saveWithId("insert into tbl_book (title, pubId) values (?,?)",
				new Object[] { book.getTitle(), book.getPublisher().getPublisherId() });
		
		for(Author a : book.getAuthors()) {
			save("insert into tbl_book_authors (bookId, authorId) values (?,?)",
					new Object[] { bookId, a.getAuthorId() });
		}

		for(Genre g : book.getGenres()) {
			save("insert into tbl_book_genres (bookId, genre_Id) values (?,?)",
					new Object[] { bookId, g.getGenre_id() });
		}
	}

	public void update(Book book) throws Exception {
		 save("update tbl_book set title=?, pubId=? where bookId=?",
				new Object[] { book.getTitle() ,book.getPublisher().getPublisherId(),book.getBookId()});
		 
		 save("delete from tbl_book_authors where bookId="+book.getBookId(),null);
		 
		 for(Author a : book.getAuthors()) {
				save("insert into tbl_book_authors (bookId, authorId) values (?,?)",
						new Object[] { book.getBookId(), a.getAuthorId() });
			}
		 	
		 save("delete from tbl_book_genres where bookId="+book.getBookId(),null);
			for(Genre g : book.getGenres()) {
				save("insert into tbl_book_genres (bookId, genre_Id) values (?,?)",
						new Object[] {book.getBookId(), g.getGenre_id() });
			}
		 
	}
	

	public void delete(Book book) throws Exception {
		save("delete from tbl_book where bookId = ?",
				new Object[] { book.getBookId() });
	}

	@SuppressWarnings("unchecked")
	public List<Book> readAll() throws Exception {
		
		return (List<Book>) read("select bk.bookId,bk.title,bk.pubId,a.authorId,g.genre_id from tbl_book bk,tbl_book_authors a,tbl_book_genres g where bk.bookId=a.bookId and bk.bookId=g.bookId", null);
	}

	@SuppressWarnings("unchecked")
	public Book readOne(int bookId) throws Exception {
		List<Book> list = (List<Book>) read(
				"select bk.bookId,bk.title,bk.pubId,a.authorId,g.genre_id from tbl_book bk,tbl_book_authors a,tbl_book_genres g where bk.bookId=a.bookId and bk.bookId=g.bookId and bk.bookId = ?",
				new Object[] { bookId });
		
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	protected List<Book> extractData(ResultSet rs) throws Exception {
		List<Book> list = new ArrayList<Book>();
		AuthorDAO ath = new AuthorDAO(getConnection());
		GenreDAO gen = new GenreDAO(getConnection());
		Book a = new Book();
		a.setAuthors(new ArrayList<Author>());
		a.setGenres(new ArrayList<Genre>());
		while (rs.next()) {
			
			a.setBookId(rs.getInt("bookId"));
			a.setTitle(rs.getString("title"));
			a.setPublisher(new Publisher());
			a.getPublisher().setPublisherId(rs.getInt("pubId"));
			a.getAuthors().add(ath.readOne(rs.getInt("authorId")));
			a.getGenres().add(gen.readOne(rs.getInt("genre_id")));
			list.add(a);
		}
		
		return list;
	}

	public List<Book> readByBranch(int branchId) throws Exception {
		return (List<Book>) read("select bk.bookId,bk.title,bk.pubId,a.authorId,g.genre_id from tbl_book bk,tbl_book_authors a,tbl_book_genres g join tbl_book_copies bc on bk.bookId=bc.bookId where bk.bookId=a.bookId and bk.bookId=g.bookId  and bc.branchId="+branchId, null);
	}
	
	
	public Book readOneBook(int bookId)throws Exception{
		
		HashMap<String,Object> hm = Read("tbl_book", bookId);
		AuthorDAO ad = new AuthorDAO(getConnection());
		GenreDAO gd = new GenreDAO(getConnection());
		Book bk = new Book();
		bk.setPublisher(new Publisher());
		bk.setAuthors(new ArrayList<Author>());
		bk.setGenres(new ArrayList<Genre>());
		
		bk.setBookId((int)hm.get("bookId"));
		bk.setTitle(hm.get("title").toString());
		//bk.getPublisher().setPublisherId((int)hm.get("pubId"));
		PublisherDAO p = new PublisherDAO(getConnection());
		
		bk.setPublisher(p.readOne((int)hm.get("pubId")));
		
		ArrayList<HashMap<String,Object>> authors = Read("select authorId from tbl_book_authors where bookId ="+bk.getBookId());
		ArrayList<HashMap<String, Object>> genres = Read("select genre_id from tbl_book_genres where bookId ="+bk.getBookId());
	
		for(HashMap<String,Object> h:authors){
			
			bk.getAuthors().add(ad.readOne((int)h.get("authorId")));
		}
		for(HashMap<String,Object> h:genres){
			
			bk.getGenres().add(gd.readOne((int)h.get("genre_id")));
		}
		
		
		return bk;
	}
	public List<Book> readAllBooks() throws Exception{
		
		ArrayList<HashMap<String, Object>> input =  new ArrayList<HashMap<String, Object>>();
		ArrayList<Book> output= new ArrayList<Book>();
	
		input = Read("select * from tbl_book");
		
		
			
		for(HashMap<String, Object> h:input){
			
			output.add(readOneBook((int)h.get("bookId")));
		}
		
		return output;
	}

}
