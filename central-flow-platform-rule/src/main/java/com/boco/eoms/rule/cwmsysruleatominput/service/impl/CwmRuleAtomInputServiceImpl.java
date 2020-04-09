package com.boco.eoms.rule.cwmsysruleatominput.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.eoms.rule.cwmsysruleatominput.mapper.CwmRuleAtomInputMapper;
import com.boco.eoms.rule.cwmsysruleatominput.model.CwmRuleAtomInput;
import com.boco.eoms.rule.cwmsysruleatominput.service.ICwmRuleAtomInputService;

@Service("cwmRuleAtomInputService")
public class CwmRuleAtomInputServiceImpl implements ICwmRuleAtomInputService{

	@Autowired
	private CwmRuleAtomInputMapper cwmRuleAtomInputMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleAtomInputMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int insert(CwmRuleAtomInput record) {
		// TODO Auto-generated method stub
		return cwmRuleAtomInputMapper.insert(record);
	}

	@Override
	public CwmRuleAtomInput selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleAtomInputMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int updateByPrimaryKey(CwmRuleAtomInput record) {
		// TODO Auto-generated method stub
		return cwmRuleAtomInputMapper.updateByPrimaryKey(record);
	}

}
