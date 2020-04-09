package com.boco.eoms.rule.cwmsysrulegroupoutputrel.service;

import com.boco.eoms.rule.cwmsysrulegroupoutputrel.model.CwmRuleGroupOutputRel;

/**
 * 规则集合关联原子规则集合输出service接口
 * @author chenjianghe
 *
 */
public interface ICwmRuleGroupOutputRelService {

	/**
	 * 删除规则集合原子规则集合排列输出结果
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);
    /**
     * 新增规则集合原子规则集合排列输出结果
     * @param record
     * @return
     */
    int insert(CwmRuleGroupOutputRel record);
    /**
     * 查询规则集合原子规则集合排列输出结果
     * @param id
     * @return
     */
    CwmRuleGroupOutputRel selectByPrimaryKey(String id);
    /**
     * 更新规则集合原子规则集合排列输出结果
     * @param record
     * @return
     */
    int updateByPrimaryKey(CwmRuleGroupOutputRel record);
}
