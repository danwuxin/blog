/**
*ʱ�䣺2014-7-16����12:06:17
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



public class FindArticleAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 1����ȡҪ�޸ĵ�����Id
				int articleId = Integer.parseInt(request.getParameter("articleId"));
				// 2����ȡҪ�޸ĵ����²����浽request��
				ArticleService articleService = new ArticleService();
				Article article = articleService.findArticleById(articleId);
				article.setContents(article.getContents().replace("<br/>", "\n"));
				request.setAttribute("article", article);
				// 3����ת���޸����µ�ҳ��
//				request.getRequestDispatcher("updateArticle.jsp").forward(request,
//						response);
				return new ActionForward("updateArticle.jsp", true);

			}

}
