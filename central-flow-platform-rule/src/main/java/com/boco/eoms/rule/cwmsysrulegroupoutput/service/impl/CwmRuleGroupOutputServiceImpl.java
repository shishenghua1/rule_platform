package com.boco.eoms.rule.cwmsysrulegroupoutput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.eoms.rule.cwmsysrulegroupoutput.mapper.CwmRuleGroupOutputMapper;
import com.boco.eoms.rule.cwmsysrulegroupoutput.model.CwmRuleGroupOutput;
import com.boco.eoms.rule.cwmsysrulegroupoutput.service.ICwmRuleGroupOutputService;

@Service("cwmRuleGroupOutputService")
public class CwmRuleGroupOutputServiceImpl implements ICwmRuleGroupOutputService{

	@Autowired
	public CwmRuleGroupOutputMapper cwmRuleGroupOutputMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleGroupOutputMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int insert(CwmRuleGroupOutput record) {
		// TODO Auto-generated method stub
		return cwmRuleGroupOutputMapper.insert(record);
	}

	@Override
	public CwmRuleGroupOutput selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleGroupOutputMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int updateByPrimaryKey(CwmRuleGroupOutput record) {
		// TODO Auto-generated method stub
		return cwmRuleGroupOutputMapper.updateByPrimaryKey(record);
	}

}
