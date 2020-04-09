package com.boco.eoms.rule.cwmsysruleexcutelog.mapper;

import java.util.List;

import com.boco.eoms.rule.cwmsysruleexcutelog.model.CwmRuleExecuteLog;

public interface CwmRuleExecuteLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(CwmRuleExecuteLog record);

    CwmRuleExecuteLog selectByPrimaryKey(String id);

    int updateByPrimaryKey(CwmRuleExecuteLog record);
    
    int batchInsert(List<CwmRuleExecuteLog> list);
}