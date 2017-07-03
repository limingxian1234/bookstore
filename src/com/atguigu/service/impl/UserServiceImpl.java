package com.atguigu.service.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}

	@Override
	public void registUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	public User login(User user) {
		return userDao.findUserByUsernameAndPassword(user);
	}

	@Override
	public boolean usernameExist(String username) {
		// 根据用户名查找用户信息
	    User user =	userDao.findUserByUsername(username);
		// 如果查找到对象。就说明用户名存在。返回true
		if (user != null) {
			return true;
		} else {
			// 如果根据用户名查找用户信息。返回null,说明用户名不存
			return false;
		}
	}

}
