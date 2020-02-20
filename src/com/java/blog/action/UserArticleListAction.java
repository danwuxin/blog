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
		// 1、获取要修改的文章Id
		int userId =Integer.parseInt( request.getParameter("userId"));
		String parameterPageIndex =request.getParameter("pageIndex");
		if (parameterPageIndex != null) {
			// 如果获取到，则转换
			pageIndex = Integer.parseInt(parameterPageIndex);
		}
		// 2、调用Service层的方法，获取该用户发表的文章列表
		UserService userService = new UserService();
		User selectedUser = userService.findByUserId(userId);
		request.setAttribute("selectedUser", selectedUser);
		ArticleService articleService = new ArticleService();

		request.setAttribute("userId", userId);
		PaginationData paginationData = articleService
				.getUserArticleVoByPaging(pageIndex, userId);
		request.setAttribute("paginationData", paginationData);

		// 2、调用Service层的方法，获取该用户发表的文章列表
		/*
		 * ArticleService articleService = new ArticleService(); List<ArticleVo>
		 * articleVos = articleService .getArticleVoByUserId(userId);
		 * request.setAttribute("articleVos", articleVos);
		 */
		// 3、跳转显示文章列表的界面
//		request.getRequestDispatcher("listUserArticle.jsp").forward(request,
//				response);
		return new ActionForward("/listUserArticle.jsp", true);


	}

}
