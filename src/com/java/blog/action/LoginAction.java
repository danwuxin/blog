/**
 *ʱ�䣺2014-7-15����3:57:50
 *
 *���ߣ��Ź���
 *���ܣ�TODO
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
		// �����ַ���
		//response.setHeader("content-type", "text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		// �������� �˺� ���� ȷ������
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String[] rememberMe = request.getParameterValues("rememberMe");
		// ��֤�˺�����
		if (null == account || account.trim().length() == 0) {
			request.setAttribute("errorInfo", "��¼�˺Ų���Ϊ�ա�");
			
			return new ActionForward("/index.jsp", true);
		}
		// ��֤��������
		if (null == password || password.trim().length() == 0) {
			request.setAttribute("errorInfo", "��¼���벻��Ϊ�ա�");
			return new ActionForward("/index.jsp", true);
		}
		// ����service��
		UserService userService = new UserService();
		User user = userService.login(account, password);

		// �ж��û��Ƿ�Ϊ��
		if (user == null) {
			request.setAttribute("errorInfo", "��¼�˺Ż��¼�������");
//			request.getRequestDispatcher("index.jsp")
//					.forward(request, response);
			return new ActionForward("/index.jsp", true);
		} else {
			// �û���Ϣ���浽user��
			request.getSession().setAttribute("user", user);

			// �ж��û��Ƿ�ѡ�˼�ס��
			if (rememberMe != null) {
				if (rememberMe[0].equals("1")) {
					// �����û���Ϣ��cookie��
					Cookie cookieUserid = new Cookie("userid",
							URLEncoder.encode(user.getUserid() + "", "utf-8"));
					cookieUserid.setMaxAge(60 * 60 * 24 * 30);
					response.addCookie(cookieUserid);
				}
			}
			// ҳ����ת
			return new ActionForward("/index.jsp", false);
		}

	
		

	}
}
