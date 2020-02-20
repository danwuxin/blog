/**
 *ʱ�䣺2014-7-15����9:02:22
 *
 *���ߣ��Ź���
 *���ܣ�TODO
 */
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
		// �����ַ���
		request.setCharacterEncoding("utf-8");
		// �������� ���ݺͱ���
		String contents = request.getParameter("contents");
		String ArticleTitle = request.getParameter("ArticleTitle");
		// ��֤��������
		if (null == ArticleTitle || ArticleTitle.trim().length() == 0) {
			request.setAttribute("errorInfo", "���±��ⲻ��Ϊ�ա�");
//			request.getRequestDispatcher("addArticle.jsp").forward(request,
//					response);
//			return;
			return new ActionForward("/addArticle.jsp", true);
		}
		// ��֤��������

		if (null == contents || contents.trim().length() == 0) {
			request.setAttribute("errorInfo", "�������ݲ���Ϊ�ա�");
//			request.getRequestDispatcher("addArticle.jsp").forward(request,
//					response);
//			return;
			return new ActionForward("/addArticle.jsp", true);
		}
		// new һ�����¶��� ��������Ϣ���浽article������
		Article article = new Article();
		article.setTitle(ArticleTitle);
		article.setContents(contents.replace("\n", "<br/>"));
		User user = (User) request.getSession().getAttribute("user");
		article.setUser(user);
		// ����service��
		ArticleService articleService = new ArticleService();
		boolean result = articleService.addArticle(article);
		// �ж��û��Ƿ�Ϊ��
		if (result) {
//			response.sendRedirect("ListArticleServlet");
         return new ActionForward("/ListArticle.do",false);
		} else {
			request.setAttribute("errorInfo", "��������ʧ��");
//			request.getRequestDispatcher("addArticle.jsp").forward(request,
//					response);
			return new ActionForward("/addArticle.jsp", true);
		}
	}

}
