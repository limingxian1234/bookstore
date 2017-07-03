package com.atguigu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService;

	public UserServlet() {
		super();
		userService = new UserServiceImpl();
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		//通过在jsp页面的表单中，添加一个隐藏域，告诉服务器。当前执行业务是哪个
//		String action = request.getParameter("action");
//		// 如果当前的业务是登录业务。就调用login方法
//		if ("login".equals(action)) {
//			login(request, response);
//			
//		} 
//		// 如果当前的业务是注册业务，就调用regist方法
//		else if ("regist".equals(action)) {
//			regist(request, response);
//		} 
//		// 现在要添加一个删除的业务
//		else if ("delete".equals(action)) {
////			delete(request, response);
//		}
//		
//		
//	}
	
	
	protected void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		1.先获取传递过来的参数
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		String email = request.getParameter("email");
		String code = request.getParameter("code");
		
		User t = WebUtils.copyParam2Bean(request.getParameterMap(), new User());
		
//		4.如果验证码不为abcde说明验证码错误
		if ("abcde".equalsIgnoreCase(code)) {
			//
//			2.判断用户名是否存在 ，
			boolean usernameExist = userService.usernameExist(t.getUsername());
			// 如果usernameExist==true说明用户名已存在
			if (usernameExist) {
//			2.1. 如果用户名已存在。注册失败
				System.out.println("用户名【" + t.getUsername() + "】已存在，注册失败！");
				
				//先把需要回显的信息，保存到Request域对象中
				//错误信息
				request.setAttribute("msg", "用户名已存在！");
				//用户名
				request.setAttribute("username", t.getUsername());
				//邮箱
				request.setAttribute("email", t.getEmail());
				
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
//				 获取工程路径
//				String projectPath = request.getContextPath();
				// 验证码错误，就重定向回注册页面
//				response.sendRedirect(projectPath + "/pages/user/regist.jsp");
			} else {
	//			2.2 如果用户名不存在。注册用户信息
	//				userService.registUser()
				userService.registUser(t);
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
			request.setAttribute("username", t.getUsername());
			//邮箱
			request.setAttribute("email", t.getEmail());
			
			
			System.out.println("验证码错误！注册失败！");
//			 获取工程路径
//			String projectPath = request.getContextPath();
//			// 验证码错误，就重定向回注册页面
//			response.sendRedirect(projectPath + "/pages/user/regist.jsp");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}



	}
	
	
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1。获取表单传递过来的参数
		// 用户名
//		String username = request.getParameter("username");
		// 密码
//		String password = request.getParameter("password");
		//
		// 2.调用UserService业务接口
		// login方法去做登录的业务
//		User t = new User();
//		System.out.println("在beanUtils注入之前：" + t);
//		try {
//			BeanUtils.populate(t, request.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			e.printStackTrace();
//		}
//		System.out.println(request.getParameterMap().keySet());
//		System.out.println("在beanUtils注入之后：" + t);
		
		
//		User user = userService.login(new User(0, username, password, ""));
		User t = WebUtils.copyParam2Bean(request.getParameterMap(), new User());
		User user = userService.login(t);
		
		//
		// 3.根据userServcie.login（）方法的返回值，判断是否登录成功.
		// 如果登录失败。跳转回原来的
		// 登录页面
		if (user == null) {
			//把错误信息，保存到域对象中
			request.setAttribute("msg", "用户登录失败，用户名或密码错误");
			request.setAttribute("username", t.getUsername());
			
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
