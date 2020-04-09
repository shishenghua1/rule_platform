package com.boco.eoms.rule.cwmsysruleatomgrouprel.service;

import com.boco.eoms.rule.cwmsysruleatomgrouprel.model.CwmRuleAtomGroupRel;

/**
 * 原子规则集合关联service接口
 * @author chenjianghe
 *
 */
public interface ICwmRuleAtomGroupRelService {

	/**
	 * 删除一条原子规则关联集合
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);

    /**
     * 插入一条原子规则关联集合
     * @param record
     * @return
     */
    int insert(CwmRuleAtomGroupRel record);

    /**
     * 根据id查询原子规则关联集合
     * @param id
     * @return
     */
    CwmRuleAtomGroupRel selectByPrimaryKey(String id);

    /**
     * 更新原子规则关联集合
     * @param record
     * @return
     */
    int updateByPrimaryKey(CwmRuleAtomGroupRel record);
}
