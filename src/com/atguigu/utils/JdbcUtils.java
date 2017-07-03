package com.atguigu.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {

	private static ComboPooledDataSource dataSource = new ComboPooledDataSource("book_devoloper");

	/**
	 * 从C3p0数据库连接池中获取数据库连接对象<br/>
	 * 
	 * @return 如果返回为null,说明获取数据库连接失败<br/>
	 *         如果返回有值。就获取成功。
	 */
	public static Connection getConnection() {
		Connection connection = null;

		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	/**
	 * 释放 数据库连接对象。放到c3p0数据库连接池中
	 * 
	 * @param connection
	 *            需要释放的数据库连接对象
	 */
	public static void releaseConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
