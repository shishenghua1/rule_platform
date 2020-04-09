package com.boco.eoms.rule.cwmsysrulerel.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.rule.cwmsysrulerel.model.CwmRuleGroupConfig;
import com.boco.eoms.rule.cwmsysrulerel.model.ParamInfo;
import com.boco.eoms.rule.cwmsysrulerel.model.RuleContent;
import com.boco.eoms.rule.cwmsysrulerel.model.RuleGroupInfo;

/**

* 创建时间：2019年7月3日 下午2:55:02

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：规则多模型互联接口类

*/
public interface ICwmRuleRelService {
	/**
	 * 规则集合配置保存
	 * @param cwmRuleGroupConfig
	 */
	JSONObject saveCwmRuleGroupConfig(CwmRuleGroupConfig cwmRuleGroupConfig) throws Exception;
	/**
	 * 规则集合配置查询
	 * @param groupId
	 * @param ruleType 规则集合类型
	 * @return
	 */
	CwmRuleGroupConfig queryCwmRuleGroupConfig(String groupId,String ruleType) throws Exception;
	/**
	 * 规则集合信息查询
	 * @param groupIds
	 * @return
	 */
	List<RuleGroupInfo> selectByGroupIds(String groupIds);
	/**
	 * 规则集合配置修改
	 * @param cwmRuleGroupConfig
	 */
	JSONObject updateCwmRuleGroupConfig(CwmRuleGroupConfig cwmRuleGroupConfig) throws Exception;
	
	 /**
     * 返回规则平台左侧的json数据
     * @return
     */
    JSONArray getRulePlatformZtree();
    /**
     * 规则集相关联原子规则参数信息的获取
     * @param groupId
     * @param moduleId
     * @return
     */
    List<ParamInfo> getParamInfo(String groupId,String moduleId);

	/**
	 * 流程平台规则集合内容查询
	 * @param groupId
	 * @return
	 */
	RuleContent queryRuleContent(String groupId) throws Exception;
	
	/**
	 * 获取计算变量相关的参数信息
	 * @param moduleId 规则模块id
	 * @param inputEnParam 参数英文名
	 * @return
	 */
	List<ParamInfo> getParamListInfo(String moduleId,String inputEnParam);

	/**
	 * 原子规则配置插入
	 * @param cwmRuleAtomConfig
	 *//*
	void saveRuleAtomConfig(CwmRuleAtomConfig cwmRuleAtomConfig) throws Exception;
	*//**
	 * 原子规则配置修改
	 * @param cwmRuleAtomConfig
	 * @throws Exception
	 *//*
	void updateRuleAtomConfig(CwmRuleAtomConfig cwmRuleAtomConfig) throws Exception;
	*//**
	 * 原子规则配置数据查询
	 * @param atomId
	 * @return
	 *//*
	CwmRuleAtomConfig queryCwmRuleAtomConfig(String atomId);
	*//**
	 * 原子规则集合配置信息修改
	 * @param ruleAtomGroupConfigList
	 *//*
	void saveRuleAtomGroupConfig(List<CwmRuleAtomGroupConfig> ruleAtomGroupConfigList);
	
	*//**
	 * 规则集合关联原子规则集合信息查询
	 * @param groupId
	 * @return
	 *//*
	List<CwmRuleGroupRelAtomGroupConfig> getRuleGroupRelAtomGroupConfig(String groupId);

	*//**
	 * 原子规则集合配置数据查询
	 * @param atomGroupId
	 * @return
	 *//*

	List<CwmRuleAtomGroupConfig> getRuleAtomGroupConfig(String atomGroupId);
	*//**
	 * 原子规则集合配置信息更新
	 * @param ruleAtomGroupConfigList
	 *//*
	void updateRuleAtomGroupConfig(List<CwmRuleAtomGroupConfig> ruleAtomGroupConfigList);
	
	*//**
	 * 规则集合关联插入
	 * @param CwmRuleGroupRels
	 *//*
	public void saveGroupRelAtomGroup(List<CwmRuleGroupRel> CwmRuleGroupRels);*/

}

