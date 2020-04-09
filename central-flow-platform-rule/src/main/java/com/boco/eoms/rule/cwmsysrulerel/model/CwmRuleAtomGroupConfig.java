package com.boco.eoms.rule.cwmsysrulerel.model;

import java.util.List;

import com.boco.eoms.rule.cwmsysruleatomgroupoutput.model.CwmRuleAtomGroupOutput;
import com.boco.eoms.rule.cwmsysruleatomgroupoutputrel.model.CwmRuleAtomGroupOutputRel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**

* 创建时间：2019年6月28日 上午9:57:49

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：原子规则集合配置模型

*/
@ApiModel(value="CwmRuleAtomGroupConfig:原子规则集合配置模型")
public class CwmRuleAtomGroupConfig {
	@ApiModelProperty(value="原子规则集合输出")
	private CwmRuleAtomGroupOutput cwmRuleAtomGroupOutput;
	
	@ApiModelProperty(value="原子规则集合关联原子规则输出关系")
	private List<CwmRuleAtomGroupOutputRel> cwmRuleAtomGroupOutputRelList;

	/**
	 * @return the cwmRuleAtomGroupOutput
	 */
	public CwmRuleAtomGroupOutput getCwmRuleAtomGroupOutput() {
		return cwmRuleAtomGroupOutput;
	}

	/**
	 * @param cwmRuleAtomGroupOutput the cwmRuleAtomGroupOutput to set
	 */
	public void setCwmRuleAtomGroupOutput(CwmRuleAtomGroupOutput cwmRuleAtomGroupOutput) {
		this.cwmRuleAtomGroupOutput = cwmRuleAtomGroupOutput;
	}

	/**
	 * @return the cwmRuleAtomGroupOutputRelList
	 */
	public List<CwmRuleAtomGroupOutputRel> getCwmRuleAtomGroupOutputRelList() {
		return cwmRuleAtomGroupOutputRelList;
	}

	/**
	 * @param cwmRuleAtomGroupOutputRelList the cwmRuleAtomGroupOutputRelList to set
	 */
	public void setCwmRuleAtomGroupOutputRelList(List<CwmRuleAtomGroupOutputRel> cwmRuleAtomGroupOutputRelList) {
		this.cwmRuleAtomGroupOutputRelList = cwmRuleAtomGroupOutputRelList;
	}
	
} 

