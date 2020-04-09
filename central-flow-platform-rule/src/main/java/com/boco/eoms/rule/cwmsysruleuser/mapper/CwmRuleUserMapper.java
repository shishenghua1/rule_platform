package com.boco.eoms.rule.cwmsysruleuser.mapper;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.boco.eoms.rule.cwmsysruleuser.model.CwmRuleUser;

/**
 * mapper类
 * @author chenjianghe
 *
 */
@Repository
public interface CwmRuleUserMapper {
	
	/**
	 * 新增信息
	 * @param systemUser
	 */
	public void addSystemUser(CwmRuleUser systemUser);
	
	/**
	 * 删除信息
	 * @param systemUser
	 */
	public void removeSystemUser(CwmRuleUser systemUser);
	
	
	/**
	 * 更新信息
	 */
	public void updateSystemUser(CwmRuleUser systemUser);
	
    /**
     * 根据条件获取列表
     * @param map
     * @return
     */
	public List<CwmRuleUser> showListPage(Map<String,Object> map);
	/**
	 * 根据条件获取列表
	 * @param map
	 * @return
	 */
	public List<CwmRuleUser> querySystemUserList(Map<String,Object> map);

    
    
	/**
	 * 校验账号唯一性
	 * @param userId
	 * @return
	 */
	public String checkUserId(String user_id);

	/**
	 * 根据id查询人员信息
	 * @param id
	 * @return
	 */
	public List<CwmRuleUser> querySystemUserone(String id);
	/**
	 * 根据user_id(账号)查询人员信息
	 * @param uid
	 * @return
	 */
	public CwmRuleUser querysystemUserByUid(String uid);
	
	/**
	 * 禁用信息
	 * @param systemUser
	 */
	public void disabledSystemUser(CwmRuleUser systemUser);
	
	/**
	 * 启用信息
	 * @param systemUser
	 */
	public void playSystemUser(CwmRuleUser systemUser);
	
	/**
	 * 登陆验证
	 * @param id,pw
	 */
	public List<CwmRuleUser> userLogin(Map<String, String> map);
	
	/**
	 * uid转name
	 * @return
	 */
	public String getUserNameByUserId(String id);
	
	public int querySystemUserBycondtion(Map condition);
}
