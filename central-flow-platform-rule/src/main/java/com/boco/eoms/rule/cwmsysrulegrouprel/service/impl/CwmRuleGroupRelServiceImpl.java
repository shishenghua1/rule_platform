package com.boco.eoms.rule.cwmsysrulegrouprel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.eoms.rule.cwmsysrulegrouprel.mapper.CwmRuleGroupRelMapper;
import com.boco.eoms.rule.cwmsysrulegrouprel.model.CwmRuleGroupRel;
import com.boco.eoms.rule.cwmsysrulegrouprel.service.ICwmRuleGroupRelService;

/**
 * 规则集合关联service实现
 * @author chenjianghe
 *
 */
@Service("cwmRuleGroupRelService")
public class CwmRuleGroupRelServiceImpl implements ICwmRuleGroupRelService{
	
	@Autowired
	private CwmRuleGroupRelMapper cwmRuleGroupRelMapper;

	/**
	 * 删除规则集合关联
	 * @param id
	 * @return
	 */
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleGroupRelMapper.deleteByPrimaryKey(id);
	}

	/**
     * 插入规则集合关联
     * @param record
     * @return
     */
	@Override
	@Transactional
	public int insert(CwmRuleGroupRel record) {
		// TODO Auto-generated method stub
		return cwmRuleGroupRelMapper.insert(record);
	}

	/**
     * 查询规则集合关联
     * @param id
     * @return
     */
	@Override
	public CwmRuleGroupRel selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleGroupRelMapper.selectByPrimaryKey(id);
	}
	
	/**
     * 更新规则集合关联
     * @param record
     * @return
     */
	@Override
	@Transactional
	public int updateByPrimaryKey(CwmRuleGroupRel record) {
		// TODO Auto-generated method stub
		return cwmRuleGroupRelMapper.updateByPrimaryKey(record);
	}
	
	/**
     * 批量插入规则集合关联
     * @param CwmRuleGroupRels
     */
	public void batchInsert(List<CwmRuleGroupRel> CwmRuleGroupRels) {
		cwmRuleGroupRelMapper.batchInsert(CwmRuleGroupRels);
	}

	/**
     * 根据规则集合查询规则集合关联
     * @param groupId
     * @return
     */
	@Override
	public List<CwmRuleGroupRel> selectByGroupId(String groupId) {
		// TODO Auto-generated method stub
		return cwmRuleGroupRelMapper.selectByGroupId(groupId);
	}

}
