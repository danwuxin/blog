/**
 *ʱ�䣺2014-4-30����12:30:36
 *
 *���ߣ��Ź���
 *���ܣ������ҳ������
 */
package com.java.blog.util;

import com.java.blog.vo.ArticleVo;

import java.util.List;



public class PaginationData {
	// ��ҳ��ʾ�����ݣ�����������ҳ���С
	private List<ArticleVo> data;
	// �ܼ�¼��
	private int recordCount;
	// ҳ���С
	private int pageSize;
	// ��ǰҳ��ţ���1��ʼ��
	private int pageIndex;
	// ��ҳ��
	private int pageCount;
	public List<ArticleVo> getData() {
		return data;
	}
	public void setData(List<ArticleVo> data) {
		this.data = data;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	
}
