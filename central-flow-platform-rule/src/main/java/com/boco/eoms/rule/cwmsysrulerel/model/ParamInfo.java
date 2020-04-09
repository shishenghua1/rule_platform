package com.boco.eoms.rule.cwmsysrulerel.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**

* 创建时间：2019年7月22日 上午9:46:30

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：参数信息

*/
@ApiModel(value="ParamInfo:规则集参数信息模型")
public class ParamInfo {
	@ApiModelProperty(value="参数英文名",example="goajing")
	private String inputEnParam;
	@ApiModelProperty(value="参数中文名",example="告警")
	private String inputCnParam;
	@ApiModelProperty(value="参数类型",example="字符变量")
	private String inputParamType;
	/**
	 * @return the inputEnParam
	 */
	public String getInputEnParam() {
		return inputEnParam;
	}
	/**
	 * @param inputEnParam the inputEnParam to set
	 */
	public void setInputEnParam(String inputEnParam) {
		this.inputEnParam = inputEnParam;
	}
	/**
	 * @return the inputCnParam
	 */
	public String getInputCnParam() {
		return inputCnParam;
	}
	/**
	 * @param inputCnParam the inputCnParam to set
	 */
	public void setInputCnParam(String inputCnParam) {
		this.inputCnParam = inputCnParam;
	}
	/**
	 * @return the inputParamType
	 */
	public String getInputParamType() {
		return inputParamType;
	}
	/**
	 * @param inputParamType the inputParamType to set
	 */
	public void setInputParamType(String inputParamType) {
		this.inputParamType = inputParamType;
	}
	
	
}

