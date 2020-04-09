package com.boco.eoms.rule.cwmsysruleconfig.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface UseRuleService {

	/**
	 * 执行规则
	 * @param map
	 * @param ruleId
	 * @return
	 */
	public JSONObject excuteRule(Map<String,Object> map,String ruleId);
}
