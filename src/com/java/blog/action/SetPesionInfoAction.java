package com.java.blog.action;

import com.java.blog.entity.User;
import com.java.blog.service.UserService;
import com.java.mvc.Action;
import com.java.mvc.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class SetPesionInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 设置字符集
		// response.setHeader("content-type","text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		// 接受数据
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String imageUrl = request.getParameter("imageUrl");

		// 验证姓名数据
		if (null == name || name.trim().length() == 0) {
			request.setAttribute("errorInfo", "用户真实姓名不能为空。");
//			request.getRequestDispatcher("setPesionInfo.jsp").forward(request,
//					response);
//			return;
			return new ActionForward("/setPesionInfo.jsp", true);
		}
		// 验证年龄数据

		if (null == age || age.trim().length() == 0) {
			request.setAttribute("errorInfo", "真实年龄不能为空。");
//			request.getRequestDispatcher("setPesionInfo.jsp").forward(request,
//					response);
//			return;
			return new ActionForward("/setPesionInfo.jsp", true);
		} else {

			try {
				int tmp = Integer.parseInt(age);
				if (tmp < 16 || tmp > 100) {
					request.setAttribute("errorInfo", "年龄必须在16到60之间。");
//					request.getRequestDispatcher("setPesionInfo.jsp").forward(
//							request, response);
//					return; // 一定要有，否则后面继续执行，将报错
					return new ActionForward("/setPesionInfo.jsp", true);
				}
			} catch (Exception e) {
				request.setAttribute(" errorInfo", "年龄格式错误。");
//				request.getRequestDispatcher("setPesionInfo.jsp").forward(
//						request, response);
//				return;
				return new ActionForward("/setPesionInfo.jsp", true);
			}

		}
		// 调用service层
		UserService userService = new UserService();
		// 从session中取出user
		User olduser = (User) request.getSession().getAttribute("user");

		User tmpUser = new User();
		tmpUser.setUserid(olduser.getUserid());
		tmpUser.setName(name);
		tmpUser.setGender(gender);
		tmpUser.setAge(Integer.parseInt(age));
		tmpUser.setImageUrl(imageUrl);

		User updatedUser = userService.updateUser(tmpUser);

		// 5、判断结果并跳转
		if (updatedUser == null) {
			request.setAttribute("errorInfo", userService.getErrorMessage());
//			request.getRequestDispatcher("setPesionInfo.jsp").forward(request,
//					response);
			return new ActionForward("/setPesionInfo.jsp", true);
		} else {
			// 重新保存User对象到session中
			request.getSession().setAttribute("user", updatedUser);
//			response.sendRedirect("ok.jsp");
			return new ActionForward("/ok.jsp", false);
		}
	}
}
