package com.boco.eoms.rule.cwmsysdatadetail.model;

import java.util.Date;

import com.boco.eoms.base.util.ConstantConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 数据详情表
 * 2019年7月2日上午10:04:49
 *ssh
 */
@ApiModel(value=" 数据详情模型")
public class CwmDataDetail {
	//主键id	
	@ApiModelProperty(value="系统生成,主键id",example="7eb90bc4036145bc80dcbaba5176496c")
    private String id;
    //数据模块id
	@ApiModelProperty(value="数据模块id",example="7eb90bc4036145bc80dcbaba5176496c")
    private String dataModuleId;
    //字段中文名
	@ApiModelProperty(value="字段中文名",example="紧急程度")
    private String fieldCnName;
    //字段英文名
	@ApiModelProperty(value="字段英文名",example="jinjichengdu")
    private String fieldEnName;
    //字段类型	
	@ApiModelProperty(value="字段类型,包括字符常量、字符变量,数字常量、数字变量,时间常量、时间变量,计算变量",example="字符变量")
    private String fieldType;
	//计算公式	
	@ApiModelProperty(value="计算公式",example="a-b=c")
    private String fieldCalculate;
	//计算公式描述	
	@ApiModelProperty(value="计算公式描述",example="派单总量-异常派单=成功派单")
    private String fieldCalculateDescribe;
	//计算公式json串
	@ApiModelProperty(value="计算公式json串",example="")
	private String fieldCalculateJson;
    //删除标识
	@ApiModelProperty(value="删除标识",example="0")
    private String deleted;
    //创建时间	
	@JsonFormat(pattern = ConstantConfig.DATE_FORMAT)
    @ApiModelProperty(value="创建时间",dataType="Date",example="2019-06-05 10:25:30")
    private Date createTime;
    //创建人	
	@ApiModelProperty(value="创建人",example="admin")
    private String createUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDataModuleId() {
        return dataModuleId;
    }

    public void setDataModuleId(String dataModuleId) {
        this.dataModuleId = dataModuleId == null ? null : dataModuleId.trim();
    }

    public String getFieldCnName() {
        return fieldCnName;
    }

    public void setFieldCnName(String fieldCnName) {
        this.fieldCnName = fieldCnName == null ? null : fieldCnName.trim();
    }

    public String getFieldEnName() {
        return fieldEnName;
    }

    public void setFieldEnName(String fieldEnName) {
        this.fieldEnName = fieldEnName == null ? null : fieldEnName.trim();
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType == null ? null : fieldType.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
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
	 * @return the fieldCalculate
	 */
	public String getFieldCalculate() {
		return fieldCalculate;
	}

	/**
	 * @param fieldCalculate the fieldCalculate to set
	 */
	public void setFieldCalculate(String fieldCalculate) {
		this.fieldCalculate = fieldCalculate;
	}

	/**
	 * @return the fieldCalculateDescribe
	 */
	public String getFieldCalculateDescribe() {
		return fieldCalculateDescribe;
	}

	/**
	 * @param fieldCalculateDescribe the fieldCalculateDescribe to set
	 */
	public void setFieldCalculateDescribe(String fieldCalculateDescribe) {
		this.fieldCalculateDescribe = fieldCalculateDescribe;
	}

	public String getFieldCalculateJson() {
		return fieldCalculateJson;
	}

	public void setFieldCalculateJson(String fieldCalculateJson) {
		this.fieldCalculateJson = fieldCalculateJson;
	}
}