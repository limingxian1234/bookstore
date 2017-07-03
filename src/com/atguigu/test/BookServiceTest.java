package com.atguigu.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;

public class BookServiceTest {

	static BookService bookService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bookService = new BookServiceImpl();
	}

	@Test
	public void testQueryAllBook() {
		System.out.println(bookService.queryAllBook().size());
	}

	@Test
	public void testDeleteBookById() {
		bookService.deleteBookById(22);
	}

	@Test
	public void testAddBook() {
		bookService.addBook(new Book(0, "少女萌萌拳", "萌萌", 9.9, 99999, 1, null));
	}

	@Test
	public void testUpdateBook() {
		bookService.updateBook(new Book(22, "少女萌萌拳", "国哥", 9.9, 99999, 1, null));
	}

	@Test
	public void testQueryBookById() {
		System.out.println(bookService.queryBookById(1));
	}

	@Test
	public void page() {
		System.out.println(bookService.page(100, Page.PAGE_SIZE));
	}
	
	@Test
	public void pageByPrice() {
		System.out.println(bookService.pageByPrice(4, Page.PAGE_SIZE,10,50));
	}

}
