package com.gcit.training.lms.dao;

import java.io.Serializable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.lms.entity.Genre;;


public class GenreDAO extends BaseDAO<List<Genre>> implements Serializable,ResultSetExtractor<List<Genre>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void addGenre(Genre genre) throws Exception {
		template.update("insert into tbl_genre (genre_name) values (?)",
				new Object[] { genre.getGenre_name() });
	}

	public void update(Genre genre) throws Exception {
		template.update("update tbl_genre set genre_name = ? where genre_Id = ?",
				new Object[] { genre.getGenre_name(), genre.getGenre_id() });
	}

	public void delete(Genre genre) throws Exception {
		template.update("delete from tbl_genre where genre_Id = ?",
				new Object[] { genre.getGenre_id() });
	}

	
	public List<Genre> readAll() throws Exception {
		return (List<Genre>) template.query("select * from tbl_genre", this);
	}

	
	public Genre readOne(int genreId) throws Exception {
		List<Genre> list = (List<Genre>) template.query(
				"select * from tbl_genre where genre_id = ?",
				new Object[] { genreId },this);

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		List<Genre> list = new ArrayList<Genre>();
		while (rs.next()) {
			Genre a = new Genre();
			a.setGenre_id(rs.getInt("genre_id"));
			a.setGenre_name(rs.getString("genre_name"));

			list.add(a);
		}
		return list;
	}


}
