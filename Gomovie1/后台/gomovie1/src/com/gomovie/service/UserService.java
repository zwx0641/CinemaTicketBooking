package com.gomovie.service;

import com.gomovie.model.User;

public interface UserService {
	User selectOne(User user);
	User registerVali(User user);
	void insert(User user);
	User update(User user);
	boolean modifyPassword(String usernme, String newpassword);
}
