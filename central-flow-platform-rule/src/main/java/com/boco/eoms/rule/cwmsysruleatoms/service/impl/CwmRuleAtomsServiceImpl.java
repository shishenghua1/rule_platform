package com.boco.eoms.rule.cwmsysruleatoms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.eoms.rule.cwmsysruleatoms.mapper.CwmRuleAtomsMapper;
import com.boco.eoms.rule.cwmsysruleatoms.model.CwmRuleAtoms;
import com.boco.eoms.rule.cwmsysruleatoms.service.ICwmRuleAtomsService;

/**

* 创建时间：2019年5月27日 下午3:16:54

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：规则平台原子规则业务实现层,基础方法为自动生成，不做说明

*/
@Service
public class CwmRuleAtomsServiceImpl implements ICwmRuleAtomsService{

	@Autowired
	private CwmRuleAtomsMapper cwmRuleAtomsMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return cwmRuleAtomsMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int insert(CwmRuleAtoms record) {
		// TODO Auto-generated method stub
		return cwmRuleAtomsMapper.insert(record);
	}

	@Override
	public CwmRuleAtoms selectByPrimaryKey(String id) {
		return cwmRuleAtomsMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int updateByPrimaryKey(CwmRuleAtoms record) {
		// TODO Auto-generated method stub
		return cwmRuleAtomsMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<CwmRuleAtoms> selectAllAtoms() {
		return cwmRuleAtomsMapper.selectAllAtoms();
	}

}

