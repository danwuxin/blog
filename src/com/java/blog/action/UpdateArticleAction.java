package com.java.blog.action;

import com.java.blog.entity.Article;
import com.java.blog.service.ArticleService;
import com.java.mvc.Action;
import com.java.mvc.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class UpdateArticleAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		// 设置字符集
		// response.setHeader("content-type","text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		// 接受数据
		String articleid = request.getParameter("articleid");
		String contents = request.getParameter("contents");
		String ArticleTitle = request.getParameter("ArticleTitle");
		// 验证标题数据
		if (null == ArticleTitle || ArticleTitle.trim().length() == 0) {
			request.setAttribute("errorInfo", "文章标题不能为空。");
			// request.getRequestDispatcher("addArticle.jsp").forward(request,
			// response);
			// return;
			return new ActionForward("/addArticle.jsp", true);
		}
		// 验证内容数据

		if (null == contents || contents.trim().length() == 0) {
			request.setAttribute("errorInfo", "文章内容不能为空。");
			// request.getRequestDispatcher("addArticle.jsp").forward(request,
			// response);
			// return;
			return new ActionForward("/addArticle.jsp", true);
		}

		// 调用业务层
		ArticleService articleService = new ArticleService();

		Article article = new Article();
		// 设置要更新的数据
		article.setTitle(ArticleTitle);
		article.setContents(contents.replace("\n", "<br/>"));
		article.setArticleId(Integer.parseInt(articleid));
		boolean result = articleService.updateArticle(article);

		if (result) {
			// response.sendRedirect("/ListArticleServlet");
			return new ActionForward("/ListArticle.do", false);
		} else {
			request.setAttribute("errorInfo", "更新文章失败。");
			// request.getRequestDispatcher("updateArticle.jsp").forward(request,
			// response);
			return new ActionForward("/updateArticle.jsp", true);
		}

	}

}
