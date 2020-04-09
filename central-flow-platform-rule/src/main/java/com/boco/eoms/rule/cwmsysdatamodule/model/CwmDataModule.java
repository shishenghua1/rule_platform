package com.boco.eoms.rule.cwmsysdatamodule.model;

import java.util.Date;

import com.boco.eoms.base.util.ConstantConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 数据模块表
 * 2019年7月2日上午10:05:05
 *ssh
 */
@ApiModel(value="数据模块模型")
public class CwmDataModule {
	//主键id
	@ApiModelProperty(value="主键id",example="7eb90bc4036145bc80dcbaba5176496c")
    private String id;
    //数据所属模块	
	@ApiModelProperty(value="数据所属模块",example="投诉工单模型数据")
    private String dataModule;
    //数据版本	
	@ApiModelProperty(value="数据版本",example="V1")
    private String dataVersion;
	//数据版本	
	@ApiModelProperty(value="数据类型,variable(变量)或者constant(常量)",example="variable")
    private String dataType;
    //描述
	@ApiModelProperty(value="描述",example="投诉工单信息")
    private String description;
    //删除标识	
	@ApiModelProperty(value="删除标识",example="0")
    private String deleted;
    //创建时间
	@JsonFormat(pattern = ConstantConfig.DATE_FORMAT)
    @ApiModelProperty(value="创建时间，日期格式",dataType="Date",example="2019-06-05 10:25:30")
    private Date createTime;
    //创建人
	@ApiModelProperty(value="创建人",example="admin")
    private String createUserId;

	 //模块分类对应标识	
	@ApiModelProperty(value="模块分类对应标识",example="7eb90bc4036145bc80dcbaba5176496c")
    private String moduleId;
	
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDataModule() {
        return dataModule;
    }

    public void setDataModule(String dataModule) {
        this.dataModule = dataModule == null ? null : dataModule.trim();
    }

    public String getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(String dataVersion) {
        this.dataVersion = dataVersion == null ? null : dataVersion.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * @return the moduleId
	 */
	public String getModuleId() {
		return moduleId;
	}

	/**
	 * @param moduleId the moduleId to set
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
    
}