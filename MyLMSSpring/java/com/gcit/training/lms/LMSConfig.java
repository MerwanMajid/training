package com.gcit.training.lms;


import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.gcit.training.lms.dao.AuthorDAO;
import com.gcit.training.lms.dao.BookDAO;
import com.gcit.training.lms.dao.Book_copiesDAO;
import com.gcit.training.lms.dao.Book_loansDAO;
import com.gcit.training.lms.dao.BorrowerDAO;
import com.gcit.training.lms.dao.BranchDAO;
import com.gcit.training.lms.dao.GenreDAO;
import com.gcit.training.lms.dao.PublisherDAO;

import com.gcit.training.lms.service.AdministratorService;

@Configuration
@EnableTransactionManagement
public class LMSConfig {
	
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/library";
	private static final String user = "root";
	private static final String pwd = "root";
	
	@Bean
	public AdministratorService adminService(){
		return new AdministratorService();
	}
	

	@Bean
	public AuthorDAO authDao() {
		return new AuthorDAO();
	}
	
	@Bean
	public Book_copiesDAO bkcDAO() {
		return new Book_copiesDAO();
	}
	
	@Bean
	public Book_loansDAO bklDAO() {
		return new Book_loansDAO();
	}
	
	@Bean
	public BookDAO bkDao() {
		return new BookDAO();
	}
	
	@Bean
	public BorrowerDAO borDAO() {
		return new BorrowerDAO();
	}
	
	@Bean
	public BranchDAO brDAO() {
		return new BranchDAO();
	}
	
	@Bean
	public GenreDAO genDAO() {
		return new GenreDAO();
	}
	
	@Bean
	public PublisherDAO pubDao() {
		return new PublisherDAO();
	}
	
	@Bean
	public PlatformTransactionManager txManager(){
		DataSourceTransactionManager tx = new DataSourceTransactionManager();
		tx.setDataSource(datasource());
		
		return tx;
	}
	
	@Bean
	public JdbcTemplate template() {
		JdbcTemplate jdbc = new JdbcTemplate();
		jdbc.setDataSource(datasource());
		
		return jdbc;
	}
	
	@Bean
	public BasicDataSource datasource(){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(pwd);

		return ds;
	}
	
}
