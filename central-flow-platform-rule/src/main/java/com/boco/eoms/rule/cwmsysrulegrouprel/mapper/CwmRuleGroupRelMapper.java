package com.boco.eoms.rule.cwmsysrulegrouprel.mapper;

import java.util.List;

import com.boco.eoms.rule.cwmsysrulegrouprel.model.CwmRuleGroupRel;
import com.boco.eoms.rule.cwmsysrulegroups.model.CwmRuleGroups;
import org.springframework.stereotype.Repository;

/**
 * 规则集合关联原子规则集合mapper
 * @author chenjianghe
 *
 */
@Repository
public interface CwmRuleGroupRelMapper {
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
    
    /**
     * 根据规则集合id删除规则关联
     * @param groupId
     */
    public void deleteByGroupId(String groupId);

    /**
     * 规则结合关联表的批量删除
     * @param ids
     */
    public void batchDeleteById(List<String> ids);
    
    /**
     * 根据规则集合递归查询下面所有规则集合
     * @param groupId
     * @return
     */
    public List<CwmRuleGroupRel> selectRecursionByGroupId(String groupId);
    
    /**
     * 根据规则集合id查询关联的规则集合
     * @param groupId
     * @return
     */
    public List<CwmRuleGroupRel> selectGroupRuleBGroupId(String groupId);
    
    /**
     * 根据父节点id查询关联信息
     * @param parentNodeId
     * @return
     */
    public List<CwmRuleGroupRel> selectRelByParentNodeId(String parentNodeId);
    /**
     * 根据父节点id查询开启的关联信息
     * @param parentNodeId
     * @return
     */
    public List<CwmRuleGroupRel> selectEnableRelByParentNodeId(String parentNodeId);
    
    /**
     * 递归查询规则集合关联的所有规则集合关系
     * @param parentNodeId
     * @return
     */
    public List<CwmRuleGroupRel> getAllRuleGroupsFromRel(String parentNodeId);
    
}