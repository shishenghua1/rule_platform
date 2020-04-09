package com.boco.eoms.rule.cwmsysrulerel.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ssh
 * @description:
 * @date 2019/10/1110:35
 */
@ApiModel(value="OutPutParamDetail:规则集合输出参数详情")
public class OutPutParamDetail {
    @ApiModelProperty(value="规则集合输出结果",example="true")
    private String outputResult;
    @ApiModelProperty(value="规则集合输出参数list")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private List<ParamInterName> outputParamList;

    public String getOutputResult() {
        return outputResult;
    }

    public void setOutputResult(String outputResult) {
        this.outputResult = outputResult;
    }

    public List<ParamInterName> getOutputParamList() {
        return outputParamList;
    }

    public void setOutputParamList(List<ParamInterName> outputParamList) {
        this.outputParamList = outputParamList;
    }
}
