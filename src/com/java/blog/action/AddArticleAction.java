package com.java.blog.action;

import com.java.blog.entity.Article;
import com.java.blog.entity.User;
import com.java.blog.service.ArticleService;
import com.java.mvc.Action;
import com.java.mvc.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddArticleAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 设置字符集
		request.setCharacterEncoding("utf-8");
		// 接受数据 内容和标题
		String contents = request.getParameter("contents");
		String ArticleTitle = request.getParameter("ArticleTitle");
		// 验证标题数据
		if (null == ArticleTitle || ArticleTitle.trim().length() == 0) {
			request.setAttribute("errorInfo", "文章标题不能为空。");
//			request.getRequestDispatcher("addArticle.jsp").forward(request,
//					response);
//			return;
			return new ActionForward("/addArticle.jsp", true);
		}
		// 验证内容数据

		if (null == contents || contents.trim().length() == 0) {
			request.setAttribute("errorInfo", "文章内容不能为空。");
//			request.getRequestDispatcher("addArticle.jsp").forward(request,
//					response);
//			return;
			return new ActionForward("/addArticle.jsp", true);
		}
		// new 一个文章对象 将文章信息保存到article对象中
		Article article = new Article();
		article.setTitle(ArticleTitle);
		article.setContents(contents.replace("\n", "<br/>"));
		User user = (User) request.getSession().getAttribute("user");
		article.setUser(user);
		// 调用service层
		ArticleService articleService = new ArticleService();
		boolean result = articleService.addArticle(article);
		// 判断用户是否为空
		if (result) {
//			response.sendRedirect("ListArticleServlet");
			return new ActionForward("/ListArticle.do",false);
		} else {
			request.setAttribute("errorInfo", "发表文章失败");
//			request.getRequestDispatcher("addArticle.jsp").forward(request,
//					response);
			return new ActionForward("/addArticle.jsp", true);
		}
	}

}
