package com.atguigu.dao;

import com.atguigu.bean.User;

public interface UserDao {
	// 保存用户
	public int saveUser(User user);

	// 根据用户名和密码查找用户
	public User findUserByUsernameAndPassword(User user);

	/**
	 * 根据用户名查找用户信息
	 * 
	 * @param username
	 *            查找的用户名
	 * @return 返回查找到的用户信息<br/>
	 *         如果用户不存在，返回null
	 */
	public User findUserByUsername(String username);
}
