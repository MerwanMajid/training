package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.lms.entity.Branch;


public class BranchDAO extends BaseDAO<List<Branch>>{
	
	public BranchDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void create(Branch branch) throws Exception {
		
		save("insert into tbl_library_branch (branchName, branchAddress, branchId) values (?,?,?)",
				new Object[] { branch.getBranchName(),
						branch.getBranchAddress(),
						branch.getBranchId() });
	}

	public void update(Branch branch) throws Exception {
		save("update tbl_library_branch set branchName = ?, branchAddress = ?"
				+ " where branchId = ?", new Object[] {
				branch.getBranchName(), branch.getBranchAddress(),
				branch.getBranchId() });
	}

	public void delete(Branch branch) throws Exception {
		save("delete from tbl_library_branch where branchId = ?",
				new Object[] { branch.getBranchId() });
	}

	@SuppressWarnings("unchecked")
	public List<Branch> readAll() throws Exception {
		return (List<Branch>) read("select * from tbl_library_branch", null);
	}

	@SuppressWarnings("unchecked")
	public Branch readOne(int branchId) throws Exception {
		List<Branch> List = (List<Branch>) read(
				"select * from tbl_library_branch where branchId = ?",
				new Object[] { branchId });

		if (List != null && List.size() > 0) {
			return List.get(0);
		} else {
			return null;
		}
	}

	@Override
	protected List<Branch> extractData(ResultSet rs) throws SQLException {
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
