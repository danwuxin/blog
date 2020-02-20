/**
 *时间：2014-4-27下午3:25:29
 *
 *作者：张国宝
 *功能：TODO
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
	 * 查询某篇文章的评论业务
	 * */
	public List<Comment> findCommentByArticleId(int articleId){
		return commentDao.findByArticleId(articleId);
	}
	/**
	 * 增加文章评论业务
	 * */
	public boolean addComment(Comment comment) {
		boolean result = commentDao.insert(comment)> 0;
		if (result) {
			// 添加附加的业务，给发表评论的用户增加积分
			User originalUser = userDao.findUserById(comment.getUser().getUserid());
			originalUser.setIntegrals(originalUser.getIntegrals() + 1);
			userDao.updateUser(originalUser);
		}
		return result;
	}
}
