package com.boco.eoms.rule.cwmsysruleatoms.model;

import java.util.Date;

import com.boco.eoms.base.util.ConstantConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 规则平台原子规则表
 * 2019年5月27日下午3:12:00
 * 注：如果为常量，英文名存值，中文名存父节点id
 *ssh
 */
@ApiModel(value="CwmRuleAtoms:原子规则模型")
public class CwmRuleAtoms {
	@ApiModelProperty(value="主键id",example="7eb90bc4036145bc80dcbaba5176496c")
    private String id;
    //原子规则名称	
	@ApiModelProperty(value="原子规则名称",example="x>y")
    private String atomName;
    //原子规则描述	
	@ApiModelProperty(value="原子规则描述",example="派单时间>接单时间")
    private String atomDescription;
	@ApiModelProperty(value="是否删除",example="0")
    private String deleted;
	@ApiModelProperty(value="创建人",example="admin")
    private String createUserId;
	@JsonFormat(pattern = ConstantConfig.DATE_FORMAT)
    @ApiModelProperty(value="创建时间，日期格式",dataType="Date",example="2019-06-05 10:25:30")
    private Date createTime;

	/**
	 * 输入参数1英文名
	 */
	@ApiModelProperty(value="输入参数1英文名",example="canshu1")
    private String inputEnParam1;
    /**
	 * 输入参数1中文名
	 */
	@ApiModelProperty(value="输入参数1中文名",example="参数1")
    private String inputCnParam1;
    /**
	 * 输入参数1类型
	 */
	@ApiModelProperty(value="输入参数1类型,包括字符常量、字符变量,数字常量、数字变量,时间常量、时间变量，计算变量",example="字符常量")
    private String inputParamType1;
    /**
	 * 运算标识1
	 */
	@ApiModelProperty(value="运算标识1",example="+")
    private String operator1;
    /**
	 * 输入参数2英文名
	 */
	@ApiModelProperty(value="输入参数2英文名",example="canshu2")
    private String inputEnParam2;
    /**
   	 * 输入参数2中文名
   	 */
	@ApiModelProperty(value="输入参数2中文名",example="参数2")
    private String inputCnParam2;
    /**
   	 * 输入参数2类型
   	 */
	@ApiModelProperty(value="输入参数2类型,包括字符常量、字符变量,数字常量、数字变量,时间常量、时间变量，计算变量",example="时间变量")
    private String inputParamType2;
    /**
   	 * 运算标识2
   	 */
	@ApiModelProperty(value="运算标识2",example="-")
    private String operator2;
    /**
   	 * 输入参数3英文名
   	 */
	@ApiModelProperty(value="输入参数3英文名",example="canshu3")
    private String inputEnParam3;
    /**
   	 * 输入参数3中文名
   	 */
	@ApiModelProperty(value="输入参数3中文名",example="参数3")
    private String inputCnParam3;
    /**
   	 * 输入参数3类型
   	 */
	@ApiModelProperty(value="输入参数3类型,包括字符常量、字符变量,数字常量、数字变量,时间常量、时间变量，计算变量",example="数字变量")
    private String inputParamType3;

	/**
	 * 输入参数1来源
	 */
	@ApiModelProperty(value="输入参数1来源，字典值包括ruleLibrary（规则库）和directInput(直接输入)",example="ruleLibrary")
	private String inputParamOrigin1;

	/**
	 * 输入参数2来源
	 */
	@ApiModelProperty(value="输入参数2来源，字典值包括ruleLibrary（规则库）和directInput(直接输入)",example="ruleLibrary")
	private String inputParamOrigin2;

	/**
	 * 输入参数3来源
	 */
	@ApiModelProperty(value="输入参数3来源，字典值包括ruleLibrary（规则库）和directInput(直接输入)",example="directInput")
	private String inputParamOrigin3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAtomName() {
        return atomName;
    }

    public void setAtomName(String atomName) {
        this.atomName = atomName == null ? null : atomName.trim();
    }

    public String getAtomDescription() {
        return atomDescription;
    }

