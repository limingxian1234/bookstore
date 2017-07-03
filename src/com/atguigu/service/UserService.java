package com.atguigu.service;

import com.atguigu.bean.User;

public interface UserService {
	// 用户注册
	public void registUser(User user);

	// 用户登录
	public User login(User object);

	// 验证用户名是否存在
	public boolean usernameExist(String username);
}
