package com.atguigu.service.impl;

import java.util.List;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.service.BookService;

public class BookServiceImpl implements BookService {

	private BookDao bookDao;

	public BookServiceImpl() {
		bookDao = new BookDaoImpl();
	}

	@Override
	public List<Book> queryAllBook() {
		return bookDao.queryAllBook();
	}

	@Override
	public void deleteBookById(int id) {
		bookDao.deleteBookById(id);
	}

	@Override
	public void addBook(Book book) {
		bookDao.saveBook(book);
	}

	@Override
	public void updateBook(Book book) {
		bookDao.updateBook(book);
	}

	@Override
	public Book queryBookById(int id) {
		return bookDao.queryBookById(id);
	}

	@Override
	public Page<Book> page(int pageNo, int pageSize) {
		Page<Book> page = new Page<Book>();
		// 设置每页显示的记录数
		page.setPageSize(pageSize);
		// 求总的记录数
		int pageTotalCount = bookDao.queryForPageTotalCount();
		// 设置总的记录数
		page.setPageTotalCount(pageTotalCount);
		// 总的页码
		int pageTotal = 0;
		// 总的页码由 总记录数除以每页显示的记录数可得
		pageTotal = page.getPageTotalCount() / page.getPageSize();
		// 如果除不尽，那么 总页码加1
		if (page.getPageTotalCount() % page.getPageSize() > 0) {
			pageTotal += 1;
		}
		// 设置总的页码
		page.setPageTotal(pageTotal);
		// 设置当前页码
		page.setPageNo(pageNo);

		// Select * from t_book Limit begin ,pageSize;
		int begin = 0;
		// begin可以由公式求得（pageNo-1）* pageSize
		begin = (page.getPageNo()-1)* page.getPageSize();
		
		List<Book> items = bookDao.queryForPageItems(begin, page.getPageSize());

		page.setItems(items);
		
		return page;
	}

	@Override
	public Page<Book> pageByPrice(int pageNo, int pageSize, double minPrice, double maxPrice) {
		Page<Book> page = new Page<Book>();
		// 设置每页显示的记录数
		page.setPageSize(pageSize);
		// 求总的记录数
		int pageTotalCount = bookDao.queryForPageTotalCountByPrice(minPrice, maxPrice);
		// 设置总的记录数
		page.setPageTotalCount(pageTotalCount);
		// 总的页码
		int pageTotal = 0;
		// 总的页码由 总记录数除以每页显示的记录数可得
		pageTotal = page.getPageTotalCount() / page.getPageSize();
		// 如果除不尽，那么 总页码加1
		if (page.getPageTotalCount() % page.getPageSize() > 0) {
			pageTotal += 1;
		}
		// 设置总的页码
		page.setPageTotal(pageTotal);
		// 设置当前页码
		page.setPageNo(pageNo);

		// Select * from t_book Limit begin ,pageSize;
		int begin = 0;
		// begin可以由公式求得（pageNo-1）* pageSize
		begin = (page.getPageNo()-1)* page.getPageSize();
		
		List<Book> items = bookDao.queryForPageItemsByPrice(begin, page.getPageSize(),minPrice,maxPrice);

		page.setItems(items);
		
		return page;
	}

}
