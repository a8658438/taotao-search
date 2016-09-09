package com.taotao.search.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;

/**
 * 商品管理controller
 * @author Administrator
 *
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	/**
	 * 导入所有的商品数据到solr
	 * @return
	 */
	@RequestMapping("/manager/importItemAll")
	@ResponseBody
	public TaotaoResult importItemAll(){
		return itemService.importItemList();
	}
	/**
	 * 同步一条商品数据到solr
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/manager/importItemOne",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult importItemOne(Long cid,Item item) throws UnsupportedEncodingException{
		System.out.println(111);
		return itemService.addItem(item,cid);
	}
	
	/**
	 * 删除solr中的数据
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/manager/delItem",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult removeItem(String[] ids){
		System.out.println(11);
		return itemService.removeItem(ids);
	}
}
