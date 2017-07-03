package com.atguigu.test;

import org.junit.Test;

import com.atguigu.utils.JdbcUtils;

public class JdbcUtilsTest {

	@Test
	public void getConnectionTest() {
//		for (int i = 0; i < 1000; i++) {
//			Connection connection = JdbcUtils.getConnection();
//			System.out.println(connection);
//			JdbcUtils.releaseConnection(connection);
//		}
		System.out.println(JdbcUtils.getConnection());
		
	}

}
