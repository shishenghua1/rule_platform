package com.boco.eoms.rule.cwmsysruleatominput.service;

import com.boco.eoms.rule.cwmsysruleatominput.model.CwmRuleAtomInput;

public interface ICwmRuleAtomInputService {

	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);

    /**
     * 插入规则输入属性
     * @param record
     * @return
     */
    int insert(CwmRuleAtomInput record);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    CwmRuleAtomInput selectByPrimaryKey(String id);

    /**
     * 更新规则输入属性
     * @param record
     * @return
     */
    int updateByPrimaryKey(CwmRuleAtomInput record);
}
