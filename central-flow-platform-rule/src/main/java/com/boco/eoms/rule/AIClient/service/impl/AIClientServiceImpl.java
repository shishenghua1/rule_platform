package com.boco.eoms.rule.AIClient.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.base.config.AIServerProps;
import com.boco.eoms.base.util.HttpClientServlet;
import com.boco.eoms.rule.AIClient.service.IAIClientService;

@Service
public class AIClientServiceImpl implements IAIClientService{
	
	@Autowired
	private AIServerProps aIServerProps;

	/**
	 * AI错别字检查
	 * @param check_content 待检查的文本
	 * @param check_type 错别字类型。（1-确定错别字；2-疑似错别字；3-全部）默认为3。
	 * @return
	 */
	@Override
	public JSONObject check(String check_content, String check_type) {
		String check_url = "http://"+aIServerProps.getIp()+":"+aIServerProps.getPort()+"/aicheck/check/";
		JSONObject propertyObj = new JSONObject();
		check_type = ("".equals(check_type))?"3":check_type;
		propertyObj.put("CHECK_CONTENT", check_content);
		propertyObj.put("CHECK_TYPE", check_type);
		String entityStr = HttpClientServlet.httpPostRaw(check_url,propertyObj.toString(),null,null);
		JSONObject entity = JSON.parseObject(entityStr);
		return entity;
	}

	/**
	 * AI相似度检查
	 * @param check_content 待检查的文本
	 * @param check_template 匹配的模板文本
	 * @return
	 */
	@Override
	public JSONObject similar(String check_content, String check_template) {
		String similar_url = "http://"+aIServerProps.getIp()+":"+aIServerProps.getPort()+"/aicheck/similar/";
		JSONObject propertyObj = new JSONObject();
		propertyObj.put("CHECK_CONTENT", check_content);
		propertyObj.put("CHECK_TEMPLATE", check_template);
		String entityStr = HttpClientServlet.httpPostRaw(similar_url,propertyObj.toString(),null,null);
		//AI相似度接口不准确 会有返回NaN的情况 导致转换JSONObject会报错 提前处理 这种情况统一处理为相似度为0
		if(entityStr.contains("NaN")) {
			entityStr.replace("NaN", "0");
		}
		JSONObject entity = JSON.parseObject(entityStr);
		return entity;
	}
	

}
