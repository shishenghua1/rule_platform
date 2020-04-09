package com.boco.eoms.rule.cwmsysrulegrouprel.model;

import java.util.Date;

import com.boco.eoms.base.util.ConstantConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**

* 创建时间：2019年6月26日 下午3:10:22

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：

*/
@ApiModel(value="CwmRuleGroupRel:规则集合关联")
public class CwmRuleGroupRel {
	//主键id
	@ApiModelProperty(value="主键id",example="7eb90bc4036145bc80dcbaba5176496c")
	private String id;
	//父节点id
	@ApiModelProperty(value="父节点id",example="7eb90bc4036145bc80dcbaba51764961")
	private String parentNodeId;
	//节点类型
	@ApiModelProperty(value="节点类型,atomRule表示原子规则,ruleGroup表示规则集,condition表示规则条件",example="atomRule")
	private String nodeType;
	//排序
	@ApiModelProperty(value="排序",example="1")
	private int orderBy;
	//创建时间
	@JsonFormat(pattern = ConstantConfig.DATE_FORMAT)
    @ApiModelProperty(value="创建时间，日期格式",dataType="Date",example="2019-06-05 10:25:30")
	private Date createTime;
	
	//关联规则id	
	@ApiModelProperty(value="关联规则id",example="7eb90bc4036145bc80dcbaba5176496c")
	private String ruleIdRel;
	
	//关联规则信息
	@ApiModelProperty(value="关联规则信息,and或者or或规则描述",example="告警清除时间-故障发生时间＞30分钟")
	private String ruleInfoRel;
	
	@ApiModelProperty(value="状态，字典值包括enable(表示启用)和 forbidden(表示禁用)",example="enable")
	private String status;

	public CwmRuleGroupRel() {
		super();
	}
	public CwmRuleGroupRel(int orderBy, String ruleInfoRel) {
		super();
		this.orderBy = orderBy;
		this.ruleInfoRel = ruleInfoRel;
	}
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
	 * @return the parentNodeId
	 */
	public String getParentNodeId() {
		return parentNodeId;
	}
	/**
	 * @param parentNodeId the parentNodeId to set
	 */
	public void setParentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
	}
	/**
	 * @return the nodeType
	 */
	public String getNodeType() {
		return nodeType;
	}
	/**
	 * @param nodeType the nodeType to set
	 */
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	/**
	 * @return the orderBy
	 */
	public int getOrderBy() {
		return orderBy;
	}
	/**
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the ruleIdRel
	 */
	public String getRuleIdRel() {
		return ruleIdRel;
	}
	/**
	 * @param ruleIdRel the ruleIdRel to set
	 */
	public void setRuleIdRel(String ruleIdRel) {
		this.ruleIdRel = ruleIdRel;
	}
	/**
	 * @return the ruleInfoRel
	 */
	public String getRuleInfoRel() {
		return ruleInfoRel;
	}
	/**
	 * @param ruleInfoRel the ruleInfoRel to set
	 */
	public void setRuleInfoRel(String ruleInfoRel) {
		this.ruleInfoRel = ruleInfoRel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	
}

