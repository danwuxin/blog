/**
 *时间：2014-7-16上午12:11:07
 *
 *作者：张国宝
 *功能：TODO
 */
package com.java.blog.action;

import com.java.blog.entity.Article;
import com.java.blog.entity.User;
import com.java.blog.service.ArticleService;
import com.java.mvc.Action;
import com.java.mvc.ActionForward;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ListArticleAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//从数据库中查询登陆用户的所有文章
		User user = (User)request.getSession().getAttribute("user");
		ArticleService articleService = new ArticleService();

		List<Article> articles = articleService.findByUserId(user.getUserid());
		//查找的结果保存到Request中
		request.setAttribute("articles", articles);
		// 页面跳转
//		request.getRequestDispatcher("listArticle.jsp").forward(request,
//				response);
		return new ActionForward("/listArticle.jsp", true);

	}

}
