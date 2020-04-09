package com.boco.eoms.rule.cwmsysruleconfig.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface RuleService {

	/**
	 * 根据规则名称获取规则所需传入参数
	 * @param ruleName
	 * @return
	 */
	public JSONObject getRuleParams(String ruleName);
	
	/**
	 * 根据规则名称获取规则输入参数及类型 (返回值为JSON key为字段 value为类型)
	 * @param ruleName
	 * @return
	 */
	public JSONObject getRuleParamsAndType(String ruleName);
	
	/**
	 * 根据规则名称、输入参数处理和检测规则输入参数
	 * @param map
	 * @param ruleName
	 * @return
	 */
	public JSONObject checkAndDealParams(Map<String,Object> map,String ruleName);
	
	/**
	 * AI处理
	 * @param map
	 * @param ruleName
	 * @return
	 */
	public JSONObject AIDeal(Map<String,Object> map,String ruleName);
	
	/**
	 * 获取规则输入参数中英文字段
	 * @param ruleName
	 * @return json key为字段英文名 value为字段中文名
	 */
	public JSONObject getInputParamEnAndCnName(String ruleName);
}
