package com.java.blog.action;

import com.java.blog.entity.User;
import com.java.blog.service.UserService;
import com.java.mvc.Action;
import com.java.mvc.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChangePasswordAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		// 设置字符集
		// response.setHeader("content-type","text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		// 接受数据 原密码 新密码 确认新密码
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		// 验证标题数据
		if (null == oldPassword || oldPassword.trim().length() == 0) {
			request.setAttribute("errorInfo", "原密码不能为空。");
			// request.getRequestDispatcher("changePassword.jsp").forward(
			// request, response);
			// return;
			return new ActionForward("/changePassword.jsp", true);
		}
		// 验证内容数据

		if (null == newPassword || newPassword.trim().length() == 0) {
			request.setAttribute("errorInfo", "新密码不能为空。");
			// request.getRequestDispatcher("changePassword.jsp").forward(
			// request, response);
			// return;
			return new ActionForward("/changePassword.jsp", true);
		}
		if (!newPassword.equals(confirmPassword)) {
			request.setAttribute("errorInfo", "新密码与确认密码输入不一致。");
			// request.getRequestDispatcher("changePassword.jsp").forward(
			// request, response);
			// return;
			return new ActionForward("/changePassword.jsp", true);
		}

		// 调用service层
		UserService userService = new UserService();
		// 登录后session保存信息 从session中取出正在用户的user对象
		User user = (User) request.getSession().getAttribute("user");
		// 传入user的账号 旧密码 新密码
		boolean result = userService.changePassword(user.getAccount(),
				oldPassword, newPassword);
		if (result) {
			// response.sendRedirect("ok.jsp");
			return new ActionForward("/ok.jsp", false);
		} else {
			request.setAttribute("errorInfo", userService.getErrorMessage());
			// request.getRequestDispatcher("changePassword.jsp").forward(
			// request, response);
			return new ActionForward("/changePassword", true);
		}
	}
}
