package com.boco.eoms.rule.cwmsysrulegrouplibraryrel.model;

import com.boco.eoms.base.util.ConstantConfig;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
* @description: 规则集合库关联表
* @author ssh
* @date 2019/8/28 11:17 
*/
@ApiModel(value="CwmRuleGroupLibraryRel:规则集合库模型")
public class CwmRuleGroupLibraryRel {
    @ApiModelProperty(value="主键id",example="7eb90bc4036145bc80dcbaba5176496c")
    private String id;
    @ApiModelProperty(value="规则集合id",example="7eb90bc4036145bc80dcbaba51764961")
    private String groupId;
    @ApiModelProperty(value="数据模块id",example="7eb90bc4036145bc80dcbaba51764961")
    private String dataModuleId;
    @ApiModelProperty(value="数据模块名字",example="故障工单")
    private String dataModule;
    @ApiModelProperty(value="变量",example="variable")
    private String dataType;
    @JsonFormat(pattern = ConstantConfig.DATE_FORMAT)
    @ApiModelProperty(value="创建时间，日期格式",dataType="Date",example="2019-06-05 10:25:30")
    private Date createTime;
    @ApiModelProperty(value="字段中文名(包括字典值)",example="成功")
    private String fieldCnName;
    @ApiModelProperty(value="字段英文名(包括字典值)",example="success")
    private String fieldEnName;

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

    public String getDataModuleId() {
        return dataModuleId;
    }

    public void setDataModuleId(String dataModuleId) {
        this.dataModuleId = dataModuleId == null ? null : dataModuleId.trim();
    }

    public String getDataModule() {
        return dataModule;
    }

    public void setDataModule(String dataModule) {
        this.dataModule = dataModule == null ? null : dataModule.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFieldCnName() {
        return fieldCnName;
    }

    public void setFieldCnName(String fieldCnName) {
        this.fieldCnName = fieldCnName;
    }

    public String getFieldEnName() {
        return fieldEnName;
    }

    public void setFieldEnName(String fieldEnName) {
        this.fieldEnName = fieldEnName;
    }
}