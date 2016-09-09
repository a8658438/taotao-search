package com.taotao.search.dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.util.SolrDocumentUtil;

/**
 * solr搜索实现类
 * @author Administrator
 *
 */
@Repository
public class SearchDaoImpl implements SearchDao {
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public SearchResult search(SolrQuery solrQuery) throws Exception {
		//进行查询
		QueryResponse response = solrServer.query(solrQuery);
		//获取返回的docment数据
		SolrDocumentList documentList = response.getResults();
		//取高亮展示
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		//进行数据转换
		List<Item> itemList = SolrDocumentUtil.docListToItemList(documentList,highlighting);
		
		//创建返回值对象
		SearchResult result = new SearchResult();
		result.setDataList(itemList);
		result.setDataCount(documentList.getNumFound());//获取搜索结果总量
		return result;
	}

}
