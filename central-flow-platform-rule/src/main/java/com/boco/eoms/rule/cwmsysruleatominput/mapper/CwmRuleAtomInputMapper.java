package com.boco.eoms.rule.cwmsysruleatominput.mapper;

import java.util.List;

import com.boco.eoms.rule.cwmsysruleatominput.model.CwmRuleAtomInput;

/**
 * 原子规则输入属性mapper
 * @author chenjianghe
 *
 */
public interface CwmRuleAtomInputMapper {
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);

    /**
     * 插入规则输入属性
     * @param record
     * @return
     */
    int insert(CwmRuleAtomInput record);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    CwmRuleAtomInput selectByPrimaryKey(String id);

    /**
     * 更新规则输入属性
     * @param record
     * @return
     */
    int updateByPrimaryKey(CwmRuleAtomInput record);
    /**
     * 批量插入
     * @param cwmRuleAtomInputList
     */
    void batchInsert(List<CwmRuleAtomInput> cwmRuleAtomInputList);
    /**
     * 批量修改
     * @param cwmRuleAtomInputList
     */
    void batchUpdate(List<CwmRuleAtomInput> cwmRuleAtomInputList);
    /**
     * 根据原子规则id查询原子规则输入信息
     * @param atomId
     * @return
     */
    List<CwmRuleAtomInput> selectByAtomId(String atomId);
}