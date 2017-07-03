package com.atguigu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

/**
 * Servlet implementation class BookServlet
 */
public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService;

	public BookServlet() {
		bookService = new BookServiceImpl();
	}

	protected void updateBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.获取求参数封装到book对象
		Book book = WebUtils.copyParam2Bean(request.getParameterMap(), new Book());
		int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
		// 2调用updateBook方法，更新图书信息
		bookService.updateBook(book);
		// 3重定向到http://ip:port/工程名/manager/bookServlet?action=queryAllBook
		response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
	}

	protected void queryBookById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.先获取需要修改的id，然后查询 出需要修改的图书信息。
		int id = WebUtils.parseInt(request.getParameter("id"), 0);
		Book book = bookService.queryBookById(id);
		// 2.并且保存到Request域对象中
		request.setAttribute("book", book);
		// 3.转发到book_edit.jsp页面
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
	}

	protected void deleteBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.先获取请求的参数。id的值
		int id = WebUtils.parseInt(request.getParameter("id"), 0);
		int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1) ;
		// 2.调用bookService.deleteBookById()
		bookService.deleteBookById(id);
		// 3.重定向回 http://ip:port/工程名/manager/bookServlet?action=queryAllBook
		response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
	}

	protected void addBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.获取请求的图书信息，封装成为book对象
		Book book = WebUtils.copyParam2Bean(request.getParameterMap(), new Book());
		int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
		// 2.使用BookService.addBook方法保存图书信息
		bookService.addBook(book);
		// 3.重定向到bookServlet中的queryAllBook方法
		// /book/manager/bookServlet?action=queryAllBook
		// 在重定向中。/表示浏览器地址栏是http://ip:port/

		response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
	}

	protected void queryAllBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.查询所有图书的信息。---bookService
		List<Book> books = bookService.queryAllBook();
		// 2.把所有图书的信息，都保存到Request域对象中
		request.setAttribute("list", books);
		// 3.转发到pages/manager/book_manager.jsp页面
		// 转发的/表示 http://ip:port/工程名/ 也就是代码中的WebContent目录
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}
	
	public void page(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.先获取请求的参数。pageNo和pageSize
		int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
		int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
		//2.通过bookService.page方法查询分页信息
		Page<Book> page = bookService.page(pageNo, pageSize);
		page.setUrl("manager/bookServlet?action=page");
		System.out.println(page);
		//3.要把page对象保存到Request域对象中
		request.setAttribute("page", page);
		//4.转发到book_mananger.jsp页面
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}

}
