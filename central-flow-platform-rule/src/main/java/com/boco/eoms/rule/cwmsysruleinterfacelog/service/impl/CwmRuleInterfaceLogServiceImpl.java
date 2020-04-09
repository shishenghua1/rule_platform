package com.boco.eoms.rule.cwmsysruleinterfacelog.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.rule.cwmsysruleinterfacelog.mapper.CwmRuleInterfaceLogMapper;
import com.boco.eoms.rule.cwmsysruleinterfacelog.model.CwmRuleInterfaceLog;
import com.boco.eoms.rule.cwmsysruleinterfacelog.service.ICwmRuleInterfaceLogService;

@Service
public class CwmRuleInterfaceLogServiceImpl implements ICwmRuleInterfaceLogService{

	@Autowired
	private CwmRuleInterfaceLogMapper cwmRuleInterfaceLogMapper;
	
	/**
	 * 新增记录
	 * @param record
	 * @return
	 */
	@Override
	public int insert(CwmRuleInterfaceLog record) {
		try {
			String id = StaticMethod.nullObject2String(record.getId());
			if("".equals(id)) {
				record.setId(UUIDHexGenerator.getInstance().getID());
			}
			return cwmRuleInterfaceLogMapper.insert(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
     * 获取日志对象
     * @param map
     * @return
     */
	@Override
	public CwmRuleInterfaceLog generateLogModel(JSONObject json) {
		CwmRuleInterfaceLog cwmRuleInterfaceLog = new CwmRuleInterfaceLog();
		cwmRuleInterfaceLog.setRuleId(StaticMethod.nullObject2String(json.get("ruleId")));
		cwmRuleInterfaceLog.setInputParams(StaticMethod.nullObject2String(json.get("inputParams")));
		cwmRuleInterfaceLog.setOutputParams(StaticMethod.nullObject2String(json.get("outputParams")));
		cwmRuleInterfaceLog.setExceptionMsg(StaticMethod.nullObject2String(json.get("exceptionMsg")));
		cwmRuleInterfaceLog.setInterfaceCnName(StaticMethod.nullObject2String(json.get("interfaceCnName")));
		cwmRuleInterfaceLog.setInterfaceEnName(StaticMethod.nullObject2String(json.get("interfaceEnName")));
		cwmRuleInterfaceLog.setCreateTime(new Date());
		return cwmRuleInterfaceLog;
	}

	
}
