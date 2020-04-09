package com.boco.eoms.rule.cwmsysrulegrouprel.service;

import java.util.List;

import com.boco.eoms.rule.cwmsysrulegrouprel.model.CwmRuleGroupRel;

/**
 * 规则集合关联service接口
 * @author chenjianghe
 *
 */
public interface ICwmRuleGroupRelService {
	/**
	 * 删除规则集合关联
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);
    /**
     * 插入规则集合关联
     * @param record
     * @return
     */
    int insert(CwmRuleGroupRel record);
    /**
     * 查询规则集合关联
     * @param id
     * @return
     */
    CwmRuleGroupRel selectByPrimaryKey(String id);
    /**
     * 更新规则集合关联
     * @param record
     * @return
     */
    int updateByPrimaryKey(CwmRuleGroupRel record);
    
    /**
     * 批量插入规则集合关联
     * @param CwmRuleGroupRels
     */
    public void batchInsert(List<CwmRuleGroupRel> CwmRuleGroupRels);
    
    /**
     * 根据规则集合查询规则集合关联
     * @param groupId
     * @return
     */
    public List<CwmRuleGroupRel> selectByGroupId(String groupId);
}
