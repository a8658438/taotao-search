package com.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;

/**
 * solr搜索服务实现类
 * @author Administrator
 *
 */
@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	private SearchDao searchDao;
	
	@Override
	public TaotaoResult search(String query, int page, int rows) throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		//设置搜索数据
		solrQuery.setQuery(query);//设置查询条件
		solrQuery.setStart((page-1) * rows);
		solrQuery.setRows(rows);
		solrQuery.set("df", "item_keywords");//设置默认搜索域
		
		useHighLight(solrQuery,"item_title");//启动高亮
		
		//查询数据
		SearchResult result = searchDao.search(solrQuery);
		result.setCurPage(page);
		
		return TaotaoResult.ok(result);
	}

	/**
	 * 设置高亮
	 * @param solrQuery
	 * @param field
	 */
	private void useHighLight(SolrQuery solrQuery,String field) {
		//设置高亮数据
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField(field);//设置高亮字段
		solrQuery.setHighlightSimplePre("<em style=\"color:red;\">");
		solrQuery.setHighlightSimplePost("</em>");
	}

}
