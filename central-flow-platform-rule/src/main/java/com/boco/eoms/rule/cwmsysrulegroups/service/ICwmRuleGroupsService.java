package com.boco.eoms.rule.cwmsysrulegroups.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.rule.cwmsysrulegroups.model.CwmRuleGroups;
/**
 * 
 * 2019年5月28日上午8:38:23
 *ssh
 */
public interface ICwmRuleGroupsService {
    JSONObject deleteByPrimaryKey(String id, String groupType);

    JSONObject insert(CwmRuleGroups record) throws Exception;

    CwmRuleGroups selectByPrimaryKey(String id);

    JSONObject updateByPrimaryKey(CwmRuleGroups record);
    
    /**
     * 根据模块id查询规则集合
     * @param moduleId
     * @return
     */
    public List<CwmRuleGroups> selectByModuleId(String moduleId,String groupType) throws Exception;
    /**
     * 根据系统和模块查询规则集合
     * @param condition 条件map
     * @return
     */
    public List<CwmRuleGroups> selectByCondition(Map<String,Object> condition);
}