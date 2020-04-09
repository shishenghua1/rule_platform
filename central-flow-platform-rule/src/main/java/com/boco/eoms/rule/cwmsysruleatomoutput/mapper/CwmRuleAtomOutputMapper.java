package com.boco.eoms.rule.cwmsysruleatomoutput.mapper;

import java.util.List;

import com.boco.eoms.rule.cwmsysruleatomoutput.model.CwmRuleAtomOutput;

/**
 * 原子规则输出属性mapper
 * @author chenjianghe
 *
 */
public interface CwmRuleAtomOutputMapper {
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);

    /**
     * 出入规则输出属性
     * @param record
     * @return
     */
    int insert(CwmRuleAtomOutput record);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    CwmRuleAtomOutput selectByPrimaryKey(String id);

    /**
     * 更新规则输出属性
     * @param record
     * @return
     */
    int updateByPrimaryKey(CwmRuleAtomOutput record);
    /**
     * 批量插入
     * @param cwmRuleAtomOutputList
     */
    void batchInsert(List<CwmRuleAtomOutput> cwmRuleAtomOutputList);
    
    /**
     * 根据原子规则id查询原子规则输出信息
     * @param atomId
     * @return
     */
    List<CwmRuleAtomOutput>  selectByAtomId(String atomId);
    /**
     * 批量更新
     * @param cwmRuleAtomOutputList
     */
    void batchUpdate(List<CwmRuleAtomOutput> cwmRuleAtomOutputList);
}