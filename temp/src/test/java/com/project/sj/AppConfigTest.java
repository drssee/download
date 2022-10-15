package com.project.sj;

import java.sql.Connection;
import java.sql.SQLException;

import com.project.sj.config.AppConfig;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class AppConfigTest {
	
	AppConfig appConfig = new AppConfig();
	
	@Test
	public void db커넥션테스트() throws SQLException {
		Connection conn = appConfig.getConnection();
		System.out.println("conn = " + conn);
		log.info("test");
	}
}
