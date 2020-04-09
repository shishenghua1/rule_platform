package com.boco.eoms.rule.cwmsysrulerel.model;

import io.swagger.annotations.ApiModelProperty;

/**

* 创建时间：2019年6月26日 上午10:50:05

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：

*/
public class SelectData {
	@ApiModelProperty(value="节点名称",example="电子运维系统")
    private String name;
	@ApiModelProperty(value="节点关系",example="-1")
    private String pid;
	@ApiModelProperty(value="节点标识",example="001")
    private String id;
	@ApiModelProperty(value="节点点击的菜单类型",example="rightAppData")
    private String clickType;
	
	
	public SelectData(String name, String pid, String id, String clickType) {
		super();
		this.name = name;
		this.pid = pid;
		this.id = id;
		this.clickType = clickType;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the clickType
	 */
	public String getClickType() {
		return clickType;
	}
	/**
	 * @param clickType the clickType to set
	 */
	public void setClickType(String clickType) {
		this.clickType = clickType;
	}
	
	
}

