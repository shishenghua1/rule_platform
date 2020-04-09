package com.boco.eoms.rule.cwmsysruleconfig.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.drools.core.base.RuleNameEndsWithAgendaFilter;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.rule.AIClient.service.IAIClientService;
import com.boco.eoms.rule.cwmsysruleatoms.mapper.CwmRuleAtomsMapper;
import com.boco.eoms.rule.cwmsysruleconfig.drools.KieSessionHelper;
import com.boco.eoms.rule.cwmsysruleconfig.service.RuleService;
import com.boco.eoms.rule.cwmsysruleconfig.service.UseRuleService;
import com.boco.eoms.rule.cwmsysrulegroupoutput.mapper.CwmRuleGroupOutputMapper;
import com.boco.eoms.rule.cwmsysrulegroupoutput.model.CwmRuleGroupOutput;
import com.boco.eoms.rule.cwmsysrulegroupoutputparams.mapper.CwmRuleGroupOutputParamsMapper;
import com.boco.eoms.rule.cwmsysrulegroupoutputparams.model.CwmRuleGroupOutputParams;
import com.boco.eoms.rule.cwmsysrulegroupoutputrel.mapper.CwmRuleGroupOutputRelMapper;
import com.boco.eoms.rule.cwmsysrulegroupoutputrel.model.CwmRuleGroupOutputRel;
import com.boco.eoms.rule.cwmsysrulegrouprel.mapper.CwmRuleGroupRelMapper;
import com.boco.eoms.rule.cwmsysrulegrouprel.model.CwmRuleGroupRel;
import com.boco.eoms.rule.cwmsysrulegroups.mapper.CwmRuleGroupsMapper;
import com.boco.eoms.rule.cwmsysrulegroups.model.CwmRuleGroups;
import com.boco.eoms.rule.cwmsysrulerel.controller.CwmRuleRelRestController;

import bsh.Interpreter;


@Service
public class UseRuleServiceImpl implements UseRuleService {
	
	@Autowired
	private CwmRuleGroupRelMapper cwmRuleGroupRelMapper;
	
	@Autowired
	private CwmRuleGroupOutputRelMapper cwmRuleGroupOutputRelMapper;
	
	@Autowired
	private CwmRuleGroupOutputParamsMapper cwmRuleGroupOutputParamsMapper;
	
	@Autowired
	private CwmRuleGroupsMapper cwmRuleGroupsMapper;
	
	@Autowired
	private CwmRuleGroupOutputMapper cwmRuleGroupOutputMapper;
	
	@Autowired
	private KieSessionHelper kieSessionHelper;
	
	@Autowired
	private RuleService ruleService;
	
	private Logger logger = LoggerFactory.getLogger(CwmRuleRelRestController.class);
	
	/**
	 * 执行规则
	 * @param map
	 * @param ruleId
	 * @return
	 */
	@Override
	public JSONObject excuteRule(Map<String,Object> map,String ruleId) {
		JSONObject json = new JSONObject();
		//根据规则id查询该规则是否需要全部执行 当子规则集合遇到true时就返回 不再执行之后的规则
		CwmRuleGroups cwmRuleGroupsEnable = cwmRuleGroupsMapper.selectEnableRuleByRuleId(ruleId);
		CwmRuleGroups cwmRuleGroups = cwmRuleGroupsMapper.selectByPrimaryKey(ruleId);
		if(cwmRuleGroupsEnable == null) {
			if(cwmRuleGroups != null) {
				String groupName = StaticMethod.nullObject2String(cwmRuleGroups.getGroupName());
				json.put("excuteFlag", "3");
				json.put("msg", "【"+groupName+"】"+"规则已关闭");
				logger.info(json.toJSONString());
			}else {
				json.put("excuteFlag", "3");
				json.put("msg", "【"+ruleId+"】"+"规则不存在");
				logger.info(json.toJSONString());
			}
			return json;
		}
		
		//输入参数校验及处理
		JSONObject checkJson = ruleService.checkAndDealParams(map, ruleId);
//		String msg = StaticMethod.nullObject2String(checkJson.get("msg"));
//		if(!"".equals(msg)) {
//			checkJson.put("excuteFlag", "2");
//			return checkJson;
//		}
		logger.info("格式化后的输入参数"+map.toString());
		
		//针对福建AI功能临时添加
		ruleService.AIDeal(map, ruleId);
		
		String firstSubsetIsEnd = StaticMethod.nullObject2String(cwmRuleGroups.getFirstSubsetIsEnd());
		
		//查询规则不符合情况的详细信息
		CwmRuleGroupOutput cwmRuleGroupOutput = cwmRuleGroupOutputMapper.selectByGroupIdAndResult(ruleId, "false");
		String desc = StaticMethod.nullObject2String(cwmRuleGroupOutput.getGroupOutputExplain());
		
		if("true".equals(firstSubsetIsEnd)) {
			//若遇到符合的情况就停止 需循环遍历当前规则下的规则集合
			List<String> groupIds = new ArrayList<String>();
			getGroupIdList(groupIds, ruleId);
			if(groupIds != null && groupIds.size() > 0) {
				for(String groupId : groupIds) {
					//执行规则
					json = useRule(map, groupId);
					String result = StaticMethod.nullObject2String(json.get("result"));
					if("true".equals(result)) {
						return json;
					}
				}
			}
			json.put("result", "false");
			json.put("desc", desc);
			json.put("excuteFlag", "1");
			outputDesc("false", ruleId, json, map);
		}else {
			return useRule(map, ruleId);
		}
		return json;
	}
	
