package com.boco.eoms.rule.cwmsysruleapps.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.rule.cwmsysruleapps.model.CwmRuleApps;
import com.boco.eoms.rule.cwmsysruleapps.service.ICwmRuleAppsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**

* 创建时间：2019年6月4日 上午9:35:59

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：规则场景restful 相关接口

*/
@RestController
@RequestMapping("/api/v1/apps")
@Api(value="规则场景controller",tags={"规则场景restful接口"})
public class CwmRuleAppsRestController {
	@Autowired
    private ICwmRuleAppsService cwmRuleAppsService;
	
	/**
	 * 获取规则场景信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*过期@ResponseBody
	@RequestMapping(value = "{appName}", method = RequestMethod.GET)  
	@ApiResponses({ @ApiResponse(code = 500, message = "查询异常") })
	@ApiOperation(value="规则场景查询",notes="根据应用场景名称查询规则场景",response = CwmRuleApps.class)
    @ApiImplicitParam(paramType="path", name = "appName", value = "规则场景名称", required = true, dataType = "String")
	public Object query(@PathVariable("appName") String appName){
		try {
			return cwmRuleAppsService.selectByAppName(StaticMethod.nullObject2String(appName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
	/**
	 *增加规则场景信息，单个增加
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="规则场景插入",notes="规则场景插入,只需要填写appName和createUserId即可，其它系统自动生成。")
	@ApiResponses({ @ApiResponse(code = 500, message = "插入异常") })
	@RequestMapping(method = RequestMethod.POST)
	public JSONObject saveCwmRuleApps(@RequestBody    CwmRuleApps cwmRuleApps){
		try {
			return cwmRuleAppsService.insert(cwmRuleApps);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *修改规则场景信息，单个修改
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="规则场景修改",notes="规则场景修改,只需要填写id,appName和createUserId即可，其它系统自动生成的，无法修改。")
	@ApiResponses({ @ApiResponse(code = 500, message = "修改异常") })
	@RequestMapping(method=RequestMethod.PUT)
	public JSONObject update(@RequestBody  CwmRuleApps cwmRuleApps){
		try {
			return cwmRuleAppsService.updateByPrimaryKey(cwmRuleApps);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *根据id删除规则场景
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{appId}",method=RequestMethod.DELETE)
	@ApiOperation(value="规则场景删除",notes="规则场景标识id")
	@ApiResponses({ @ApiResponse(code = 500, message = "删除异常") })
	@ApiImplicitParam(paramType="path", name = "appId", value = "规则场景标识id", required = true, dataType = "String")
	public void delete(@PathVariable("appId") String appId,HttpServletRequest request,HttpServletResponse response){
		try {
			cwmRuleAppsService.deleteByPrimaryKey(appId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

