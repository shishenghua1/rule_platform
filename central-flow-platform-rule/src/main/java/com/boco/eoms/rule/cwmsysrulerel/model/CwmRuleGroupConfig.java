package com.boco.eoms.rule.cwmsysrulerel.model;

import java.util.List;

import com.boco.eoms.rule.cwmsysruleatoms.model.CwmRuleAtoms;
import com.boco.eoms.rule.cwmsysrulegrouplibraryrel.model.CwmRuleGroupLibraryRel;
import com.boco.eoms.rule.cwmsysrulegroupoutput.model.CwmRuleGroupOutput;
import com.boco.eoms.rule.cwmsysrulegroupoutputparams.model.CwmRuleGroupOutputParams;
import com.boco.eoms.rule.cwmsysrulegroupoutputrel.model.CwmRuleGroupOutputRel;
import com.boco.eoms.rule.cwmsysrulegrouprel.model.CwmRuleGroupRel;
import com.boco.eoms.rule.cwmsysrulegroups.model.CwmRuleGroups;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**

* 创建时间：2019年6月28日 下午3:04:59

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：规则集合配置模型

*/
@ApiModel(value="CwmRuleGroupConfig:规则集合配置保存模型")
public class CwmRuleGroupConfig {
	@ApiModelProperty(value="原子规则的保存")
	private List<CwmRuleAtoms> cwmRuleAtomsList;
	@ApiModelProperty(value="规则输出集合的关联")
	private List<CwmRuleGroupOutput> ruleGroupOutputList;
	@ApiModelProperty(value="规则输出参数的配置")
	private List<CwmRuleGroupOutputParams> ruleGroupOutputParamsList;
	@ApiModelProperty(value="规则集合关联")
	private List<CwmRuleGroupRel> cwmRuleGroupRelList;
	@ApiModelProperty(value="规则集合的更改")
	private CwmRuleGroups CwmRuleGroups;
	@ApiModelProperty(value="规则集合输出关联信息")
	private List<CwmRuleGroupOutputRel> cwmRuleGroupOutputRelList;
	@ApiModelProperty(value="规则集合库关联表")
	private List<CwmRuleGroupLibraryRel> cwmRuleGroupLibraryRelList;

	/**
	 * @return the cwmRuleAtomsList
	 */
	public List<CwmRuleAtoms> getCwmRuleAtomsList() {
		return cwmRuleAtomsList;
	}
	/**
	 * @param cwmRuleAtomsList the cwmRuleAtomsList to set
	 */
	public void setCwmRuleAtomsList(List<CwmRuleAtoms> cwmRuleAtomsList) {
		this.cwmRuleAtomsList = cwmRuleAtomsList;
	}

	public List<CwmRuleGroupOutput> getRuleGroupOutputList() {
		return ruleGroupOutputList;
	}

	public void setRuleGroupOutputList(List<CwmRuleGroupOutput> ruleGroupOutputList) {
		this.ruleGroupOutputList = ruleGroupOutputList;
	}

	/**
	 * @return the ruleGroupOutputParamsList
	 */
	public List<CwmRuleGroupOutputParams> getRuleGroupOutputParamsList() {
		return ruleGroupOutputParamsList;
	}
	/**
	 * @param ruleGroupOutputParamsList the ruleGroupOutputParamsList to set
	 */
	public void setRuleGroupOutputParamsList(List<CwmRuleGroupOutputParams> ruleGroupOutputParamsList) {
		this.ruleGroupOutputParamsList = ruleGroupOutputParamsList;
	}
	/**
	 * @return the cwmRuleGroupRelList
	 */
	public List<CwmRuleGroupRel> getCwmRuleGroupRelList() {
		return cwmRuleGroupRelList;
	}
	/**
	 * @param cwmRuleGroupRelList the cwmRuleGroupRelList to set
	 */
	public void setCwmRuleGroupRelList(List<CwmRuleGroupRel> cwmRuleGroupRelList) {
		this.cwmRuleGroupRelList = cwmRuleGroupRelList;
	}
	/**
	 * @return the cwmRuleGroups
	 */
	public CwmRuleGroups getCwmRuleGroups() {
		return CwmRuleGroups;
	}
	/**
	 * @param cwmRuleGroups the cwmRuleGroups to set
	 */
	public void setCwmRuleGroups(CwmRuleGroups cwmRuleGroups) {
		CwmRuleGroups = cwmRuleGroups;
	}

	public List<CwmRuleGroupOutputRel> getCwmRuleGroupOutputRelList() {
		return cwmRuleGroupOutputRelList;
	}

	public void setCwmRuleGroupOutputRelList(List<CwmRuleGroupOutputRel> cwmRuleGroupOutputRelList) {
		this.cwmRuleGroupOutputRelList = cwmRuleGroupOutputRelList;
	}

	public List<CwmRuleGroupLibraryRel> getCwmRuleGroupLibraryRelList() {
		return cwmRuleGroupLibraryRelList;
	}

	public void setCwmRuleGroupLibraryRelList(List<CwmRuleGroupLibraryRel> cwmRuleGroupLibraryRelList) {
		this.cwmRuleGroupLibraryRelList = cwmRuleGroupLibraryRelList;
	}
}

