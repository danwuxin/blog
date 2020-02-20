/**
 *时间：2014-4-20下午9:20:48
 *评论实体类
 *作者：张国宝
 *功能：TODO
 */
package com.java.blog.entity;



import java.util.Date;

public class Comment {
	//评论id  int类型
	private int commentId;
	//评论内容
	private String content;
	//评论时间
	private Date postTime;
	//被评论的文章
	private Article article;
	// 评论人
	private User user;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
