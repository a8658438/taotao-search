package com.taotao.search.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.taotao.search.pojo.Item;



/**
 * solr类转换工具
 * @author Administrator
 *
 */
public class SolrDocumentUtil {
	/**
	 * 商品信息集合转换成对应的文档对象
	 * @param itemList
	 * @return
	 */
	public static List<SolrInputDocument> ItemListToDocList(List<Item> itemList){
		List<SolrInputDocument> list = new ArrayList<SolrInputDocument>();
		for (Item item : itemList) {
			list.add(ItemToDoc(item));
		}
		return list;
	}

	/**
	 * 单个商品信息转换成对应的文档对象
	 * @param item
	 */
	public static SolrInputDocument ItemToDoc(Item item) {
		SolrInputDocument document = new SolrInputDocument();
		document.setField("id", item.getId());
		document.setField("item_title", item.getTitle());
		document.setField("item_sell_point", item.getSell_point());
		document.setField("item_price", item.getPrice());
		document.setField("item_image", item.getImage());
		document.setField("item_category_name", item.getCategory_name());
		document.setField("item_desc", item.getItem_desc());
		return document;
	}
	
	/**
	 * 单个文档对象转换为商品信息对象
	 * @param highlighting 
	 * @return
	 */
	public static Item docToItem(SolrDocument solrDocument, Map<String, Map<String, List<String>>> highlighting){
		//取得高亮的标题
		List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
		String title = list != null && list.size() > 0 ? list.get(0) : (String) solrDocument.get("item_title");
		
		//创建一商品对象
		Item item = new Item();
		item.setId((String) solrDocument.get("id"));
		item.setTitle(title);
		item.setImage((String) solrDocument.get("item_image"));
		item.setPrice((Long) solrDocument.get("item_price"));
		item.setSell_point((String) solrDocument.get("item_sell_point"));
		item.setCategory_name((String) solrDocument.get("item_category_name"));
		return item;
	}
	
	/**
	 * 文档集合对象转换为商品信息对象List
	 * @return
	 */
	public static List<Item> docListToItemList(SolrDocumentList docList,Map<String, Map<String, List<String>>> highlighting){
		List<Item> list = new ArrayList<Item>();
		for (SolrDocument solrDocument : docList) {
			list.add(docToItem(solrDocument,highlighting));
		}
		return list;
	}
}
