package com.boco.eoms.rule.cwmsysruleinterfacelog.model;

import java.util.Date;

/**
 * 规则调用接口日志记录
 * @author chenjianghe
 *
 */
public class CwmRuleInterfaceLog {
    private String id;
    
    private String ruleId;

    private String interfaceEnName;

    private String interfaceCnName;

    private String inputParams;

    private String outputParams;

    private String exceptionMsg;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getInterfaceEnName() {
        return interfaceEnName;
    }

    public void setInterfaceEnName(String interfaceEnName) {
        this.interfaceEnName = interfaceEnName == null ? null : interfaceEnName.trim();
    }

    public String getInterfaceCnName() {
        return interfaceCnName;
    }

    public void setInterfaceCnName(String interfaceCnName) {
        this.interfaceCnName = interfaceCnName == null ? null : interfaceCnName.trim();
    }

    public String getInputParams() {
        return inputParams;
    }

    public void setInputParams(String inputParams) {
        this.inputParams = inputParams == null ? null : inputParams.trim();
    }

    public String getOutputParams() {
        return outputParams;
    }

    public void setOutputParams(String outputParams) {
        this.outputParams = outputParams == null ? null : outputParams.trim();
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg == null ? null : exceptionMsg.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
}