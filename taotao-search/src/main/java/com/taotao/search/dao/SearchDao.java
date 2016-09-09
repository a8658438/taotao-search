package com.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;

/**
 * solr搜索dao
 * @author Administrator
 *
 */
public interface SearchDao {
	/**
	 * 根据solr查询条件进行数据检索
	 * @param solrQuery
	 * @return
	 */
	SearchResult search(SolrQuery solrQuery)  throws Exception;
}
