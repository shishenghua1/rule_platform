package com.boco.eoms.rule.cwmsysrulerel.model;

import java.util.List;

import com.boco.eoms.rule.cwmsysruleatomgrouprel.model.CwmRuleAtomGroupRel;
import com.boco.eoms.rule.cwmsysruleatominput.model.CwmRuleAtomInput;
import com.boco.eoms.rule.cwmsysruleatomoutput.model.CwmRuleAtomOutput;
import com.boco.eoms.rule.cwmsysruleatoms.model.CwmRuleAtoms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**

* 创建时间：2019年6月28日 上午9:57:49

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：原子规则配置模型

*/
@ApiModel(value="CwmRuleAtomConfig:原子规则配置模型")
public class CwmRuleAtomConfig {
	@ApiModelProperty(value="原子规则表")
	private CwmRuleAtoms cwmRuleAtoms;
	@ApiModelProperty(value="原子规则输入表")
	private CwmRuleAtomInput cwmRuleAtomInput;
	@ApiModelProperty(value="原子规则输出信息")
	private List<CwmRuleAtomOutput> cwmRuleAtomOutputList;
	@ApiModelProperty(value="原子规则集合关联表")
	private CwmRuleAtomGroupRel cwmRuleAtomGroupRel;

	/**
	 * @return the cwmRuleAtoms
	 */
	public CwmRuleAtoms getCwmRuleAtoms() {
		return cwmRuleAtoms;
	}
	/**
	 * @param cwmRuleAtoms the cwmRuleAtoms to set
	 */
	public void setCwmRuleAtoms(CwmRuleAtoms cwmRuleAtoms) {
		this.cwmRuleAtoms = cwmRuleAtoms;
	}
	
	/**
	 * @return the cwmRuleAtomOutputList
	 */
	public List<CwmRuleAtomOutput> getCwmRuleAtomOutputList() {
		return cwmRuleAtomOutputList;
	}
	/**
	 * @param cwmRuleAtomOutputList the cwmRuleAtomOutputList to set
	 */
	public void setCwmRuleAtomOutputList(List<CwmRuleAtomOutput> cwmRuleAtomOutputList) {
		this.cwmRuleAtomOutputList = cwmRuleAtomOutputList;
	}
	/**
	 * @return the cwmRuleAtomInput
	 */
	public CwmRuleAtomInput getCwmRuleAtomInput() {
		return cwmRuleAtomInput;
	}
	/**
	 * @param cwmRuleAtomInput the cwmRuleAtomInput to set
	 */
	public void setCwmRuleAtomInput(CwmRuleAtomInput cwmRuleAtomInput) {
		this.cwmRuleAtomInput = cwmRuleAtomInput;
	}
	/**
	 * @return the cwmRuleAtomGroupRel
	 */
	public CwmRuleAtomGroupRel getCwmRuleAtomGroupRel() {
		return cwmRuleAtomGroupRel;
	}
	/**
	 * @param cwmRuleAtomGroupRel the cwmRuleAtomGroupRel to set
	 */
	public void setCwmRuleAtomGroupRel(CwmRuleAtomGroupRel cwmRuleAtomGroupRel) {
		this.cwmRuleAtomGroupRel = cwmRuleAtomGroupRel;
	}
	
} 

