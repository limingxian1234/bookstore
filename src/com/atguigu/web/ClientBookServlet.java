package com.atguigu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

/**
 * Servlet implementation class ClientBookServlet
 */
public class ClientBookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService;
	
    /**
     * Default constructor. 
     */
    public ClientBookServlet() {
    	bookService = new BookServiceImpl();
    }
	public void pageByPrice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.先获取请求的参数。pageNo和pageSize
		int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
		int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
		
		String minPriceStr = request.getParameter("min");
		String maxPriceStr = request.getParameter("max");
		
		double minPrice = WebUtils.parseDouble(minPriceStr, 0);
		double maxPrice = WebUtils.parseDouble(maxPriceStr, Double.MAX_VALUE);
		
		//2.通过bookService.page方法查询分页信息
		Page<Book> page = bookService.pageByPrice(pageNo, pageSize,minPrice,maxPrice);
		
		String url = "client/bookServlet?action=pageByPrice";
		if (minPriceStr != null) {
			url += "&min=" + minPriceStr;
		}
		if (maxPriceStr != null) {
			url += "&max=" + maxPriceStr;
		}
		page.setUrl(url);
		System.out.println(page);
		//3.要把page对象保存到Request域对象中
		request.setAttribute("page", page);
		//4.转发到book_mananger.jsp页面
		request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
	}

}
