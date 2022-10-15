package com.project.sj.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

public class Hikari_ds {
	private final String CLASSNAME = "org.mariadb.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mariadb://localhost:3306/culture_db";
	private final String USERNAME = "root";
	private final String PASSWORD = "root";
	private final String CACHE_PREP_STMTS="true";
    HikariDataSource ds;
    HikariConfig config;
    
	public DataSource config(){
	    /* HikariCP 로드 */
    	config = new HikariConfig();
	    
	    config.setDriverClassName(CLASSNAME);
		config.setJdbcUrl( JDBC_URL );
		config.setUsername( USERNAME );
		config.setPassword( PASSWORD );
		config.addDataSourceProperty( "cachePrepStmts" , CACHE_PREP_STMTS );
		config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
		config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
		ds = new HikariDataSource( config );
		System.out.println("성공:" + ds);
		
		return ds;
	}
    
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			if (conn != null) {
				System.out.println("DB 접속 성공");
				return conn;
			}
		} catch (SQLException e) {
			System.out.println("DB 접속 실패");
			e.printStackTrace();
		}
		return null;
	}
	
	//close 메서드 만들기
}
