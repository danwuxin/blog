/**
 *ʱ�䣺2014-7-15����10:17:55
 *
 *���ߣ��Ź���
 *���ܣ�TODO
 */
package com.java.blog.action;

import com.java.blog.service.ArticleService;
import com.java.mvc.Action;
import com.java.mvc.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteArticleAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 1����ȡҪɾ��������Id
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		// 2��ɾ������
		ArticleService articleService = new ArticleService();
		boolean result = articleService.deleteArticleById(articleId);
		// 3����ת���������µ�ҳ��
		if (result) {
//			response.sendRedirect("ListArticleServlet");
			return new ActionForward("/ListArticle.do", false);
		} else {
			request.setAttribute("errorInfo", "ɾ������ʧ�ܡ�");
//			request.getRequestDispatcher("ListArticleServlet").forward(request,
//					response);
			return new ActionForward("/ListArticle.do", true);
		}

	}
}
