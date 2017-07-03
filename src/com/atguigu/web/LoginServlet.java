package com.atguigu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;

	public LoginServlet() {
		super();
		userService = new UserServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1。获取表单传递过来的参数
		// 用户名
		String username = request.getParameter("username");
		// 密码
		String password = request.getParameter("password");
		//
		// 2.调用UserService业务接口
		// login方法去做登录的业务
		User user = userService.login(new User(0, username, password, ""));
		//
		// 3.根据userServcie.login（）方法的返回值，判断是否登录成功.
		// 如果登录失败。跳转回原来的
		// 登录页面
		if (user == null) {
			//把错误信息，保存到域对象中
			request.setAttribute("msg", "用户登录失败，用户名或密码错误");
			request.setAttribute("username", username);
			
			System.out.println("用户登录失败，用户名或密码错误");
			
//			String projectPath = request.getContextPath();
//			// "http://ip:port/工程名/pages/user/login.jsp"
//			// 重定向的/表示http://ip:port/
//			String fullPath = projectPath + "/pages/user/login.jsp";
//			System.out.println(fullPath);
//			response.sendRedirect(fullPath);
			
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
			
		} else {
			// 如果登录成功。跳转到login_success.jsp
			System.out.println("登录成功！");
			// 在转发中。/表示http://ip:port/工程名/
			request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);;
		}

	}

}
