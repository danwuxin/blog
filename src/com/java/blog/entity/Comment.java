/**
 *ʱ�䣺2014-4-20����9:20:48
 *����ʵ����
 *���ߣ��Ź���
 *���ܣ�TODO
 */
package com.java.blog.entity;

import java.util.Date;

public class Comment {
    //����id  int����
	private int commentId;
	//��������  
	private String content;
    //����ʱ��
	private Date postTime;
	//�����۵�����
	private Article article;
	// ������
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
