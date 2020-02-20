/**
*ʱ�䣺2014-4-20����9:26:13
*
*���ߣ��Ź���
*���ܣ�TODO
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
	 * ��ʾĳƪ���µ�����
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

	// ��rsת����Comment����ķ���
	private Comment rs2Comment(ResultSet rs) throws SQLException {
		Comment comment = new Comment();

		comment.setCommentId(rs.getInt("commentId")); 
		comment.setContent(rs.getString("Content"));
		comment.setPostTime(rs.getTimestamp("PostTime"));

		// ��UserDao�л�ȡuser����
		int user_Id = rs.getInt("UserId");
		User user = new UserDao().findUserById(user_Id);
		comment.setUser(user);
		// ��ArticleDao�л�ȡarticle����
		int articleId = rs.getInt("ArticleId");
		Article article = new ArticleDao().findByArticleId(articleId);
		comment.setArticle(article);

		return comment;
	}
	/**
	 * ������������
	 * sql�������comments���в���һ�������
	 * */
	public int insert(Comment comment) {
		String sql = "insert into comments(Content,PostTime,ArticleId,UserId) values(?,?,?,?)";
		return new DBhelper().executeSql(sql, comment.getContent(), comment
				.getPostTime(), comment.getArticle().getArticleId(), comment.getUser()
				.getUserid());
	}
}
