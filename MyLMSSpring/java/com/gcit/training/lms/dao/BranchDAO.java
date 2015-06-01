package com.gcit.training.lms.dao;

import java.io.Serializable;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.training.lms.entity.Branch;


public class BranchDAO extends BaseDAO<List<Branch>> implements Serializable,ResultSetExtractor<List<Branch>>{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void addBranch(Branch branch) throws Exception {
		
		template.update("insert into tbl_library_branch (branchName, branchAddress, branchId) values (?,?,?)",
				new Object[] { branch.getBranchName(),
						branch.getBranchAddress(),
						branch.getBranchId() });
	}

	public void update(Branch branch) throws Exception {
		template.update("update tbl_library_branch set branchName = ?, branchAddress = ?"
				+ " where branchId = ?", new Object[] {
				branch.getBranchName(), branch.getBranchAddress(),
				branch.getBranchId() });
	}

	public void delete(Branch branch) throws Exception {
		template.update("delete from tbl_library_branch where branchId = ?",
				new Object[] { branch.getBranchId() });
	}

	 
	public List<Branch> readAll() throws Exception {
		return (List<Branch>) template.query("select * from tbl_library_branch", this);
	}

	
	public Branch readOne(int branchId) throws Exception {
		List<Branch> List = (List<Branch>) template.query(
				"select * from tbl_library_branch where branchId = ?",
				new Object[] { branchId },this);

		if (List != null && List.size() > 0) {
			return List.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Branch> extractData(ResultSet rs) throws SQLException {
		List<Branch> list = new ArrayList<Branch>();
		while (rs.next()) {
			Branch br = new Branch();
			br.setBranchId(rs.getInt("branchId"));
			br.setBranchName(rs.getString("branchName"));
			br.setBranchAddress(rs.getString("branchAddress"));
			

			list.add(br);
		}
		return list;
	}

	
	

}
