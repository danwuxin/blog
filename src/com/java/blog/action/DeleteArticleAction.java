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
		// 1、获取要删除的文章Id
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		// 2、删除文章
		ArticleService articleService = new ArticleService();
		boolean result = articleService.deleteArticleById(articleId);
		// 3、跳转到管理文章的页面
		if (result) {
//			response.sendRedirect("ListArticleServlet");
			return new ActionForward("/ListArticle.do", false);
		} else {
			request.setAttribute("errorInfo", "删除文章失败。");
//			request.getRequestDispatcher("ListArticleServlet").forward(request,
//					response);
			return new ActionForward("/ListArticle.do", true);
		}

	}
}
