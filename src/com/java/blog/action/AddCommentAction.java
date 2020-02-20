package com.java.blog.action;

import com.java.blog.entity.Article;
import com.java.blog.entity.Comment;
import com.java.blog.entity.User;
import com.java.blog.service.ArticleService;
import com.java.blog.service.CommentService;
import com.java.mvc.Action;
import com.java.mvc.ActionForward;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class AddCommentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 1、设置编码
		request.setCharacterEncoding("utf-8");
		// response.setHeader("content-type","text/html;charset=UTF-8");
		// 2、获取数据
		String articleId = request.getParameter("articleId");
		String content = request.getParameter("content");
		// 3、验证数据
		if (null == content || content.trim().length() == 0) {
			request.setAttribute("errorInfo", "评论的内容不能为空。");
			// request.getRequestDispatcher("detailArticle.jsp").forward(
			// request, response);
			// return; // 一定要有，否则后面继续执行，将报错
			return new ActionForward("/detailArticle.jsp", true);
		}
		// 4、调用Service层的方法处理业务
		ArticleService articleService = new ArticleService();
		CommentService commentService = new CommentService();
		// new一个评论对象
		Comment comment = new Comment();
		comment.setContent(content.replace("\n", "<br/>"));
		comment.setPostTime(new Date());
		// 从session中取出user 用于评论人
		User user = (User) request.getSession().getAttribute("user");
		comment.setUser(user);

		Article article = articleService.findArticleById(Integer
				.parseInt(articleId));
		comment.setArticle(article);

		boolean result = commentService.addComment(comment);
		// 5、判断结果并跳转
		if (result) {
			// response.sendRedirect("DetailArticleServlet?articleId="
			// + articleId);
			return new ActionForward("/DetailArticle.do?articleId="
					+ articleId, false);
		} else {
			request.setAttribute("errorInfo", "添加评论失败。");
			// request.getRequestDispatcher("detailArticle.jsp").forward(
			// request, response);
			return new ActionForward("/detailArticle.jsp", true);
		}

	}

}
