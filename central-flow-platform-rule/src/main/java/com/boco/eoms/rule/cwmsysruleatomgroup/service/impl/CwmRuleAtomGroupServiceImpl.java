package com.boco.eoms.rule.cwmsysruleatomgroup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.rule.cwmsysruleatomgroup.mapper.CwmRuleAtomGroupMapper;
import com.boco.eoms.rule.cwmsysruleatomgroup.model.CwmRuleAtomGroup;
import com.boco.eoms.rule.cwmsysruleatomgroup.service.ICwmRuleAtomGroupService;

/**
 * 原子规则集合service实现
 * @author chenjianghe
 *
 */
@Service("cwmRuleAtomGroupService")
public class CwmRuleAtomGroupServiceImpl implements ICwmRuleAtomGroupService{

	@Autowired
	private CwmRuleAtomGroupMapper cwmRuleAtomGroupMapper;
	/**
	 * 删除一条原子规则集合
	 * @param id
	 * @return
	 */
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleAtomGroupMapper.deleteByPrimaryKey(id);
	}

	/**
     * 插入一条原子规则集合
     * @param record
     * @return
	 * @throws Exception 
     */
	@Override
	@Transactional
	public int insert(CwmRuleAtomGroup record) throws Exception {
		// TODO Auto-generated method stub
		String id = StaticMethod.nullObject2String(record.getId());
		if("".equals(id)) {
			record.setId(UUIDHexGenerator.getInstance().getID());
		}
		return cwmRuleAtomGroupMapper.insert(record);
	}

	/**
     * 根据id查询原子规则集合
     * @param id
     * @return
     */
	@Override
	public CwmRuleAtomGroup selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleAtomGroupMapper.selectByPrimaryKey(id);
	}

	/**
     * 更新原子规则集合
     * @param record
     * @return
     */
	@Override
	@Transactional
	public int updateByPrimaryKey(CwmRuleAtomGroup record) {
		// TODO Auto-generated method stub
		return cwmRuleAtomGroupMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<CwmRuleAtomGroup> findAll() {
		// TODO Auto-generated method stub
		return cwmRuleAtomGroupMapper.findAll();
	}

}
