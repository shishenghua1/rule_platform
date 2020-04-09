
package com.boco.eoms.rule.cwmsysrulegroupoutput.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.boco.eoms.rule.cwmsysrulegroupoutput.model.CwmRuleGroupOutput;
import org.springframework.stereotype.Repository;

/**
 * 规则集合输出mapper
 * @author chenjianghe
 *
 */
@Repository
public interface CwmRuleGroupOutputMapper {
	/**
	 * 删除规则集合输出
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(String id);
    /**
     * 插入规则集合输出
     * @param record
     * @return
     */
    int insert(CwmRuleGroupOutput record);

    /**
     * 规则集合输出批量插入
     * @param ruleGroupOutputList
     */
    void batchInsert(List<CwmRuleGroupOutput> ruleGroupOutputList);
    /**
     * 查询规则集合输出
     * @param id
     * @return
     */
    CwmRuleGroupOutput selectByPrimaryKey(String id);
    /**
     * 更新规则集合输出
     * @param record
     * @return
     */
    int updateByPrimaryKey(CwmRuleGroupOutput record);

    /**
     * 批量更新规则集合输出
     * @param
     * @return
     */
    void batchUpdate(List<CwmRuleGroupOutput> cwmRuleGroupOutputList);
    
    /**
     * 根据规则集合id查询规则集合输出
     * @param groupId
     * @return
     */
    List<CwmRuleGroupOutput> selectByGroupId(String groupId);
    
    /**
     * 查询不符合规则的输出
     * @param groupId
     * @return
     */
    CwmRuleGroupOutput selectByGroupIdAndResult(@Param("groupId") String groupId,@Param("result") String result);
    
    
}