package com.boco.eoms.rule.cwmsysrulemodules.model;

import java.util.Date;

import com.boco.eoms.base.util.ConstantConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 规则平台模块分类表
 * 2019年5月27日下午3:13:40
 *ssh
 */
@ApiModel(value="规则模块模型")
public class CwmRuleModules {

	@ApiModelProperty(value="主键id",example="7eb90bc4036145bc80dcbaba5176496c")
    private String id;
    //模块名称	
	@ApiModelProperty(value="规则模块名称",example="故障工单")
    private String moduleName;
    //父模块Id	
	@ApiModelProperty(value="规则模块父级id",example="7eb90bc4036145bc80dcbaba5176496c",hidden=true)
    private String parentModuleId;
	@ApiModelProperty(value="逻辑删除,0表示不删除，1表示删除",example="0")
    private String deleted;
	@ApiModelProperty(value="数据创建的关联英文用户名",example="admin")
    private String createUserId;
	@JsonFormat(pattern = ConstantConfig.DATE_FORMAT)
    @ApiModelProperty(value="创建时间，日期格式",dataType="Date",example="2019-06-05 10:25:30")
    private Date createTime;
    
    //关联系统id	
	@ApiModelProperty(value="系统标识",example="7eb90bc4036145bc80dcbaba5176496c")
    private String appId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getParentModuleId() {
        return parentModuleId;
    }

    public void setParentModuleId(String parentModuleId) {
        this.parentModuleId = parentModuleId == null ? null : parentModuleId.trim();
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

	/**
	 * @return the appId
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * @param appId the appId to set
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}
    
}