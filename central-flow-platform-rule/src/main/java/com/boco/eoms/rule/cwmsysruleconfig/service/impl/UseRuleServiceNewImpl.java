package com.boco.eoms.rule.cwmsysruleconfig.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.drools.core.base.RuleNameEndsWithAgendaFilter;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.base.config.RuleProjectServer;
import com.boco.eoms.base.util.HttpClientServlet;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.rule.cwmsysruleatoms.mapper.CwmRuleAtomsMapper;
import com.boco.eoms.rule.cwmsysruleatoms.model.CwmRuleAtoms;
import com.boco.eoms.rule.cwmsysruleconfig.drools.KieSessionHelper;
import com.boco.eoms.rule.cwmsysruleconfig.service.RuleService;
import com.boco.eoms.rule.cwmsysruleconfig.service.UseRuleServiceNew;
import com.boco.eoms.rule.cwmsysruleexcutelog.mapper.CwmRuleExecuteLogMapper;
import com.boco.eoms.rule.cwmsysruleexcutelog.model.CwmRuleExecuteLog;
import com.boco.eoms.rule.cwmsysrulegroupoutput.mapper.CwmRuleGroupOutputMapper;
import com.boco.eoms.rule.cwmsysrulegroupoutput.model.CwmRuleGroupOutput;
import com.boco.eoms.rule.cwmsysrulegroupoutputparams.mapper.CwmRuleGroupOutputParamsMapper;
import com.boco.eoms.rule.cwmsysrulegroupoutputparams.model.CwmRuleGroupOutputParams;
import com.boco.eoms.rule.cwmsysrulegroupoutputrel.mapper.CwmRuleGroupOutputRelMapper;
import com.boco.eoms.rule.cwmsysrulegrouprel.mapper.CwmRuleGroupRelMapper;
import com.boco.eoms.rule.cwmsysrulegrouprel.model.CwmRuleGroupRel;
import com.boco.eoms.rule.cwmsysrulegroups.mapper.CwmRuleGroupsMapper;
import com.boco.eoms.rule.cwmsysrulegroups.model.CwmRuleGroups;

import bsh.Interpreter;


@Service
public class UseRuleServiceNewImpl implements UseRuleServiceNew {
	
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
	
	@Autowired
	private CwmRuleAtomsMapper cwmRuleAtomsMapper;
	
	@Autowired
	private CwmRuleExecuteLogMapper cwmRuleExecuteLogMapper;
	
	@Autowired
	private RuleProjectServer ruleProjectServer;
	
	//注入福建固定字段
	@Value("${excuteLogFlag}")
	private boolean excuteLogFlag;
	
	//注入Ai相似度规则名称
	@Value("${aisimilarName}")
	private String aisimilarName;
	
	private Logger logger = LoggerFactory.getLogger(UseRuleServiceNewImpl.class);
	
	/**
	 * 执行规则
	 * @param map
	 * @param ruleId
	 * @return
	 */
	@Override
	@Async("asyncServiceExecutor")
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
		
		//规则执行日志集合 先封装 规则执行完之后再插入
		List<CwmRuleExecuteLog> logList = new ArrayList<CwmRuleExecuteLog>();
		Map logMap = new HashMap();
		logMap.put("callerName", "测试");
		logMap.put("callerFlag", "测试");
		logMap.put("ruleIdRel", ruleId);
		
