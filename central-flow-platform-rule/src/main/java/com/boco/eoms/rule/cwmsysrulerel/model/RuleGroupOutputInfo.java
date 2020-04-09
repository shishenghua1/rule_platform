package com.boco.eoms.rule.cwmsysrulerel.model;

import java.util.List;

import com.boco.eoms.rule.cwmsysrulegroupoutput.model.CwmRuleGroupOutput;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**

* 创建时间：2019年8月6日 下午2:51:16

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：

*/
@ApiModel(value="RuleGroupOutPutInfo:规则集合输出信息模型")
public class RuleGroupOutputInfo {
	@ApiModelProperty(value="规则集合输出表")
	private CwmRuleGroupOutput cwmRuleGroupOutput;
	@ApiModelProperty(value="参数信息")
	private List<ParamInfo> paramInfoList;
	/**
	 * @return the cwmRuleGroupOutput
	 */
	public CwmRuleGroupOutput getCwmRuleGroupOutput() {
		return cwmRuleGroupOutput;
	}
	/**
	 * @param cwmRuleGroupOutput the cwmRuleGroupOutput to set
	 */
	public void setCwmRuleGroupOutput(CwmRuleGroupOutput cwmRuleGroupOutput) {
		this.cwmRuleGroupOutput = cwmRuleGroupOutput;
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

