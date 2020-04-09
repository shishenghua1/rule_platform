package com.boco.eoms.rule.cwmsysruleconfig.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.rule.AIClient.service.IAIClientService;
import com.boco.eoms.rule.cwmsysdatadetail.mapper.CwmDataDetailMapper;
import com.boco.eoms.rule.cwmsysdatadetail.model.CwmDataDetail;
import com.boco.eoms.rule.cwmsysruleatoms.mapper.CwmRuleAtomsMapper;
import com.boco.eoms.rule.cwmsysruleatoms.model.CwmRuleAtoms;
import com.boco.eoms.rule.cwmsysruleconfig.service.RuleService;
import com.boco.eoms.rule.cwmsysrulegroupoutputparams.mapper.CwmRuleGroupOutputParamsMapper;
import com.boco.eoms.rule.cwmsysrulegroupoutputparams.model.CwmRuleGroupOutputParams;
import com.boco.eoms.rule.cwmsysrulegroups.mapper.CwmRuleGroupsMapper;
import com.boco.eoms.rule.cwmsysrulegroups.model.CwmRuleGroups;
import com.boco.eoms.rule.cwmsysrulerel.model.ParamInfo;
import com.boco.eoms.rule.cwmsysrulerel.service.ICwmRuleRelService;

@Service
public class RuleServiceImpl implements RuleService{
	
	@Autowired
	private CwmRuleAtomsMapper cwmRuleAtomsMapper;
	
	@Autowired
	private CwmRuleGroupOutputParamsMapper cwmRuleGroupOutputParamsMapper;
	
	@Autowired
	private CwmRuleGroupsMapper cwmRuleGroupsMapper;
	
	@Autowired
	private CwmDataDetailMapper cwmDataDetailMapper;
	
	@Autowired
	private IAIClientService AIClientService;
	
	//注入福建固定字段
	@Value("${filedsJson}")
	private String filedsJson;
	
	@Autowired
	private ICwmRuleRelService cwmRuleRelService;

