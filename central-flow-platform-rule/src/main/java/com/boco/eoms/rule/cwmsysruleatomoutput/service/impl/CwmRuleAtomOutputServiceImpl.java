package com.boco.eoms.rule.cwmsysruleatomoutput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.eoms.rule.cwmsysruleatomoutput.mapper.CwmRuleAtomOutputMapper;
import com.boco.eoms.rule.cwmsysruleatomoutput.model.CwmRuleAtomOutput;
import com.boco.eoms.rule.cwmsysruleatomoutput.service.ICwmRuleAtomOutputService;

@Service("cwmRuleAtomOutputService")
public class CwmRuleAtomOutputServiceImpl implements ICwmRuleAtomOutputService{

	@Autowired
	private CwmRuleAtomOutputMapper cwmRuleAtomOutputMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleAtomOutputMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int insert(CwmRuleAtomOutput record) {
		// TODO Auto-generated method stub
		return cwmRuleAtomOutputMapper.insert(record);
	}

	@Override
	public CwmRuleAtomOutput selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleAtomOutputMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int updateByPrimaryKey(CwmRuleAtomOutput record) {
		// TODO Auto-generated method stub
		return cwmRuleAtomOutputMapper.updateByPrimaryKey(record);
	}

}
