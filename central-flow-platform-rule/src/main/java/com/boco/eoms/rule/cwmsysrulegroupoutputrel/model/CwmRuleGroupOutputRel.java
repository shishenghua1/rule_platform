package com.boco.eoms.rule.cwmsysrulegroupoutputrel.model;

import java.util.Date;

import com.boco.eoms.base.util.ConstantConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**

* 创建时间：2019年6月28日 下午3:25:50

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：规则集合输出关联表

*/
@ApiModel(value="CwmRuleGroupOutputRel:规则集合输出关联表")
public class CwmRuleGroupOutputRel {
	//主键id
	@ApiModelProperty(value="主键id",example="7eb90bc4036145bc80dcbaba5176496c")
    private String id;
	//规则集合输出表id
	@ApiModelProperty(value="规则集合输出表id",example="7eb90bc4036145bc80dcbaba5176496c")
    private String groupOutputId;
	//规则集合id的关联
	@ApiModelProperty(value="关联规则集合id",example="7eb90bc4036145bc80dcbaba5176496c")
    private String groupIdRel;
	//关联规则集合输出的结果
	@ApiModelProperty(value="关联规则集合输出的结果",example="true")
    private String groupOutputResultRel;
	//规则集合输出结果说明的关联
	@ApiModelProperty(value="规则集合输出结果说明的关联",example="true")
    private String groupOutputExplainRel;
	@ApiModelProperty(value="是否删除",example="0")
    private String deleted;
	@ApiModelProperty(value="创建人",example="admin")
    private String createUserId;
	@JsonFormat(pattern = ConstantConfig.DATE_FORMAT)
    @ApiModelProperty(value="创建时间，日期格式",dataType="Date",example="2019-06-05 10:25:30")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGroupOutputId() {
        return groupOutputId;
    }

    public void setGroupOutputId(String groupOutputId) {
        this.groupOutputId = groupOutputId == null ? null : groupOutputId.trim();
    }

    public String getGroupIdRel() {
        return groupIdRel;
    }

    public void setGroupIdRel(String groupIdRel) {
        this.groupIdRel = groupIdRel == null ? null : groupIdRel.trim();
    }

    public String getGroupOutputResultRel() {
        return groupOutputResultRel;
    }

    public void setGroupOutputResultRel(String groupOutputResultRel) {
        this.groupOutputResultRel = groupOutputResultRel == null ? null : groupOutputResultRel.trim();
    }

    public String getGroupOutputExplainRel() {
        return groupOutputExplainRel;
    }

    public void setGroupOutputExplainRel(String groupOutputExplainRel) {
        this.groupOutputExplainRel = groupOutputExplainRel == null ? null : groupOutputExplainRel.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }
}