package com.boco.eoms.rule.cwmsysruleuserroles.mapper;

import org.apache.ibatis.annotations.Param;

import com.boco.eoms.rule.cwmsysruleuserroles.model.CwmRuleUserRoles;

public interface CwmRuleUserRolesMapper {
	
	/**
	 * 根据userid查询权限
	 * @param userId
	 * @return
	 */
	public String selectByUserId(@Param("userId") String userId);
}