/**
*ʱ�䣺2014-4-27����3:25:29
*
*���ߣ��Ź���
*���ܣ�TODO
*/
package com.java.blog.service;

import com.java.blog.dao.CommentDao;
import com.java.blog.dao.UserDao;
import com.java.blog.entity.Comment;
import com.java.blog.entity.User;

import java.util.List;



public class CommentService {
	private CommentDao commentDao = new CommentDao();
	private UserDao userDao = new UserDao();
	/**
	 * ��ѯĳƪ���µ�����ҵ��
	 * */
	public List<Comment> findCommentByArticleId(int articleId){
		return commentDao.findByArticleId(articleId);
	}
	/**
	 * ������������ҵ��
	 * */
	public boolean addComment(Comment comment) {
		boolean result = commentDao.insert(comment)> 0;
		if (result) {
			// ��Ӹ��ӵ�ҵ�񣬸��������۵��û����ӻ���
			User originalUser = userDao.findUserById(comment.getUser().getUserid());
			originalUser.setIntegrals(originalUser.getIntegrals() + 1);
			userDao.updateUser(originalUser);
		}
		return result;
	}
}
