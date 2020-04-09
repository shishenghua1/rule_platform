package com.boco.eoms.rule.cwmsysrulegroupoutputparams.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**

* 创建时间：2019年6月28日 下午3:30:17

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：规则集合输出参数表

*/
@ApiModel(value="CwmRuleGroupOutputParams:规则集合输出参数表")
public class CwmRuleGroupOutputParams {
	//主键id
	@ApiModelProperty(value="主键id",example="7eb90bc4036145bc80dcbaba5176496c")
	private String id;
	//输出参数集合id
	@ApiModelProperty(value="输出参数集合id",example="7eb90bc4036145bc80dcbaba5176496c")
	private String groupOutputParamsId;
	@ApiModelProperty(value="输出参数1英文名",example="gaojingqingchu")
	private String outputEnParam1;
	@ApiModelProperty(value="输出参数1中文名",example="告警清除时间")
    private String outputCnParam1;
	@ApiModelProperty(value="输出参数1类型(字符常量、字符变量,数字常量、数字变量，时间常量、时间变量，计算变量)",example="字符变量")
    private String outputParamType1;
	@ApiModelProperty(value="运算标识1",example="+")
    private String operator1;
	@ApiModelProperty(value="输出参数2英文名",example="guzhangfasheng")
    private String outputEnParam2;
    @ApiModelProperty(value="输出参数2中文名",example="故障发生时间")
    private String outputCnParam2;
    @ApiModelProperty(value="输出参数2类型(字符常量、字符变量,数字常量、数字变量，时间常量、时间变量，计算变量)",example="字符常量")
    private String outputParamType2;
    @ApiModelProperty(value="运算标识2",example="=")
    private String operator2;
    @ApiModelProperty(value="输出参数3英文名",example="status")
    private String outputEnParam3;
    @ApiModelProperty(value="输出参数3中文名",example="状态")
    private String outputCnParam3;
    @ApiModelProperty(value="输出参数3类型(字符常量、字符变量,数字常量、数字变量，时间常量、时间变量，计算变量)",example="字符常量")
    private String outputParamType3;
    @ApiModelProperty(value="输出参数1来源，字典值包括ruleLibrary（规则库）和directInput(直接输入)",example="ruleLibrary")
    private String outputParamOrigin1;
    @ApiModelProperty(value="输出参数2来源，字典值包括ruleLibrary（规则库）和directInput(直接输入)",example="ruleLibrary")
    private String outputParamOrigin2;
    @ApiModelProperty(value="输出参数3来源，字典值包括ruleLibrary（规则库）和directInput(直接输入)",example="directInput")
    private String outputParamOrigin3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGroupOutputParamsId() {
        return groupOutputParamsId;
    }

    public void setGroupOutputParamsId(String groupOutputParamsId) {
        this.groupOutputParamsId = groupOutputParamsId == null ? null : groupOutputParamsId.trim();
    }

    public String getOutputEnParam1() {
        return outputEnParam1;
    }

    public void setOutputEnParam1(String outputEnParam1) {
        this.outputEnParam1 = outputEnParam1 == null ? null : outputEnParam1.trim();
    }

    public String getOutputCnParam1() {
        return outputCnParam1;
    }

    public void setOutputCnParam1(String outputCnParam1) {
        this.outputCnParam1 = outputCnParam1 == null ? null : outputCnParam1.trim();
    }

    public String getOutputParamType1() {
        return outputParamType1;
    }

    public void setOutputParamType1(String outputParamType1) {
        this.outputParamType1 = outputParamType1 == null ? null : outputParamType1.trim();
    }

    public String getOperator1() {
        return operator1;
    }

    public void setOperator1(String operator1) {
        this.operator1 = operator1 == null ? null : operator1.trim();
    }

    public String getOutputEnParam2() {
        return outputEnParam2;
    }

    public void setOutputEnParam2(String outputEnParam2) {
        this.outputEnParam2 = outputEnParam2 == null ? null : outputEnParam2.trim();
    }

    public String getOutputCnParam2() {
        return outputCnParam2;
    }

    public void setOutputCnParam2(String outputCnParam2) {
        this.outputCnParam2 = outputCnParam2 == null ? null : outputCnParam2.trim();
    }

    public String getOutputParamType2() {
        return outputParamType2;
    }

    public void setOutputParamType2(String outputParamType2) {
        this.outputParamType2 = outputParamType2 == null ? null : outputParamType2.trim();
    }

    public String getOperator2() {
        return operator2;
    }

    public void setOperator2(String operator2) {
        this.operator2 = operator2 == null ? null : operator2.trim();
    }

    public String getOutputEnParam3() {
        return outputEnParam3;
    }

    public void setOutputEnParam3(String outputEnParam3) {
        this.outputEnParam3 = outputEnParam3 == null ? null : outputEnParam3.trim();
    }

    public String getOutputCnParam3() {
        return outputCnParam3;
    }

    public void setOutputCnParam3(String outputCnParam3) {
        this.outputCnParam3 = outputCnParam3 == null ? null : outputCnParam3.trim();
    }

    public String getOutputParamType3() {
        return outputParamType3;
    }

    public void setOutputParamType3(String outputParamType3) {
        this.outputParamType3 = outputParamType3 == null ? null : outputParamType3.trim();
    }

    public String getOutputParamOrigin1() {
        return outputParamOrigin1;
    }

    public void setOutputParamOrigin1(String outputParamOrigin1) {
        this.outputParamOrigin1 = outputParamOrigin1;
    }

    public String getOutputParamOrigin2() {
        return outputParamOrigin2;
    }

    public void setOutputParamOrigin2(String outputParamOrigin2) {
        this.outputParamOrigin2 = outputParamOrigin2;
    }

    public String getOutputParamOrigin3() {
        return outputParamOrigin3;
    }

    public void setOutputParamOrigin3(String outputParamOrigin3) {
        this.outputParamOrigin3 = outputParamOrigin3;
    }
}

