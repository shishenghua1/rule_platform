package com.boco.eoms.rule.cwmsysruleatomgrouprel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.boco.eoms.rule.cwmsysruleatomgrouprel.model.CwmRuleAtomGroupRel;

/**
 * 原子规则集合关联mapper
 * @author chenjianghe
 *
 */
public interface CwmRuleAtomGroupRelMapper {
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
    /**
     * 批量插入
     * @param cwmRuleAtomGroupRelList
     */
    void batchInsert(List<CwmRuleAtomGroupRel> cwmRuleAtomGroupRelList);
    /**
     * 批量更新
     * @param cwmRuleAtomGroupRelList
     */
    void batchUpdate(List<CwmRuleAtomGroupRel> cwmRuleAtomGroupRelList);
    /**
     * 根据原子集合id查询数据
     * @param atomGroupId
     * @return
     */
    List<CwmRuleAtomGroupRel> listByAtomGroupId(@Param("atomGroupId")String atomGroupId);
}