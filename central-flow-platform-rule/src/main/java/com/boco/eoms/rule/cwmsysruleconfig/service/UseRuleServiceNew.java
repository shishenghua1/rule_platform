package com.boco.eoms.rule.cwmsysruleconfig.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface UseRuleServiceNew {

	/**
	 * 执行规则
	 * @param map
	 * @param ruleId
	 * @return
	 */
	public JSONObject excuteRule(Map<String,Object> map,String ruleId);
	
	/**
	 * 多线程执行规则 （仿真功能）
	 * @param ruleId
	 * @param simulationId
	 * @param isLastGateWay
	 * @param excuteList
	 * @return
	 */
	public JSONObject asyncExcuteRule(String ruleId,String simulationId,String isLastGateWay,List<Object> excuteList);
}
