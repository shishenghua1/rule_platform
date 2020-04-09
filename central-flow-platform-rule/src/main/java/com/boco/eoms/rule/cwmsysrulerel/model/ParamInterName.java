package com.boco.eoms.rule.cwmsysrulerel.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ssh
 * @description:
 * @date 2019/10/2110:02
 */
@ApiModel(value="ParamInterName:接口参数对象")
public class ParamInterName {
    @ApiModelProperty(value="接口参数英文名",example="goajing")
    private String paramInterEnName;
    @ApiModelProperty(value="接口参数参数中文名",example="告警")
    private String paramInterCnName;

    public String getParamInterEnName() {
        return paramInterEnName;
    }

    public void setParamInterEnName(String paramInterEnName) {
        this.paramInterEnName = paramInterEnName;
    }

    public String getParamInterCnName() {
        return paramInterCnName;
    }

    public void setParamInterCnName(String paramInterCnName) {
        this.paramInterCnName = paramInterCnName;
    }
}
