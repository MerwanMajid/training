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

public class AuthorDAO extends BaseDAO<List<Author>> {

	public AuthorDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(Author author) throws Exception {
		save("insert into tbl_author (authorName) values (?)",
				new Object[] { author.getAuthorName() });
	}

	public void update(Author author) throws Exception {
		save("update tbl_author set authorName = ? where authorId = ?",
				new Object[] { author.getAuthorName(), author.getAuthorId() });
	}

	public void delete(Author author) throws Exception {
		save("delete from tbl_author where authorId = ?",
				new Object[] { author.getAuthorId() });
	}

	@SuppressWarnings("unchecked")
	public List<Author> readAll() throws Exception {
		return (List<Author>) read("select * from tbl_author", null);
	}

	@SuppressWarnings("unchecked")
	public Author readOne(int authorId) throws Exception {
		List<Author> list = (List<Author>) read(
				"select * from tbl_author where authorId = ?",
				new Object[] { authorId });

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Author readOne(String authorName) throws Exception {
		List<Author> list = (List<Author>) read(
				"select * from tbl_author where authorName = ?",
				new Object[] { authorName });

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}


	@Override
	protected List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> list = new ArrayList<Author>();
		while (rs.next()) {
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));

			list.add(a);
		}
		return list;
	}

	public Author readOneAuthor(int authorId)throws Exception{
		
		HashMap<String,Object> hm = Read("tbl_author", authorId);
		
		
		Author author = new Author();
		
		author.setAuthorId((int)hm.get("authorId"));
		author.setAuthorName(hm.get("authorName").toString());
		
	
		
		return author;
	}
	public ArrayList<Author> readAllAuthors(int authorId)throws Exception{
		
		ArrayList<HashMap<String, Object>> hm = Read("select * from tbl_author");
		ArrayList<Author> out = new ArrayList<Author>();
	
		for(HashMap<String, Object> h:hm){
			
		out.add(readOneAuthor((int)h.get("authorId")));
		
		
		}
		
	
		
		return out;
	}
}
