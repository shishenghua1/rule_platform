package com.boco.eoms.rule.cwmsysrulegroupoutputrel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.eoms.rule.cwmsysrulegroupoutputrel.mapper.CwmRuleGroupOutputRelMapper;
import com.boco.eoms.rule.cwmsysrulegroupoutputrel.model.CwmRuleGroupOutputRel;
import com.boco.eoms.rule.cwmsysrulegroupoutputrel.service.ICwmRuleGroupOutputRelService;


/**
 * 规则集合关联原子规则集合输出service实现
 * @author chenjianghe
 *
 */
@Service("cwmRuleGroupOutputRelService")
public class CwmRuleGroupOutputRelServiceImpl implements ICwmRuleGroupOutputRelService{
	
	@Autowired
	private CwmRuleGroupOutputRelMapper cwmRuleGroupOutputRelMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleGroupOutputRelMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int insert(CwmRuleGroupOutputRel record) {
		// TODO Auto-generated method stub
		return cwmRuleGroupOutputRelMapper.insert(record);
	}

	@Override
	public CwmRuleGroupOutputRel selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleGroupOutputRelMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int updateByPrimaryKey(CwmRuleGroupOutputRel record) {
		// TODO Auto-generated method stub
		return cwmRuleGroupOutputRelMapper.updateByPrimaryKey(record);
	}

}
