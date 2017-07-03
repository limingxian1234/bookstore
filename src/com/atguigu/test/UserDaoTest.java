package com.atguigu.test;

import org.junit.BeforeClass;
import org.junit.Test;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;

public class UserDaoTest {

	private static UserDao userDao;
	
	//@beforeClass这个注解的方法可以在测试之前执行之前执行。做一些初始化工作。
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("在所有test测试之前执行之前执行");
		userDao = new UserDaoImpl();
	}
	

	@Test
	public void testSaveUser() {
		System.out.println("11111");
//		UserDao userDao = new UserDaoImpl();
//		int updateCount = userDao.saveUser(new User(0, "wzg168", "168168", "wzg168@168.com"));
//		userDao.saveUser(new User(0, "aaaa", "aaaa", "aaa@168.com"));
//		userDao.saveUser(new User(0, "bbbb", "bbbb", "bbb@168.com"));
		userDao.saveUser(new User(0, "iiii", "iii", "bbb@168.com"));
//		System.out.println(updateCount);
	}

	@Test
	public void testFindUserByUsernameAndPassword() {
		System.out.println( userDao.findUserByUsernameAndPassword(new User(0, "ccccc", "cccc", null)) );
		System.out.println( userDao.findUserByUsernameAndPassword(new User(0, "admin", "admin", null)) );
	}

	@Test
	public void testFindUserByUsername() {
		System.out.println( userDao.findUserByUsername("abc") );
		System.out.println( userDao.findUserByUsername("bbbb") );
	}

}
