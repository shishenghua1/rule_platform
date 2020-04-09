package com.boco.eoms.rule.cwmsysruleatomgroupoutputrel.mapper;

import java.util.List;

import com.boco.eoms.rule.cwmsysruleatomgroupoutputrel.model.CwmRuleAtomGroupOutputRel;

/**
 * 原子规则集合关联原子规则输出mapper
 * @author chenjianghe
 *
 */
public interface CwmRuleAtomGroupOutputRelMapper {
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
    
    /**
     * 批量更新原子规则集合关联原子规则输出
     * @param cwmRuleAtomGroupOutputRelList
     */
    void batchUpdate(List<CwmRuleAtomGroupOutputRel> cwmRuleAtomGroupOutputRelList);
    /**
     * 批量插入原子规则集合关联原子规则输出
     * @param cwmRuleAtomGroupOutputRelList
     */
    void batchInsert(List<CwmRuleAtomGroupOutputRel> cwmRuleAtomGroupOutputRelList);
    /**
     * 根据原子集合id查询数据
     * @param atomGroupRelId
     */
    List<CwmRuleAtomGroupOutputRel>  selectByAtomGroupRelId(String atomGroupRelId);
}