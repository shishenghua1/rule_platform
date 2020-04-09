package com.boco.eoms.rule.cwmsysruleinterfacelog.service;

import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.rule.cwmsysruleinterfacelog.model.CwmRuleInterfaceLog;

public interface ICwmRuleInterfaceLogService {
	
	/**
	 * 新增记录
	 * @param record
	 * @return
	 */
    public int insert(CwmRuleInterfaceLog record);
    
    /**
     * 获取日志对象
     * @param map 
     * @return
     */
    public CwmRuleInterfaceLog generateLogModel(JSONObject json);

}
