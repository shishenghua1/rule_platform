package com.boco.eoms.rule.cwmsysruleconfig.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.rule.cwmsysruleconfig.drools.RuleLoaderNew;
import com.boco.eoms.rule.cwmsysruleconfig.service.RuleService;
import com.boco.eoms.rule.cwmsysruleconfig.service.UseRuleServiceNew;
import com.boco.eoms.rule.cwmsysruleinterfacelog.service.ICwmRuleInterfaceLogService;
import com.boco.eoms.rule.cwmsysrulerel.controller.CwmRuleRelRestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/rule")
@Api(value="规则调用controller",tags={"规则调用restful接口"})
public class RestRule {

	@Autowired
	private UseRuleServiceNew useRuleService;
	
	@Autowired
	private RuleService ruleService;
	
	@Autowired
	private ICwmRuleInterfaceLogService cwmRuleInterfaceLogService;
	
	@Autowired
	private RuleLoaderNew ruleLoader;
	
	private Logger logger = LoggerFactory.getLogger(CwmRuleRelRestController.class);

	/**
	 * 规则调用rest接口
	 * @param ruleId
	 * @param map
	 * @return  excuteFlag 1表示规则已执行 2表示输入参数问题 3表示规则已关闭 4表示规则执行报错
	 */
	@ApiOperation(value="规则调用rest接口",notes="规则调用rest接口")
	@ApiResponses({ @ApiResponse(code = 500, message = "接口调用异常") })
	@RequestMapping(value = "/{ruleId}/excuteRule",method = RequestMethod.POST)
	public Object excuteRule(@PathVariable String ruleId,@RequestBody JSONObject paramsJson) {
		logger.info("输入参数"+paramsJson.toJSONString());
		//转为map传入规则调用
		Map map = (Map)JSON.parse(paramsJson.toJSONString());
		logger.info("输入参数map"+map.toString());
		JSONObject json = new JSONObject();
		//构建日志记录信息json
		JSONObject logJson = new JSONObject();
		logJson.put("interfaceEnName", "excuteRule");
		logJson.put("interfaceCnName", "规则调用rest接口");
		logJson.put("ruleId", ruleId);
		logJson.put("inputParams", paramsJson.toJSONString());
		try {
			//调用规则
			JSONObject ruleResultJson = useRuleService.excuteRule(map, ruleId);
			String excuteFlag = StaticMethod.nullObject2String(ruleResultJson.get("excuteFlag"));
			if("2".equals(excuteFlag)) {
				//参数错误
				json.put("status", "500");
				json.put("ruleResult", ruleResultJson);
				logJson.put("outputParams", json.toJSONString());
			}else if("3".equals(excuteFlag)){
				//规则关闭 相当于规则不执行 不影响工单后续流转
				json.put("status", "500");
				json.put("ruleResult", ruleResultJson);
				logJson.put("outputParams", json.toJSONString());
			}else{
				//规则执行成功
				json.put("status", "200");
				json.put("ruleResult", ruleResultJson);
				logJson.put("outputParams", json.toJSONString());
			}
		} catch (Exception e) {
			json.put("status", "500");
			json.put("error", "执行异常");
			logJson.put("exceptionMsg", e.toString());
			//保存日志
			cwmRuleInterfaceLogService.insert(cwmRuleInterfaceLogService.generateLogModel(logJson));
			logger.info("规则调用rest接口报错",e);
		}
		logger.info("输出参数"+json.toJSONString());
		//保存日志
		cwmRuleInterfaceLogService.insert(cwmRuleInterfaceLogService.generateLogModel(logJson));
		return json;
	}
	
