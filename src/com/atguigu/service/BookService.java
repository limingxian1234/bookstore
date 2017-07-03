package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;

public interface BookService {

	// 查找所有图书信息
	public List<Book> queryAllBook();

	// 删除图书
	public void deleteBookById(int id);

	// 添加图书
	public void addBook(Book book);

	// 修改图书
	public void updateBook(Book book);

	// 根据图书id查找图书信息
	public Book queryBookById(int id);

	// 分页查询
	public Page<Book> page(int pageNo, int pageSize);
	
	public Page<Book> pageByPrice(int pageNo, int pageSize,double minPrice,double maxPrice);
}
