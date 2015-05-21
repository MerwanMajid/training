package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public abstract class BaseDAO<T> {

	private Connection connection = null; 
	public BaseDAO(Connection conn) {
		this.connection = conn;
	}

	protected Connection getConnection() {
		return connection;
	}


	public void save(String query, Object[] vals) throws Exception {
		Connection con = getConnection();

		PreparedStatement stmt = con.prepareStatement(query);
		int count = 1;
		if(vals!=null)
		for(Object o : vals) {
			stmt.setObject(count, o);
			count++;
		}
		
		stmt.executeUpdate();
	}
	
	public int saveWithId(String query, Object[] vals) throws Exception {
		Connection con = getConnection();

		PreparedStatement stmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		int count = 1;
		for (Object o : vals) {
			stmt.setObject(count, o);
			count++;
		}

		stmt.executeUpdate();
		
		ResultSet rs = stmt.getGeneratedKeys();
		
		if (rs.next())
			return rs.getInt(1);
		else
			return -1;

	}
	
	
	public List<?> read(String query, Object[] vals) throws Exception {
		Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(query);
		
		if(vals != null) {
			int count = 1;
			for(Object o : vals) {
				stmt.setObject(count, o);
				count++;
			}
		}
		
		ResultSet rs = stmt.executeQuery();
	

		return extractData(rs);
	}
	
	protected abstract List<?> extractData(ResultSet rs) throws Exception;
	
	

	public void executeUpdate(String query,String oper){
		
		  try {
		    	
			   
			    Connection con = getConnection();
			    
			    PreparedStatement stmt = con.prepareStatement(query);
			   
			    
			    stmt.executeUpdate();
			    
			    if(!(oper.equals("")))
			    System.out.println("reccord successfully "+oper);
		  }
		  catch(Exception e){
			  
			  e.printStackTrace();
		  }
		
	}
	
	public HashMap<String,Object> Read(String tableName,int Id){
		
		String query="select * from "+tableName;
		  HashMap<String,Object> hm  = new HashMap<String, Object>();
		 
		  try {
		    	
			  
			    Connection con = getConnection();
			    
			    PreparedStatement stmt = con.prepareStatement(query);
			   
			    
			    ResultSet rst = stmt.executeQuery();
			   
			  
			    
			    while(rst.next()){
			    	
			    	int temp = rst.getInt(1);
			    	for(int i=1;i<=rst.getMetaData().getColumnCount();i++){
			    		
			    		if (temp == Id) {
			    			hm.put(rst.getMetaData().getColumnName(i),rst.getObject(i));
			    			
			    		}
			    		else
			    			break;
			    	}
			    }
			  
			    
				
		  }
		  catch(Exception e){
			  
			  e.printStackTrace();
		  }
		  
		  return hm;
		
	}

	public ArrayList<HashMap<String,Object>> Read(String query){
		
		
		 ArrayList<HashMap<String,Object>> output = new ArrayList<HashMap<String,Object>>();
		  
		
		  try {
		    	
			   
			    Connection con = getConnection();
			    
			    PreparedStatement stmt = con.prepareStatement(query);
			   
			    
			    ResultSet rst = stmt.executeQuery();
			    HashMap<String,Object> hm  = new HashMap<String, Object>();
			    
			    while(rst.next()){
			    
			    	for(int i=1;i<=rst.getMetaData().getColumnCount();i++){
			    		
			    		   
			    		
			    			hm.put(rst.getMetaData().getColumnName(i),rst.getObject(i));
			    		
			    			
			    	}
			    	output.add(hm);
	    			
			    	 hm  = new HashMap<String, Object>();
			    }
			  
			    
				
		  }
		  catch(Exception e){
			  
			  e.printStackTrace();
		  }
		  
		  return output;
		
	}
}
