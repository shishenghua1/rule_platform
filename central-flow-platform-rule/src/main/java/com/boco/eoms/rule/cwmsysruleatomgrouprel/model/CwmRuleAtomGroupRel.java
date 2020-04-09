package com.boco.eoms.rule.cwmsysruleatomgrouprel.model;

import java.util.Date;

import com.boco.eoms.base.util.ConstantConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**

* 创建时间：2019年6月28日 下午5:52:19

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：原子规则集合关联表

*/
@ApiModel(value="CwmRuleAtomGroupRel:原子规则集合关联模型")
public class CwmRuleAtomGroupRel {
	//主键id
	@ApiModelProperty(value="主键id",example="7eb90bc4036145bc80dcbaba5176496c")
	private String id;
	//原子规则集合id	
	@ApiModelProperty(value="原子规则集合id",example="7eb90bc4036145bc80dcbaba5176496c")
	private String atomGroupId;
	//关联原子规则id	
	@ApiModelProperty(value="关联原子规则id",example="7eb90bc4036145bc80dcbaba5176496c")
	private String atomId;
	//显示排序	
	@ApiModelProperty(value="显示排序",example="1")
	private int orderBy;
	//执行条件	
	@ApiModelProperty(value="执行条件",example="and")
	private String executeCondtion;
	//删除标识	
	@ApiModelProperty(value="删除标识",example="1")
	private String deleted;
	//创建人	
	@ApiModelProperty(value="创建人",example="admin")
	private String createUserId;
	//创建时间	
	@JsonFormat(pattern = ConstantConfig.DATE_FORMAT)
    @ApiModelProperty(value="创建时间，日期格式",dataType="Date",example="2019-06-05 10:25:30")
	private Date createTime;
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
	public String getAtomGroupId() {
		return atomGroupId;
	}
	public void setAtomGroupId(String atomGroupId) {
		this.atomGroupId = atomGroupId;
	}
	public String getAtomId() {
		return atomId;
	}
	public void setAtomId(String atomId) {
		this.atomId = atomId;
	}
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	public String getExecuteCondtion() {
		return executeCondtion;
	}
	public void setExecuteCondtion(String executeCondtion) {
		this.executeCondtion = executeCondtion;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

