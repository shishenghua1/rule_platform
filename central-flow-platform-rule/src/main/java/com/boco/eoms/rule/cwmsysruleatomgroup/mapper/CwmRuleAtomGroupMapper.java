package com.boco.eoms.rule.cwmsysruleatomgroup.mapper;

import java.util.List;

import com.boco.eoms.rule.cwmsysruleatomgroup.model.CwmRuleAtomGroup;

/**
 * 原子规则集合mapper
 * @author chenjianghe
 *
 */
public interface CwmRuleAtomGroupMapper {
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
    int insert(CwmRuleAtomGroup record);

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