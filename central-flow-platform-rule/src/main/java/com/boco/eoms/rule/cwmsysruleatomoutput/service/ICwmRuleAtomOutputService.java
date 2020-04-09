package com.boco.eoms.rule.cwmsysruleatomoutput.service;

import com.boco.eoms.rule.cwmsysruleatomoutput.model.CwmRuleAtomOutput;

public interface ICwmRuleAtomOutputService {

	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);

    /**
     * 出入规则输出属性
     * @param record
     * @return
     */
    int insert(CwmRuleAtomOutput record);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    CwmRuleAtomOutput selectByPrimaryKey(String id);

    /**
     * 更新规则输出属性
     * @param record
     * @return
     */
    int updateByPrimaryKey(CwmRuleAtomOutput record);
}
