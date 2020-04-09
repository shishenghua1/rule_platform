package com.boco.eoms.rule.cwmsysruleatomgroupoutputrel.service;

import java.util.List;

import com.boco.eoms.rule.cwmsysruleatomgroupoutputrel.model.CwmRuleAtomGroupOutputRel;

/**
 * 原子规则集合关联原子规则输出service接口
 * @author chenjianghe
 *
 */
public interface ICwmRuleAtomGroupOutputRelService {
	
	/**
	 * 删除原子规则集合关联原子规则输出
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);

    /**
     * 新增原子规则集合关联原子规则输出
     * @param record
     * @return
     */
    int insert(CwmRuleAtomGroupOutputRel record);

    /**
     * 查询原子规则集合关联原子规则输出
     * @param id
     * @return
     */
    CwmRuleAtomGroupOutputRel selectByPrimaryKey(String id);

    /**
     * 更新原子规则集合关联原子规则输出
     * @param record
     * @return
     */
    int updateByPrimaryKey(CwmRuleAtomGroupOutputRel record);

}
