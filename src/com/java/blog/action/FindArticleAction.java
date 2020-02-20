/**
*时间：2014-7-16上午12:06:17
*
*作者：张国宝
*功能：TODO
*/
package com.java.blog.action;

import com.java.blog.entity.Article;
import com.java.blog.service.ArticleService;
import com.java.mvc.Action;
import com.java.mvc.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class FindArticleAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 1、获取要修改的文章Id
				int articleId = Integer.parseInt(request.getParameter("articleId"));
				// 2、获取要修改的文章并保存到request中
				ArticleService articleService = new ArticleService();
				Article article = articleService.findArticleById(articleId);
				article.setContents(article.getContents().replace("<br/>", "\n"));
				request.setAttribute("article", article);
				// 3、跳转到修改文章的页面
//				request.getRequestDispatcher("updateArticle.jsp").forward(request,
//						response);
				return new ActionForward("updateArticle.jsp", true);

			}

}
