package com.boco.eoms.rule.cwmsysrulerel.model;

import java.util.List;

import com.boco.eoms.rule.cwmsysrulegroups.model.CwmRuleGroups;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**

* 创建时间：2019年8月6日 上午11:37:33

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：

*/
@ApiModel(value="RuleGroupInfo:规则集合信息模型")
public class RuleGroupInfo {
	@ApiModelProperty(value="规则集合表")
	private CwmRuleGroups cwmRuleGroups;
	@ApiModelProperty(value="规则集合输入信息集合")
	private List<RuleGroupInputInfo> ruleGroupInputInfoList;
	@ApiModelProperty(value="规则集合输出信息")
	private RuleGroupOutputInfo ruleGroupOutPutInfo;
	/**
	 * @return the ruleGroupInputInfoList
	 */
	public List<RuleGroupInputInfo> getRuleGroupInputInfoList() {
		return ruleGroupInputInfoList;
	}
	/**
	 * @param ruleGroupInputInfoList the ruleGroupInputInfoList to set
	 */
	public void setRuleGroupInputInfoList(List<RuleGroupInputInfo> ruleGroupInputInfoList) {
		this.ruleGroupInputInfoList = ruleGroupInputInfoList;
	}
	/**
	 * @return the cwmRuleGroups
	 */
	public CwmRuleGroups getCwmRuleGroups() {
		return cwmRuleGroups;
	}
	/**
	 * @param cwmRuleGroups the cwmRuleGroups to set
	 */
	public void setCwmRuleGroups(CwmRuleGroups cwmRuleGroups) {
		this.cwmRuleGroups = cwmRuleGroups;
	}
	/**
	 * @return the ruleGroupOutPutInfo
	 */
	public RuleGroupOutputInfo getRuleGroupOutPutInfo() {
		return ruleGroupOutPutInfo;
	}
	/**
	 * @param ruleGroupOutPutInfo the ruleGroupOutPutInfo to set
	 */
	public void setRuleGroupOutPutInfo(RuleGroupOutputInfo ruleGroupOutPutInfo) {
		this.ruleGroupOutPutInfo = ruleGroupOutPutInfo;
	}
}

