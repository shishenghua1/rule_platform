package com.boco.eoms.rule.cwmsysruleatomoutput.model;

import java.util.Date;

import com.boco.eoms.base.util.ConstantConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 原子规则输出属性
 * @author chenjianghe
 *
 */
@ApiModel(value="CwmRuleAtomOutput:原子规则输出模型")
public class CwmRuleAtomOutput {
	/**
	 * 主键id
	 */
	@ApiModelProperty(value="主键id",example="7eb90bc4036145bc80dcbaba5176496c")
    private String id;
    /**
	 * 所属原子规则id
	 */
	@ApiModelProperty(value="所属原子规则id",example="7eb90bc4036145bc80dcbaba5176496c")
    private String atomId;
    /**
	 * 原子输入规则id
	 */
	@ApiModelProperty(value="原子输入规则id",example="7eb90bc4036145bc80dcbaba5176496c")
    private String atomInputId;
    /**
	 * 原子输出类型
	 */
	@ApiModelProperty(value="原子输出类型",example="满足条件")
    private String atomOutputType;
    /**
	 * 对应输出结果
	 */
	@ApiModelProperty(value="对应输出结果",example="true")
    private String paramResult;
    /**
	 * 对应输出结果说明
	 */
	@ApiModelProperty(value="对应输出结果说明",example="满足")
    private String paramResultDescription;
    /**
	 * 接口参数英文名
	 */
	@ApiModelProperty(value="接口参数英文名",example="jiekou1")
    private String paramInterEnName;
    /**
	 * 接口参数中文名
	 */
	@ApiModelProperty(value="接口参数中文名",example="接口1")
    private String paramInterCnName;
    /**
	 * 显示排序
	 */
	@ApiModelProperty(value="显示排序",example="1")
    private int orderBy;
    /**
	 * 删除标识
	 */
	@ApiModelProperty(value="删除标识",example="0")
    private String deleted;
    /**
	 * 创建人
	 */
	@ApiModelProperty(value="创建人",example="admin")
    private String createUserId;
    /**
	 * 创建时间
	 */
	@JsonFormat(pattern = ConstantConfig.DATE_FORMAT)
    @ApiModelProperty(value="创建时间，日期格式",dataType="Date",example="2019-06-05 10:25:30")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAtomId() {
        return atomId;
    }

    public void setAtomId(String atomId) {
        this.atomId = atomId == null ? null : atomId.trim();
    }

    public String getAtomInputId() {
        return atomInputId;
    }

    public void setAtomInputId(String atomInputId) {
        this.atomInputId = atomInputId == null ? null : atomInputId.trim();
    }

    public String getAtomOutputType() {
        return atomOutputType;
    }

    public void setAtomOutputType(String atomOutputType) {
        this.atomOutputType = atomOutputType == null ? null : atomOutputType.trim();
    }

    public String getParamResult() {
        return paramResult;
    }

    public void setParamResult(String paramResult) {
        this.paramResult = paramResult == null ? null : paramResult.trim();
    }

    public String getParamResultDescription() {
        return paramResultDescription;
    }

    public void setParamResultDescription(String paramResultDescription) {
        this.paramResultDescription = paramResultDescription == null ? null : paramResultDescription.trim();
    }

    public String getParamInterEnName() {
        return paramInterEnName;
    }

    public void setParamInterEnName(String paramInterEnName) {
        this.paramInterEnName = paramInterEnName == null ? null : paramInterEnName.trim();
    }

    public String getParamInterCnName() {
        return paramInterCnName;
    }

    public void setParamInterCnName(String paramInterCnName) {
        this.paramInterCnName = paramInterCnName == null ? null : paramInterCnName.trim();
    }

    public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
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