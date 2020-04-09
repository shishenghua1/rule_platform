package com.boco.eoms.rule.cwmsysruleconfig.service;

import java.util.List;

import com.boco.eoms.rule.cwmsysruleconfig.model.RuleInfo;
import com.boco.eoms.rule.cwmsysrulegroups.model.CwmRuleGroups;

/**
 * 规则信息service
 * @author chenjianghe
 *
 */
public interface RuleInfoServiceNew {
	
	/**
	 * 获取所有规则信息
	 * @return
	 */
	public List<RuleInfo> getAllRule();
	
	/**
     * 获取所有非草稿的规则集合
     * @return
     */
    public List<RuleInfo> getAllRuleGroupsNonDraft();
    
    /**
     * 获取所有原子规则集合
     * @return
     */
    public List<RuleInfo> getAllAtoms();
	
	/**
	 * 根据规则id获取规则信息
	 * @param ruleId
	 * @return
	 */
	public List<RuleInfo> getRuleInfoByRuleId(String ruleId);
}