    /**
     * 执行规则详情
     */
    public JSONObject useRule(Map<String,Object> map,String ruleId) {
    		JSONObject json = new JSONObject();
    		try {
	    			//获取kieSession
//	    	        KieSession kieSession = kieSessionHelper.getKieSessionByRuleId(ruleId);
	    	        Map<String,Object> outputMap = new HashMap<String,Object>();
	    	    		//根据当前规则查询关联的规则集合并执行
	    	        findAndExcuteRule(map, ruleId, outputMap);
	    	        //查询规则不成立的情况 并将情况先赋值到结果中 
	    	        CwmRuleGroupOutput cwmRuleGroupOutput = cwmRuleGroupOutputMapper.selectByGroupIdAndResult(ruleId, "false");
	    	        map.put("result", "false");
	    	        map.put("desc", (cwmRuleGroupOutput == null)?"":StaticMethod.nullObject2String(cwmRuleGroupOutput.getGroupOutputExplain()));
	    	        //执行当前规则
	    	        threadExcuteRule(map, ruleId);
//	    	        kieSession.insert(map);
//	    		    kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter(ruleId));
//	    		    kieSession.dispose();
	    		    String desc = StaticMethod.nullObject2String(map.get("desc"));
	    		    //根据规则id和规则执行结果查询规则输出关联信息
	    		    List<CwmRuleGroupOutputRel> cwmRuleGroupOutputRels = cwmRuleGroupOutputRelMapper.selectByGroupId(ruleId,StaticMethod.nullObject2String(map.get("result")));
	    		    //遍历map中的key
	    		    for(Entry<String, Object> entry:outputMap.entrySet()){
	    		    		String key = StaticMethod.nullObject2String(entry.getKey());
	    		    			if(cwmRuleGroupOutputRels != null && cwmRuleGroupOutputRels.size() > 0) {
	    			    	    		for(CwmRuleGroupOutputRel cwmRuleGroupOutputRel : cwmRuleGroupOutputRels) {
	    			    	    			String groupIdRel = StaticMethod.nullObject2String(cwmRuleGroupOutputRel.getGroupIdRel());
	    			    	    			if(key.equals(groupIdRel)) {
	    			    	    				String groupOutputResultRel = StaticMethod.nullObject2String(cwmRuleGroupOutputRel.getGroupOutputResultRel());
	    			    	    				String result = StaticMethod.nullObject2String(outputMap.get(key));
	    			    	    				if(result.equals(groupOutputResultRel)) {
	    			    	    					String groupOutputExplainRel = StaticMethod.nullObject2String(cwmRuleGroupOutputRel.getGroupOutputExplainRel());
	    			    	    					//拼接所有关联的输出描述
	    			    	    					desc = desc + ";" + groupOutputExplainRel;
	    			    	    				}
	    			    	    			}
	    			    	    		}
	    		    			}
	    	        }
	    	        //设置规则执行结果返回值
	    		    json.put("result", map.get("result"));
	    		    json.put("desc", desc);
	    		    json.put("excuteFlag", "1");
	    		    outputDesc(StaticMethod.nullObject2String(map.get("result")), ruleId, json, outputMap);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("执行规则报错",e);
			}
        return json;
    }
    
    
    /**
     * 查询并执行规则下关联的所有规则集合 按指定格式把执行结果放入参数map中
     * @param map
     * @param ruleId
     * @param kieSession
     */
    public void findAndExcuteRule(Map map,String ruleId,Map<String,Object> outputMap) {
    		//递归查询当前规则下所有关联的规则集合关系
    		List<CwmRuleGroupRel> cwmRuleGroupRels = cwmRuleGroupRelMapper.getAllRuleGroupsFromRel(ruleId);
    		if(cwmRuleGroupRels != null && cwmRuleGroupRels.size() > 0) {
    			//由于递归查询时是从外向内递归 而执行的时候需要从内向外执行 里面的规则集合结果需要作为外面的规则集合的条件 所以这里需要倒着遍历
    			for(int i = cwmRuleGroupRels.size()-1;i>=0;i--) {
    				map.put("result", "");
    				CwmRuleGroupRel cwmRuleGroupRel = cwmRuleGroupRels.get(i);
    				String parentNodeId = StaticMethod.nullObject2String(cwmRuleGroupRel.getParentNodeId());//父节点id
    				String ruleIdRel = StaticMethod.nullObject2String(cwmRuleGroupRel.getRuleIdRel());//关联规则集合id
    				//参数id 拼接规则时候按照 这种 父节点id_关联节点id 来替换规则集合
    				String varId = "a"+parentNodeId + "_" + ruleIdRel;
    				//执行
    				threadExcuteRule(map, ruleIdRel);
//	    	        KieSession kieSession = kieSessionHelper.getKieSessionByRuleId(ruleIdRel);
//	    	        kieSession.insert(map);
//	    	        kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter(ruleIdRel));
//	    	        kieSession.dispose();
        	        //由于drools执行 只走成功的情况 失败的情况是没有返回值的 这里需要处理一下
        	        boolean result = ("".equals(StaticMethod.nullObject2String(map.get("result")))?false:true);
        	        outputMap.put(ruleIdRel, result);
        	        map.put(varId, result);
    			}
    		}
    }
    
    
    /**
     * 递归查询规则集合 当当前的规则集合下没有关联的规则集合时 则执行当前的规则集合
     * @param map
     * @param ruleName
     */
    public void findGroupRule(Map map,String ruleName,JSONObject relJson,KieSession kieSession) {
    		//递归查询当前规则关联的规则集合
    		//若当前规则集合下没有关联的规则集合则执行该规则
    		List<CwmRuleGroupRel> cwmRuleGroupRels = cwmRuleGroupRelMapper.selectGroupRuleBGroupId(ruleName);
    		if(cwmRuleGroupRels != null && cwmRuleGroupRels.size() > 0) {
    			for(CwmRuleGroupRel cwmRuleGroupRel : cwmRuleGroupRels) { 
    				String ruleRelId = StaticMethod.nullObject2String(cwmRuleGroupRel.getRuleIdRel());//关联的规则集合id
    				relJson.put(ruleRelId, ruleName);
    				findGroupRule(map, ruleRelId,relJson,kieSession);
    			}
    		}else {
    			//递归执行规则
    			excuteRule(map, ruleName, relJson,kieSession);
    		}
    }
    
