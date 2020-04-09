package com.boco.eoms.rule.cwmsysruleatomgroupoutput.service;

import java.util.List;

import com.boco.eoms.rule.cwmsysruleatomgroupoutput.model.CwmRuleAtomGroupOutput;

/**
 * 原子规则集合输出service接口
 * @author chenjianghe
 *
 */
public interface ICwmRuleAtomGroupOutputService {
	/**
	 * 删除原子规则集合输出
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);

    /**
     * 插入原子规则集合输出
     * @param record
     * @return
     */
    int insert(CwmRuleAtomGroupOutput record);

    /**
     * 查询原子规则集合输出
     * @param id
     * @return
     */
    CwmRuleAtomGroupOutput selectByPrimaryKey(String id);

    /**
     * 更新原子规则集合输出
     * @param record
     * @return
     */
    int updateByPrimaryKey(CwmRuleAtomGroupOutput record);
    
    /**
     * 根据原子规则集合id查询原子规则集合输出
     * @param atomGroupId
     * @return
     */
    List<CwmRuleAtomGroupOutput> selectByAtomGroupId(String atomGroupId);

}
