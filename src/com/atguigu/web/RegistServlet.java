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
 * Servlet implementation class RegistServlet
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;
	
	public RegistServlet() {
		super();
		// TODO Auto-generated constructor stub
		userService = new UserServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		1.先获取传递过来的参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String code = request.getParameter("code");
		
//		4.如果验证码不为abcde说明验证码错误
		if ("abcde".equalsIgnoreCase(code)) {
			//
//			2.判断用户名是否存在 ，
			boolean usernameExist = userService.usernameExist(username);
			// 如果usernameExist==true说明用户名已存在
			if (usernameExist) {
//			2.1. 如果用户名已存在。注册失败
				System.out.println("用户名【" + username + "】已存在，注册失败！");
				
				//先把需要回显的信息，保存到Request域对象中
				//错误信息
				request.setAttribute("msg", "用户名已存在！");
				//用户名
				request.setAttribute("username", username);
				//邮箱
				request.setAttribute("email", email);
				
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
//				 获取工程路径
//				String projectPath = request.getContextPath();
				// 验证码错误，就重定向回注册页面
//				response.sendRedirect(projectPath + "/pages/user/regist.jsp");
			} else {
	//			2.2 如果用户名不存在。注册用户信息
	//				userService.registUser()
				userService.registUser(new User(0, username, password, email));
	//			3.当用户成功后。跳转到注册成功页面
				System.out.println("用户注册成功！！！！跳转到注册成功页面");
	//				regist_success.jsp
				// 在转发中/ 表示http://ip:port/工程名/
				request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);;
			}
		} else {
			//先把需要回显的信息，保存到Request域对象中
			//错误信息
			request.setAttribute("msg", "验证码错误！注册失败！");
			//用户名
			request.setAttribute("username", username);
			//邮箱
			request.setAttribute("email", email);
			
			
			System.out.println("验证码错误！注册失败！");
//			 获取工程路径
//			String projectPath = request.getContextPath();
//			// 验证码错误，就重定向回注册页面
//			response.sendRedirect(projectPath + "/pages/user/regist.jsp");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}



	}

}
