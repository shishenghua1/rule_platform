package com.boco.eoms.rule.cwmsysruleconfig.model;

/**
 * 规则信息
 * @author chenjianghe
 *
 */
public class RuleInfo {
	
	/**
	 * 规则id
	 */
	private String ruleId;
	
	/**
	 * 规则内容
	 */
	private String content;

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
