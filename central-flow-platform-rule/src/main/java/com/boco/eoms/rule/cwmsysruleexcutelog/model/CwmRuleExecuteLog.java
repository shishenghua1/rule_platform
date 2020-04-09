package com.boco.eoms.rule.cwmsysruleexcutelog.model;

import java.util.Date;

/**
 * 规则执行记录日志
 * @author chenjianghe
 *
 */
public class CwmRuleExecuteLog {

	/**
	 * 主键id
	 */
	private String id;
	
	/**
	 * 调用方名称
	 */
	private String callerName;
	
	/**
	 * 调用方标识
	 */
	private String callerFlag;
	
	/**
	 * 规则类型 atom原子规则，group规则集合，assembling装配/网关
	 */
	private String ruleType;
	
	/**
	 * 规则id
	 */
	private String ruleId;
	
	/**
	 * 规则名称
	 */
	private String ruleName;
	
	/**
	 * 规则输入
	 */
	private String inputParam;
	
	/**
	 * 规则输出结果
	 */
	private String outputParamResult;
	
	/**
	 * 规则输出描述
	 */
	private String outputParamDesc;
	
	/**
	 * 规则执行时长
	 */
	private String executeTime;
	
	/**
	 * 所属规则id  当前仿真/执行的规则id
	 */
	private String ruleIdRel;
	
	/**
	 * 特殊规则标识 例如AI规则
	 */
	private String ruleFlag;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCallerName() {
		return callerName;
	}

	public void setCallerName(String callerName) {
		this.callerName = callerName;
	}

	public String getCallerFlag() {
		return callerFlag;
	}

	public void setCallerFlag(String callerFlag) {
		this.callerFlag = callerFlag;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getInputParam() {
		return inputParam;
	}

	public void setInputParam(String inputParam) {
		this.inputParam = inputParam;
	}

	public String getOutputParamResult() {
		return outputParamResult;
	}

	public void setOutputParamResult(String outputParamResult) {
		this.outputParamResult = outputParamResult;
	}

	public String getOutputParamDesc() {
		return outputParamDesc;
	}

	public void setOutputParamDesc(String outputParamDesc) {
		this.outputParamDesc = outputParamDesc;
	}

	public String getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
	}

	public String getRuleIdRel() {
		return ruleIdRel;
	}

	public void setRuleIdRel(String ruleIdRel) {
		this.ruleIdRel = ruleIdRel;
	}

	public String getRuleFlag() {
		return ruleFlag;
	}

	public void setRuleFlag(String ruleFlag) {
		this.ruleFlag = ruleFlag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
