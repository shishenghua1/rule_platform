package com.boco.eoms.rule.cwmsysrulemodules.service.impl;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.base.util.ReturnJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.rule.cwmsysrulemodules.mapper.CwmRuleModulesMapper;
import com.boco.eoms.rule.cwmsysrulemodules.model.CwmRuleModules;
import com.boco.eoms.rule.cwmsysrulemodules.service.ICwmRuleModulesService;

/**

* 创建时间：2019年5月27日 下午3:27:10

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：规则平台模块分类实现层

*/
@Service
public class CwmRuleModulesServiceImpl implements ICwmRuleModulesService{

	@Autowired
	private CwmRuleModulesMapper cwmRuleModulesMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleModulesMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public JSONObject insert(CwmRuleModules record) throws Exception {
		//获取当前用户
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		record.setCreateUserId(userId);
		record.setCreateTime(new Date());
		// TODO Auto-generated method stub
		String id = StaticMethod.nullObject2String(record.getId());
		if("".equals(id)) {
			record.setId(UUIDHexGenerator.getInstance().getID());
		}
		String moduleName = StaticMethod.nullObject2String(record.getModuleName());
		String appId = StaticMethod.nullObject2String(record.getAppId());
		int ruleModuleNum = cwmRuleModulesMapper.isExist(appId,moduleName);
		if(ruleModuleNum<=0){
			cwmRuleModulesMapper.insert(record);
			return ReturnJsonUtil.returnSuccessList("保存成功");
		}
		return ReturnJsonUtil.returnFailList("保存失败,规则模块名字已经存在");
	}

	@Override
	public CwmRuleModules selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleModulesMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public JSONObject updateByPrimaryKey(CwmRuleModules record) {
		String id = StaticMethod.nullObject2String(record.getId());
		String moduleName = StaticMethod.nullObject2String(record.getModuleName());
		CwmRuleModules cwmRuleModules = cwmRuleModulesMapper.selectByPrimaryKey(id);
		if(cwmRuleModules != null) {
			String moduleNameSql = StaticMethod.nullObject2String(cwmRuleModules.getModuleName());
			if(!moduleName.equals(moduleNameSql)) {
				String appId = StaticMethod.nullObject2String(record.getAppId());
				int ruleModuleNum = cwmRuleModulesMapper.isExist(appId,moduleName);
				if(ruleModuleNum<=0) {
					cwmRuleModulesMapper.updateByPrimaryKey(record);
					return ReturnJsonUtil.returnSuccessList("保存成功");
				}
			}else {
				return ReturnJsonUtil.returnSuccessList("保存成功");
			}
		}
		return ReturnJsonUtil.returnFailList("保存失败,规则模块名字已经存在");
	}

	/**
     * 根据所属系统查询模块
     * @param appId
     * @return
     */
	@Override
	public List<CwmRuleModules> selectByAppId(String appId) {
		// TODO Auto-generated method stub
		return cwmRuleModulesMapper.selectByAppId(appId);
	}

}

