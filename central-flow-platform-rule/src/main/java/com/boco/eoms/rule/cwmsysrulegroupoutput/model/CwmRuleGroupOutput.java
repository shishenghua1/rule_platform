package com.boco.eoms.rule.cwmsysrulegroupoutput.model;

import java.util.Date;

import com.boco.eoms.base.util.ConstantConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 规则集合输出属性表
 * 2019年5月28日下午3:31:16
 *ssh
 */
@ApiModel(value="CwmRuleGroupOutput:规则集合输出属性表")
public class CwmRuleGroupOutput {
	@ApiModelProperty(value="主键id",example="7eb90bc4036145bc80dcbaba5176496c")
    private String id;
    //规则集合id
	@ApiModelProperty(value="规则集合id",example="7eb90bc4036145bc80dcbaba51764961")
    private String groupId;
    //规则集合输出结果	
	@ApiModelProperty(value="规则集合输出结果",example="true")
    private String groupOutputResult;
    //规则集合输出结果说明	
	@ApiModelProperty(value="规则集合输出结果说明",example="故障成功解决")
    private String groupOutputExplain;
    //输出参数集合id	
	@ApiModelProperty(value="输出参数集合id",example="7eb90bc4036145bc80dcbaba5176496c")
    private String groupOutputParamsId;
    //输出参数集合结果	
	@ApiModelProperty(value="输出参数集合结果",example="json格式字符串")
    private String groupOutputParamsResult;
	@ApiModelProperty(value="是否删除",example="0")
    private String deleted;
	@ApiModelProperty(value="创建人",example="admin")
    private String createUserId;
	@JsonFormat(pattern = ConstantConfig.DATE_FORMAT)
    @ApiModelProperty(value="创建时间，日期格式",dataType="Date",example="2019-06-05 10:25:30")
    private Date createTime;

    public CwmRuleGroupOutput() {
		super();
	}
	public CwmRuleGroupOutput(String groupOutputResult, String groupOutputExplain) {
		super();
		this.groupOutputResult = groupOutputResult;
		this.groupOutputExplain = groupOutputExplain;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

	public String getGroupOutputResult() {
        return groupOutputResult;
    }

    public void setGroupOutputResult(String groupOutputResult) {
        this.groupOutputResult = groupOutputResult == null ? null : groupOutputResult.trim();
    }

    /**
	 * @return the groupOutputExplain
	 */
	public String getGroupOutputExplain() {
		return groupOutputExplain;
	}
	/**
	 * @param groupOutputExplain the groupOutputExplain to set
	 */
	public void setGroupOutputExplain(String groupOutputExplain) {
		this.groupOutputExplain = groupOutputExplain;
	}
	public String getGroupOutputParamsId() {
        return groupOutputParamsId;
    }

    public void setGroupOutputParamsId(String groupOutputParamsId) {
        this.groupOutputParamsId = groupOutputParamsId == null ? null : groupOutputParamsId.trim();
    }

    public String getGroupOutputParamsResult() {
        return groupOutputParamsResult;
    }

    public void setGroupOutputParamsResult(String groupOutputParamsResult) {
        this.groupOutputParamsResult = groupOutputParamsResult == null ? null : groupOutputParamsResult.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}