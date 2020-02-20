/**
 *ʱ�䣺2014-7-15����11:46:34
 *
 *���ߣ��Ź���
 *���ܣ�TODO
 */
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

		// �����ַ���
		// response.setHeader("content-type","text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		// ��������
		String articleid = request.getParameter("articleid");
		String contents = request.getParameter("contents");
		String ArticleTitle = request.getParameter("ArticleTitle");
		// ��֤��������
		if (null == ArticleTitle || ArticleTitle.trim().length() == 0) {
			request.setAttribute("errorInfo", "���±��ⲻ��Ϊ�ա�");
			// request.getRequestDispatcher("addArticle.jsp").forward(request,
			// response);
			// return;
			return new ActionForward("/addArticle.jsp", true);
		}
		// ��֤��������

		if (null == contents || contents.trim().length() == 0) {
			request.setAttribute("errorInfo", "�������ݲ���Ϊ�ա�");
			// request.getRequestDispatcher("addArticle.jsp").forward(request,
			// response);
			// return;
			return new ActionForward("/addArticle.jsp", true);
		}

		// ����ҵ���
		ArticleService articleService = new ArticleService();

		Article article = new Article();
		// ����Ҫ���µ�����
		article.setTitle(ArticleTitle);
		article.setContents(contents.replace("\n", "<br/>"));
		article.setArticleId(Integer.parseInt(articleid));
		boolean result = articleService.updateArticle(article);

		if (result) {
			// response.sendRedirect("/ListArticleServlet");
			return new ActionForward("/ListArticle.do", false);
		} else {
			request.setAttribute("errorInfo", "��������ʧ�ܡ�");
			// request.getRequestDispatcher("updateArticle.jsp").forward(request,
			// response);
			return new ActionForward("/updateArticle.jsp", true);
		}

	}

}
