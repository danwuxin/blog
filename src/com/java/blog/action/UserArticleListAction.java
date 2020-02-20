/**
 *ʱ�䣺2014-7-16����5:04:26
 *
 *���ߣ��Ź���
 *���ܣ�TODO
 */
package com.java.blog.action;

import com.java.blog.entity.User;
import com.java.blog.service.ArticleService;
import com.java.blog.service.UserService;
import com.java.blog.util.PaginationData;
import com.java.mvc.Action;
import com.java.mvc.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class UserArticleListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int pageIndex = 1;
		// 1����ȡҪ�޸ĵ�����Id
		int userId =Integer.parseInt( request.getParameter("userId"));
		String parameterPageIndex =request.getParameter("pageIndex");
		if (parameterPageIndex != null) {
			// �����ȡ������ת��
			pageIndex = Integer.parseInt(parameterPageIndex);
		}
		// 2������Service��ķ�������ȡ���û�����������б�
		UserService userService = new UserService();
		User selectedUser = userService.findByUserId(userId);
		request.setAttribute("selectedUser", selectedUser);
		ArticleService articleService = new ArticleService();

		request.setAttribute("userId", userId);
		PaginationData paginationData = articleService
				.getUserArticleVoByPaging(pageIndex, userId);
		request.setAttribute("paginationData", paginationData);

		// 2������Service��ķ�������ȡ���û�����������б�
		/*
		 * ArticleService articleService = new ArticleService(); List<ArticleVo>
		 * articleVos = articleService .getArticleVoByUserId(userId);
		 * request.setAttribute("articleVos", articleVos);
		 */
		// 3����ת��ʾ�����б�Ľ���
//		request.getRequestDispatcher("listUserArticle.jsp").forward(request,
//				response);
	return new ActionForward("/listUserArticle.jsp", true);	
		
 
	}

}
