package com.boco.eoms.rule.cwmsysruleatomgroupoutput.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.eoms.rule.cwmsysruleatomgroupoutput.mapper.CwmRuleAtomGroupOutputMapper;
import com.boco.eoms.rule.cwmsysruleatomgroupoutput.model.CwmRuleAtomGroupOutput;
import com.boco.eoms.rule.cwmsysruleatomgroupoutput.service.ICwmRuleAtomGroupOutputService;

/**
 * 原子规则集合输出servcie实现
 * @author chenjianghe
 *
 */
@Service("cwmRuleAtomGroupOutputService")
public class CwmRuleAtomGroupOutputServiceImpl implements ICwmRuleAtomGroupOutputService{

	@Autowired
	private CwmRuleAtomGroupOutputMapper cwmRuleAtomGroupOutputMapper;
	
	/**
	 * 删除原子规则集合输出
	 * @param id
	 * @return
	 */
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleAtomGroupOutputMapper.deleteByPrimaryKey(id);
	}

	/**
     * 插入原子规则集合输出
     * @param record
     * @return
     */
	@Override
	@Transactional
	public int insert(CwmRuleAtomGroupOutput record) {
		// TODO Auto-generated method stub
		return cwmRuleAtomGroupOutputMapper.insert(record);
	}

	/**
     * 查询原子规则集合输出
     * @param id
     * @return
     */
	@Override
	public CwmRuleAtomGroupOutput selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleAtomGroupOutputMapper.selectByPrimaryKey(id);
	}
	 /**
     * 更新原子规则集合输出
     * @param record
     * @return
     */
	@Override
	@Transactional
	public int updateByPrimaryKey(CwmRuleAtomGroupOutput record) {
		// TODO Auto-generated method stub
		return cwmRuleAtomGroupOutputMapper.updateByPrimaryKey(record);
	}

    /**
     * 根据原子规则集合id查询原子规则集合输出
     * @param atomGroupId
     * @return
     */
	@Override
	public List<CwmRuleAtomGroupOutput> selectByAtomGroupId(String atomGroupId) {
		// TODO Auto-generated method stub
		return cwmRuleAtomGroupOutputMapper.selectByAtomGroupId(atomGroupId);
	}

}
