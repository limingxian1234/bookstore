package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.Book;

public interface BookDao {
	/**
	 * 根据图书的id号来查找图书
	 * 
	 * @param id
	 * @return
	 */
	public Book queryBookById(int id);

	/**
	 * 查询所有的图书
	 * 
	 * @return
	 */
	public List<Book> queryAllBook();

	/**
	 * 添加图书
	 * 
	 * @param book
	 * @return
	 */
	public int saveBook(Book book);

	/**
	 * 修改图书
	 * 
	 * @param book
	 * @return
	 */
	public int updateBook(Book book);

	/**
	 * 根据图书的id号来删除图书
	 * 
	 * @param id
	 * @return
	 */
	public int deleteBookById(int id);

	/**
	 * 查询图书的总记录数
	 * 
	 * @return
	 */
	public int queryForPageTotalCount();

	/**
	 * 查询每页显示的数据
	 * 
	 * @param begin
	 * @param size
	 * @return
	 */
	public List<Book> queryForPageItems(int begin, int size);

	/**
	 * 根据价格区间查找这个区间内图书总数量
	 */
	public int queryForPageTotalCountByPrice(double minPrice, double maxPrice);

	/**
	 * 根据价格区间，查找当前页显示的数据集合
	 * 
	 * @param begin
	 * @param size
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	public List<Book> queryForPageItemsByPrice(int begin, int size, double minPrice, double maxPrice);

}
