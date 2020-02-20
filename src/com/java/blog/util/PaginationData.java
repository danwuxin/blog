/**
 *时间：2014-4-30上午12:30:36
 *
 *作者：张国宝
 *功能：保存分页的数据
 */
package com.java.blog.util;

import com.java.blog.vo.ArticleVo;

import java.util.List;


public class PaginationData {
	// 分页显示的数据，条数不超过页面大小
	private List<ArticleVo> data;
	// 总记录数
	private int recordCount;
	// 页面大小
	private int pageSize;
	// 当前页面号（从1开始）
	private int pageIndex;
	// 总页数
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
