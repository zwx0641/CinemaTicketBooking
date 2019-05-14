package com.gomovie.dao;

import com.gomovie.model.User;

public interface UserDao {
	
	User selectOne(User user);
	User registerVali(User user);
	void insert(User user);
	User update(User user);
	boolean modifyPassword(String username, String newpassword);
}
