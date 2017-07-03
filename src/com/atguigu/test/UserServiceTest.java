package com.atguigu.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

public class UserServiceTest {

	public static UserService userService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userService = new UserServiceImpl();
	}

	@Test
	public void testRegistUser() {
		userService.registUser(new User(0, "ccc", "ccc", "ccc@qq.com"));
	}

	@Test
	public void testLogin() {
		System.out.println( userService.login(new User(0, "admin", "admin168", "")));
		System.out.println( userService.login(new User(0, "admin", "admin", "")) );
	}

	@Test
	public void testUsernameExist() {
		System.out.println(userService.usernameExist("admin"));
		System.out.println(userService.usernameExist("admin168"));
	}

}
