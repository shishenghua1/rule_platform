	package com.boco.eoms.rule.cwmsysruleatomgroupoutputrel.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**

* 创建时间：2019年7月1日 下午4:42:17

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：原子规则集合关联原子规则输出表

*/
@ApiModel(value="CwmRuleAtomGroupOutputRel:原子规则集合关联原子规则输出表")
public class CwmRuleAtomGroupOutputRel {
	//主键id
	@ApiModelProperty(value="主键id",example="7eb90bc4036145bc80dcbaba5176496c")
	private String id;
	//原子规则集合关联原子规则输出id	
	@ApiModelProperty(value="原子规则集合关联原子规则输出id	",example="7eb90bc4036145bc80dcbaba5176496c")
	private String atomGroupRelId;
	//原子规则Id	
	@ApiModelProperty(value="原子规则Id",example="7eb90bc4036145bc80dcbaba5176496c")
	private String atomId;
	//原子规则结果	
	@ApiModelProperty(value="原子规则结果",example="通过")
	private String atomResult;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the atomGroupRelId
	 */
	public String getAtomGroupRelId() {
		return atomGroupRelId;
	}
	/**
	 * @param atomGroupRelId the atomGroupRelId to set
	 */
	public void setAtomGroupRelId(String atomGroupRelId) {
		this.atomGroupRelId = atomGroupRelId;
	}
	/**
	 * @return the atomId
	 */
	public String getAtomId() {
		return atomId;
	}
	/**
	 * @param atomId the atomId to set
	 */
	public void setAtomId(String atomId) {
		this.atomId = atomId;
	}
	/**
	 * @return the atomResult
	 */
	public String getAtomResult() {
		return atomResult;
	}
	/**
	 * @param atomResult the atomResult to set
	 */
	public void setAtomResult(String atomResult) {
		this.atomResult = atomResult;
	}
	
}

