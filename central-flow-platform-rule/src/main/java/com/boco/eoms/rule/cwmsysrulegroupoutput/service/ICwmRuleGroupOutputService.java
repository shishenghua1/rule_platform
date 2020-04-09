package com.boco.eoms.rule.cwmsysrulegroupoutput.service;

import com.boco.eoms.rule.cwmsysrulegroupoutput.model.CwmRuleGroupOutput;

public interface ICwmRuleGroupOutputService {

	/**
	 * 删除规则集合输出
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);
    /**
     * 插入规则集合输出
     * @param record
     * @return
     */
    int insert(CwmRuleGroupOutput record);
    /**
     * 查询规则集合输出
     * @param id
     * @return
     */
    CwmRuleGroupOutput selectByPrimaryKey(String id);
    /**
     * 更新规则集合输出
     * @param record
     * @return
     */
    int updateByPrimaryKey(CwmRuleGroupOutput record);
}
