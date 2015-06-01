package com.gcit.training.lms.dao;

import java.io.Serializable;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.lms.entity.Borrower;
 



public class BorrowerDAO extends BaseDAO<List<Borrower>> implements Serializable,ResultSetExtractor<List<Borrower>>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void addBorrower(Borrower borrower) throws Exception {
		template.update("insert into tbl_borrower (name, address, phone) values (?,?,?)",
				new Object[] { borrower.getName(),
						borrower.getAddress(),
						borrower.getPhone() });
	}

	public void update(Borrower borrower) throws Exception {
		template.update("update tbl_borrower set name = ?, address = ?, "
				+ "phone = ? where cardNo = ?", new Object[] {
				borrower.getName(), borrower.getAddress(),
				borrower.getPhone(), borrower.getCardNo() });
	}

	public void delete(Borrower borrower) throws Exception {
		template.update("delete from tbl_borrower where cardNo = ?",
				new Object[] { borrower.getCardNo() });
	}

	 
	public List<Borrower> readAll() throws Exception {
		return (List<Borrower>)  template.query("select * from tbl_borrower", this);
	}

	 
	public Borrower readOne(int cardNo) throws Exception {
		List<Borrower> List = (List<Borrower>)  template.query(
				"select * from tbl_borrower where cardNo = ?",
				new Object[] { cardNo },this);

		if (List != null && List.size() > 0) {
			return List.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> list = new ArrayList<Borrower>();
		while (rs.next()) {
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setName(rs.getString("name"));
			b.setAddress(rs.getString("address"));
			b.setPhone(rs.getString("phone"));

			list.add(b);
		}
		return list;
	}


}
