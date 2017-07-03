package com.atguigu.dao.impl;

import java.util.List;

import com.atguigu.bean.Book;
import com.atguigu.dao.BookDao;

public class BookDaoImpl extends BaseDaoImpl<Book> implements BookDao {

	@Override
	public Book queryBookById(int id) {
		// 查询 的sql语句
		String sql = "select `id`, `name`, `author`, `price`, `stock`, `sales`, `img_path` imgPath from tb_book where id = ?";
		return queryOne(sql, id);
	}

	@Override
	public List<Book> queryAllBook() {
		// 查询 的sql语句
		String sql = "select `id`, `name`, `author`, `price`, `stock`, `sales`, `img_path` imgPath from tb_book";
		return queryList(sql);
	}

	@Override
	public int saveBook(Book book) {
		String sql = "insert into tb_book( `name` , `author` , `price` , `sales` , `stock` , `img_path`) "
				+ "values( ? ,? ,? ,? ,? ,? )";
		return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(),
				book.getStock(), book.getImgPath());
	}

	@Override
	public int updateBook(Book book) {
		String sql = "update tb_book " + "set " + "`name` = ? , " + "`author` = ? ,"
				+ "`price` = ? , " + "`stock` = ? ," + " `sales` = ? ," + "`img_path` = ? "
				+ "where `id` = ?";

		return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getStock(),
				book.getSales(), book.getImgPath(), book.getId());
	}

	@Override
	public int deleteBookById(int id) {
		String sql = "delete from tb_book where id = ?";
		return update(sql, id);
	}

	@Override
	public int queryForPageTotalCount() {
		// 查询 总记录数的sql语句
		String sql = "select count(*) from tb_book";
		// 执行 sql语句。返回一个列的结果
		Object result = queryForSingleValue(sql);

		return new Integer(result.toString()).intValue();
	}

	@Override
	public List<Book> queryForPageItems(int begin, int size) {
		// 执行查询 分页中，每页显示数据的集合
		String sql = "select `id`, `name`, `author`, `price`, `stock`, `sales`, `img_path` imgPath from tb_book limit ? , ? ";
		// 执行查询
		return queryList(sql, begin, size);

	}

	@Override
	public int queryForPageTotalCountByPrice(double minPrice, double maxPrice) {
		// 查询 总记录数的sql语句
		String sql = "select count(*) from tb_book where price between ? and ? ";
		// 执行 sql语句。返回一个列的结果
		Object result = queryForSingleValue(sql, minPrice, maxPrice);

		return new Integer(result.toString()).intValue();
	}

	@Override
	public List<Book> queryForPageItemsByPrice(int begin, int size, double minPrice, double maxPrice) {
		// 执行查询 分页中，每页显示数据的集合
		String sql = "select `id`, `name`, `author`, `price`, `stock`, `sales`, `img_path` imgPath "
				+ "from tb_book where price between ? and ? order by price limit ? , ? ";
		// 执行查询
		return queryList(sql, minPrice, maxPrice, begin, size);
	}

}
