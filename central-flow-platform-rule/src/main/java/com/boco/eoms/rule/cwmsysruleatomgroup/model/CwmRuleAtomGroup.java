package com.boco.eoms.rule.cwmsysruleatomgroup.model;

import java.util.Date;

import com.boco.eoms.base.util.ConstantConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**

* 创建时间：2019年6月26日 下午4:55:29

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：

*/
@ApiModel(value="CwmRuleAtomGroup:原子规则集合模型")
public class CwmRuleAtomGroup {
	//主键
	@ApiModelProperty(value="主键id",example="7eb90bc4036145bc80dcbaba5176496c")
	private String id;
	//原子规则集合名称	
	@ApiModelProperty(value="原子规则集合名称",example="故障时间")
	private String atomGroupName;
	//原子规则集合描述	
	@ApiModelProperty(value="原子规则集合描述	",example="故障时间>100天")
	private String atomGroupDescription;
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
	/**
	 * @return the atom_group_name
	 */
	public String getAtomGroupName() {
		return atomGroupName;
	}
	/**
	 * @param atom_group_name the atom_group_name to set
	 */

	public void setAtomGroupName(String atomGroupName) {
		this.atomGroupName = atomGroupName;
	}
	/**
	 * @return the atomGroupDescription
	 */
	public String getAtomGroupDescription() {
		return atomGroupDescription;
	}
	/**
	 * @param atomGroupDescription the atomGroupDescription to set
	 */
	public void setAtomGroupDescription(String atomGroupDescription) {
		this.atomGroupDescription = atomGroupDescription;
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
	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}
	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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
	
}

