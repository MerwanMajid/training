package com.gcit.training.lms.dao;

import java.io.Serializable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;


import com.gcit.training.lms.entity.*;

public class PublisherDAO extends BaseDAO<List<Publisher>> implements  Serializable, ResultSetExtractor<List<Publisher>>  {

	private static final long serialVersionUID = 1L;


	public void addPublisher(Publisher publisher) throws Exception {
		template.update("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?,?,?)",
				new Object[] { publisher.getPublisherName(),
						publisher.getPublisherAddress(),
						publisher.getPublisherPhone() });
	}

	public void update(Publisher publisher) throws Exception {
		template.update("update tbl_publisher set publisherName = ?, publisherAddress = ?, "
				+ "publisherPhone = ? where publisherId = ?", new Object[] {
				publisher.getPublisherName(), publisher.getPublisherAddress(),
				publisher.getPublisherPhone(), publisher.getPublisherId() });
	}

	public void delete(Publisher publisher) throws Exception {
		template.update("delete from tbl_publisher where publisherId = ?",
				new Object[] { publisher.getPublisherId() });
	}

	
	public List<Publisher> readAll() throws Exception {
		return (List<Publisher>) template.query("select * from tbl_publisher", this);
	}

	
	public Publisher readOne(int publisherId) throws SQLException {
		List<Publisher> pubList = (List<Publisher>) template.query(
				"select * from tbl_publisher where publisherId = ?",
				new Object[] { publisherId },this);
		
		if (pubList != null && pubList.size() > 0) {
			return pubList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
		List<Publisher> pubList = new ArrayList<Publisher>();
		while (rs.next()) {
			
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));

			pubList.add(p);
		}
		return pubList;
	}

}
