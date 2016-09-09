package com.taotao.search.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;
import com.taotao.search.util.SolrDocumentUtil;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private TbItemCatMapper catMapper;
	
	@Autowired 
	private SolrServer solrServer;
	
	@Override
	public TaotaoResult importItemList() {
		try {
			//查询数据库数据
			List<Item> itemList = itemMapper.getItemList();
			//转换数据
			List<SolrInputDocument> docList = SolrDocumentUtil.ItemListToDocList(itemList);
			//添加并提交
			solrServer.add(docList);
			solrServer.commit();
		}catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult addItem(Item item, Long cid) {
		//查询分类数据
		TbItemCat itemCat = catMapper.selectByPrimaryKey(cid);
		item.setCategory_name(itemCat.getName());
		
		try {
			//转换数据
			SolrInputDocument doc = SolrDocumentUtil.ItemToDoc(item);
			//添加并提交
			solrServer.add(doc);
			solrServer.commit();
		}catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult removeItem(String[] ids) {
		try {
			for (String id : ids) {
				solrServer.deleteById(id);
			}
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(400, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}

}
