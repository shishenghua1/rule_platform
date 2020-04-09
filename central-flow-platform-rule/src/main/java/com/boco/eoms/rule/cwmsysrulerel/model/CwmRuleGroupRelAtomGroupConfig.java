package com.boco.eoms.rule.cwmsysrulerel.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *  规则集合关联原子规则集合信息查询模型
 * @author chenjianghe
 *
 */
@ApiModel(value="CwmRuleGroupRelAtomGroupConfig:规则集合关联原子规则集合信息查询模型")
public class CwmRuleGroupRelAtomGroupConfig {
	@ApiModelProperty(value="规则集合id")
	public String groupId;
	@ApiModelProperty(value="原子规则集合id")
	public String atomGroupId;
	@ApiModelProperty(value="原子规则集合描述")
	public String atomGroupDescription;
	@ApiModelProperty(value="原子规则集合是否启用")
	public String atomGroupEnable;
	@ApiModelProperty(value="是否关联")
	public String related;
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getAtomGroupId() {
		return atomGroupId;
	}
	public void setAtomGroupId(String atomGroupId) {
		this.atomGroupId = atomGroupId;
	}
	public String getAtomGroupDescription() {
		return atomGroupDescription;
	}
	public void setAtomGroupDescription(String atomGroupDescription) {
		this.atomGroupDescription = atomGroupDescription;
	}
	public String getAtomGroupEnable() {
		return atomGroupEnable;
	}
	public void setAtomGroupEnable(String atomGroupEnable) {
		this.atomGroupEnable = atomGroupEnable;
	}
	public String getRelated() {
		return related;
	}
	public void setRelated(String related) {
		this.related = related;
	}
}
