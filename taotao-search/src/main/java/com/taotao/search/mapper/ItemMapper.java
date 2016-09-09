package com.taotao.search.mapper;

import java.util.List;
import java.util.Map;

import com.taotao.search.pojo.Item;

/**
 * ItemMapper接口
 * @author Administrator
 *
 */
public interface ItemMapper {
	/**
	 * 查询商品数量集合
	 * @param itemId
	 * @return
	 */
	List<Item> getItemList();
}
