package com.taotao.search.service;

import java.util.Map;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.search.pojo.Item;

/**
 * mysql商品数据查询
 * @author Administrator
 *
 */
public interface ItemService {
	/**
	 * 查询数据所有的商品数据
	 * @param itemId 
	 * @return
	 */
	TaotaoResult importItemList();

	/**
	 * 添加商品信息到solr
	 * @param item
	 * @param cid
	 * @return
	 */
	TaotaoResult addItem(Item item, Long cid);

	/**
	 * 删除solr中的商品数据
	 * @param ids
	 * @return
	 */
	TaotaoResult removeItem(String[] ids);
}
