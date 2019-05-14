package com.gomovie.util;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class JdbcUtil {
	final static String url = "jdbc:mysql://localhost:3306/gomovie?useUnicode=true&characterEncoding=utf-8";
	final static String username = "root";
//    final static String password = "";
	final static String password = "123456";
	
	Connection conn = null;
	Statement pre = null;
	ResultSet res = null;
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
//			DriverManager.registerDriver(new Driver());
			Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn,Statement stat,ResultSet res){
		
			try {
				if(res != null){
					res.close();
				}
				if(stat != null){
					stat.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
