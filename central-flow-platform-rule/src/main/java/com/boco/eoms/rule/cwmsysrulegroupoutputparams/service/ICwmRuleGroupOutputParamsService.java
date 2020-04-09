package com.boco.eoms.rule.cwmsysrulegroupoutputparams.service;

import com.boco.eoms.rule.cwmsysrulegroupoutputparams.model.CwmRuleGroupOutputParams;

/**
 * 规则集合输出参数service接口
 * @author chenjianghe
 *
 */
public interface ICwmRuleGroupOutputParamsService {

	/**
	 * 删除规则集合输出参数
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);
    /**
     * 插入规则集合输出参数
     * @param record
     * @return
     */
    int insert(CwmRuleGroupOutputParams record);
    /**
     * 查询规则集合输出参数
     * @param id
     * @return
     */
    CwmRuleGroupOutputParams selectByPrimaryKey(String id);
    /**
     * 更新规则集合输出参数
     * @param record
     * @return
     */
    int updateByPrimaryKey(CwmRuleGroupOutputParams record);
}
