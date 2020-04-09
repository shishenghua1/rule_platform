package com.boco.eoms.rule.cwmsysdatadict.model;

import java.util.Date;

import com.boco.eoms.base.util.ConstantConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
/**
 * 数据字典表
 * 2019年7月17日下午3:17:47
 *ssh
 */
public class CwmDataDict {
	//主键id
	@ApiModelProperty(value="系统生成,主键id",example="7eb90bc4036145bc80dcbaba5176496c")
    private String id;
    //数据表常量类型字段id
	@ApiModelProperty(value="数据表常量类型字段id",example="7eb90bc4036145bc80dcbaba5176496c")
    private String parentDictId;
    //字典id	
	@ApiModelProperty(value="系统生成,字典id",example="7eb90bc4036145bc80dcbaba5176496c")
    private String dictId;
    //字典中文名称	
	@ApiModelProperty(value="字典中文名称",example="派单时间")
    private String dictCnName;
	//字典英文名称	
	@ApiModelProperty(value="字典英文名称",example="派单时间")
    private String dictEnName;
    //字典类型	
	@ApiModelProperty(value="字典类型",example="字符串")
    private String dictType;
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
    //数据模块id
    @ApiModelProperty(value="数据模块id",example="7eb90bc4036145bc80dcbaba5176496c")
    private String dataModuleId;

    public String getDataModuleId() {
        return dataModuleId;
    }

    public void setDataModuleId(String dataModuleId) {
        this.dataModuleId = dataModuleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentDictId() {
        return parentDictId;
    }

    public void setParentDictId(String parentDictId) {
        this.parentDictId = parentDictId == null ? null : parentDictId.trim();
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId == null ? null : dictId.trim();
    }


    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType == null ? null : dictType.trim();
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
	 * @return the dictCnName
	 */
	public String getDictCnName() {
		return dictCnName;
	}

	/**
	 * @param dictCnName the dictCnName to set
	 */
	public void setDictCnName(String dictCnName) {
		this.dictCnName = dictCnName;
	}

	/**
	 * @return the dictEnName
	 */
	public String getDictEnName() {
		return dictEnName;
	}

	/**
	 * @param dictEnName the dictEnName to set
	 */
	public void setDictEnName(String dictEnName) {
		this.dictEnName = dictEnName;
	}
    
}