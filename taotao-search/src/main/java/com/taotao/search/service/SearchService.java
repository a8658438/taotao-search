package com.taotao.search.service;

import com.taotao.common.utils.TaotaoResult;

/**
 * solr搜索服务层
 * @author Administrator
 *
 */
public interface SearchService {
	/**
	 * 分页查询数据
	 * @param query
	 * @param page
	 * @param rows
	 * @return
	 */
	TaotaoResult search(String query,int page,int rows) throws Exception;
}
