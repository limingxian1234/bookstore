package com.atguigu.web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 1.先获取Action参数。
		String action = request.getParameter("action");
		// 2.通过action属性值，获取需要调用的业务方法
		try {
			Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class,
					HttpServletResponse.class);
//			System.out.println(method);
			// 3.然后调用这个业务方法执行业务
			method.invoke(this,request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
