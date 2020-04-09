package com.boco.eoms.rule.cwmsysrulegroupoutputparams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.eoms.rule.cwmsysrulegroupoutputparams.mapper.CwmRuleGroupOutputParamsMapper;
import com.boco.eoms.rule.cwmsysrulegroupoutputparams.model.CwmRuleGroupOutputParams;
import com.boco.eoms.rule.cwmsysrulegroupoutputparams.service.ICwmRuleGroupOutputParamsService;

/**
 * 规则集合输出参数service实现
 * @author chenjianghe
 *
 */
@Service("cwmRuleGroupOutputParamsService")
public class CwmRuleGroupOutputParamsServiceImpl implements ICwmRuleGroupOutputParamsService{

	
	@Autowired
	private CwmRuleGroupOutputParamsMapper cwmRuleGroupOutputParamsMapper;
	
	/**
	 * 删除规则集合输出参数
	 * @param id
	 * @return
	 */
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleGroupOutputParamsMapper.deleteByPrimaryKey(id);
	}

	/**
     * 插入规则集合输出参数
     * @param record
     * @return
     */
	@Override
	@Transactional
	public int insert(CwmRuleGroupOutputParams record) {
		// TODO Auto-generated method stub
		return cwmRuleGroupOutputParamsMapper.insert(record);
	}
	/**
     * 查询规则集合输出参数
     * @param id
     * @return
     */
	@Override
	public CwmRuleGroupOutputParams selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleGroupOutputParamsMapper.selectByPrimaryKey(id);
	}

	/**
     * 更新规则集合输出参数
     * @param record
     * @return
     */
	@Override
	@Transactional
	public int updateByPrimaryKey(CwmRuleGroupOutputParams record) {
		// TODO Auto-generated method stub
		return cwmRuleGroupOutputParamsMapper.updateByPrimaryKey(record);
	}

}
