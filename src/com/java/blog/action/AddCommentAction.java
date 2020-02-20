/**
 *ʱ�䣺2014-7-15����11:16:39
 *
 *���ߣ��Ź���
 *���ܣ�TODO
 */
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
		// 1�����ñ���
		request.setCharacterEncoding("utf-8");
		// response.setHeader("content-type","text/html;charset=UTF-8");
		// 2����ȡ����
		String articleId = request.getParameter("articleId");
		String content = request.getParameter("content");
		// 3����֤����
		if (null == content || content.trim().length() == 0) {
			request.setAttribute("errorInfo", "���۵����ݲ���Ϊ�ա�");
			// request.getRequestDispatcher("detailArticle.jsp").forward(
			// request, response);
			// return; // һ��Ҫ�У�����������ִ�У�������
			return new ActionForward("/detailArticle.jsp", true);
		}
		// 4������Service��ķ�������ҵ��
		ArticleService articleService = new ArticleService();
		CommentService commentService = new CommentService();
		// newһ�����۶���
		Comment comment = new Comment();
		comment.setContent(content.replace("\n", "<br/>"));
		comment.setPostTime(new Date());
		// ��session��ȡ��user ����������
		User user = (User) request.getSession().getAttribute("user");
		comment.setUser(user);

		Article article = articleService.findArticleById(Integer
				.parseInt(articleId));
		comment.setArticle(article);

		boolean result = commentService.addComment(comment);
		// 5���жϽ������ת
		if (result) {
			// response.sendRedirect("DetailArticleServlet?articleId="
			// + articleId);
			return new ActionForward("/DetailArticle.do?articleId="
					+ articleId, false);
		} else {
			request.setAttribute("errorInfo", "�������ʧ�ܡ�");
			// request.getRequestDispatcher("detailArticle.jsp").forward(
			// request, response);
			return new ActionForward("/detailArticle.jsp", true);
		}

	}

}
