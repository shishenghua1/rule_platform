package com.boco.eoms.rule.cwmsysrulegroupoutputparams.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.boco.eoms.rule.cwmsysrulegroupoutputparams.model.CwmRuleGroupOutputParams;
import org.springframework.stereotype.Repository;

/**
 * 规则集合输出参数表mapper
 * @author chenjianghe
 *
 */
@Repository
public interface CwmRuleGroupOutputParamsMapper {
	/**
	 * 删除规则集合输出参数
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);
    /**
     * 根据输出参数集合id删除数据
     * @param groupOutputParamsIds
     */
    void delByParamId(String[] groupOutputParamsIds);
    /**
     * 插入规则集合输出参数
     * @param record
     * @return
     */
    int insert(CwmRuleGroupOutputParams record);
    /**
     * 查询规则集合输出参数
     * @param id
     * @return
     */
    CwmRuleGroupOutputParams selectByPrimaryKey(String id);
    /**
     * 更新规则集合输出参数
     * @param record
     * @return
     */
    int updateByPrimaryKey(CwmRuleGroupOutputParams record);
    
    /**
     * 根据规则集合输出参数id查询输出参数
     * @param selectByParamsId
     * @return
     */
    List<CwmRuleGroupOutputParams> selectByParamsId(String selectByParamsId);
    
    /**
     * 批量插入规则集合输出参数
     * @param ruleGroupOutputParamsList
     */
    public void batchInsert(List<CwmRuleGroupOutputParams> ruleGroupOutputParamsList);
    
    /**
     * 根据规则集合id和输出结果查询规则集合输出关联参数
     * @param groupId
     * @return
     */
    public List<CwmRuleGroupOutputParams> getParamsByGroupIdAndResult(@Param("groupId") String groupId,@Param("groupOutputResult") String groupOutputResult);
    /**
     * 根据规则集合id查询规则集合输出关联参数
     * @param groupId
     * @return
     */
    public List<CwmRuleGroupOutputParams> getParamsByGroupId(String groupId);

    /**
     * 根据规则集合输出参数id集合查询数据
     * @param outputParamsIds
     * @return
     */
    public List<CwmRuleGroupOutputParams> selectByParamsIds(String[] outputParamsIds);
}