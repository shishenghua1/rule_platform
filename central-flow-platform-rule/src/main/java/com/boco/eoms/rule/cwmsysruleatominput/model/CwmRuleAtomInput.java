package com.boco.eoms.rule.cwmsysruleatominput.model;

import java.util.Date;

import com.boco.eoms.base.util.ConstantConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 原子规则输入属性
 * @author chenjianghe
 *
 */
@ApiModel(value="CwmRuleAtomInput:原子规则输入模型")
public class CwmRuleAtomInput {
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
	 * 输入参数1英文名
	 */
	@ApiModelProperty(value="输入参数1英文名",example="canshu1")
    private String inputEnParam1;
    /**
	 * 输入参数1中文名
	 */
	@ApiModelProperty(value="输入参数1中文名",example="参数1")
    private String inputCnParam1;
    /**
	 * 输入参数1类型
	 */
	@ApiModelProperty(value="输入参数1类型",example="字符常量")
    private String inputParamType1;
    /**
	 * 运算标识1
	 */
	@ApiModelProperty(value="运算标识1",example="+")
    private String operator1;
    /**
	 * 输入参数2英文名
	 */
	@ApiModelProperty(value="输入参数2英文名",example="canshu2")
    private String inputEnParam2;
    /**
   	 * 输入参数2中文名
   	 */
	@ApiModelProperty(value="输入参数2中文名",example="参数2")
    private String inputCnParam2;
    /**
   	 * 输入参数2类型
   	 */
	@ApiModelProperty(value="输入参数2类型",example="时间变量")
    private String inputParamType2;
    /**
   	 * 运算标识2
   	 */
	@ApiModelProperty(value="运算标识2",example="-")
    private String operator2;
    /**
   	 * 输入参数3英文名
   	 */
	@ApiModelProperty(value="输入参数3英文名",example="canshu3")
    private String inputEnParam3;
    /**
   	 * 输入参数3中文名
   	 */
	@ApiModelProperty(value="输入参数3中文名",example="参数3")
    private String inputCnParam3;
    /**
   	 * 输入参数3类型
   	 */
	@ApiModelProperty(value="输入参数3类型",example="数字变量")
    private String inputParamType3;
    /**
   	 * 删除标识
   	 */
	@ApiModelProperty(value="删除标识",example="0")
    private String deleted;
    /**
   	 * 创建人
   	 */
	@ApiModelProperty(value="创建人",example="创建人")
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

    public String getInputEnParam1() {
        return inputEnParam1;
    }

    public void setInputEnParam1(String inputEnParam1) {
        this.inputEnParam1 = inputEnParam1 == null ? null : inputEnParam1.trim();
    }

    public String getInputCnParam1() {
        return inputCnParam1;
    }

    public void setInputCnParam1(String inputCnParam1) {
        this.inputCnParam1 = inputCnParam1 == null ? null : inputCnParam1.trim();
    }

    public String getInputParamType1() {
        return inputParamType1;
    }

    public void setInputParamType1(String inputParamType1) {
        this.inputParamType1 = inputParamType1 == null ? null : inputParamType1.trim();
    }

    public String getOperator1() {
        return operator1;
    }

    public void setOperator1(String operator1) {
        this.operator1 = operator1 == null ? null : operator1.trim();
    }

    public String getInputEnParam2() {
        return inputEnParam2;
    }

    public void setInputEnParam2(String inputEnParam2) {
        this.inputEnParam2 = inputEnParam2 == null ? null : inputEnParam2.trim();
    }

    public String getInputCnParam2() {
        return inputCnParam2;
    }

    public void setInputCnParam2(String inputCnParam2) {
        this.inputCnParam2 = inputCnParam2 == null ? null : inputCnParam2.trim();
    }

    public String getInputParamType2() {
        return inputParamType2;
    }

    public void setInputParamType2(String inputParamType2) {
        this.inputParamType2 = inputParamType2 == null ? null : inputParamType2.trim();
    }

    public String getOperator2() {
        return operator2;
    }

    public void setOperator2(String operator2) {
        this.operator2 = operator2 == null ? null : operator2.trim();
    }

    public String getInputEnParam3() {
        return inputEnParam3;
    }

    public void setInputEnParam3(String inputEnParam3) {
        this.inputEnParam3 = inputEnParam3 == null ? null : inputEnParam3.trim();
    }

    public String getInputCnParam3() {
        return inputCnParam3;
    }

    public void setInputCnParam3(String inputCnParam3) {
        this.inputCnParam3 = inputCnParam3 == null ? null : inputCnParam3.trim();
    }

    public String getInputParamType3() {
        return inputParamType3;
    }

    public void setInputParamType3(String inputParamType3) {
        this.inputParamType3 = inputParamType3 == null ? null : inputParamType3.trim();
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