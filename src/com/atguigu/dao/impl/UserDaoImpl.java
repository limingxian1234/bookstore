package com.atguigu.dao.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public int saveUser(User user) {
		// 插入用户的sql语句
		String sql = "insert into t_user(`username`,`password`,`email`) values(?,?,?)";
		// 执行sql语句
		return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
	}

	@Override
	public User findUserByUsernameAndPassword(User user) {
		String sql = "select `username`,`password`,`email` from t_user where `username` = ? and `password` = ?";
		return queryOne(sql, user.getUsername(), user.getPassword());
	}

	@Override
	public User findUserByUsername(String username) {
		String sql = "select `username`,`password`,`email` from t_user where `username` = ?";
		return queryOne(sql, username);
	}

}
