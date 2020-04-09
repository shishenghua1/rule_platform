package com.boco.eoms.rule.cwmsysrulerel.model;

import java.util.List;

import com.boco.eoms.rule.cwmsysrulegrouprel.model.CwmRuleGroupRel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**

* 创建时间：2019年8月6日 下午3:43:35

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：

*/
@ApiModel(value="RuleGroupInputInfo:规则集合输入信息模型")
public class RuleGroupInputInfo {
	@ApiModelProperty(value="规则集合关联表")
	private CwmRuleGroupRel cwmRuleGroupRel;
	@ApiModelProperty(value="参数信息")
	private List<ParamInfo> paramInfoList;
	/**
	 * @return the cwmRuleGroupRel
	 */
	public CwmRuleGroupRel getCwmRuleGroupRel() {
		return cwmRuleGroupRel;
	}
	/**
	 * @param cwmRuleGroupRel the cwmRuleGroupRel to set
	 */
	public void setCwmRuleGroupRel(CwmRuleGroupRel cwmRuleGroupRel) {
		this.cwmRuleGroupRel = cwmRuleGroupRel;
	}
	/**
	 * @return the paramInfoList
	 */
	public List<ParamInfo> getParamInfoList() {
		return paramInfoList;
	}
	/**
	 * @param paramInfoList the paramInfoList to set
	 */
	public void setParamInfoList(List<ParamInfo> paramInfoList) {
		this.paramInfoList = paramInfoList;
	}
}

