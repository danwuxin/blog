/**
*ʱ�䣺2014-4-26����11:47:50
*
*���ߣ��Ź���
*���ܣ�Article Voʵ����
*/
package com.java.blog.vo;


import com.java.blog.entity.Article;

public class ArticleVo {
    //���¶��� 
	private Article article;
	//����������
	private int commentCount;
	
	
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
}