    /**
     * 递归执行规则 从里向外
     * 执行规则集合 按照relJson中数据查询上一级规则集合并执行 当取不到上一级规则集合时 则说明此节点为根节点 递归结束
     * 执行结果放在全局参数map中 key为上一级id_当前id （规则集合加载时 按照这种形式加载规则集合 上一级id_当前id为规则集合中的参数 最终执行根节点规则时替换该参数执行）
     * @param map
     * @param ruleName
     * @param preRuleName
     */
    public void excuteRule(Map map,String ruleName,JSONObject relJson,KieSession kieSession) {
    		String parentRuleId = StaticMethod.nullObject2String(relJson.get(ruleName));
    		if(!"".equals(parentRuleId)) {
    			map.put(parentRuleId+"_"+ruleName, false);
    	        kieSession.insert(map);
    	        kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter(ruleName));
    	        kieSession.dispose();
    	        map.put(parentRuleId+"_"+ruleName, map.get("result"));
    	        excuteRule(map, parentRuleId, relJson,kieSession);
    		}
    }
    
    /**
     * map中的参数格式化
     * @param map
     */
    public void mapDataFormat(Map<String,Object> map) {
    	 	for(Entry<String, Object> entry:map.entrySet()){
    	 		String key = StaticMethod.nullObject2String(entry.getKey());
    	 		String value = StaticMethod.nullObject2String(entry.getValue());
    	 		//判断是否为时间格式
    	 		boolean isDate = StaticMethod.isDate(value);
    	 		if(isDate) {
    	 			//获取时间
    	 			Date date = StaticMethod.str2Date(value);
    	 			long minute = date.getTime()/1000/60;//将时间转为分钟 规则平台默认时间单位为分钟
    	 			map.put(key, minute);
    	 		}
    	 	}
    }
    
    /**
     * 递归获取当前规则下的规则集合id （不包含子规则集合关联的规则集合）
     * @param parentNodeId
     * @return
     */
    public List<String> getGroupIdList(List<String> groupIds,String parentNodeId){
    		List<CwmRuleGroupRel> cwmRuleGroupRels = cwmRuleGroupRelMapper.selectEnableRelByParentNodeId(parentNodeId);
    		if(cwmRuleGroupRels != null && cwmRuleGroupRels.size() > 0) {
    			for(CwmRuleGroupRel cwmRuleGroupRel : cwmRuleGroupRels) {
    				String nodeType = StaticMethod.nullObject2String(cwmRuleGroupRel.getNodeType());
    				String relId = StaticMethod.nullObject2String(cwmRuleGroupRel.getRuleIdRel());
    				if("condition".equals(nodeType)) {
    					getGroupIdList(groupIds, relId);
    				}else if("ruleGroup".equals(nodeType)) {
    					groupIds.add(relId);
    				}
    			}
    		}
    		return groupIds; 
    }
    
    /**
     * 规则输出参数处理
     * @param result
     * @param ruleId
     * @param json
     */
    public void outputDesc(String result,String ruleId,JSONObject json,Map map) {
    		try {
    			//根据规则id查询规则输出参数
    		    Interpreter interpreter = new Interpreter();  
    		    List<CwmRuleGroupOutputParams> cwmRuleGroupOutputParames = cwmRuleGroupOutputParamsMapper.getParamsByGroupIdAndResult(ruleId,result);
    		    if(cwmRuleGroupOutputParames != null && cwmRuleGroupOutputParames.size()  > 0) {
    		    		for(CwmRuleGroupOutputParams cwmRuleGroupOutputParams : cwmRuleGroupOutputParames) {
    		    			String param1 = StaticMethod.nullObject2String(cwmRuleGroupOutputParams.getOutputEnParam1());
    		    			String param2 = StaticMethod.nullObject2String(cwmRuleGroupOutputParams.getOutputEnParam2());
    		    			String type2 = StaticMethod.nullObject2String(cwmRuleGroupOutputParams.getOutputParamType2());
    		    			String operate2 = StaticMethod.nullObject2String(cwmRuleGroupOutputParams.getOperator2());
    		    			String param3 = StaticMethod.nullObject2String(cwmRuleGroupOutputParams.getOutputEnParam3());
    		    			String type3 = StaticMethod.nullObject2String(cwmRuleGroupOutputParams.getOutputParamType3());
    		    			//如果参数为变量 则从map中获取
    		    			if("".equals(param3)) {
			    			json.put(param1, param2);
			    		}else {
			    			param2 = (type2.contains("变量")?StaticMethod.nullObject2String(map.get(param2)):param2);
	    		    			param3 = (type3.contains("变量")?StaticMethod.nullObject2String(map.get(param3)):param3);
	    		    			//拼接表达式
	    		    			String expresion = param2 + operate2 + param3;
	    		    			Object excuteResult = interpreter.eval(expresion);
	    		    			json.put(param1, excuteResult); 
			    		}
    		    		}
    		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    /**
     * drools执行规则
     * @param map
     * @param ruleId
     */
    public void threadExcuteRule(Map map,String ruleId) {
		KieSession kieSession = kieSessionHelper.getKieSessionByRuleId(ruleId);
        kieSession.insert(map);
        kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter(ruleId));
        kieSession.dispose();
    }
    
}
