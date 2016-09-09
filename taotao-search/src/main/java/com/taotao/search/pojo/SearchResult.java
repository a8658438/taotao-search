package com.taotao.search.pojo;

import java.util.List;

/**
 * solr检索返回结果
 * @author Administrator
 *
 */
public class SearchResult {
	private List<?> dataList;//数据集合
	private long dataCount;//数据总数
	private long curPage;//当前页数
	private long pageCount;//总页数
	
	public SearchResult(List<?> dataList, long dataCount, long curPage,
			long pageCount) {
		super();
		this.dataList = dataList;
		this.dataCount = dataCount;
		this.curPage = curPage;
		this.pageCount = pageCount;
	}
	public SearchResult() {
		super();
	}
	public List<?> getDataList() {
		return dataList;
	}
	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}
	public long getDataCount() {
		return dataCount;
	}
	public void setDataCount(long dataCount) {
		this.dataCount = dataCount;
	}
	public long getCurPage() {
		return curPage;
	}
	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}
	public long getPageCount() {
		pageCount = 0;
		if (dataList != null && dataList.size() != 0) {
			pageCount = dataCount/dataList.size();
			
			//判断是否有余数
			if (dataCount%dataList.size()>0) {
				pageCount ++;
			}
		}
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	
	
}
