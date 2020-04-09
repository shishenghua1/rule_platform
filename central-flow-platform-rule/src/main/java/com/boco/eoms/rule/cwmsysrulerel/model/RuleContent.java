package com.boco.eoms.rule.cwmsysrulerel.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ssh
 * @description:
 * @date 2019/10/817:45
 */
@ApiModel(value="RuleContent:规则集合相关内容模型")
public class RuleContent {
    @ApiModelProperty(value="规则集合名称")
    private String groupName ;
    @ApiModelProperty(value="规则集合描述")
    private String groupDescription;
    @ApiModelProperty(value="规则集合输入参数信息")
    private List<ParamInterName> inputParamList;
    @ApiModelProperty(value="规则集合输出参数信息")
    private OutputParamInfo outputParamInfo;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public List<ParamInterName> getInputParamList() {
        return inputParamList;
    }

    public void setInputParamList(List<ParamInterName> inputParamList) {
        this.inputParamList = inputParamList;
    }

    public OutputParamInfo getOutputParamInfo() {
        return outputParamInfo;
    }

    public void setOutputParamInfo(OutputParamInfo outputParamInfo) {
        this.outputParamInfo = outputParamInfo;
    }
}
