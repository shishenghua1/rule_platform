package com.boco.eoms.rule.cwmsysruleuser.service;

import com.boco.eoms.rule.cwmsysruleuser.model.CwmRuleUser;

/**
 * 人员管理service
 * @author chenjianghe
 *
 */
public interface ICwmRuleUserService{
	/**
	 * 登陆验证
	 * @param id,pw
	 */
	public Object userLogin(String id,String pw);
	 /**
     * 根据user_id(账号)查询人员信息
     * @param uid
     * @return
     */
	public CwmRuleUser querysystemUserByUid(final String uid);
}
