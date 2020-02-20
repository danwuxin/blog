/**
 *ʱ�䣺2014-7-15����11:08:51
 *
 *���ߣ��Ź���
 *���ܣ�TODO
 */
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
		// �����ַ���
		// response.setHeader("content-type","text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		// ��������
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String imageUrl = request.getParameter("imageUrl");

		// ��֤��������
		if (null == name || name.trim().length() == 0) {
			request.setAttribute("errorInfo", "�û���ʵ��������Ϊ�ա�");
//			request.getRequestDispatcher("setPesionInfo.jsp").forward(request,
//					response);
//			return;
			return new ActionForward("/setPesionInfo.jsp", true);
		}
		// ��֤��������

		if (null == age || age.trim().length() == 0) {
			request.setAttribute("errorInfo", "��ʵ���䲻��Ϊ�ա�");
//			request.getRequestDispatcher("setPesionInfo.jsp").forward(request,
//					response);
//			return;
			return new ActionForward("/setPesionInfo.jsp", true);
		} else {

			try {
				int tmp = Integer.parseInt(age);
				if (tmp < 16 || tmp > 100) {
					request.setAttribute("errorInfo", "���������16��60֮�䡣");
//					request.getRequestDispatcher("setPesionInfo.jsp").forward(
//							request, response);
//					return; // һ��Ҫ�У�����������ִ�У�������
					return new ActionForward("/setPesionInfo.jsp", true);
				}
			} catch (Exception e) {
				request.setAttribute(" errorInfo", "�����ʽ����");
//				request.getRequestDispatcher("setPesionInfo.jsp").forward(
//						request, response);
//				return;
				return new ActionForward("/setPesionInfo.jsp", true);
			}

		}
		// ����service��
		UserService userService = new UserService();
		// ��session��ȡ��user
		User olduser = (User) request.getSession().getAttribute("user");

		User tmpUser = new User();
		tmpUser.setUserid(olduser.getUserid());
		tmpUser.setName(name);
		tmpUser.setGender(gender);
		tmpUser.setAge(Integer.parseInt(age));
		tmpUser.setImageUrl(imageUrl);

		User updatedUser = userService.updateUser(tmpUser);

		// 5���жϽ������ת
		if (updatedUser == null) {
			request.setAttribute("errorInfo", userService.getErrorMessage());
//			request.getRequestDispatcher("setPesionInfo.jsp").forward(request,
//					response);
			return new ActionForward("/setPesionInfo.jsp", true);
		} else {
			// ���±���User����session��
			request.getSession().setAttribute("user", updatedUser);
//			response.sendRedirect("ok.jsp");
			return new ActionForward("/ok.jsp", false);
		}
	}
}
