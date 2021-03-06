package com.gcit.training.lms.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gcit.training.lms.entity.*;

public class Book_loansDAO extends BaseDAO<List<Book_loans>> implements
		Serializable, ResultSetExtractor<List<Book_loans>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int addBook_loans(Book_loans book_loans) throws Exception {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		template.update(
				"insert into tbl_book_loans (bookId,branchId,cardNo,dateOut,dueDate) values (?,?,?,?,?)",

				new Object[] { book_loans.getBook().getBookId(),
						book_loans.getBranch().getBranchId(),
						book_loans.getBorrower().getCardNo(),
						book_loans.getDateOut(), book_loans.getDueDate() },
				keyHolder);

		int bkLoanId = keyHolder.getKey().intValue();
		return bkLoanId;
	}

	public void update(Book_loans book_loans) throws Exception {
		int bkLoanId = book_loans.getBkLoanId();
		int bookId = book_loans.getBook().getBookId();
		int branchId = book_loans.getBranch().getBranchId();
		int cardNo = book_loans.getBorrower().getCardNo();
		Date dataOut = book_loans.getDateOut();
		Date dueDate = book_loans.getDueDate();
		Date dateIn = book_loans.getDateIn();

		template.update(
				"update tbl_book_loans set  bookId =?, branchId =?, cardNo =?, dateOut =?, dueDate =?, dateIn =? where bkLoanId=?",
				new Object[] { bookId, branchId, cardNo, dataOut, dueDate,
						dateIn, bkLoanId });
	}

	public void delete(Book_loans book_loans) throws Exception {
		template.update("delete from tbl_book_loans where BkLoanId = ? ",
				new Object[] { book_loans.getBkLoanId() });
	}

	 
	public List<Book_loans> readAll() throws Exception {
		return (List<Book_loans>) template.query(
				"select * from tbl_book_loans", this);
	}

	 
	public Book_loans readOne(int BkLoanId) throws Exception {
		List<Book_loans> list = (List<Book_loans>) template.query(
				"select * from tbl_book_loans where BkLoanId = ?",
				new Object[] { BkLoanId }, this);

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Book_loans> extractData(ResultSet rs) throws SQLException {
		List<Book_loans> list = new ArrayList<Book_loans>();
		while (rs.next()) {
			Book_loans a = new Book_loans();
			a.setBook(new Book());
			a.setBorrower(new Borrower());
			a.setBranch(new Branch());

			a.setBkLoanId(rs.getInt("bkLoanId"));
			a.getBook().setBookId(rs.getInt("bookId"));
			a.getBorrower().setCardNo(rs.getInt("cardNo"));
			a.getBranch().setBranchId(rs.getInt("branchId"));
			a.setDateOut(rs.getDate("dateOut"));
			a.setDueDate(rs.getDate("dueDate"));
			a.setDateIn(rs.getDate("dateIn"));
			list.add(a);
		}
		return list;
	}

}
