package com.boco.eoms.rule.cwmsysrulemodules.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boco.eoms.rule.cwmsysrulemodules.model.CwmRuleModules;
import com.boco.eoms.rule.cwmsysrulemodules.service.ICwmRuleModulesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**

* 创建时间：2019年6月25日 上午9:35:59

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：规则模块restful 相关接口,规则模块的修改新增没有校验功能。

*/
@RestController
@RequestMapping("/api/v1/modules")
@Api(value="规则模块controller",tags={"规则模块restful接口"})
public class CwmRuleModulesRestController {
	@Autowired
    private ICwmRuleModulesService cwmRuleModulesService;
	
	/**
	 *增加规则模块信息，单个增加
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="规则模块插入",notes="规则模块插入,只需要填写moduleName和appId即可，其它系统自动生成。")
	@ApiResponses({ @ApiResponse(code = 500, message = "插入异常") })
	@RequestMapping(method = RequestMethod.POST)
	public JSONObject saveCwmRuleApps(@RequestBody    CwmRuleModules cwmRuleModules) {
		try {
			return cwmRuleModulesService.insert(cwmRuleModules);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *修改规则模块信息，单个修改
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="规则模块修改",notes="规则模块修改,只需要填写id,moduleName和createUserId即可，其它系统自动生成的，无法修改。")
	@ApiResponses({ @ApiResponse(code = 500, message = "修改异常") })
	@RequestMapping(method=RequestMethod.PUT)
	public JSONObject update(@RequestBody  CwmRuleModules cwmRuleModules) {
		try {
			return cwmRuleModulesService.updateByPrimaryKey(cwmRuleModules);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *根据id删除规则模块
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{moduleId}",method=RequestMethod.DELETE)
	@ApiOperation(value="规则模块删除",notes="规则模块标识id")
	@ApiResponses({ @ApiResponse(code = 500, message = "删除异常") })
	@ApiImplicitParam(paramType="path", name = "moduleId", value = "规则模块标识id", required = true, dataType = "String")
	public void delete(@PathVariable("moduleId") String moduleId,HttpServletRequest request,HttpServletResponse response) {
		try {
			cwmRuleModulesService.deleteByPrimaryKey(moduleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

