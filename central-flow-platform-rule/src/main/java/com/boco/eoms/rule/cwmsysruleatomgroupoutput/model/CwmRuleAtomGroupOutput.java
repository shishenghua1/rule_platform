	package com.boco.eoms.rule.cwmsysruleatomgroupoutput.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**

* 创建时间：2019年7月1日 下午4:42:17

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：原子规则集合表输出表

*/
@ApiModel(value="CwmRuleAtomGroupOutput:原子规则集合表输出表")
public class CwmRuleAtomGroupOutput {
	//主键id
	@ApiModelProperty(value="主键id",example="7eb90bc4036145bc80dcbaba5176496c")
	private String id;
	//原子规则集合id
	@ApiModelProperty(value="原子规则集合id",example="7eb90bc4036145bc80dcbaba5176496c")
	private String atomGroupId;
	//原子规则集合关联原子规则输出id	
	@ApiModelProperty(value="原子规则集合关联原子规则输出id	",example="7eb90bc4036145bc80dcbaba5176496c")
	private String atomGroupRelId;
	//输出结果	
	@ApiModelProperty(value="输出结果",example="true")
	private String paramResult;
	//输出结果说明	
	@ApiModelProperty(value="输出结果说明",example="通过")
	private String paramResultDescription;
	//删除标识
	@ApiModelProperty(value="删除标识",example="0")
	private String deleted;
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
	 * @return the atomGroupId
	 */
	public String getAtomGroupId() {
		return atomGroupId;
	}
	/**
	 * @param atomGroupId the atomGroupId to set
	 */
	public void setAtomGroupId(String atomGroupId) {
		this.atomGroupId = atomGroupId;
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
	 * @return the paramResult
	 */
	public String getParamResult() {
		return paramResult;
	}
	/**
	 * @param paramResult the paramResult to set
	 */
	public void setParamResult(String paramResult) {
		this.paramResult = paramResult;
	}
	/**
	 * @return the paramResultDescription
	 */
	public String getParamResultDescription() {
		return paramResultDescription;
	}
	/**
	 * @param paramResultDescription the paramResultDescription to set
	 */
	public void setParamResultDescription(String paramResultDescription) {
		this.paramResultDescription = paramResultDescription;
	}
	/**
	 * @return the deleted
	 */
	public String getDeleted() {
		return deleted;
	}
	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	
}

