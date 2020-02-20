/**
 *时间：2014-7-15下午3:57:50
 *
 *作者：张国宝
 *功能：TODO
 */
package com.java.blog.action;

import com.java.blog.entity.User;
import com.java.blog.service.UserService;
import com.java.mvc.Action;
import com.java.mvc.ActionForward;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class LoginAction implements Action {
	
	@Override          
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		// 设置字符集
		//response.setHeader("content-type", "text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		// 接受数据 账号 密码 确认密码
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String[] rememberMe = request.getParameterValues("rememberMe");
		// 验证账号数据
		if (null == account || account.trim().length() == 0) {
			request.setAttribute("errorInfo", "登录账号不能为空。");
			
			return new ActionForward("/index.jsp", true);
		}
		// 验证密码数据
		if (null == password || password.trim().length() == 0) {
			request.setAttribute("errorInfo", "登录密码不能为空。");
			return new ActionForward("/index.jsp", true);
		}
		// 调用service层
		UserService userService = new UserService();
		User user = userService.login(account, password);

		// 判断用户是否为空
		if (user == null) {
			request.setAttribute("errorInfo", "登录账号或登录密码错误。");
//			request.getRequestDispatcher("index.jsp")
//					.forward(request, response);
			return new ActionForward("/index.jsp", true);
		} else {
			// 用户信息保存到user中
			request.getSession().setAttribute("user", user);

			// 判断用户是否勾选了记住我
			if (rememberMe != null) {
				if (rememberMe[0].equals("1")) {
					// 保存用户信息到cookie中
					Cookie cookieUserid = new Cookie("userid",
							URLEncoder.encode(user.getUserid() + "", "utf-8"));
					cookieUserid.setMaxAge(60 * 60 * 24 * 30);
					response.addCookie(cookieUserid);
				}
			}
			// 页面跳转
			return new ActionForward("/index.jsp", false);
		}

	
		

	}
}
