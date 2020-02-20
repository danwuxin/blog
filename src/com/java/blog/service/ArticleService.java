/**
 *时间：2014-4-20下午3:07:11
 *
 *作者：张国宝
 *功能：TODO
 */
package com.java.blog.service;

import com.java.blog.dao.ArticleDao;
import com.java.blog.dao.CommentDao;
import com.java.blog.dao.UserDao;
import com.java.blog.entity.Article;
import com.java.blog.entity.User;
import com.java.blog.util.IConstant;
import com.java.blog.util.PaginationData;
import com.java.blog.vo.ArticleVo;

import java.util.Date;
import java.util.List;



public class ArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private CommentDao commentDao = new CommentDao();
	private UserDao userDao = new UserDao();
	/**
	 * 增加文章业务
	 * 先设置文章的点击数0和发表时间（自动获取）
	 * */
	public boolean addArticle(Article article) {
		article.setClicks(0);
		article.setPostTime(new Date());
		int result = articleDao.insert(article);
		//插入成功  增加新的业务
		if (result > 0) {
			// 添加附加的业务，给发表文章的用户增加积分
			User originalUser = userDao.findUserById(article.getUser()
					.getUserid());
			//设置用户发表文章后记分的增加
			originalUser.setIntegrals(originalUser.getIntegrals() + 5);
			userDao.updateUser(originalUser);
		}
		return (result > 0);
	}
	/**
	 * 根据作者查询出该作者发表的所有文章业务
	 * */
	public List<Article> findByUserId(int userid) {
		return articleDao.findByUserId(userid);

	}

	public boolean deleteArticleById(int articleId) {
		// 1、要先删除该文章的评论
		int resultComment = commentDao.deleteByArticleId(articleId);
		// 2、然后再删除该文章
		int resultArticle = articleDao.deleteById(articleId);
		return (resultComment >= 0 && resultArticle > 0);
	}
	/**
	 * 根据文正id查询文章业务
	 * */
	public Article findArticleById(int articleId) {
		return articleDao.findByArticleId(articleId);
	}
	/**
	 * 更新文章业务
	 * */
	public boolean updateArticle(Article article) {
		// 更新时传入的数据不完全，只有内容和标题可以修改
		// 1、按传入对象的Id查找数据库中原对象
		Article originalArticle = articleDao.findByArticleId(article
				.getArticleId());
		originalArticle.setTitle(article.getTitle());
		originalArticle.setContents(article.getContents());
		int result = articleDao.update(originalArticle);
		return (result > 0);

	}
	//查询所有文章业务
	public List<ArticleVo> getAllArticleVo() {
		return articleDao.findAll();
	}

	// 获取某个用户所有的显示在界面中的VO列表
	public List<ArticleVo> getArticleVoByUserId(int userId) {
		return articleDao.findArticleVoByUserId(userId);
	}

	public ArticleVo findArticleDetailById(int articleId) {
		// 文章的点击数加1
		Article originalArticle = articleDao.findByArticleId(articleId);
		originalArticle.setClicks(originalArticle.getClicks() + 1);
		articleDao.update(originalArticle);

		return articleDao.findArticleVoById(articleId);
	}

	/**
	 * 分页功能
	 *
	 * */
	public PaginationData getArticleVoByPaging(int pageIndex) {
		// 1、获取总记录数
		int recordCount = articleDao.getArticleCount();
		// 2、获取分页要显示的数据
		List<ArticleVo> list = articleDao.getArticleByPaging(pageIndex);
		// 3、计算总页面数
		int pageCount = recordCount / IConstant.PAGE_SIZE;
		// ==0是不够一页 不能整除是不满PAGE_SIZE时用一页
		if (pageCount == 0 || recordCount % IConstant.PAGE_SIZE != 0) {
			pageCount++;
		}
		// 4、构造Pagination对象并返回
		PaginationData paginationData = new PaginationData();
		// 填充数据
		paginationData.setData(list);
		paginationData.setPageCount(pageCount);
		paginationData.setPageIndex(pageIndex);
		paginationData.setPageSize(IConstant.PAGE_SIZE);
		paginationData.setRecordCount(recordCount);

		return paginationData;
	}
	public PaginationData getUserArticleVoByPaging(int pageIndex, int userId) {
		// 1、获取总记录数
		int recordCount = articleDao.getArticleCount(userId);
		// 2、获取分页要显示的数据
		List<ArticleVo> list = articleDao.getArticleByPaging(pageIndex, userId);
		// 3、计算总页面数
		int pageCount = recordCount / IConstant.PAGE_SIZE;
		if (pageCount == 0 || recordCount % IConstant.PAGE_SIZE != 0) {
			pageCount++;
		}
		// 4、构造Pagination对象并返回
		PaginationData paginationData = new PaginationData();
		// 填充数据
		paginationData.setData(list);
		paginationData.setPageCount(pageCount);
		paginationData.setPageIndex(pageIndex);
		paginationData.setPageSize(IConstant.PAGE_SIZE);
		paginationData.setRecordCount(recordCount);

		return paginationData;
	}
}
