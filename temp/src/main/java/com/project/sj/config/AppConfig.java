package com.project.sj.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.project.sj.repository.JdbcUserRepository;
import com.project.sj.repository.UserRepository;
import com.project.sj.service.UserService;
import com.project.sj.service.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

public class AppConfig {
		
	//userService
	public UserService userService() {
		return new UserServiceImpl(
				userRepository(dataSource()));
	}
	
	//userRepository
	public UserRepository userRepository(DataSource ds) {
		return new JdbcUserRepository(ds);
	}
	
	//dataSource
	private DataSource dataSource() {
		return new Hikari_ds().config();
	}
	
	//getConnection
	public Connection getConnection() {
		try {
			return dataSource().getConnection();
		} catch (SQLException e) {
			System.out.println("db커넥션을 얻어오지 못했습니다.");
			e.printStackTrace();
		}
		return null;
	}

	public void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
