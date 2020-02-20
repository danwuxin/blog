/**
*时间：2014-4-20下午9:26:13
*
*作者：张国宝
*功能：TODO
*/
package com.java.blog.dao;

import com.java.blog.db.DBhelper;
import com.java.blog.entity.Article;
import com.java.blog.entity.Comment;
import com.java.blog.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class CommentDao {

	public int deleteByArticleId(int articleId){
		String sql = "delete from comments where ArticleId=?";
		return new DBhelper().executeSql(sql, articleId) ;
		
	}
	/**
	 * 显示某篇文章的评论
	 * */
	public List<Comment> findByArticleId(int articleId) {
		List<Comment> list = new ArrayList<Comment>();
		String sql = "select * from comments where ArticleId=?";
		DBhelper helper = new DBhelper();
		ResultSet rs = helper.executeQuery(sql, articleId);
		try {
			while (rs.next()) {
				Comment comment = rs2Comment(rs);

				list.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		helper.close();
		return list;
	}

	// 把rs转换成Comment对象的方法
	private Comment rs2Comment(ResultSet rs) throws SQLException {
		Comment comment = new Comment();

		comment.setCommentId(rs.getInt("commentId")); 
		comment.setContent(rs.getString("Content"));
		comment.setPostTime(rs.getTimestamp("PostTime"));

		// 从UserDao中获取user对象
		int user_Id = rs.getInt("UserId");
		User user = new UserDao().findUserById(user_Id);
		comment.setUser(user);
		// 从ArticleDao中获取article对象
		int articleId = rs.getInt("ArticleId");
		Article article = new ArticleDao().findByArticleId(articleId);
		comment.setArticle(article);

		return comment;
	}
	/**
	 * 增加文章评论
	 * sql语句是在comments表中插入一条评论语。
	 * */
	public int insert(Comment comment) {
		String sql = "insert into comments(Content,PostTime,ArticleId,UserId) values(?,?,?,?)";
		return new DBhelper().executeSql(sql, comment.getContent(), comment
				.getPostTime(), comment.getArticle().getArticleId(), comment.getUser()
				.getUserid());
	}
}
