package com.boco.eoms.rule.cwmsysruleatomgroupoutput.mapper;

import java.util.List;

import com.boco.eoms.rule.cwmsysruleatomgroupoutput.model.CwmRuleAtomGroupOutput;

/**
 * 原子规则集合输出mapper
 * @author chenjianghe
 *
 */
public interface CwmRuleAtomGroupOutputMapper {
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
     * 批量更新原子规则集合输出
     * @param cwmRuleAtomGroupOutputList
     */
    void batchUpdate(List<CwmRuleAtomGroupOutput> cwmRuleAtomGroupOutputList);
    /**
     * 批量插入原子规则集合输出
     * @param cwmRuleAtomGroupOutputList
     */
    void batchInsert(List<CwmRuleAtomGroupOutput> cwmRuleAtomGroupOutputList);
    /**
     * 根据原子集合id查询数据
     * @param atomGroupId
     */
    List<CwmRuleAtomGroupOutput> selectByAtomGroupId(String atomGroupId);
}