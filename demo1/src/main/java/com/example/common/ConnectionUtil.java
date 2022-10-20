package com.example.common;

import com.example.AppConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {

    public static final ConnectionUtil CONN_UTIL
            = new ConnectionUtil();

    private final DataSource ds;

    private ConnectionUtil() {
        AppConfig appConfig = new AppConfig();
        this.ds= appConfig.dataSource();
    }

    //getConnection
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = ds.getConnection();
            if (conn != null) {
                System.out.println("DB 연결 성공");
                return conn;
            }
        } catch (SQLException e) {
            System.out.println("DB 연결 실패");
            e.printStackTrace();
        }
        return null;
    }

    //close
    public void close(PreparedStatement pstmt,Connection conn){
        try {
            if(pstmt!=null){
                pstmt.close();
            }
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("db자원 반환에 실패했습니다");
            throw new RuntimeException(e);
        }
    }
    public void close(ResultSet rs,PreparedStatement pstmt,Connection conn){
        try {
            if(rs!=null){
                rs.close();
            }
            if(pstmt!=null){
                pstmt.close();
            }
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("db자원 반환에 실패했습니다");
            throw new RuntimeException(e);
        }
    }
}
