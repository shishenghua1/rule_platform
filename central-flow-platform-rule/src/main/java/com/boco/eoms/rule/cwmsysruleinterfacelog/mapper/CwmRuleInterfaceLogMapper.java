package com.boco.eoms.rule.cwmsysruleinterfacelog.mapper;

import com.boco.eoms.rule.cwmsysruleinterfacelog.model.CwmRuleInterfaceLog;

/**
 * 接口调用日志记录mapper
 * @author chenjianghe
 *
 */
public interface CwmRuleInterfaceLogMapper {
	
	/**
	 * 新增记录
	 * @param record
	 * @return
	 */
    int insert(CwmRuleInterfaceLog record);

}