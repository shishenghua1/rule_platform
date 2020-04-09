package com.boco.eoms.rule.cwmsysrulerel.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.base.util.ReturnJsonUtil;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.rule.cwmsysdatamodule.model.CwmDataModule;
import com.boco.eoms.rule.cwmsysrulerel.model.RuleContent;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.boco.eoms.rule.cwmsysrulerel.model.CwmRuleGroupConfig;
import com.boco.eoms.rule.cwmsysrulerel.model.ParamInfo;
import com.boco.eoms.rule.cwmsysrulerel.service.ICwmRuleRelService;

/**

* 创建时间：2019年6月25日 上午9:35:59

* 项目名称：central-flow-platform-rule

* @author ssh
* 类说明：规则多模型互联restful 相关接口

*/
@RestController
@RequestMapping("/api/v1/rel")
@Api(value="规则多模型关联controller",tags={"规则多模型restful接口"})
public class CwmRuleRelRestController {
	
	@Autowired
	private ICwmRuleRelService cwmRuleRelService;

	private Logger logger = LoggerFactory.getLogger(CwmRuleRelRestController.class);
	
	/**
	 *保存规则集合配置信息
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="规则集合配置保存",notes="规则集合配置保存")
	@ApiResponses({ @ApiResponse(code = 500, message = "保存异常") })
	@RequestMapping(value="saveRuleGroupConfig",method = RequestMethod.POST)
	public JSONObject saveCwmRuleGroupConfig(@RequestBody CwmRuleGroupConfig cwmRuleGroupConfig) {
		try {
			return cwmRuleRelService.saveCwmRuleGroupConfig(cwmRuleGroupConfig);
		} catch (Exception e) {
			logger.error("规则集合配置保存报错",e);
			return ReturnJsonUtil.returnFailList("保存失败,请检查传递的数据是否正确");
		}
	}

	/**
	 * 规则集合配置查询
	 * @param groupId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getRuleGroupConfig", method = RequestMethod.GET)
	@ApiResponses({ @ApiResponse(code = 500, message = "查询异常") })
	@ApiImplicitParams({
			@ApiImplicitParam(name="groupId",value="规则集合id",required=true,paramType="query",dataType = "String"),
			@ApiImplicitParam(name="ruleType",value="规则集合类型,规则集合ruleGroup,装配assembling",required=true,paramType="query",dataType = "String")
	})
	@ApiOperation(value="规则集合配置查询",notes="")
	public CwmRuleGroupConfig queryCwmRuleGroupConfig(String groupId,String ruleType){
		try {
			return cwmRuleRelService.queryCwmRuleGroupConfig(groupId,ruleType);
		} catch (Exception e) {
			logger.error("规则集合配置查询报错",e);
		}
		return null;
	}

	/**
	 * 规则集合信息查询对外接口
	 * @param groupId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getRuleInfo", method = RequestMethod.GET)
	@ApiResponses({ @ApiResponse(code = 500, message = "查询异常") })
	@ApiImplicitParams({
			@ApiImplicitParam(name="groupId",value="规则集合id",required=true,paramType="query",dataType = "String")
	})
	@ApiOperation(value="规则集合信息查询对外接口",notes="")
	public RuleContent queryRuleContent(String groupId){
		try {
			return cwmRuleRelService.queryRuleContent(groupId);
		} catch (Exception e) {
			logger.error("规则集合内容查询报错",e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 规则集合查询
	 * @param groupIds
	 * @return
	 */
	/*@ResponseBody
	@GetMapping("getRuleGroupInfo")
	@ApiResponses({ @ApiResponse(code = 500, message = "接口调用异常") })
	@ApiOperation(value="根据规则集合id集合查询规则集合",notes="规则集合id",response = RuleGroupInfo.class)
	@ApiImplicitParam(name="groupIds",value="规则集合id的内容,id以英文逗号隔开",required=true,paramType="query",dataType = "String")
	public List<RuleGroupInfo> queryRuleGroupByModuleId(@RequestParam String groupIds) {
		try {
			return cwmRuleRelService.selectByGroupIds(groupIds);
		} catch (Exception e) {
			logger.error("根据规则集合id集合查询规则集合报错",e);
		}
		return null;
	}*/
	
