package com.boco.eoms.rule.cwmsysruleatomgroupoutputrel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.eoms.rule.cwmsysruleatomgroupoutputrel.mapper.CwmRuleAtomGroupOutputRelMapper;
import com.boco.eoms.rule.cwmsysruleatomgroupoutputrel.model.CwmRuleAtomGroupOutputRel;
import com.boco.eoms.rule.cwmsysruleatomgroupoutputrel.service.ICwmRuleAtomGroupOutputRelService;

/**
 * 原子规则集合关联原子规则输出service实现
 * @author chenjianghe
 *
 */
@Service("cwmRuleAtomGroupOutputRelService")
public class CwmRuleAtomGroupOutputRelServiceImpl implements ICwmRuleAtomGroupOutputRelService{

	@Autowired
	private CwmRuleAtomGroupOutputRelMapper cwmRuleAtomGroupOutputRelMapper;
	
	/**
	 * 删除原子规则集合关联原子规则输出
	 * @param id
	 * @return
	 */
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleAtomGroupOutputRelMapper.deleteByPrimaryKey(id);
	}

	/**
     * 新增原子规则集合关联原子规则输出
     * @param record
     * @return
     */
	@Override
	@Transactional
	public int insert(CwmRuleAtomGroupOutputRel record) {
		// TODO Auto-generated method stub
		return cwmRuleAtomGroupOutputRelMapper.insert(record);
	}

	/**
     * 查询原子规则集合关联原子规则输出
     * @param id
     * @return
     */
	@Override
	public CwmRuleAtomGroupOutputRel selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleAtomGroupOutputRelMapper.selectByPrimaryKey(id);
	}

	/**
     * 更新原子规则集合关联原子规则输出
     * @param record
     * @return
     */
	@Override
	@Transactional
	public int updateByPrimaryKey(CwmRuleAtomGroupOutputRel record) {
		// TODO Auto-generated method stub
		return cwmRuleAtomGroupOutputRelMapper.updateByPrimaryKey(record);
	}

}
