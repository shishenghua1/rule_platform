package com.boco.eoms.rule.AIClient.service;

import com.alibaba.fastjson.JSONObject;

public interface IAIClientService {
	
	/**
	 * AI错别字检查
	 * @param check_content 待检查的文本
	 * @param check_type 错别字类型。（1-确定错别字；2-疑似错别字；3-全部）默认为3。
	 * @return
	 */
	public JSONObject check(String check_content,String check_type);
	
	/**
	 * AI相似度检查
	 * @param check_content 待检查的文本
	 * @param check_template 匹配的模板文本
	 * @return
	 */
	public JSONObject similar(String check_content,String check_template);

}
