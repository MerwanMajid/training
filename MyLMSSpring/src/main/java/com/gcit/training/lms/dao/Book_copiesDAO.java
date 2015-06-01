package com.gcit.training.lms.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.lms.entity.*;


public class Book_copiesDAO extends BaseDAO<List<Book_copies>> implements Serializable,ResultSetExtractor<List<Book_copies>>{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void addBook_copies(Book_copies book_copies) throws Exception {
		template.update("insert into tbl_book_copies (bookId,branchId,noOfCopies) values (?,?,?)",
				new Object[] { book_copies.getBook().getBookId(),book_copies.getBranch().getBranchId(),book_copies.getNoOfCopies() });
	}

	public void update(Book_copies book_copies) throws Exception {
		int bookId = book_copies.getBook().getBookId();
		int branchId = book_copies.getBranch().getBranchId();
		int noOfCopies = book_copies.getNoOfCopies();
		
		template.update("update tbl_book_copies set  noOfCopies = ? where bookId = ? and branchId = ?",
				new Object[] {noOfCopies,bookId,branchId});
	}

	public void delete(Book_copies book_copies) throws Exception {
		template.update("delete from tbl_book_copies where bookId = ? and branchId = ?",
				new Object[] { book_copies.getBook().getBookId(),book_copies.getBranch().getBranchId()});
	}

	
	public List<Book_copies> readAll() throws Exception {
		return (List<Book_copies>) template.query("select * from tbl_book_copies", this);
	}

	 
	public Book_copies readOne(int bookId,int branchId) throws Exception {
		List<Book_copies> list = (List<Book_copies>) template.query(
				"select * from tbl_book_copies where bookId = ? and branchId = ?",
				new Object[] { bookId, branchId },this);
		
			
		if (list != null && list.size() > 0) {
			//System.out.println(list.get(0).getBook().getBookId());
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Book_copies> extractData(ResultSet rs) throws SQLException {
		List<Book_copies> list = new ArrayList<Book_copies>();
		while (rs.next()) {
		
			Book_copies a = new Book_copies();
			a.setBook(new Book());
			a.setBranch(new Branch());
			a.getBook().setBookId(rs.getInt("bookId"));
			a.getBranch().setBranchId(rs.getInt("branchId"));
			a.setNoOfCopies(rs.getInt("noOfCopies"));
			
			list.add(a);
		}
		return list;
	}

	
	

}
