package com.boco.eoms.rule.cwmsysrulemodules.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.rule.cwmsysrulemodules.model.CwmRuleModules;

/**

* 创建时间：2019年5月27日 下午3:26:12

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：规则平台模块分类接口层

*/
public interface ICwmRuleModulesService {
	int deleteByPrimaryKey(String id);

    JSONObject insert(CwmRuleModules record) throws Exception;

    CwmRuleModules selectByPrimaryKey(String id);

    JSONObject updateByPrimaryKey(CwmRuleModules record);
    
    /**
     * 根据所属系统查询模块
     * @param appId
     * @return
     */
    public List<CwmRuleModules> selectByAppId(String appId);
}

