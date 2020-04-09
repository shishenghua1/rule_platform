package com.boco.eoms.rule.cwmsysruleatomgrouprel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.eoms.rule.cwmsysruleatomgrouprel.mapper.CwmRuleAtomGroupRelMapper;
import com.boco.eoms.rule.cwmsysruleatomgrouprel.model.CwmRuleAtomGroupRel;
import com.boco.eoms.rule.cwmsysruleatomgrouprel.service.ICwmRuleAtomGroupRelService;

/**
 * 原子规则集合关联service实现
 * @author chenjianghe
 *
 */
@Service("cwmRuleAtomGroupRelService")
public class CwmRuleAtomGroupRelServiceImpl implements ICwmRuleAtomGroupRelService{

	@Autowired
	private CwmRuleAtomGroupRelMapper cwmRuleAtomGroupRelMapper;
	/**
	 * 删除一条原子规则关联集合
	 * @param id
	 * @return
	 */
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleAtomGroupRelMapper.deleteByPrimaryKey(id);
	}

	/**
     * 插入一条原子规则关联集合
     * @param record
     * @return
     */
	@Override
	@Transactional
	public int insert(CwmRuleAtomGroupRel record) {
		// TODO Auto-generated method stub
		return cwmRuleAtomGroupRelMapper.insert(record);
	}

	/**
     * 根据id查询原子规则关联集合
     * @param id
     * @return
     */
	@Override
	public CwmRuleAtomGroupRel selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleAtomGroupRelMapper.selectByPrimaryKey(id);
	}

	/**
     * 更新原子规则关联集合
     * @param record
     * @return
     */
	@Override
	@Transactional
	public int updateByPrimaryKey(CwmRuleAtomGroupRel record) {
		// TODO Auto-generated method stub
		return cwmRuleAtomGroupRelMapper.updateByPrimaryKey(record);
	}

}
