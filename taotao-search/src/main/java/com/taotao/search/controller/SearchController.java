package com.taotao.search.controller;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.search.service.SearchService;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;

/**
 * solr搜索服务controller
 * @author Administrator
 *
 */
@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	/**
	 * 分页查询商品
	 * @param query
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/queryItem",method=RequestMethod.GET)
	@ResponseBody
	public TaotaoResult searchItem(@RequestParam("q")String query,
			@RequestParam(defaultValue="1")int page,
			@RequestParam(defaultValue="50")int rows){
		//判断条件
		if (StringUtils.isEmpty(query)) {
			return TaotaoResult.build(400, "查询条件不能为空");
		}
		
		//查询数据
		try {
			query = new String(query.getBytes("ISO8859-1"),"utf8");
			return searchService.search(query, page, rows);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
