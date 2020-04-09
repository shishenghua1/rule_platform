package com.boco.eoms.rule.cwmsysrulegroups.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.base.util.ReturnJsonUtil;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.rule.cwmsysrulegroups.mapper.CwmRuleGroupsMapper;
import com.boco.eoms.rule.cwmsysrulegroups.model.CwmRuleGroups;
import com.boco.eoms.rule.cwmsysrulegroups.service.ICwmRuleGroupsService;

/**

* 创建时间：2019年5月28日 上午8:43:42

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：规则平台的规则集合service实现层

*/
@Service
public class CwmRuleGroupsServiceImpl implements ICwmRuleGroupsService{

	@Autowired
	private CwmRuleGroupsMapper cwmRuleGroupsMapper;

	/**
	 * 根据id删除规则集合
	 * @param id
	 * @param groupType
	 * @return
	 */
	public JSONObject deleteByPrimaryKey(String id,String groupType) {
		if("ruleGroup".equals(groupType)) {
			int groupNum = cwmRuleGroupsMapper.isExistInAssembling(id);
			if(groupNum<=0){
				cwmRuleGroupsMapper.deleteByPrimaryKey(id);
				return ReturnJsonUtil.returnSuccessList("删除成功");
			}else {
				return ReturnJsonUtil.returnFailList("删除失败,该集合存在装配里");
			}
		}else{
			cwmRuleGroupsMapper.deleteByPrimaryKey(id);
			return ReturnJsonUtil.returnSuccessList("删除成功");
		}
	}

	@Override
	public JSONObject insert(CwmRuleGroups record) throws Exception {
		//获取当前用户
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		record.setCreateUserId(userId);
		record.setCreateTime(new Date());
		String id = StaticMethod.nullObject2String(record.getId());
		if("".equals(id)) {
			record.setId(UUIDHexGenerator.getInstance().getID());
		}
		String groupName = StaticMethod.nullObject2String(record.getGroupName());
		String moduleId = StaticMethod.nullObject2String(record.getModuleId());
		int dataModuleNum = cwmRuleGroupsMapper.isExist(groupName,moduleId);
		if(dataModuleNum<=0){
			cwmRuleGroupsMapper.insert(record);
			return ReturnJsonUtil.returnSuccessList("保存成功");
		}
		return ReturnJsonUtil.returnFailList("保存失败,规则集合名字已经存在");
	}

	@Override
	public CwmRuleGroups selectByPrimaryKey(String id) {
		return cwmRuleGroupsMapper.selectByPrimaryKey(id);
	}

	@Override
	public JSONObject updateByPrimaryKey(CwmRuleGroups record) {
		String groupType = StaticMethod.nullObject2String(record.getGroupType());
		String id = StaticMethod.nullObject2String(record.getId());
		String groupName = StaticMethod.nullObject2String(record.getGroupName());
		String moduleId = StaticMethod.nullObject2String(record.getModuleId());
		CwmRuleGroups cwmRuleGroups = cwmRuleGroupsMapper.selectByPrimaryKey(id);
		if(cwmRuleGroups != null) {
			String groupNameSql = StaticMethod.nullObject2String(cwmRuleGroups.getGroupName());
			if(!groupName.equals(groupNameSql)) {
				int dataModuleNum = cwmRuleGroupsMapper.isExist(groupName,moduleId);
				if(dataModuleNum<=0){
					cwmRuleGroupsMapper.updateByPrimaryKey(record);
					return ReturnJsonUtil.returnSuccessList("更新成功");
				}else {
					return ReturnJsonUtil.returnFailList("更新失败,规则集合名字已经存在");
				}
			}else {
				return ReturnJsonUtil.returnSuccessList("更新成功");
			}
		}
		if("ruleGroup".equals(groupType)) {
			int groupNum = cwmRuleGroupsMapper.isExistInAssembling(id);
			if(groupNum<=0){
				cwmRuleGroupsMapper.updateByPrimaryKey(record);
				return ReturnJsonUtil.returnSuccessList("更新成功");
			}else {
				return ReturnJsonUtil.returnFailList("更新失败,该集合存在装配里");
			}
		}else{
			cwmRuleGroupsMapper.updateByPrimaryKey(record);
			return ReturnJsonUtil.returnSuccessList("更新成功");
		}
	}

	@Override
	public List<CwmRuleGroups> selectByModuleId(String moduleId,String groupType) throws Exception{
		List<CwmRuleGroups> cwmRuleGroupList = cwmRuleGroupsMapper.selectByModuleId(moduleId,groupType);
		return cwmRuleGroupList;
	}

	@Override
	public List<CwmRuleGroups> selectByCondition(Map<String,Object> condition) {
		return cwmRuleGroupsMapper.selectByCondition(condition);
	}
}

