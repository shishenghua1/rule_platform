package com.boco.eoms.rule.cwmsysruleapps.service.impl;

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
import com.boco.eoms.rule.cwmsysruleapps.mapper.CwmRuleAppsMapper;
import com.boco.eoms.rule.cwmsysruleapps.model.CwmRuleApps;
import com.boco.eoms.rule.cwmsysruleapps.service.ICwmRuleAppsService;

/**

* 创建时间：2019年5月27日 下午3:31:42

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：

*/
@Service
public class CwmRuleAppsServiceImpl implements ICwmRuleAppsService{

	@Autowired
	private CwmRuleAppsMapper cwmRuleAppsMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleAppsMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public JSONObject insert(CwmRuleApps record) throws Exception {
		//获取当前用户
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		record.setCreateUserId(userId);
		record.setCreateTime(new Date());
		String id = StaticMethod.nullObject2String(record.getId());
		if("".equals(id)) {
			record.setId(UUIDHexGenerator.getInstance().getID());
		}
		String appName = StaticMethod.nullObject2String(record.getAppName());
		int ruleModuleNum = cwmRuleAppsMapper.isExist(appName);
		if(ruleModuleNum<=0){
			cwmRuleAppsMapper.insert(record);
			return ReturnJsonUtil.returnSuccessList("保存成功");
		}
		return ReturnJsonUtil.returnFailList("保存失败,规则场景名字已经存在");
	}

	@Override
	public CwmRuleApps selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return cwmRuleAppsMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public JSONObject updateByPrimaryKey(CwmRuleApps record) {
		String id = StaticMethod.nullObject2String(record.getId());
		String appName = StaticMethod.nullObject2String(record.getAppName());
		CwmRuleApps cwmRuleApps = cwmRuleAppsMapper.selectByPrimaryKey(id);
		if(cwmRuleApps != null) {
			String appNameSql = StaticMethod.nullObject2String(cwmRuleApps.getAppName());
			if(!appName.equals(appNameSql)) {
				int ruleModuleNum = cwmRuleAppsMapper.isExist(appName);
				if(ruleModuleNum<=0) {
					cwmRuleAppsMapper.updateByPrimaryKey(record);
					return ReturnJsonUtil.returnSuccessList("修改成功");
				}
			}else {
				return ReturnJsonUtil.returnSuccessList("修改成功");
			}
		}
		return ReturnJsonUtil.returnFailList("修改失败,规则场景名字已经存在");
	}

	@Override
	public List<CwmRuleApps> selectByAppName(String appName) {
		// TODO Auto-generated method stub
		return cwmRuleAppsMapper.selectByAppName(appName);
	}

}

