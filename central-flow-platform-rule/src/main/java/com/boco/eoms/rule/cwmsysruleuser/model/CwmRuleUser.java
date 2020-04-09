package com.boco.eoms.rule.cwmsysruleuser.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Service;

/**
 * 人员管理model类
 * @author chenjianhge
 *
 */
public class CwmRuleUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7585913722405333697L;

	/**
	 * 人员id
	 */
	private String id;
	
	/**
	 * 人员账户
	 */
    private java.lang.String userId;
    
    /**
     * 人员名称
     */
    private String userName;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 联系电话
     */
    private String mobile;
    
    /**
     * 性别
     */
    private String gender;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 状态    //NORMAL启用     DISABLED禁用    DELETED删除
     */
    private String status;
    
    /**
 	* 创建时间
	*/  
    private Date createdTime;
    
    /**
 	* 创建人
	*/ 
    private String createdUserId;
    /**
     * 最后修改时间
     */
    private Date lastedTime;
    /**
     * 最后修改人
     */
    private String lastedUserId;
    
    /**
     * 所属接入系统
     */
    private String appId;
    
    /**
     * 所属模块
     */
    private String moduleId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public java.lang.String getUserId() {
		return userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Date getLastedTime() {
		return lastedTime;
	}

	public void setLastedTime(Date lastedTime) {
		this.lastedTime = lastedTime;
	}

	public String getLastedUserId() {
		return lastedUserId;
	}

	public void setLastedUserId(String lastedUserId) {
		this.lastedUserId = lastedUserId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}


}
