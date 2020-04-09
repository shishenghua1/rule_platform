package com.boco.eoms.rule.cwmsysrulegroupoutputrel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.boco.eoms.rule.cwmsysrulegroupoutputrel.model.CwmRuleGroupOutputRel;

/**
 * 规则集合输出关联表mapper
 * @author chenjianghe
 *
 */
@Repository
public interface CwmRuleGroupOutputRelMapper {

    int deleteByPrimaryKey(String id);

    int insert(CwmRuleGroupOutputRel record);

    CwmRuleGroupOutputRel selectByPrimaryKey(String id);

    int updateByPrimaryKey(CwmRuleGroupOutputRel record);

    /**
     * 根据规则集合输出
     * @param outputId
     */
    void deleteByOutputIds(String[] outputId);
    /**
     * 根据规则输出id查询规则关联的输出
     * @param outputId
     * @return
     */
    public List<CwmRuleGroupOutputRel> selectByOutputId(String outputId);
    /**
     * 根据规则集合输出id删除规则集合输出关联数据
     * @param outputId
     * @return
     */
    public void deleteByOutputId(String outputId);
    /**
     * 根据规则集合id和输出结果查询规则集合关联输出
     * @param groupId
     * @return
     */
    public List<CwmRuleGroupOutputRel> selectByGroupId(@Param("groupId") String groupId,@Param("result") String result);
    /**
     * 规则集合输出关联批量保存
     * @param cwmRuleGroupOutputRelList
     */
    public void batchInsert(List<CwmRuleGroupOutputRel> cwmRuleGroupOutputRelList);
}