package com.boco.eoms.rule.cwmsysrulerel.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ssh
 * @description:
 * @date 2019/10/99:21
 */
@ApiModel(value="OutputParamInfo:规则集合输出参数信息")
public class OutputParamInfo {
    @ApiModelProperty(value="规则集合输出参数数量",example="10")
    private int outputParamNum;
    @ApiModelProperty(value="规则集合输出ture的相关内容")
    private OutPutParamDetail outputTrueInfo;
    @ApiModelProperty(value="规则集合输出false的相关内容")
    private OutPutParamDetail outputFalseInfo;

    public int getOutputParamNum() {
        return outputParamNum;
    }

    public void setOutputParamNum(int outputParamNum) {
        this.outputParamNum = outputParamNum;
    }

    public OutPutParamDetail getOutputTrueInfo() {
        return outputTrueInfo;
    }

    public void setOutputTrueInfo(OutPutParamDetail outputTrueInfo) {
        this.outputTrueInfo = outputTrueInfo;
    }

    public OutPutParamDetail getOutputFalseInfo() {
        return outputFalseInfo;
    }

    public void setOutputFalseInfo(OutPutParamDetail outputFalseInfo) {
        this.outputFalseInfo = outputFalseInfo;
    }
}