    public void setAtomDescription(String atomDescription) {
        this.atomDescription = atomDescription == null ? null : atomDescription.trim();
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
	 * @return the inputEnParam1
	 */
	public String getInputEnParam1() {
		return inputEnParam1;
	}

	/**
	 * @param inputEnParam1 the inputEnParam1 to set
	 */
	public void setInputEnParam1(String inputEnParam1) {
		this.inputEnParam1 = inputEnParam1;
	}

	/**
	 * @return the inputCnParam1
	 */
	public String getInputCnParam1() {
		return inputCnParam1;
	}

	/**
	 * @param inputCnParam1 the inputCnParam1 to set
	 */
	public void setInputCnParam1(String inputCnParam1) {
		this.inputCnParam1 = inputCnParam1;
	}

	/**
	 * @return the inputParamType1
	 */
	public String getInputParamType1() {
		return inputParamType1;
	}

	/**
	 * @param inputParamType1 the inputParamType1 to set
	 */
	public void setInputParamType1(String inputParamType1) {
		this.inputParamType1 = inputParamType1;
	}

	/**
	 * @return the operator1
	 */
	public String getOperator1() {
		return operator1;
	}

	/**
	 * @param operator1 the operator1 to set
	 */
	public void setOperator1(String operator1) {
		this.operator1 = operator1;
	}

	/**
	 * @return the inputEnParam2
	 */
	public String getInputEnParam2() {
		return inputEnParam2;
	}

	/**
	 * @param inputEnParam2 the inputEnParam2 to set
	 */
	public void setInputEnParam2(String inputEnParam2) {
		this.inputEnParam2 = inputEnParam2;
	}

	/**
	 * @return the inputCnParam2
	 */
	public String getInputCnParam2() {
		return inputCnParam2;
	}

	/**
	 * @param inputCnParam2 the inputCnParam2 to set
	 */
	public void setInputCnParam2(String inputCnParam2) {
		this.inputCnParam2 = inputCnParam2;
	}

	/**
	 * @return the inputParamType2
	 */
	public String getInputParamType2() {
		return inputParamType2;
	}

	/**
	 * @param inputParamType2 the inputParamType2 to set
	 */
	public void setInputParamType2(String inputParamType2) {
		this.inputParamType2 = inputParamType2;
	}

	/**
	 * @return the operator2
	 */
	public String getOperator2() {
		return operator2;
	}

	/**
	 * @param operator2 the operator2 to set
	 */
	public void setOperator2(String operator2) {
		this.operator2 = operator2;
	}

	/**
	 * @return the inputEnParam3
	 */
	public String getInputEnParam3() {
		return inputEnParam3;
	}

	/**
	 * @param inputEnParam3 the inputEnParam3 to set
	 */
	public void setInputEnParam3(String inputEnParam3) {
		this.inputEnParam3 = inputEnParam3;
	}

	/**
	 * @return the inputCnParam3
	 */
	public String getInputCnParam3() {
		return inputCnParam3;
	}

	/**
	 * @param inputCnParam3 the inputCnParam3 to set
	 */
	public void setInputCnParam3(String inputCnParam3) {
		this.inputCnParam3 = inputCnParam3;
	}

	/**
	 * @return the inputParamType3
	 */
	public String getInputParamType3() {
		return inputParamType3;
	}

	/**
	 * @param inputParamType3 the inputParamType3 to set
	 */
	public void setInputParamType3(String inputParamType3) {
		this.inputParamType3 = inputParamType3;
	}

	public String getInputParamOrigin1() {
		return inputParamOrigin1;
	}

	public void setInputParamOrigin1(String inputParamOrigin1) {
		this.inputParamOrigin1 = inputParamOrigin1;
	}

	public String getInputParamOrigin2() {
		return inputParamOrigin2;
	}

	public void setInputParamOrigin2(String inputParamOrigin2) {
		this.inputParamOrigin2 = inputParamOrigin2;
	}

	public String getInputParamOrigin3() {
		return inputParamOrigin3;
	}

	public void setInputParamOrigin3(String inputParamOrigin3) {
		this.inputParamOrigin3 = inputParamOrigin3;
	}
}