package com.java.blog.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.blog.entity.Comment;
import com.java.blog.service.ArticleService;
import com.java.blog.service.CommentService;
import com.java.blog.vo.ArticleVo;
import com.java.mvc.Action;
import com.java.mvc.ActionForward;


public class DetailArticleAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int articleId = Integer.parseInt(request.getParameter("articleId"));

		ArticleService articleService = new ArticleService();
		ArticleVo articleVo = articleService.findArticleDetailById(articleId);
		articleVo.getArticle().setContents(
				articleVo.getArticle().getContents().replace("<br/>", "\n"));
		request.setAttribute("articleVo", articleVo);
		CommentService commentService = new CommentService();
		List<Comment> comments = commentService
				.findCommentByArticleId(articleId);
		request.setAttribute("comments", comments);

//		request.getRequestDispatcher("detailArticle.jsp").forward(request,
//				response);
		return new ActionForward("/detailArticle.jsp", true);

	}
}
