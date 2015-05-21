package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.*;


public class Book_copiesDAO extends BaseDAO<List<Book_copies>>{
	
	public Book_copiesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(Book_copies book_copies) throws Exception {
		save("insert into tbl_book_copies (bookId,branchId,noOfCopies) values (?,?,?)",
				new Object[] { book_copies.getBook().getBookId(),book_copies.getBranch().getBranchId(),book_copies.getNoOfCopies() });
	}

	public void update(Book_copies book_copies) throws Exception {
		int bookId = book_copies.getBook().getBookId();
		int branchId = book_copies.getBranch().getBranchId();
		int noOfCopies = book_copies.getNoOfCopies();
		
		save("update tbl_book_copies set  noOfCopies = ? where bookId = ? and branchId = ?",
				new Object[] {noOfCopies,bookId,branchId});
	}

	public void delete(Book_copies book_copies) throws Exception {
		save("delete from tbl_book_copies where bookId = ? and branchId = ?",
				new Object[] { book_copies.getBook().getBookId(),book_copies.getBranch().getBranchId()});
	}

	@SuppressWarnings("unchecked")
	public List<Book_copies> readAll() throws Exception {
		return (List<Book_copies>) read("select * from tbl_book_copies", null);
	}

	@SuppressWarnings("unchecked")
	public Book_copies readOne(int bookId,int branchId) throws Exception {
		List<Book_copies> list = (List<Book_copies>) read(
				"select * from tbl_book_copies where bookId = ? and branchId = ?",
				new Object[] { bookId, branchId });
		
			
		if (list != null && list.size() > 0) {
			//System.out.println(list.get(0).getBook().getBookId());
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	protected List<Book_copies> extractData(ResultSet rs) throws SQLException {
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
