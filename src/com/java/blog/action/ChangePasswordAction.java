/**
 *ʱ�䣺2014-7-15����10:48:30
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



public class ChangePasswordAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		// �����ַ���
		// response.setHeader("content-type","text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		// �������� ԭ���� ������ ȷ��������
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		// ��֤��������
		if (null == oldPassword || oldPassword.trim().length() == 0) {
			request.setAttribute("errorInfo", "ԭ���벻��Ϊ�ա�");
			// request.getRequestDispatcher("changePassword.jsp").forward(
			// request, response);
			// return;
			return new ActionForward("/changePassword.jsp", true);
		}
		// ��֤��������

		if (null == newPassword || newPassword.trim().length() == 0) {
			request.setAttribute("errorInfo", "�����벻��Ϊ�ա�");
			// request.getRequestDispatcher("changePassword.jsp").forward(
			// request, response);
			// return;
			return new ActionForward("/changePassword.jsp", true);
		}
		if (!newPassword.equals(confirmPassword)) {
			request.setAttribute("errorInfo", "��������ȷ���������벻һ�¡�");
			// request.getRequestDispatcher("changePassword.jsp").forward(
			// request, response);
			// return;
			return new ActionForward("/changePassword.jsp", true);
		}

		// ����service��
		UserService userService = new UserService();
		// ��¼��session������Ϣ ��session��ȡ�������û���user����
		User user = (User) request.getSession().getAttribute("user");
		// ����user���˺� ������ ������
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
