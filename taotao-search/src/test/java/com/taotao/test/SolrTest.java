package com.taotao.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrTest {
	/**
	 * 需要使用jdk1.7
	 * @throws SolrServerException
	 * @throws IOException
	 */
	@Test
	public void add() throws SolrServerException, IOException{
		HttpSolrServer server = new HttpSolrServer("http://192.168.21.132:8080/solr");
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", "123");
		doc.addField("item_title", "哈哈哈哈");
		server.add(doc);
		server.commit();
	}
}
