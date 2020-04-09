package com.boco.eoms.rule.cwmsysruleapps.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.rule.cwmsysruleapps.model.CwmRuleApps;

/**

* 创建时间：2019年5月27日 下午3:29:56

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：规则平台场景分类service接口层

*/
public interface ICwmRuleAppsService {
	int deleteByPrimaryKey(String id);

    JSONObject insert(CwmRuleApps record) throws Exception;

    CwmRuleApps selectByPrimaryKey(String id);
    /**
     * 根据规则场景名称查找场景
     * @param appName
     * @return
     */
    List<CwmRuleApps> selectByAppName(String appName);

    JSONObject updateByPrimaryKey(CwmRuleApps record);
}

