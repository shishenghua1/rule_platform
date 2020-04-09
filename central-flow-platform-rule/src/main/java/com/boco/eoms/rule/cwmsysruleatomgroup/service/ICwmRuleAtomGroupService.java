package com.boco.eoms.rule.cwmsysruleatomgroup.service;

import java.util.List;

import com.boco.eoms.rule.cwmsysruleatomgroup.model.CwmRuleAtomGroup;

/**
 * 原子规则集合service接口
 * @author chenjianghe
 *
 */
public interface ICwmRuleAtomGroupService {
	
	/**
	 * 删除一条原子规则集合
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);

    /**
     * 插入一条原子规则集合
     * @param record
     * @return
     */
    int insert(CwmRuleAtomGroup record) throws Exception;

    /**
     * 根据id查询原子规则集合
     * @param id
     * @return
     */
    CwmRuleAtomGroup selectByPrimaryKey(String id);

    /**
     * 更新原子规则集合
     * @param record
     * @return
     */
    int updateByPrimaryKey(CwmRuleAtomGroup record);
    /**
     * 查询规则库信息
     * @return
     */
    List<CwmRuleAtomGroup> findAll();

}