	/**
	 * 规则调用rest接口（多线程接口）
	 * @param ruleId
	 * @param map
	 * @return  
	 */
	@ApiOperation(value="规则调用rest接口（多线程接口）",notes="规则调用rest接口（多线程接口）")
	@ApiResponses({ @ApiResponse(code = 500, message = "接口调用异常") })
	@RequestMapping(value = "/{ruleId}/asyncExcuteRule",method = RequestMethod.POST)
	public Object asyncExcuteRule(@PathVariable String ruleId,@RequestBody JSONObject paramsJson) {
		String simulationId = StaticMethod.nullObject2String(paramsJson.get("simulationId"));
		String isLastGateWay = StaticMethod.nullObject2String(paramsJson.get("isLastGateWay"));
		List paramsList = (List)paramsJson.get("simulationRunArr");//输入参数数组
		int init = 10;//每十条循环一次
		int toIndex = 0;//目标下标
		int total = paramsList.size();//总数
		for(int i=0;i<total;i+=init) {
			if(i+init > total) {//如果没有init条的时候 截取下标为当前数组长度
				toIndex = total;
			}else {
				toIndex = i+init;
			}
			List<Object> excuteList = paramsList.subList(i, toIndex);
			useRuleService.asyncExcuteRule(ruleId, simulationId, isLastGateWay, excuteList);
		}
		return true;
	}
	
	/**
	 * 规则输入参数获取接口
	 * @param ruleName
	 * @return
	 */
	@ApiOperation(value="获取规则调用输入参数接口",notes="获取规则调用输入参数接口")
	@ApiResponses({ @ApiResponse(code = 500, message = "接口调用异常") })
	@RequestMapping(value = "/{ruleId}/getRuleParams",method = RequestMethod.GET)
	public Object getRuleParams(@PathVariable String ruleId) {
		JSONObject json = new JSONObject();
		//构建日志记录信息json
		JSONObject logJson = new JSONObject();
		logJson.put("interfaceEnName", "getRuleParams");
		logJson.put("interfaceCnName", "获取规则调用输入参数接口");
		logJson.put("ruleId", ruleId);
		try {
			JSONObject paramsResultJson = ruleService.getRuleParams(ruleId);
			json.put("status", "200");
			json.put("paramsResult", paramsResultJson);
			logJson.put("outputParams", json.toJSONString());
		} catch (Exception e) {
			json.put("status", "500");
			json.put("error", e);
			logJson.put("exceptionMsg", e.toString());
			cwmRuleInterfaceLogService.insert(cwmRuleInterfaceLogService.generateLogModel(logJson));
			logger.info("获取规则调用输出参数rest接口报错",e);
		}
		cwmRuleInterfaceLogService.insert(cwmRuleInterfaceLogService.generateLogModel(logJson));
		logger.info("输出参数"+json.toJSONString());
		return json; 
	}
	
	/**
	 * 规则加载rest接口
	 * @param ruleId
	 * @return  
	 */
	@ApiOperation(value="规则加载rest接口",notes="规则加载rest接口")
	@ApiResponses({ @ApiResponse(code = 500, message = "接口调用异常") })
	@RequestMapping(value = "/{ruleId}/reloadRule",method = RequestMethod.POST)
	public Object reloadRule(@PathVariable String ruleId) {
		JSONObject json = new JSONObject();
		try {
			ruleLoader.reload(ruleId);
			json.put("flag", "success");
			json.put("msg", ruleId+"加载成功");
			logger.info(json.toJSONString());
		} catch (Exception e) {
			json.put(ruleId+"加载失败", e);
			json.put("flag", "fail");
			json.put("msg", ruleId+"加载失败");
			logger.info(json.toJSONString());
		}
		return json;
	}
	
	/**
	 * 获取规则输入参数中英文字段值
	 * @param ruleId 可传多个 多个用逗号隔开
	 * @return  
	 */
	@ApiOperation(value="获取规则输入参数中英文字段值rest接口",notes="获取规则输入参数中英文字段值rest接口")
	@ApiResponses({ @ApiResponse(code = 500, message = "接口调用异常") })
	@RequestMapping(value = "/getCnAndEnInputParams",method = RequestMethod.POST)
	public Object getCnAndEnInputParams(@RequestParam String ruleId) {
		JSONObject json = new JSONObject();
		try {
			JSONObject paramsResultJson = ruleService.getInputParamEnAndCnName(ruleId);
			json.put("status", "200");
			json.put("paramsResult", paramsResultJson);
		} catch (Exception e) {
			json.put("status", "500");
			json.put("error", e);
			logger.info("获取规则输入参数中英文字段值rest接口",e);
		}
		logger.info("输出参数"+json.toJSONString());
		return json; 
	}
}
