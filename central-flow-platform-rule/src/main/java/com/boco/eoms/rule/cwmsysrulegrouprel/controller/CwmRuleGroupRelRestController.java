package com.boco.eoms.rule.cwmsysrulegrouprel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boco.eoms.rule.cwmsysrulegrouprel.model.CwmRuleGroupRel;
import com.boco.eoms.rule.cwmsysrulegrouprel.service.ICwmRuleGroupRelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**

* 创建时间：2019年6月25日 上午9:35:59

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：规则集合关联关联restful 相关接口

*/
@RestController
@RequestMapping("/api/v1/groups/rel")
@Api(value="规则集合关联controller",tags={"规则集合关联restful接口"})
public class CwmRuleGroupRelRestController {
	
	private Logger logger = LoggerFactory.getLogger(CwmRuleGroupRelRestController.class);
	
	@Autowired
	private ICwmRuleGroupRelService cwmRuleGroupRelService;
	
	/**
	 *增加规则集合关联信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*@ApiOperation(value="规则集合关联原子规则集插入",notes="规则集合关联插入")
	@ApiResponses({ @ApiResponse(code = 500, message = "插入异常") })
	@RequestMapping(method = RequestMethod.POST)
	public void saveCwmRuleApps(@RequestBody List<CwmRuleGroupRel> cwmRuleGroupRelList) {
		try {
			cwmRuleGroupRelService.batchInsert(cwmRuleGroupRelList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	*//**
	 *修改规则集合关联信息，单个修改
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@ApiOperation(value="规则集合关联修改",notes="规则集合关联修改,只需要填写id,moduleName和createUserId即可，其它系统自动生成的，无法修改。")
	@ApiResponses({ @ApiResponse(code = 500, message = "修改异常") })
	@RequestMapping(method=RequestMethod.PUT)
	public void update(@RequestBody  CwmRuleGroupRel cwmRuleGroupRel) {
		try {
			cwmRuleGroupRelService.updateByPrimaryKey(cwmRuleGroupRel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/**
	 *根据id删除规则集合关联
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{groupId}",method=RequestMethod.DELETE)
	@ApiOperation(value="规则集合关联删除",notes="规则集合关联标识id")
	@ApiResponses({ @ApiResponse(code = 500, message = "删除异常") })
	@ApiImplicitParam(paramType="path", name = "groupId", value = "规则集合关联标识id", required = true, dataType = "String")
	public void delete(@PathVariable("groupId") String groupId) {
		try {
			cwmRuleGroupRelService.deleteByPrimaryKey(groupId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据groupId查询规则集合下的关联集合信息
	 * @param groupId
	 * @return
	 */
	@ApiOperation(value="根据集合id查询规则集合",notes="根据集合id查询规则集合")
	@ApiResponses({ @ApiResponse(code = 500, message = "查询异常") })
	@RequestMapping(value="/{groupId}",method=RequestMethod.GET)
	public List<CwmRuleGroupRel> queryRuleGroupByModuleId(@PathVariable("groupId") String groupId) {
		try {
			return cwmRuleGroupRelService.selectByGroupId(groupId);
		} catch (Exception e) {
			logger.error("根据集合id查询规则集合报错"+e);
		}
		return null;
	}
}