		if("true".equals(firstSubsetIsEnd)) {
			//若遇到符合的情况就停止 需循环遍历当前规则下的规则集合
			//执行开始时间
			long startTimeT= new Date().getTime();
			List<String> groupIds = new ArrayList<String>();
			getGroupIdList(groupIds, ruleId);
			if(groupIds != null && groupIds.size() > 0) {
				for(String groupId : groupIds) {
					//执行规则
					json = useRule(map, groupId,logList,logMap);
					String result = StaticMethod.nullObject2String(json.get("result"));
					if("true".equals(result)) {
						CwmRuleGroups rule = cwmRuleGroupsMapper.selectByPrimaryKey(ruleId);
						//执行结束时间
						long endTimeT= new Date().getTime();
						//记录规则执行日志
						//封装日志记录map
						logMap.put("ruleType", StaticMethod.nullObject2String(rule.getGroupType()));
						logMap.put("ruleId", ruleId);
						logMap.put("ruleName", StaticMethod.nullObject2String(rule.getGroupName()));
						logMap.put("inputParam", map.toString());
						logMap.put("outputParamResult", StaticMethod.nullObject2String(json.get("result")));
						logMap.put("outputParamDesc", StaticMethod.nullObject2String(json.get("desc")));
						logMap.put("executeTime", endTimeT-startTimeT);
						logMap.put("ruleFlag", "");
						//放入日志记录list 等待插入
						logList.add(getExcuteModel(logMap));
						if(excuteLogFlag) {
							cwmRuleExecuteLogMapper.batchInsert(logList);
							logger.info("规则执行日志插入成功");
						}
						return json;
					}
				}
			}
			json.put("result", "false");
			json.put("desc", desc);
			json.put("excuteFlag", "1");
			CwmRuleGroups rule = cwmRuleGroupsMapper.selectByPrimaryKey(ruleId);
			//执行结束时间
			long endTimeT= new Date().getTime();
			//记录规则执行日志
			//封装日志记录map
			logMap.put("ruleType", StaticMethod.nullObject2String(rule.getGroupType()));
			logMap.put("ruleId", ruleId);
			logMap.put("ruleName", StaticMethod.nullObject2String(rule.getGroupName()));
			logMap.put("inputParam", map.toString());
			logMap.put("outputParamResult", StaticMethod.nullObject2String(json.get("result")));
			logMap.put("outputParamDesc", StaticMethod.nullObject2String(json.get("desc")));
			logMap.put("executeTime", endTimeT-startTimeT);
			logMap.put("ruleFlag", "");
			//放入日志记录list 等待插入
			logList.add(getExcuteModel(logMap));
			if(excuteLogFlag) {
				cwmRuleExecuteLogMapper.batchInsert(logList);
				logger.info("规则执行日志插入成功");
			}
			outputDesc("false", ruleId, json, map);
		}else {
			json = useRule(map, ruleId,logList,logMap);
			if(excuteLogFlag) {
				cwmRuleExecuteLogMapper.batchInsert(logList);
				logger.info("规则执行日志插入成功");
			}
			return json;
		}
		return json;
	}
	
    /**
     * 执行规则详情
     */
    public JSONObject useRule(Map<String,Object> map,String ruleId,List<CwmRuleExecuteLog> logList,Map logMap) {
    		JSONObject json = new JSONObject();
    		CwmRuleGroups rule = cwmRuleGroupsMapper.selectByPrimaryKey(ruleId);
    		//执行开始时间
		long startTimeT= new Date().getTime();
    		try {
    				JSONArray groupIdArr = new JSONArray();
    				JSONArray atomIdArr = new JSONArray();
    				//将执行规则所关联的规则集合 原子规则取出
    				getGroupAndAtomId(ruleId, groupIdArr, atomIdArr);
    				
    				//规则集合输入map 规则集合的输入是原子规则执行的结果 输入参数名为  atom$+atomId
    				Map allGroupInputMap = new HashMap();
//    				String atomIdArr[] = atomIds.toString().split(",");
    				//先执行关联的原子规则
    				if(atomIdArr != null && atomIdArr.size() > 0) {
    					for(Object obj : atomIdArr) {
    						String atomId = StaticMethod.nullObject2String(obj);
    						Map atomInputMap = getAtomInput(map, atomId);
    						//执行开始时间
    						long startTime= new Date().getTime();
    						//执行规则
    						threadExcuteRule(atomInputMap, atomId);
    						//执行结束时间
    						long endTime= new Date().getTime();
    						//原子规则执行结果
    						String result = StaticMethod.nullObject2String(atomInputMap.get("result"));
    						boolean resultValue = ("".equals(result))?false:true;//原子规则输出 原子规则只有
    						//原子规则的输出为规则集合输入 放入所有规则集合输入map中
    						allGroupInputMap.put("atom$"+atomId, resultValue);
    						//记录原子规则执行日志
    						//当前原子规则输入map
    						atomInputMap =  getAtomInput(map, atomId);
    						//封装日志记录map
    						logMap.put("ruleType", "atom");
    						logMap.put("ruleId", atomId);
    						logMap.put("ruleName", "");
    						logMap.put("inputParam", atomInputMap.toString());
    						logMap.put("outputParamResult", resultValue);
    						logMap.put("outputParamDesc", "");
    						logMap.put("executeTime", endTime-startTime);
    						logMap.put("ruleFlag", "");
    						//放入日志记录list 等待插入
    						logList.add(getExcuteModel(logMap));
    					}
    				}
    				//执行规则集合
    				String groupOutPutResult = "";//当前执行规则输出
    				String groupOutPutdesc = "";//当前执行规则输出描述
    				if(groupIdArr != null && groupIdArr.size() > 0) {
    					//装配规则集合输入
    					Map assemblingInputMap = new HashMap();
    					//装配中各个规则集合的输出描述字符串
    					String desc = "";
    					//该规则集合为装配的规则集合
    					for(Object obj : groupIdArr) {
    						String groupId = StaticMethod.nullObject2String(obj);
    						CwmRuleGroups cwmRuleGroup = cwmRuleGroupsMapper.selectByPrimaryKey(groupId);
    						//查询规则不成立的情况 并将情况先赋值到结果中 
	    		    	        CwmRuleGroupOutput cwmRuleGroupOutput = cwmRuleGroupOutputMapper.selectByGroupIdAndResult(groupId, "false");
	    		    	        allGroupInputMap.put("result", false);
	    		    	        allGroupInputMap.put("desc", (cwmRuleGroupOutput == null)?"":StaticMethod.nullObject2String(cwmRuleGroupOutput.getGroupOutputExplain()));
	    		    	        //执行开始时间
	    		    	        long startTime= new Date().getTime();
	    		    	        //执行规则
    						threadExcuteRule(allGroupInputMap, groupId);
    						//执行结束时间
    						long endTime= new Date().getTime();
    						//当前规则输出结果
    						boolean resultValue = Boolean.getBoolean(StaticMethod.nullObject2String(allGroupInputMap.get("result")));
    						//当前规则输出描述
    						String descValue = StaticMethod.nullObject2String(allGroupInputMap.get("desc"));
    						//将当前规则输出结果放入装配规则输入map中
    						assemblingInputMap.put("group$"+groupId,resultValue);
    						//拼接每个规则集合输出描述
    						desc = ("".equals(desc))?descValue:desc+","+descValue;
    						//记录规则集合的执行日志
    						//查询当前规则集合关联的所有原子规则输入变量
    						StringBuilder atomInputParams = new StringBuilder();
    						getAtomParamByGroupId(groupId, atomInputParams);
    						//从输入map中获取当前子规则集合输入参数
    						Map groupInputMap = new HashMap();//规则集合输入
    						String atomInputParam[] = atomInputParams.toString().split(",");
    						if(atomInputParam != null && atomInputParam.length > 0) {
    							for(String inputparam : atomInputParam) {
    								//依次从初始输入map中获取参数
    								groupInputMap.put(inputparam, StaticMethod.nullObject2String(map.get("inputparam")));
    							}
    						}
    						//封装日志记录map
    						logMap.put("ruleType", "group");
    						logMap.put("ruleId", groupId);
    						logMap.put("ruleName", StaticMethod.nullObject2String(cwmRuleGroup.getGroupName()));
    						logMap.put("inputParam", groupInputMap.toString());
    						logMap.put("outputParamResult", resultValue);
    						logMap.put("outputParamDesc", descValue);
    						logMap.put("executeTime", endTime-startTime);
    						if(aisimilarName.equals(logMap.get("ruleName"))) {
    							logMap.put("ruleFlag", "AI_similar");
    						}
    						//放入日志记录list 等待插入
    						logList.add(getExcuteModel(logMap));
    					}
    					//查询装配不成立的情况 并将情况先赋值到结果中 
		    	        CwmRuleGroupOutput cwmRuleGroupOutput = cwmRuleGroupOutputMapper.selectByGroupIdAndResult(ruleId, "false");
		    	        assemblingInputMap.put("result", false);
		    	        assemblingInputMap.put("desc", (cwmRuleGroupOutput == null)?"":StaticMethod.nullObject2String(cwmRuleGroupOutput.getGroupOutputExplain()));
		    	        //执行装配
    					threadExcuteRule(assemblingInputMap, ruleId);
    					groupOutPutResult = StaticMethod.nullObject2String(assemblingInputMap.get("result"));
    					groupOutPutdesc = StaticMethod.nullObject2String(assemblingInputMap.get("desc"));
    				}else {
    					//该规则集合为单独的规则集合
    					//查询规则不成立的情况 并将情况先赋值到结果中 
		    	        CwmRuleGroupOutput cwmRuleGroupOutput = cwmRuleGroupOutputMapper.selectByGroupIdAndResult(ruleId, "false");
		    	        allGroupInputMap.put("result", false);
		    	        allGroupInputMap.put("desc", (cwmRuleGroupOutput == null)?"":StaticMethod.nullObject2String(cwmRuleGroupOutput.getGroupOutputExplain()));
		    	        //执行规则
					threadExcuteRule(allGroupInputMap, ruleId);
					groupOutPutResult = StaticMethod.nullObject2String(allGroupInputMap.get("result"));
					groupOutPutdesc = StaticMethod.nullObject2String(allGroupInputMap.get("desc"));
    				}
    				//执行结束时间
    				long endTimeT= new Date().getTime();
    				//记录规则执行日志
    				//封装日志记录map
				logMap.put("ruleType", StaticMethod.nullObject2String(rule.getGroupType()));
				logMap.put("ruleId", ruleId);
				logMap.put("ruleName", StaticMethod.nullObject2String(rule.getGroupName()));
				logMap.put("inputParam", map.toString());
				logMap.put("outputParamResult", groupOutPutResult);
				logMap.put("outputParamDesc", groupOutPutdesc);
				logMap.put("executeTime", endTimeT-startTimeT);
				logMap.put("ruleFlag", "");
				//放入日志记录list 等待插入
				logList.add(getExcuteModel(logMap));
    				//总体输出map
    				Map outputMap = new HashMap();
    				outputMap.put("result", groupOutPutResult);
    				outputMap.put("desc", groupOutPutdesc);
	    	        //设置规则执行结果返回值
	    		    json.put("result", groupOutPutResult);
	    		    json.put("desc", groupOutPutdesc);
	    		    json.put("excuteFlag", "1");
	    		    //福建AI服务输出详细结果
	    		    String AICheckDescResult = StaticMethod.nullObject2String(map.get("AICheckDescResult"));
	    		    String linkDealStep_AI_similar = StaticMethod.nullObject2String(map.get("linkDealStep_AI_similar"));
	    		    if(!"".equals(AICheckDescResult)) {
	    		    		json.put("AICheckDescResult", AICheckDescResult);
	    		    }
	    		    if(!"".equals(linkDealStep_AI_similar)) {
	    		    		json.put("linkDealStep_AI_similar", linkDealStep_AI_similar);
	    		    }
	    		    outputDesc(groupOutPutResult, ruleId, json, outputMap);
			} catch (Exception e) {
				e.printStackTrace();
				json.put("error", "执行异常");
				//执行结束时间
				long endTimeT= new Date().getTime();
				//封装日志记录map
				logMap.put("ruleType", StaticMethod.nullObject2String(rule.getGroupType()));
				logMap.put("ruleId", ruleId);
				logMap.put("ruleName", StaticMethod.nullObject2String(rule.getGroupName()));
				logMap.put("inputParam", map.toString());
				logMap.put("outputParamResult", "异常");
				logMap.put("outputParamDesc", e.toString());
				logMap.put("executeTime", endTimeT-startTimeT);
				logMap.put("ruleFlag", "");
				//放入日志记录list 等待插入
				logList.add(getExcuteModel(logMap));
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
		FactHandle handle = kieSession.insert(map);
        kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter(ruleId));
        kieSession.delete(handle);
        kieSession.dispose();
    }
    
    /**
     * 递归查询规则集合ID和原子规则id
     * @param ruleId
     * @param groupIds 规则集合id字符串
     * @param atomIds 原子规则id字符串
     */
    public void getGroupAndAtomId(String ruleId,JSONArray groupIdArr,JSONArray atomIdArr) {
    		//根据关联id查询关联关系
    		List<CwmRuleGroupRel> cwmRuleGroupRels = cwmRuleGroupRelMapper.selectEnableRelByParentNodeId(ruleId);
    		if(cwmRuleGroupRels != null && cwmRuleGroupRels.size() > 0) {
    			for(CwmRuleGroupRel cwmRuleGroupRel : cwmRuleGroupRels) {
    				String nodeType = StaticMethod.nullObject2String(cwmRuleGroupRel.getNodeType());//节点类型
    				String ruleRelId = StaticMethod.nullObject2String(cwmRuleGroupRel.getRuleIdRel());//关联的节点id
    				if("atomRule".equals(nodeType)) {
    					//原子规则直接拼接
    					atomIdArr.add(ruleRelId);
//    					atomIds = ("".equals(atomIds.toString()))?atomIds.append(ruleRelId):atomIds.append(","+ruleRelId);
    				}else if("ruleGroup".equals(nodeType)) {
    					//规则集合拼接 然后递归
    					groupIdArr.add(ruleRelId);
//    					groupIds = ("".equals(groupIds.toString()))?groupIds.append(ruleRelId):groupIds.append(","+ruleRelId);
    					getGroupAndAtomId(ruleRelId, groupIdArr, atomIdArr);
    				}else if("condition".equals(nodeType)) {
    					//条件直接递归
    					getGroupAndAtomId(ruleRelId, groupIdArr, atomIdArr);
    				}
    			}
    		}
    }
    
    /**
     * 获取原子规则输入
     * @param map
     * @param atomId
     * @return
     */
    public Map getAtomInput(Map map,String atomId) {
    		Map inputMap = new HashMap();
    		CwmRuleAtoms cwmRuleAtom = cwmRuleAtomsMapper.selectByPrimaryKey(atomId);
		String inputEnParam1 = StaticMethod.nullObject2String(cwmRuleAtom.getInputEnParam1());//输入参数1英文名
		String inputParamType1 = StaticMethod.nullObject2String(cwmRuleAtom.getInputParamType1());//输入参数1类型
		if(inputParamType1.contains("变量")) {
			String value = StaticMethod.nullObject2String(map.get(inputEnParam1));
			inputMap.put(inputEnParam1, value);
		}
		String inputEnParam2 = StaticMethod.nullObject2String(cwmRuleAtom.getInputEnParam2());//输入参数2英文名
		String inputParamType2 = StaticMethod.nullObject2String(cwmRuleAtom.getInputParamType2());//输入参数2类型
		if(inputParamType2.contains("变量")) {
			String value = StaticMethod.nullObject2String(map.get(inputEnParam2));
			inputMap.put(inputEnParam2, value);
		}
		String inputEnParam3 = StaticMethod.nullObject2String(cwmRuleAtom.getInputEnParam3());//输入参数3英文名
		String inputParamType3 = StaticMethod.nullObject2String(cwmRuleAtom.getInputParamType3());//输入参数3类型
		if(inputParamType3.contains("变量")) {
			String value = StaticMethod.nullObject2String(map.get(inputEnParam3));
			inputMap.put(inputEnParam3, value);
		}
    		return inputMap;
    }
    
    /**
     * 递归查询规则集合ID和原子规则id
     * @param ruleId
     * @param groupIds 规则集合id字符串
     * @param atomIds 原子规则id字符串
     */
    public void getAtomParamByGroupId(String groupId,StringBuilder atomInputParams) {
    		//根据关联id查询关联关系
    		List<CwmRuleGroupRel> cwmRuleGroupRels = cwmRuleGroupRelMapper.selectEnableRelByParentNodeId(groupId);
    		if(cwmRuleGroupRels != null && cwmRuleGroupRels.size() > 0) {
    			for(CwmRuleGroupRel cwmRuleGroupRel : cwmRuleGroupRels) {
    				String nodeType = StaticMethod.nullObject2String(cwmRuleGroupRel.getNodeType());//节点类型
    				String ruleRelId = StaticMethod.nullObject2String(cwmRuleGroupRel.getRuleIdRel());//关联的节点id
    				if("atomRule".equals(nodeType)) {
    					//查询原子规则 拼接所有变量
    					CwmRuleAtoms cwmRuleAtom = cwmRuleAtomsMapper.selectByPrimaryKey(ruleRelId);
    					String inputEnParam1 = StaticMethod.nullObject2String(cwmRuleAtom.getInputEnParam1());//输入参数1英文名
    					String inputParamType1 = StaticMethod.nullObject2String(cwmRuleAtom.getInputParamType1());//输入参数1类型
    					if(inputParamType1.contains("变量")) {
    						atomInputParams = ("".equals(atomInputParams.toString()))?atomInputParams.append(inputEnParam1):atomInputParams.append(","+inputEnParam1);
    					}
    					String inputEnParam2 = StaticMethod.nullObject2String(cwmRuleAtom.getInputEnParam2());//输入参数2英文名
    					String inputParamType2 = StaticMethod.nullObject2String(cwmRuleAtom.getInputParamType2());//输入参数2类型
    					if(inputParamType2.contains("变量")) {
    						atomInputParams = ("".equals(atomInputParams.toString()))?atomInputParams.append(inputEnParam2):atomInputParams.append(","+inputEnParam2);
    					}
    					String inputEnParam3 = StaticMethod.nullObject2String(cwmRuleAtom.getInputEnParam3());//输入参数3英文名
    					String inputParamType3 = StaticMethod.nullObject2String(cwmRuleAtom.getInputParamType3());//输入参数3类型
    					if(inputParamType3.contains("变量")) {
    						atomInputParams = ("".equals(atomInputParams.toString()))?atomInputParams.append(inputEnParam3):atomInputParams.append(","+inputEnParam3);
    					}
    				}else {
    					getAtomParamByGroupId(ruleRelId, atomInputParams);
    				}
    			}
    		}
    }
    
    /**
     * 规则执行日志对象转换
     * @param logMap
     * @return
     */
    public CwmRuleExecuteLog getExcuteModel(Map logMap) {
    		CwmRuleExecuteLog cwmRuleExecuteLog = new CwmRuleExecuteLog();
    		cwmRuleExecuteLog.setCallerName(StaticMethod.nullObject2String(logMap.get("callerName")));
    		cwmRuleExecuteLog.setCallerFlag(StaticMethod.nullObject2String(logMap.get("callerFlag")));
    		cwmRuleExecuteLog.setRuleIdRel(StaticMethod.nullObject2String(logMap.get("ruleIdRel")));
    		cwmRuleExecuteLog.setRuleFlag(StaticMethod.nullObject2String(logMap.get("ruleFlag")));
    		cwmRuleExecuteLog.setExecuteTime(StaticMethod.nullObject2String(logMap.get("executeTime")));
    		cwmRuleExecuteLog.setOutputParamResult(StaticMethod.nullObject2String(logMap.get("outputParamResult")));
    		cwmRuleExecuteLog.setOutputParamDesc(StaticMethod.nullObject2String(logMap.get("outputParamDesc")));
    		cwmRuleExecuteLog.setInputParam(StaticMethod.nullObject2String(logMap.get("inputParam")));
    		cwmRuleExecuteLog.setRuleName(StaticMethod.nullObject2String(logMap.get("ruleName")));
    		cwmRuleExecuteLog.setRuleId(StaticMethod.nullObject2String(logMap.get("ruleId")));
    		cwmRuleExecuteLog.setRuleType(StaticMethod.nullObject2String(logMap.get("ruleType")));
    		cwmRuleExecuteLog.setCreateTime(new Date());
    		return cwmRuleExecuteLog;
    }
    
    
    public static void main(String[] args) {
    	/** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
    		String unicode = "\\u56de\\u590d";
        String[] strs = unicode.split("\\\\u");
        String returnStr = "";
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for (int i = 1; i < strs.length; i++) {
            returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
        }
        System.out.println(returnStr);
	}

	/**
	 * 多线程执行规则 （仿真功能）
	 * @param ruleId
	 * @param simulationId
	 * @param isLastGateWay
	 * @param excuteList
	 * @return
	 */
	@Override
	@Async("asyncServiceExecutor")
	public JSONObject asyncExcuteRule(String ruleId, String simulationId, String isLastGateWay,
			List<Object> excuteList) {
		JSONObject saveObj = new JSONObject();//回调保存接口obj
		JSONArray saveArray = new JSONArray();//回调保存数组
		try {
				CwmRuleGroups cwmRuleGroups = cwmRuleGroupsMapper.selectByPrimaryKey(ruleId);
				for(Object obj : excuteList) {
					JSONObject json = new JSONObject();//规则输出数据
					JSONObject excuteJson = new JSONObject();//规则仿真保存数据
					Map map = (Map)obj;
					
					//输入参数校验及处理
					JSONObject checkJson = ruleService.checkAndDealParams(map, ruleId);
					logger.info("格式化后的输入参数"+map.toString());
					
					//针对福建AI功能临时添加
					ruleService.AIDeal(map, ruleId);
					
					String firstSubsetIsEnd = StaticMethod.nullObject2String(cwmRuleGroups.getFirstSubsetIsEnd());
					
					//查询规则不符合情况的详细信息
					CwmRuleGroupOutput cwmRuleGroupOutput = cwmRuleGroupOutputMapper.selectByGroupIdAndResult(ruleId, "false");
					String desc = StaticMethod.nullObject2String(cwmRuleGroupOutput.getGroupOutputExplain());
					
					//规则执行日志集合 先封装 规则执行完之后再插入
					List<CwmRuleExecuteLog> logList = new ArrayList<CwmRuleExecuteLog>();
					Map logMap = new HashMap();
					logMap.put("callerName", "测试");
					logMap.put("callerFlag", "测试");
					logMap.put("ruleIdRel", ruleId);
					
					if("true".equals(firstSubsetIsEnd)) {
						//若遇到符合的情况就停止 需循环遍历当前规则下的规则集合
						//执行开始时间
						long startTimeT= new Date().getTime();
						List<String> groupIds = new ArrayList<String>();
						getGroupIdList(groupIds, ruleId);
						if(groupIds != null && groupIds.size() > 0) {
							for(String groupId : groupIds) {
								//执行规则
								json = useRule(map, groupId,logList,logMap);
								String result = StaticMethod.nullObject2String(json.get("result"));
								if("true".equals(result)) {
									CwmRuleGroups rule = cwmRuleGroupsMapper.selectByPrimaryKey(ruleId);
									//执行结束时间
									long endTimeT= new Date().getTime();
									//记录规则执行日志
									//封装日志记录map
									logMap.put("ruleType", StaticMethod.nullObject2String(rule.getGroupType()));
									logMap.put("ruleId", ruleId);
									logMap.put("ruleName", StaticMethod.nullObject2String(rule.getGroupName()));
									logMap.put("inputParam", map.toString());
									logMap.put("outputParamResult", StaticMethod.nullObject2String(json.get("result")));
									logMap.put("outputParamDesc", StaticMethod.nullObject2String(json.get("desc")));
									logMap.put("executeTime", endTimeT-startTimeT);
									logMap.put("ruleFlag", "");
									//放入日志记录list 等待插入
									logList.add(getExcuteModel(logMap));
									if(excuteLogFlag) {
										cwmRuleExecuteLogMapper.batchInsert(logList);
										logger.info("规则执行日志插入成功");
									}
									break;
								}
							}
						}
						json.put("result", "false");
						json.put("desc", desc);
						json.put("excuteFlag", "1");
						CwmRuleGroups rule = cwmRuleGroupsMapper.selectByPrimaryKey(ruleId);
						//执行结束时间
						long endTimeT= new Date().getTime();
						//记录规则执行日志
						//封装日志记录map
						logMap.put("ruleType", StaticMethod.nullObject2String(rule.getGroupType()));
						logMap.put("ruleId", ruleId);
						logMap.put("ruleName", StaticMethod.nullObject2String(rule.getGroupName()));
						logMap.put("inputParam", map.toString());
						logMap.put("outputParamResult", StaticMethod.nullObject2String(json.get("result")));
						logMap.put("outputParamDesc", StaticMethod.nullObject2String(json.get("desc")));
						logMap.put("executeTime", endTimeT-startTimeT);
						logMap.put("ruleFlag", "");
						//放入日志记录list 等待插入
						logList.add(getExcuteModel(logMap));
						if(excuteLogFlag) {
							cwmRuleExecuteLogMapper.batchInsert(logList);
							logger.info("规则执行日志插入成功");
						}
						outputDesc("false", ruleId, json, map);
						json.put("executeDuration", endTimeT-startTimeT);
					}else {
						//执行开始时间
						long startTimeT= new Date().getTime();
						json = useRule(map, ruleId,logList,logMap);
						//执行结束时间
						long endTimeT= new Date().getTime();
						json.put("executeDuration", endTimeT-startTimeT);
						if(excuteLogFlag) {
							cwmRuleExecuteLogMapper.batchInsert(logList);
							logger.info("规则执行日志插入成功");
						}
					}
					excuteJson.put("simulationExecuteId", StaticMethod.nullObject2String(map.get("simulationExecuteId")));
					excuteJson.put("simulationExecuteDuration", StaticMethod.nullObject2String(json.get("executeDuration")));
					excuteJson.put("simulationOutputParam", json.toString());
					excuteJson.put("simulationError", StaticMethod.nullObject2String(json.get("error")));
					excuteJson.put("aiSimilarScore", StaticMethod.nullObject2String(json.get("linkDealStep_AI_similar")));
					excuteJson.put("executeResult", StaticMethod.nullObject2String(json.get("result")));
					saveArray.add(excuteJson);
				}
				saveObj.put("simulationId", simulationId);
				saveObj.put("isLastGateWay", isLastGateWay);
				saveObj.put("gatewayId", ruleId);
				saveObj.put("simulationRunArr", saveArray);
				//回调规则执行结果保存接口
				String project_url = "http://"+ruleProjectServer.getIp()+":"+ruleProjectServer.getPort()+"/api/v1/simulation/updateSimulationRunData";
				String entityStr = HttpClientServlet.httpPostRaw(project_url,saveObj.toString(),null,null);
			} catch (Exception e) {
				logger.error("批量执行规则报错:"+e);
			}
		
		
		return null;
	}

}
