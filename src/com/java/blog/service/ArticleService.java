/**
 *ʱ�䣺2014-4-20����3:07:11
 *
 *���ߣ��Ź���
 *���ܣ�TODO
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
     * ��������ҵ��
     * ���������µĵ����0�ͷ���ʱ�䣨�Զ���ȡ��
     * */
	public boolean addArticle(Article article) {
		article.setClicks(0);
		article.setPostTime(new Date());
		int result = articleDao.insert(article);
		//����ɹ�  �����µ�ҵ��
		if (result > 0) {
			// ��Ӹ��ӵ�ҵ�񣬸��������µ��û����ӻ���
			User originalUser = userDao.findUserById(article.getUser()
					.getUserid());
			//�����û��������º�Ƿֵ�����
			originalUser.setIntegrals(originalUser.getIntegrals() + 5);
			userDao.updateUser(originalUser);
		}
		return (result > 0);
	}
    /**
     * �������߲�ѯ�������߷������������ҵ��
     * */
	public List<Article> findByUserId(int userid) {
		return articleDao.findByUserId(userid);

	}

	public boolean deleteArticleById(int articleId) {
		// 1��Ҫ��ɾ�������µ�����
		int resultComment = commentDao.deleteByArticleId(articleId);
		// 2��Ȼ����ɾ��������
		int resultArticle = articleDao.deleteById(articleId);
		return (resultComment >= 0 && resultArticle > 0);
	}
      /**
       * ��������id��ѯ����ҵ��
       * */
	public Article findArticleById(int articleId) {
		return articleDao.findByArticleId(articleId);
	}
    /**
     * ��������ҵ��
     * */
	public boolean updateArticle(Article article) {
		// ����ʱ��������ݲ���ȫ��ֻ�����ݺͱ�������޸�
		// 1������������Id�������ݿ���ԭ����
		Article originalArticle = articleDao.findByArticleId(article
				.getArticleId());
		originalArticle.setTitle(article.getTitle());
		originalArticle.setContents(article.getContents());
		int result = articleDao.update(originalArticle);
		return (result > 0);

	}
    //��ѯ��������ҵ��
	public List<ArticleVo> getAllArticleVo() {
		return articleDao.findAll();
	}

	// ��ȡĳ���û����е���ʾ�ڽ����е�VO�б�
	public List<ArticleVo> getArticleVoByUserId(int userId) {
		return articleDao.findArticleVoByUserId(userId);
	}

	public ArticleVo findArticleDetailById(int articleId) {
		// ���µĵ������1
		Article originalArticle = articleDao.findByArticleId(articleId);
		originalArticle.setClicks(originalArticle.getClicks() + 1);
		articleDao.update(originalArticle);

		return articleDao.findArticleVoById(articleId);
	}

	/**
	 * ��ҳ����
	 * 
	 * */
	public PaginationData getArticleVoByPaging(int pageIndex) {
		// 1����ȡ�ܼ�¼��
		int recordCount = articleDao.getArticleCount();
		// 2����ȡ��ҳҪ��ʾ������
		List<ArticleVo> list = articleDao.getArticleByPaging(pageIndex);
		// 3��������ҳ����
		int pageCount = recordCount / IConstant.PAGE_SIZE;
		// ==0�ǲ���һҳ ���������ǲ���PAGE_SIZEʱ��һҳ
		if (pageCount == 0 || recordCount % IConstant.PAGE_SIZE != 0) {
			pageCount++;
		}
		// 4������Pagination���󲢷���
		PaginationData paginationData = new PaginationData();
		// �������
		paginationData.setData(list);
		paginationData.setPageCount(pageCount);
		paginationData.setPageIndex(pageIndex);
		paginationData.setPageSize(IConstant.PAGE_SIZE);
		paginationData.setRecordCount(recordCount);

		return paginationData;
	}
	public PaginationData getUserArticleVoByPaging(int pageIndex, int userId) {
		// 1����ȡ�ܼ�¼��
		int recordCount = articleDao.getArticleCount(userId);
		// 2����ȡ��ҳҪ��ʾ������
		List<ArticleVo> list = articleDao.getArticleByPaging(pageIndex, userId);
		// 3��������ҳ����
		int pageCount = recordCount / IConstant.PAGE_SIZE;
		if (pageCount == 0 || recordCount % IConstant.PAGE_SIZE != 0) {
			pageCount++;
		}
		// 4������Pagination���󲢷���
		PaginationData paginationData = new PaginationData();
		// �������
		paginationData.setData(list);
		paginationData.setPageCount(pageCount);
		paginationData.setPageIndex(pageIndex);
		paginationData.setPageSize(IConstant.PAGE_SIZE);
		paginationData.setRecordCount(recordCount);

		return paginationData;
	}
}