	/**
	 * 根据规则id获取规则所需传入参数
	 * @param ruleId
	 * @return
	 */
	@Override
	public JSONObject getRuleParams(String ruleId) {
		
		String moduleId = "";
		CwmRuleGroups cwmRuleGroups = cwmRuleGroupsMapper.selectByPrimaryKey(ruleId);
		if(cwmRuleGroups != null) {
			moduleId = StaticMethod.nullObject2String(cwmRuleGroups.getModuleId());//所属模块id
		}
		JSONObject json = new JSONObject();
		StringBuilder paramStr = new StringBuilder();
		//查询规则集合关联的原子规则
		List<CwmRuleAtoms> cwmRuleAtomes = cwmRuleAtomsMapper.selectByGroupId(ruleId);
		if(cwmRuleAtomes != null && cwmRuleAtomes.size() > 0) {
			for(CwmRuleAtoms cwmRuleAtoms : cwmRuleAtomes) {
				String param1 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputEnParam1());
				String type1 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputParamType1());
				paramStrAppend(param1, type1, paramStr,moduleId);
				String param2 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputEnParam2());
				String type2 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputParamType2());
				paramStrAppend(param2, type2, paramStr,moduleId);
				String param3 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputEnParam3());
				String type3 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputParamType3());
				paramStrAppend(param3, type3, paramStr,moduleId);
			}
		}
		//查询规则输出关联参数
		List<CwmRuleGroupOutputParams> cwmRuleGroupOutputParames = cwmRuleGroupOutputParamsMapper.getParamsByGroupId(ruleId);
		if(cwmRuleGroupOutputParames != null && cwmRuleGroupOutputParames.size() > 0) {
			for(CwmRuleGroupOutputParams cwmRuleGroupOutputParams : cwmRuleGroupOutputParames) {
				String param1 = StaticMethod.nullObject2String(cwmRuleGroupOutputParams.getOutputEnParam1());
				String type1 = StaticMethod.nullObject2String(cwmRuleGroupOutputParams.getOutputParamType1());
				paramStrAppend(param1, type1, paramStr,moduleId);
				String param2 = StaticMethod.nullObject2String(cwmRuleGroupOutputParams.getOutputEnParam2());
				String type2 = StaticMethod.nullObject2String(cwmRuleGroupOutputParams.getOutputParamType2());
				paramStrAppend(param2, type2, paramStr,moduleId);
				String param3 = StaticMethod.nullObject2String(cwmRuleGroupOutputParams.getOutputEnParam3());
				String type3 = StaticMethod.nullObject2String(cwmRuleGroupOutputParams.getOutputParamType3());
				paramStrAppend(param3, type3, paramStr,moduleId);
			}
		}
		if(paramStr != null && paramStr.length() > 0) {
			paramStr.delete(paramStr.length()-1, paramStr.length());
		}
		json.put("paramDesc", paramStr.toString());
		return json;
	}
	
	/**
	 * 参数字符串拼接
	 * @param param
	 * @param paramType
	 * @param paramStr
	 * @param moduleId
	 */
	public void paramStrAppend(String param,String paramType,StringBuilder paramStr,String moduleId) {
		//去重
		if(paramStr.indexOf(param+",") <= -1) {
			if(!"".equals(param) && paramType.contains("变量")) {
				if("计算变量".equals(paramType)) {
					List<CwmDataDetail> cwmDataDetails = cwmDataDetailMapper.getDataDetailByModuleIdAndEnName(moduleId, param);
					if(cwmDataDetails != null && cwmDataDetails.size() > 0) {
						CwmDataDetail cwmDataDetail = cwmDataDetails.get(0);
						String calculate = StaticMethod.nullObject2String(cwmDataDetail.getFieldCalculate());
						calculate = calculate.replaceAll("+", ",");
						calculate = calculate.replaceAll("-", ",");
						calculate = calculate.replaceAll("*", ",");
						calculate = calculate.replaceAll("/", ",");
						String varArr[] = calculate.split(",");
						for(String p : varArr) {
							paramStr.append(p+",");
						}
 					}
				}else {
					paramStr.append(param+",");
				}
			}
		}
	}

	/**
	 * 根据规则名称获取规则输入参数及类型 (返回值为JSON key为字段 value为类型)
	 * @param ruleName
	 * @return
	 */
	@Override
	public JSONObject getRuleParamsAndType(String ruleId) {
		JSONObject json = new JSONObject();
		//查询规则集合关联的原子规则
		List<CwmRuleAtoms> cwmRuleAtomes = cwmRuleAtomsMapper.selectByGroupId(ruleId);
		if(cwmRuleAtomes != null && cwmRuleAtomes.size() > 0) {
			for(CwmRuleAtoms cwmRuleAtom : cwmRuleAtomes) {
				String param1 = StaticMethod.nullObject2String(cwmRuleAtom.getInputEnParam1());
				String type1 = StaticMethod.nullObject2String(cwmRuleAtom.getInputParamType1());
				if(!"".equals(param1)) {
					json.put(param1, type1);
				}
				String param2 = StaticMethod.nullObject2String(cwmRuleAtom.getInputEnParam2());
				String type2 = StaticMethod.nullObject2String(cwmRuleAtom.getInputParamType2());
				if(!"".equals(param2)) {
					json.put(param2, type2);
				}
				String param3 = StaticMethod.nullObject2String(cwmRuleAtom.getInputEnParam3());
				String type3 = StaticMethod.nullObject2String(cwmRuleAtom.getInputParamType3());
				if(!"".equals(param3)) {
					json.put(param3, type3);
				}
			}
		}
		return json;
	}

	
	/**
	 * 根据规则名称、输入参数处理和检测规则输入参数
	 * @param map
	 * @param ruleName
	 * @return
	 */
	@Override
	public JSONObject checkAndDealParams(Map<String,Object> map,String ruleId) {
		JSONObject json = new JSONObject();
		CwmRuleGroups cwmRuleGroups = cwmRuleGroupsMapper.selectByPrimaryKey(ruleId);
		String groupName = "";
		String moduleId = "";
		if(cwmRuleGroups != null) {
			groupName = StaticMethod.nullObject2String(cwmRuleGroups.getGroupName());
			moduleId = StaticMethod.nullObject2String(cwmRuleGroups.getModuleId());//所属模块id
		}
		String msg = "";
		//输入参数及类型
		JSONObject paramAndType = getRuleParamsAndType(ruleId);
		if(map != null && !map.isEmpty() && paramAndType != null && !paramAndType.isEmpty()) {
			//遍历map 在paramAndType查找map中参数的类型
			for(String key : map.keySet()){
				String value = StaticMethod.nullObject2String(map.get(key));
				String paramType = StaticMethod.nullObject2String(paramAndType.get(key));
				
				//判断是否为空
				if("计算变量".equals(paramType)) {
					List<ParamInfo> list = cwmRuleRelService.getParamListInfo(moduleId, key);
					for(ParamInfo paramInfo : list) {
						String caValue = StaticMethod.nullObject2String(map.get(paramInfo.getInputEnParam()));
						String caType = StaticMethod.nullObject2String(paramInfo.getInputParamType());
						if(caType.contains("变量") && "".equals(caValue)) {
							msg = msg + paramInfo.getInputEnParam() + "值为空；";
							continue;
						}
					}
				}
				
				if(!"随机数变量".equals(paramType) && "".equals(value) && !key.contains("_AI_check") && !key.contains("_AI_similar")) {
					msg = msg + key + "值为空；";
					continue;
				}
				//随机数变量特殊处理
				if("随机数变量".equals(paramType)) {
					map.put(key, StaticMethod.getPointTowRandomNum());
				}
				//时间变量特殊处理
				if("时间变量".equals(paramType)) {
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
		}
		if(!"".equals(msg)) {
			msg = "【"+groupName+"】"+"规则执行失败；失败原因:" + msg;
		}
		json.put("msg", msg);
		return json;
	}

	/**
	 * AI处理
	 * @param map
	 * @param ruleName
	 * @return
	 */
	@Override
	public JSONObject AIDeal(Map<String,Object> map,String ruleId) {
		//获取当前规则所有变量，包含AI特殊标识字段
		JSONObject paramObj = getRuleParams(ruleId);
		String paramStr = StaticMethod.nullObject2String(paramObj.get("paramDesc"));
		String paramArr[] = paramStr.split(",");
		if(paramArr != null && paramArr.length > 0) {
			for(String param : paramArr) {
				if(param.contains("_AI_check")) {
					//获取AI错别字检测字段
					String key = param.split("_AI_check")[0];
					String value = StaticMethod.nullObject2String(map.get(key));
					//调用AI错别字检测接口
					JSONObject AIcheckResult = AIClientService.check(value, "3");
					//将AI错别字检测结果放入规则执行参数中
					JSONObject checkObject = AIcheckResult.getJSONObject("wrongWordMap");
					//判断检测结果中错别字集合是否为空
					boolean isEmpty = checkObject.isEmpty();
					String paramValue = "";
					if(isEmpty) {
						paramValue = "否";
					}else {
						paramValue = "是";
						//将错别字执行详细结果放入map中 以便执行完获取
						JSONObject cnCheckDesc = new JSONObject();
						for (Map.Entry<String, Object> entry : checkObject.entrySet()) {
							//错别字返回结果是unicode 需要转换
							String unicodeKey = entry.getKey();//unicodekey
							String unicodeValue = entry.getValue().toString();//unicodeValue
							String cnkey = StaticMethod.unicodeToCn(unicodeKey);//中文key
							String cnValue = StaticMethod.unicodeToCn(unicodeValue);//中文value
							cnCheckDesc.put(cnkey, cnValue);
						}
						//放入错别字执行结果详情
						map.put("AICheckDescResult", cnCheckDesc.toJSONString());
					}
					//放入错别字执行结果
					map.put(param, paramValue);
				}
				if(param.contains("_AI_similar")) {
					//获取AI相似度带检查字段
					String key = param.split("_AI_similar")[0];
					String value = StaticMethod.nullObject2String(map.get(key));
					//获取
					String similar_template = "";
					for(String param1 : paramArr) {
						if(param1.contains("_AI_template_similar")) {
							similar_template = StaticMethod.nullObject2String(map.get(param1));
						}
					}
					//调用AI相似度匹配检测接口
					JSONObject AIsimilarResult = AIClientService.similar(value, similar_template);
					String similar_score = StaticMethod.nullObject2String(AIsimilarResult.get("SIMILAR_SCORE"));
					//放入相似度匹配结果值
					map.put(param, similar_score);
				}
			}
		}
		return null;
	}

	/**
	 * 获取规则输入参数中英文字段
	 * @param ruleName
	 * @return json key为字段英文名 value为字段中文名
	 */
	@Override
	public JSONObject getInputParamEnAndCnName(String ruleName) {
		JSONObject json = new JSONObject(true);
		json = JSONObject.parseObject(filedsJson);
		String ruleIds[] = ruleName.split(",");
		if(ruleIds != null && ruleIds.length > 0) {
			for(String ruleId : ruleIds) {
				String moduleId = "";
				CwmRuleGroups cwmRuleGroups = cwmRuleGroupsMapper.selectByPrimaryKey(ruleId);
				if(cwmRuleGroups != null) {
					moduleId = StaticMethod.nullObject2String(cwmRuleGroups.getModuleId());//所属模块id
				}
				//查询规则集合关联的原子规则
				List<CwmRuleAtoms> cwmRuleAtomes = cwmRuleAtomsMapper.selectByGroupId(ruleId);
				if(cwmRuleAtomes != null && cwmRuleAtomes.size() > 0) {
					for(CwmRuleAtoms cwmRuleAtom : cwmRuleAtomes) {
						String enParam1 = StaticMethod.nullObject2String(cwmRuleAtom.getInputEnParam1());
						String cnParam1 = StaticMethod.nullObject2String(cwmRuleAtom.getInputCnParam1());
						String type1 = StaticMethod.nullObject2String(cwmRuleAtom.getInputParamType1());
						getParamEnAndCn(enParam1, cnParam1, type1, moduleId, json);
						String enParam2 = StaticMethod.nullObject2String(cwmRuleAtom.getInputEnParam2());
						String cnParam2 = StaticMethod.nullObject2String(cwmRuleAtom.getInputCnParam2());
						String type2 = StaticMethod.nullObject2String(cwmRuleAtom.getInputParamType2());
						getParamEnAndCn(enParam2, cnParam2, type2, moduleId, json);
						String enParam3 = StaticMethod.nullObject2String(cwmRuleAtom.getInputEnParam3());
						String cnParam3 = StaticMethod.nullObject2String(cwmRuleAtom.getInputCnParam3());
						String type3 = StaticMethod.nullObject2String(cwmRuleAtom.getInputParamType3());
						getParamEnAndCn(enParam3, cnParam3, type3, moduleId, json);
					}
				}	
			}
		}
		return json;
	}
	
	/**
	 * 参数变量中英文提取
	 * @param enParam
	 * @param cnParam
	 * @param type
	 * @param moduleId
	 * @param json
	 */
	public void getParamEnAndCn(String enParam,String cnParam,String type,String moduleId,JSONObject json) {
		if(!"".equals(enParam) && type.contains("变量")) {
			if("计算变量".equals(type)) {
				List<ParamInfo> list = cwmRuleRelService.getParamListInfo(moduleId, enParam);
				if(list != null && list.size() > 0) {
					for(ParamInfo paramInfo : list) {
						String paramType = StaticMethod.nullObject2String(paramInfo.getInputParamType());
						if(paramType.contains("变量")) {
							json.put(paramInfo.getInputEnParam(), paramInfo.getInputCnParam());
						}
					}
				}
			}else {
				json.put(enParam, cnParam);
			}
		}
	}
	
	public static void main(String[] args) {
		String jsonStr = "{\n" + 
				"  \"SIMILAR_SCORE\": NaN, \n" + 
				"  \"VERSION\": \"v1\"\n" + 
				"}";
		if(jsonStr.contains("NaN")) {
			jsonStr = jsonStr.replace("NaN", "0");
		}
		System.out.println(jsonStr);
		
	}
	
}
