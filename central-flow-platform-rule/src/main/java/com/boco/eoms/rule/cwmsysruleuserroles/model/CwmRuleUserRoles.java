package com.boco.eoms.rule.cwmsysruleuserroles.model;

import java.util.Date;

/**
 * 规则平台权限
 * @author chenjianghe
 *
 */
public class CwmRuleUserRoles {
	/**
	 * 主键id
	 */
    private String id;

    /**
     * 角色id 1表示超级管理员  2表示系统管理员 3表示模块管理员 4表示普通用户
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 删除标识
     */
    private String deleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }
}