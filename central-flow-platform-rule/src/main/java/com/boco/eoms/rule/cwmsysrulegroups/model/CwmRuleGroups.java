package com.boco.eoms.rule.cwmsysrulegroups.model;

import java.util.Date;

import com.boco.eoms.base.util.ConstantConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 规则平台的规则集合
 * 2019年5月28日上午8:38:31
 *ssh
 */
@ApiModel(value="CwmRuleGroups:规则集合模型")
public class CwmRuleGroups {
	@ApiModelProperty(value="主键id",example="7eb90bc4036145bc80dcbaba51764961")
    private String id;
    //规则集合名称	
	@ApiModelProperty(value="规则集合名称",example="规则集合1")
    private String groupName;
	//规则集合描述	
	@ApiModelProperty(value="规则集合描述",example="告警清除时间-故障发生时间＞30分钟")
    private String groupDescription;
    //关联模块id	
	@ApiModelProperty(value="关联模块id",example="7eb90bc4036145bc80dcbaba5176496c")
    private String moduleId;
    @ApiModelProperty(value="逻辑删除,0表示不删除，1表示删除",example="0")
    private String deleted;
    @ApiModelProperty(value="数据创建的关联英文用户名",example="admin")
    private String createUserId;
    @JsonFormat(pattern = ConstantConfig.DATE_FORMAT)
    @ApiModelProperty(value="创建时间，日期格式",dataType="Date",example="2019-06-05 10:25:30")
    private Date createTime;
    
    
    //生效日期	
  	@JsonFormat(pattern = ConstantConfig.DATE_FORMAT)
    @ApiModelProperty(value="生效日期",dataType="Date",example="2019-06-05 10:25:30")
    private Date effectiveDate;
    
    //失效日期	
  	@JsonFormat(pattern = ConstantConfig.DATE_FORMAT)
    @ApiModelProperty(value="失效日期",dataType="Date",example="2019-06-05 10:25:30")
    private Date expiringDate;
  	
  	//是否循环
  	@ApiModelProperty(value="是否循环,true表示循环,fasle表示不循环",example="true")
    private String isRepeat;

  	//规则集合类型
  	@ApiModelProperty(value="规则集合类型,规则集合ruleGroup,装配assembling",example="ruleGroup")
    private String groupType;
  	
  	//第一个为true的子集是否结束
  	@ApiModelProperty(value="第一个为true的子集是否结束,true表示结束,false表示继续执行,默认为true",example="true")
  	private String firstSubsetIsEnd;
  	
    //草稿标识
  	@ApiModelProperty(value="草稿标识，0为草稿，1不是草稿",example="true")
  	private String draftFlag;
  	
  	@ApiModelProperty(value="状态，字典值包括enable(表示启用)和 forbidden(表示禁用)",example="enable")
  	private String status;
  	
    public CwmRuleGroups() {
		super();
	}
    
    
	public CwmRuleGroups(String id, String groupName, String groupDescription) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.groupDescription = groupDescription;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId == null ? null : moduleId.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	/**
	 * @return the groupDescription
	 */
	public String getGroupDescription() {
		return groupDescription;
	}

	/**
	 * @param groupDescription the groupDescription to set
	 */
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	/**
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return the expiringDate
	 */
	public Date getExpiringDate() {
		return expiringDate;
	}

	/**
	 * @param expiringDate the expiringDate to set
	 */
	public void setExpiringDate(Date expiringDate) {
		this.expiringDate = expiringDate;
	}

	/**
	 * @return the isRepeat
	 */
	public String getIsRepeat() {
		return isRepeat;
	}

	/**
	 * @param isRepeat the isRepeat to set
	 */
	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}

	/**
	 * @return the groupType
	 */
	public String getGroupType() {
		return groupType;
	}

	/**
	 * @param groupType the groupType to set
	 */
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getFirstSubsetIsEnd() {
		return firstSubsetIsEnd;
	}

	public void setFirstSubsetIsEnd(String firstSubsetIsEnd) {
		this.firstSubsetIsEnd = firstSubsetIsEnd;
	}

	public String getDraftFlag() {
		return draftFlag;
	}

	public void setDraftFlag(String draftFlag) {
		this.draftFlag = draftFlag;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
}