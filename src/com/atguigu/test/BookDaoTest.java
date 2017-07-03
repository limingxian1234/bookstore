package com.atguigu.test;

import org.junit.BeforeClass;
import org.junit.Test;

import com.atguigu.bean.Book;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;

public class BookDaoTest {

	private static BookDao bookDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookDao = new BookDaoImpl();
	}

	@Test
	public void testQueryBookById() {
		System.out.println(bookDao.queryBookById(1));
	}

	@Test
	public void testQueryAllBook() {
		System.out.println(bookDao.queryAllBook().size());
	}

	@Test
	public void testSaveBook() {
		bookDao.saveBook(new Book(0, "XXXX", "康哥", 99, 99, 99, null));
	}

	@Test
	public void testUpdateBook() {
		bookDao.updateBook(new Book(21, "XXXX", "国哥", 99, 99, 99, null));
	}

	@Test
	public void testDeleteBookById() {
		bookDao.deleteBookById(21);
	}

	@Test
	public void testQueryForPageTotalCount() {
		System.out.println( bookDao.queryForPageTotalCount() );
	}

	@Test
	public void queryForPageItems() {
		System.out.println( bookDao.queryForPageItems(4, 4) );
	}
	@Test
	public void testQueryForPageTotalCountByPrice() {
		System.out.println( bookDao.queryForPageTotalCountByPrice(10,50) );
	}
	
	@Test
	public void queryForPageItemsByPrice() {
		System.out.println( bookDao.queryForPageItemsByPrice(0, 4, 10, 50) );
	}

}