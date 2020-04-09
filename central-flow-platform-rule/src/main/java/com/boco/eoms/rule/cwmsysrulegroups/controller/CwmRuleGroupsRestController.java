package com.boco.eoms.rule.cwmsysrulegroups.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.rule.cwmsysrulegroups.model.CwmRuleGroups;
import com.boco.eoms.rule.cwmsysrulegroups.service.ICwmRuleGroupsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**

* 创建时间：2019年6月25日 上午9:35:59

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：规则集合restful 相关接口

*/
@RestController
@RequestMapping("/api/v1/groups")
@Api(value="规则集合controller",tags={"规则集合restful接口"})
public class CwmRuleGroupsRestController {

	private Logger logger = LoggerFactory.getLogger(CwmRuleGroupsRestController.class);

	@Autowired
    private ICwmRuleGroupsService cwmRuleGroupsService;

	/**
	 * 根据moduleId查询规则集合
	 * @param moduleId
	 * @return
	 */
	@ApiOperation(value="根据模块id,规则类型查询规则集合",notes="根据模块id和规则类型查询规则集合")
	@ApiResponses({ @ApiResponse(code = 500, message = "查询异常") })
	@RequestMapping(method = RequestMethod.GET)
	@ApiImplicitParams({
			@ApiImplicitParam(name="moduleId",value="模块分类对应标识	",required=true,paramType="query",dataType = "String"),
			@ApiImplicitParam(name="groupType",value="规则集合数据类型,规则集合表示为ruleGroup,装配表示为assembling",required=true,paramType="query",dataType = "String")
	})
	public List queryRuleGroupByModuleId(@RequestParam String moduleId,@RequestParam String groupType) {
		try {
			return cwmRuleGroupsService.selectByModuleId(moduleId,groupType);
		} catch (Exception e) {
			logger.error("根据模块id查询规则集合报错",e);
		}
		return null;
	}


	/**
	 * 根据系统,模块,规则集合类型查询规则集合的对外接口
	 * @param appName
	 * @param moduleName
	 * @return
	 */
	@ResponseBody
	@GetMapping("getListByAppAndModule")
	@ApiResponses({ @ApiResponse(code = 500, message = "接口调用异常") })
	@ApiOperation(value="列表查询:根据系统,模块,规则集合类型查询规则集合",notes="列表查询:根据系统,模块,规则集合类型查询规则集合",response = CwmRuleGroups.class)
	@ApiImplicitParams({
	    @ApiImplicitParam(name="appName",value="系统名称",required=false,paramType="query",dataType = "String"),
	    @ApiImplicitParam(name="moduleName",value="模块名称",required=false,paramType="query",dataType = "String")
	})
	public List<CwmRuleGroups> selectByCondition(@ApiParam(name="appName",value="系统名称",required=false)String appName,
			@ApiParam(name="moduleName",value="模块名称",required=false)String moduleName) {
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			condition.put("appName", appName);
			condition.put("moduleName", moduleName);
			condition.put("draftFlag", "1");
			return cwmRuleGroupsService.selectByCondition(condition);//查询非草稿的规则集合
		} catch (Exception e) {
			logger.error("根据系统和模块查询规则集合报错",e);
		}
		return null;
	}

	/**
	 * 根据id删除规则集合
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value="/deleteById",method=RequestMethod.DELETE)
	@ApiOperation(value="规则集合删除",notes="规则集合标识id")
	@ApiResponses({ @ApiResponse(code = 500, message = "删除异常") })
	@ApiImplicitParams({
			@ApiImplicitParam(name="groupId",value="规则集合标识id",required=true,paramType="query",dataType = "String"),
			@ApiImplicitParam(name="groupType",value="规则集合类型,规则集合表示为ruleGroup,装配表示为assembling",required=true,paramType="query",dataType = "String")
	})
	public JSONObject delete(String groupId, String groupType) {
		try {
			return cwmRuleGroupsService.deleteByPrimaryKey(groupId,groupType);
		} catch (Exception e) {
			logger.error("规则集合删除报错",e);
		}
		return null;
	}

	/**
	 * 增加规则集合信息，单个增加
	 * @param cwmRuleGroups
	 */
	@ApiOperation(value="规则集合插入",notes="规则集合插入,只需要填写groupName和enabled和moduleId即可，其它系统自动生成。")
	@ApiResponses({ @ApiResponse(code = 500, message = "插入异常") })
	@RequestMapping(method = RequestMethod.POST)
	public JSONObject saveCwmRuleApps(@RequestBody    CwmRuleGroups cwmRuleGroups) {
		try {
			return cwmRuleGroupsService.insert(cwmRuleGroups);
		} catch (Exception e) {
			logger.error("规则集合插入报错",e);
		}
		return null;
	}

	/**
	 * 修改规则集合信息，单个修改
	 * @param cwmRuleGroups
	 */
	@ApiOperation(value="规则集合修改",notes="规则集合修改,只需要填写id,moduleName和createUserId即可，其它系统自动生成的，无法修改。")
	@ApiResponses({ @ApiResponse(code = 500, message = "修改异常") })
	@RequestMapping(method=RequestMethod.PUT)
	public JSONObject update(@RequestBody  CwmRuleGroups cwmRuleGroups) {
		try {
			return cwmRuleGroupsService.updateByPrimaryKey(cwmRuleGroups);
		} catch (Exception e) {
			logger.error("规则集合修改报错",e);
		}
		return null;
	}
}

