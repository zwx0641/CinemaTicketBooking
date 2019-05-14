package com.gomovie.dao.impl;

import com.gomovie.dao.UserDao;
import com.gomovie.model.User;
import com.gomovie.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImpl implements UserDao{
	
	JdbcUtil jdbcUtil = new JdbcUtil();
	
	Connection conn = null;
	Statement stat = null;
	ResultSet res = null;
	PreparedStatement psts = null;
	
	@Override
	public void insert(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		int age = user.getAge();
		String email = user.getEmail();
		System.out.println(username+"===="+password);
		System.out.println(age+"===="+age);

		String sql = "insert into user(username,password,age,email) values('"+username+"','"+password+"','"+age+"','"+email+"')";
		try {
			conn = jdbcUtil.getConnection();
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


//	@Override
//	public User selectOne(User user) {
//		String username = user.getUsername();
//		String password = user.getPassword();
//		String sql = "select * from user where username = '" + username + "' and password = '" + password+"'";
//		System.out.println(sql);
//		try {
//			conn = JdbcUtil.getConnection();
//			stat = conn.createStatement();
//			res = stat.executeQuery(sql);
//			while(res.next()){
//				int id = res.getInt("id");
//				username = res.getString("username");
//				password = res.getString("password");
//				user.setId(id);
//				user.setUsername(username);
//				user.setPassword(password);
//				user.setResult(1);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return user;
//	}
	
	@Override
	public User selectOne(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		int id;
		int age;
		String email;
		String sql = "select username, id, password, email, age from user where username = '" + username + "'";
		System.out.println(sql);
		try {
			conn = JdbcUtil.getConnection();
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			if(res == null){
				user.setResult(0);
			}else{
				while(res.next()){
					username = res.getString("username");
					String password1 = res.getString("password");
					age = res.getInt("age");
					email = res.getString("email");
					
					if(!password.equals(password1)){
						user.setResult(2);//login password wrong
					}else{
						id = res.getInt("id");
						user.setId(id);
						System.out.println(id);
						user.setId(id);
						user.setAge(age);
						user.setEmail(email);
						user.setResult(1);// login success
					}
					
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User registerVali(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
//		System.out.println(user.toString());
		String sql = "select * from user where username = '" + username+"'";
		try {
			conn = JdbcUtil.getConnection();
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			while(res.next()){
				int id = res.getInt("id");
				username = res.getString("username");
				password = res.getString("password");
				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	//ĞŞ¸ÄÃÜÂë
	@Override
	public User update(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		String sql = "select * from user where username = '" + username + "'";
		System.out.println("luiolllllllllll"+sql);
		try {
			conn = JdbcUtil.getConnection();
			stat = conn.createStatement();
			res = stat.executeQuery(sql);
			if(res == null){
				user.setResult(0);
			}else{
				while(res.next()){
					username = res.getString("username");
					String password1 = res.getString("password");
					if(!password.equals(password1)){
						user.setResult(10);//login password wrong
					}else{
						user.setResult(11);// login success
					}
					
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	
	@Override
	public boolean modifyPassword(String username, String newpassword) {
		String sql = "update user set password = '"+newpassword+"' where username = '"+username+"'";
		System.out.println(sql);
		boolean flag = false;
		int i =0;
		try {
			conn = JdbcUtil.getConnection();
			psts = conn.prepareStatement(sql);
			i = psts.executeUpdate();
			psts.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(i>0){
			flag = true;
		}
		return flag;
	}

}
