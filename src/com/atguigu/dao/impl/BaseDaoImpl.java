package com.atguigu.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.utils.JdbcUtils;

public abstract class BaseDaoImpl<T> {

	private QueryRunner queryRunner;
	private Class<T> type;

	public BaseDaoImpl() {
		queryRunner = new QueryRunner();
		// this表示当前具体 的实例化的对象也就是UserDaoImpl
		System.out.println(this);
		// 获取父类的类型
		// System.out.println(this.getClass().getSuperclass());
		// 获取带有泛型信息的父类类型
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		// System.out.println(parameterizedType);
		// getActualTypeArguments从带有泛型信息的父类中获取具体 的泛型类型
		type = (Class<T>) parameterizedType.getActualTypeArguments()[0];
		System.out.println(type);
	}

	public int update(String sql, Object... params) {
		Connection conn = JdbcUtils.getConnection();
		try {
			return queryRunner.update(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}

		return 0;
	}

	/**
	 * 查询数据库表的多条记录
	 * 
	 * @param sql
	 *            查询 的语句
	 * @param params
	 *            查询 的参数
	 * @return 查询 的返回结果<br/>
	 *         如果查询失败返回null
	 */
	public List<T> queryList(String sql, Object... params) {
		Connection conn = JdbcUtils.getConnection();
		try {
			return queryRunner.query(conn, sql, new BeanListHandler<T>(type), params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return null;
	}

	/**
	 * 查询 一条记录
	 * 
	 * @param sql
	 *            查询 的语句
	 * @param params
	 *            查询 的参数
	 * @return 查询 返回的结果<br/>
	 *         如果没有查找数据，或者查询 失败返回null
	 */
	public T queryOne(String sql, Object... params) {
		Connection conn = JdbcUtils.getConnection();
		try {
			return queryRunner.query(conn, sql, new BeanHandler<T>(type), params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return null;
	}

	/**
	 * 查询 数据库，返回只有一个列的查询
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object queryForSingleValue(String sql, Object... params) {
		Connection conn = JdbcUtils.getConnection();
		try {
			return queryRunner.query(conn, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return null;
	}

}
