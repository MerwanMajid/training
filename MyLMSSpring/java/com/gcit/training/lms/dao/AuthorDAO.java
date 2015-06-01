package com.gcit.training.lms.dao;

import java.io.Serializable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.lms.entity.Author;
/*import com.gcit.training.lms.entity.Book;
import com.gcit.training.lms.entity.Genre;
import com.gcit.training.lms.entity.Publisher;*/

public class AuthorDAO extends BaseDAO<List<Author>> implements Serializable, ResultSetExtractor<List<Author>>  {

	private static final long serialVersionUID = 1L;
	public void addAuthor(Author author) throws Exception {
	template.update("insert into tbl_author (authorName) values (?)",
				new Object[] { author.getAuthorName() });
	}

	public void update(Author author) throws Exception {
		template.update("update tbl_author set authorName = ? where authorId = ?",
				new Object[] { author.getAuthorName(), author.getAuthorId() });
	}

	public void delete(Author author) throws Exception {
		template.update("delete from tbl_author where authorId = ?",
				new Object[] { author.getAuthorId() });
	}

	
	public List<Author> readAll() throws Exception {
		return (List<Author>) template.query("select * from tbl_author", this);
	}

	
	public Author readOne(int authorId) throws Exception {
		List<Author> list = (List<Author>) template.query(
				"select * from tbl_author where authorId = ?",
				new Object[] { authorId },this);

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	
	public Author readOne(String authorName) throws Exception {
		List<Author> list = (List<Author>) template.query(
				"select * from tbl_author where authorName = ?",
				new Object[] { authorName },this);

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}


	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> list = new ArrayList<Author>();
		while (rs.next()) {
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));

			list.add(a);
		}
		return list;
	}

	
	
}
