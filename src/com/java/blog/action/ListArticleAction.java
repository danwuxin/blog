/**
*ʱ�䣺2014-7-16����12:11:07
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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class ListArticleAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//�����ݿ��в�ѯ��½�û�����������
		User user = (User)request.getSession().getAttribute("user");
		ArticleService articleService = new ArticleService();

		List<Article> articles = articleService.findByUserId(user.getUserid());
		//���ҵĽ�����浽Request��
		request.setAttribute("articles", articles);		
		// ҳ����ת
//		request.getRequestDispatcher("listArticle.jsp").forward(request,
//				response);
		return new ActionForward("/listArticle.jsp", true);
	
	}

}
