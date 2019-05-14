package com.gomovie.service.impl;

import com.gomovie.dao.UserDao;
import com.gomovie.dao.impl.UserDaoImpl;
import com.gomovie.model.User;
import com.gomovie.service.UserService;

public class UserServiceImpl implements UserService{
	
	UserDao userDao = new UserDaoImpl();
	
	public User selectOne(User user) {
		return userDao.selectOne(user);
	}

	@Override
	public void insert(User user) {
		userDao.insert(user);
	}

	@Override
	public User registerVali(User user) {
		return userDao.registerVali(user);
	}

	@Override
	public User update(User user) {
		return userDao.update(user);
	}

	@Override
	public boolean modifyPassword(String usernme, String newpassword) {
		return userDao.modifyPassword(usernme, newpassword);
	}

}
