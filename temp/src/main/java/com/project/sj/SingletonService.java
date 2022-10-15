package com.project.sj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.project.sj.config.AppConfig;
import com.project.sj.repository.JdbcUserRepository;
import com.project.sj.repository.UserRepository;
import com.project.sj.service.UserService;
import com.project.sj.service.UserServiceImpl;

public class SingletonService {
	private static SingletonService instance = new SingletonService();
	private AppConfig appConfig;
	
	private SingletonService() {
		 appConfig = new AppConfig();
	}
	
	public static SingletonService getInstance() {
		return instance;
	}
	
	//userService
	public UserService userService() {
		return appConfig.userService();
	}
	
	//userRepository
	public UserRepository userRepository(DataSource ds) {
		return appConfig.userRepository(ds);
	}
	
	//getConnection
	public Connection getConnection() {
		return appConfig.getConnection();
	}

	public void close(ResultSet rs) {
		appConfig.close(rs);
	}

	public void close(PreparedStatement pstmt) {
		appConfig.close(pstmt);
	}

	public void close(Connection conn) {
		appConfig.close(conn);
	}

	public void rollback(Connection conn) {
		appConfig.rollback(conn);
	}
}
