/**
*ʱ�䣺2014-7-15����6:43:58
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



public class RegisterAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//�����ַ���
				request.setCharacterEncoding("utf-8");
				//��������  �˺�  ����  ����  ȷ������  ͷ��
				String account = request.getParameter("account");
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				String confirmPassword = request.getParameter("confirmPassword");
				String imageUrl = request.getParameter("imageUrl");
			    //��֤��������
				if (null == name || name.trim().length() == 0) {
					request.setAttribute("errorInfo", "��ʵ��������Ϊ�ա�");
//					request.getRequestDispatcher("register.jsp").forward(request,
//							response);
//					return ;
					return new ActionForward("/register.jsp", true);
					
				}
				//��֤�˺�����
				if (null == account || account.trim().length() == 0) {
					request.setAttribute("errorInfo", "��¼�˺Ų���Ϊ�ա�");
//					request.getRequestDispatcher("register.jsp").forward(request,
//							response);
//					return;
					return new ActionForward("/register.jsp", true);
				}
				
				//��֤��������
				if (null == password || password.trim().length() == 0) {
					request.setAttribute("errorInfo", "��¼���벻��Ϊ�ա�");
//					request.getRequestDispatcher("register.jsp").forward(request,
//							response);
//					return;
					return new ActionForward("/register.jsp", true);
				}
				//��֤ȷ����������
				if (null == confirmPassword || confirmPassword.trim().length() == 0) {
					request.setAttribute("errorInfo", "��¼���벻��Ϊ�ա�");
//					request.getRequestDispatcher("register.jsp").forward(request,
//						response);
//					return;
					return new ActionForward("/register.jsp", true);
				}
				if (!password.equals(confirmPassword)) {
					request.setAttribute("errorInfo", "������������Ĳ�һ�¡�");
//					request.getRequestDispatcher("register.jsp").forward(request,
//							response);
//					return;
					return new ActionForward("/register.jsp", true);
				}
				
				//new һ���û�����  �����յ������ݸ�new�������û�
				User user = new User();
				user.setAccount(account);
				user.setName(name);
				user.setPassword(password);
				user.setImageUrl(imageUrl);
				//����user  service��   ʹ��service���ҵ��
			    UserService userService = new UserService();
				boolean result = userService.register(user);
				//�ж��û��Ƿ�Ϊ��
				if (!result) {
					request.setAttribute("errorInfo", userService.getErrorMessage());
//					request.getRequestDispatcher("register.jsp").forward(request,
//							response);
					return new ActionForward("/register.jsp", true);
				} else {
//					response.sendRedirect("index.jsp");
					return new ActionForward("/index.jsp", false);
				}
	}

}