	@ApiOperation(value="规则集合配置修改",notes="规则配置配置修改")
	@ApiResponses({ @ApiResponse(code = 500, message = "修改异常") })
	@RequestMapping(value="updateRuleGroupConfig",method=RequestMethod.PUT)
	public JSONObject updateRuleGroupConfig(@RequestBody CwmRuleGroupConfig cwmRuleGroupConfig) {
		try {
			return cwmRuleRelService.updateCwmRuleGroupConfig(cwmRuleGroupConfig);
		} catch (Exception e) {
			logger.error("规则集合配置修改报错",e);
			e.printStackTrace();
			return ReturnJsonUtil.returnFailList("保存失败,请检查传递的数据是否正确");
		}
	}
	

	/**
	 * 规则平台菜单树数据
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/ztree", method = RequestMethod.GET)  
	@ApiResponses({ @ApiResponse(code = 500, message = "查询异常") })
	@ApiOperation(value="规则平台菜单树查询",notes="")
	public JSONArray  query(){
		try {
			return cwmRuleRelService.getRulePlatformZtree();
		} catch (Exception e) {
			logger.error("规则平台菜单树查询报错",e);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 规则级参数信息获取
	 * 规则模块下的字段不重复
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@GetMapping("getParamInfo")
	@ApiResponses({ @ApiResponse(code = 500, message = "查询异常") })
	@ApiOperation(value="规则集合参数信息获取",notes="根据规则集合id和规则模块id查询数据详情",response = CwmDataModule.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name="groupId",value="规则集合id",required=true,paramType="query",dataType = "String"),
			@ApiImplicitParam(name="moduleId",value="规则模块id",required=true,paramType="query",dataType = "String")
	})
	public List<ParamInfo> queryParamInfo(String groupId,String moduleId){
		try {
			return cwmRuleRelService.getParamInfo(groupId,moduleId);
		} catch (Exception e) {
			logger.error("规则集合参数信息获取报错",e);
		}
		return null;
	}

	/**
	 * 规则集合配置数据查询
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@ResponseBody
	@RequestMapping(value="getRuleGroupConfig/{groupId}", method = RequestMethod.GET)  
	@ApiResponses({ @ApiResponse(code = 500, message = "查询异常") })
	@ApiImplicitParam(paramType="path", name = "groupId", value = "规则集合id", required = true, dataType = "String")
	@ApiOperation(value="规则集合配置查询",notes="")
	public CwmRuleGroupConfig queryCwmRuleGroupConfig(@PathVariable("groupId")String groupId){
		try {
			return cwmRuleRelService.queryCwmRuleGroupConfig(groupId);
		} catch (Exception e) {
			logger.error("规则集合配置查询报错",e);
		}
		return null;
	}
	
	*//**
	 *原子规则配置信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@ApiOperation(value="原子规则配置插入",notes="原子规则集合配置插入")
	@ApiResponses({ @ApiResponse(code = 500, message = "插入异常") })
	@RequestMapping(value="saveRuleAtomConfig",method = RequestMethod.POST)
	public void saveRuleAtomConfig(@RequestBody CwmRuleAtomConfig cwmRuleAtomConfig) {
		try {
			cwmRuleRelService.saveRuleAtomConfig(cwmRuleAtomConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	*//**
	 *原子规则修改配置信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@ApiOperation(value="原子规则配置修改",notes="原子规则配置修改")
	@ApiResponses({ @ApiResponse(code = 500, message = "插入异常") })
	@RequestMapping(value="updateRuleAtomConfig",method=RequestMethod.PUT)
	public void updateRuleAtomConfig(@RequestBody CwmRuleAtomConfig cwmRuleAtomConfig) {
		try {
			cwmRuleRelService.updateRuleAtomConfig(cwmRuleAtomConfig);
		} catch (Exception e) {
			logger.error("原子规则配置插入报错",e);
		}
	}
	
	
	*//**
	 * 原子规则配置数据查询
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@ResponseBody
	@RequestMapping(value="getRuleAtomConfig/{atomId}", method = RequestMethod.GET) 
	@ApiImplicitParam(paramType="path", name = "atomId", value = "原子规则集合id", required = true, dataType = "String")
	@ApiResponses({ @ApiResponse(code = 500, message = "查询异常") })
	@ApiOperation(value="原子规则配置查询",notes="")
	public CwmRuleAtomConfig queryCwmRuleAtomConfig(@PathVariable("atomId")String atomId){
		try {
			return cwmRuleRelService.queryCwmRuleAtomConfig(atomId);
		} catch (Exception e) {
			logger.error("原子规则配置查询报错",e);
		}
		return null;
	}
	
	*//**
	 *原子规则集合配置信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@ApiOperation(value="原子规则集合配置插入",notes="原子规则集合配置插入")
	@ApiResponses({ @ApiResponse(code = 500, message = "插入异常") })
	@RequestMapping(value="saveRuleAtomGroupConfig",method = RequestMethod.POST)
	public void saveRuleAtomGroupConfig(@RequestBody List<CwmRuleAtomGroupConfig> ruleAtomGroupConfigList) {
		try {
			cwmRuleRelService.saveRuleAtomGroupConfig(ruleAtomGroupConfigList);
		} catch (Exception e) {
			logger.error("原子规则集合配置插入报错",e);
		}
	}
	
	*//**
	 * 原子规则集合配置数据查询
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@ResponseBody
	@RequestMapping(value="getRuleAtomGroupConfig/{atomGroupId}", method = RequestMethod.GET)  
	@ApiResponses({ @ApiResponse(code = 500, message = "查询异常") })
	@ApiImplicitParam(paramType="path", name = "atomGroupId", value = "原子规则集合id", required = true, dataType = "String")
	@ApiOperation(value="原子规则集合配置查询",notes="")
	public List<CwmRuleAtomGroupConfig> getRuleAtomGroupConfig(@PathVariable("atomGroupId")String atomGroupId){
		try {
			return cwmRuleRelService.getRuleAtomGroupConfig(atomGroupId);
		} catch (Exception e) {
			logger.error("原子规则集合配置查询报错",e);
		}
		return null;
	}
	
	*//**
	 * 规则集合关联原子规则集合信息查询
	 * @param groupId
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping(value="getRuleGroupRelAtomGroupConfig/{groupId}", method = RequestMethod.GET) 
	@ApiResponses({ @ApiResponse(code = 500, message = "查询异常") })
	@ApiImplicitParam(paramType="path", name = "groupId", value = "规则集合id", required = true, dataType = "String")
	@ApiOperation(value="规则集合关联原子规则集合信息查询",notes="")
	public List<CwmRuleGroupRelAtomGroupConfig> getRuleGroupRelAtomGroupConfig(@PathVariable("groupId")String groupId) {
		try {
			return cwmRuleRelService.getRuleGroupRelAtomGroupConfig(groupId);
		} catch (Exception e) {
			logger.error("原子规则集合配置查询报错",e);
		}
		return null;
	}
	
	*//**
	 * 规则集合关联插入
	 * @param CwmRuleGroupRels
	 * @return
	 *//*
	@ApiOperation(value="规则集合关联插入",notes="规则集合关联插入")
	@ApiResponses({ @ApiResponse(code = 500, message = "插入异常") })
	@RequestMapping(value="saveGroupRelAtomGroup",method = RequestMethod.POST)
	public Object saveGroupRelAtomGroup(@RequestBody List<CwmRuleGroupRel> CwmRuleGroupRels) {
		JSONObject jsonObject = new JSONObject();
		try {
			cwmRuleRelService.saveGroupRelAtomGroup(CwmRuleGroupRels);
			jsonObject.put("flag", "success");
			jsonObject.put("msg", "插入成功");
		} catch (Exception e) {
			jsonObject.put("flag", "fail");
			jsonObject.put("msg", "插入失败");
			logger.error("规则集合关联插入报错",e);
		}
		return jsonObject;
	}
	
	*//**
	 *原子规则集合修改配置信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *//*
	@ApiOperation(value="原子规则集合配置修改",notes="原子规则集合配置修改")
	@ApiResponses({ @ApiResponse(code = 500, message = "插入异常") })
	@RequestMapping(value="updateRuleAtomGroupConfig",method=RequestMethod.PUT)
	public void updateRuleAtomGroupConfig(@RequestBody List<CwmRuleAtomGroupConfig> ruleAtomGroupConfigList) {
		try {
			cwmRuleRelService.updateRuleAtomGroupConfig(ruleAtomGroupConfigList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}

