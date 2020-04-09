package com.boco.eoms.rule.cwmsysruleconfig.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.rule.cwmsysdatadetail.mapper.CwmDataDetailMapper;
import com.boco.eoms.rule.cwmsysdatadetail.model.CwmDataDetail;
import com.boco.eoms.rule.cwmsysruleatoms.mapper.CwmRuleAtomsMapper;
import com.boco.eoms.rule.cwmsysruleatoms.model.CwmRuleAtoms;
import com.boco.eoms.rule.cwmsysruleatoms.service.ICwmRuleAtomsService;
import com.boco.eoms.rule.cwmsysruleconfig.model.RuleInfo;
import com.boco.eoms.rule.cwmsysruleconfig.service.RuleInfoService;
import com.boco.eoms.rule.cwmsysrulegroupoutput.mapper.CwmRuleGroupOutputMapper;
import com.boco.eoms.rule.cwmsysrulegroupoutput.model.CwmRuleGroupOutput;
import com.boco.eoms.rule.cwmsysrulegrouprel.mapper.CwmRuleGroupRelMapper;
import com.boco.eoms.rule.cwmsysrulegrouprel.model.CwmRuleGroupRel;
import com.boco.eoms.rule.cwmsysrulegroups.mapper.CwmRuleGroupsMapper;
import com.boco.eoms.rule.cwmsysrulegroups.model.CwmRuleGroups;
import com.boco.eoms.rule.cwmsysrulerel.controller.CwmRuleRelRestController;

/**
 * 规则信息servie
 * @author chenjianghe
 *
 */
@Service
public class RuleInfoServiceImpl implements RuleInfoService{
	
	private Logger logger = LoggerFactory.getLogger(CwmRuleRelRestController.class);
	
	public static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	
	@Autowired
	private CwmRuleGroupsMapper cwmRuleGroupsMapper;
	
	@Autowired
	private CwmRuleAtomsMapper cwmRuleAtomsMapper;
	
	@Autowired
	private CwmRuleGroupOutputMapper cwmRuleGroupOutputMapper;
	
	@Autowired
	private CwmRuleGroupRelMapper cwmRuleGroupRelMapper;
	
	@Autowired
	private CwmDataDetailMapper cwmDataDetailMapper;
	
	/**
	 * 获取所有规则信息
	 */
	@Override
	public List<RuleInfo> getAllRule() {
		List<RuleInfo> ruleInfoList = new ArrayList<RuleInfo>();
		//获取规则集合
		List<CwmRuleGroups> groups = cwmRuleGroupsMapper.getAllRuleGroups();
		if(groups != null && groups.size() > 0) {
			//遍历规则集合 查询关联的规则集合 和原子规则集合 
			for(CwmRuleGroups cwmRuleGroups : groups) {
				RuleInfo ruleInfo = ruleInfoDetail(cwmRuleGroups);
				ruleInfoList.add(ruleInfo);
			}
		}
		return ruleInfoList;
	}
	
	/**
     * 获取所有非草稿的规则集合
     * @return
     */
	@Override
    public List<RuleInfo> getAllRuleGroupsNonDraft(){
		List<RuleInfo> ruleInfoList = new ArrayList<RuleInfo>();
		//获取规则集合
		List<CwmRuleGroups> groups = cwmRuleGroupsMapper.getAllRuleGroupsNonDraft();
		if(groups != null && groups.size() > 0) {
			//遍历规则集合 查询关联的规则集合 和原子规则集合 
			for(CwmRuleGroups cwmRuleGroups : groups) {
				RuleInfo ruleInfo = ruleInfoDetail(cwmRuleGroups);
				ruleInfoList.add(ruleInfo);
			}
		}
		return ruleInfoList;
	}
	
	/**
	 * 获取单条规则
	 */
	@Override
	public RuleInfo getRuleInfoByRuleId(String ruleId) {
		CwmRuleGroups cwmRuleGroups = cwmRuleGroupsMapper.selectEnableRuleByRuleId(ruleId);
		RuleInfo ruleInfo = ruleInfoDetail(cwmRuleGroups);
		return ruleInfo;
	}
	
	/**
	 * 获取规则集合详细信息
	 * @param cwmRuleGroups
	 */
	private RuleInfo ruleInfoDetail(CwmRuleGroups cwmRuleGroups) {
		if(cwmRuleGroups != null) {
			//规则信息
			String groupId = StaticMethod.nullObject2String(cwmRuleGroups.getId());//规则id
			String moduleId = StaticMethod.nullObject2String(cwmRuleGroups.getModuleId());//所属模块id
			Date effectiveDate = cwmRuleGroups.getEffectiveDate();//生效日期
			Date expiringDate = cwmRuleGroups.getExpiringDate();//失效日期
			String isRepeat = StaticMethod.nullObject2String(cwmRuleGroups.getIsRepeat());//是否循环
			
			String ruleAttr = "";
			if(effectiveDate != null) {
				//拼接生效日期
				ruleAttr = ruleAttr + "date-effective \""+sdf.format(effectiveDate)+"\"\n";
			}
			if(expiringDate != null) {
				//拼接失效日期
				ruleAttr = ruleAttr + "date-expires \""+sdf.format(expiringDate)+"\"\n";
			}
			//拼接是否循环
			ruleAttr = ruleAttr + "no-loop "+(isRepeat.equals("true")?false:true)+"";
			
			//drl拼接字符串
			//规则名称用规则id来区分 不用规则名称区分避免多个模块下有叫同一个名称的规则
			String drlStr = "package rule_"+groupId+";"+
				 	"global java.util.Map map;\n" +
	        		"rule \""+groupId+"\"\n" + 
	        		ruleAttr + 
	        		"  when\n" + 
	        		"     LHS\n" + 
	        		"  then\n" + 
	        		"     RHS\n" + 
	        		"end";
			//根据规则id处理规则条件部分LHS
			StringBuilder LHSBuilder = new StringBuilder();
			LHSBuilder.append("$map:Map(");
			getDrlLHS(LHSBuilder, groupId,"",moduleId);
			LHSBuilder.append(")");
			//根据规则id处理规则输出部分RHS
			StringBuilder RHSBuilder = new StringBuilder();
			getDrlRHS(RHSBuilder,groupId);
			//替换规则字符串中LHS和RHS
			drlStr = drlStr.replace("LHS", LHSBuilder.toString());
			drlStr = drlStr.replace("RHS", RHSBuilder.toString());
			logger.info("规则信息：\n"+drlStr);
			RuleInfo ruleInfo = generateRuleInfo(groupId, drlStr);
			return ruleInfo;
		}else {
			logger.info("规则未开启或者规则不存在，请检查！");
		}
		return null;
	}
	
	 /**
     * 根据groupId查询规则关联关系并拼接规则LHS
     * @param groupId
     */
    private void getDrlLHS(StringBuilder LHSBuilder,String parentNodeId,String connectWord,String moduleId) {
    		String replaceWord = "rep_123";
    		//根据父节点id查询关联信息
    		List<CwmRuleGroupRel> cwmRuleGroupRels = cwmRuleGroupRelMapper.selectEnableRelByParentNodeId(parentNodeId);
    		//遍历拼接/替换字符串
    		if(cwmRuleGroupRels != null && cwmRuleGroupRels.size() > 0) {
    			List<String> strList = new ArrayList<String>();
    			for(CwmRuleGroupRel cwmRuleGroupRel : cwmRuleGroupRels) {
    				String nodeType = StaticMethod.nullObject2String(cwmRuleGroupRel.getNodeType());//节点类型
    				String ruleRelId = StaticMethod.nullObject2String(cwmRuleGroupRel.getRuleIdRel());//关联的节点id
    				if(("atomRule").equals(nodeType)) {
    					//若当前节点为原子规则 则查询原子规则详情 并拼接规则
    					CwmRuleAtoms cwmRuleAtoms = cwmRuleAtomsMapper.selectByPrimaryKey(ruleRelId);
    					if(cwmRuleAtoms != null) {
    						String inputEnParam1 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputEnParam1());//输入参数1英文名
        					String inputParamType1 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputParamType1());//输入参数1类型
        					String operator1 = StaticMethod.nullObject2String(cwmRuleAtoms.getOperator1());//运算标识1
        					String inputEnParam2 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputEnParam2());//输入参数2英文名
        					String inputParamType2 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputParamType2());//输入参数2类型
        					String operator2 = StaticMethod.nullObject2String(cwmRuleAtoms.getOperator2());//运算标识1/
        					String inputEnParam3 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputEnParam3());//输入参数3英文名
        					String inputParamType3 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputParamType3());//输入参数3类型
        					//根据原子规则参数和运算符拼接规则
        					//参数处理
        					String conditionStr = paramExcute(inputEnParam1, inputParamType1, moduleId)+" "+operator1+" "+paramExcute(inputEnParam2, inputParamType2, moduleId)+" "+operator2+" "+paramExcute(inputEnParam3, inputParamType3, moduleId);
        					strList.add(conditionStr);
    					}
    				}else if("ruleGroup".equals(nodeType)) {
    					//若当前节点为规则集合 则用变量替换 变量标识为 父节点id_当前节点id
    					String varId = "a" + parentNodeId + "_" + ruleRelId;
    					strList.add(varId);
    				}else if("condition".equals(nodeType)) {
    					strList.add(replaceWord);
    				}
    			}
    			//将strList中的字符串以 connectWord 分隔为一个字符串
    			String str = listToString(strList, connectWord);
    			//替换 LHSBuilder 中第一个出现的替换字符 replaceWord
    			if(LHSBuilder.indexOf(replaceWord) <= -1) {
    				LHSBuilder.append(str);
    			}else {
    				LHSBuilder.replace(LHSBuilder.indexOf(replaceWord), LHSBuilder.indexOf(replaceWord)+replaceWord.length(), str);
    			}
    		}
    		//递归当前连接符号节点
    		if(cwmRuleGroupRels != null && cwmRuleGroupRels.size() > 0) {
    			for(CwmRuleGroupRel cwmRuleGroupRel : cwmRuleGroupRels) {
    				String nodeType = StaticMethod.nullObject2String(cwmRuleGroupRel.getNodeType());//节点类型
    				String ruleRelId = StaticMethod.nullObject2String(cwmRuleGroupRel.getRuleIdRel());//关联的节点id
    				String ruleInfoRel = StaticMethod.nullObject2String(cwmRuleGroupRel.getRuleInfoRel());//节点详细信息 and/or
    				if("condition".equals(nodeType)) {
    					getDrlLHS(LHSBuilder, ruleRelId, ruleInfoRel,moduleId);
    				}
    			}
    		}
    }
    
    /**
     * 根据groupId拼接规则RHS
     * @param RHSBuilder
     * @param groupId
     */
    public void getDrlRHS(StringBuilder RHSBuilder,String groupId) {
    		//根据规则集合id查询规则集合输出
		List<CwmRuleGroupOutput> CwmRuleGroupOutputs = cwmRuleGroupOutputMapper.selectByGroupId(groupId);
		if(CwmRuleGroupOutputs != null && CwmRuleGroupOutputs.size() > 0) {
			//遍历规则集合输出 写drools RHS
			for(CwmRuleGroupOutput cwmRuleGroupOutput : CwmRuleGroupOutputs) {
				String groupOutputResult = StaticMethod.nullObject2String(cwmRuleGroupOutput.getGroupOutputResult());//规则集合输出结果
				String groupOutputExplain = StaticMethod.nullObject2String(cwmRuleGroupOutput.getGroupOutputExplain());//规则集合输出结果说明
				if("true".equals(groupOutputResult)) {
					//拼接规则成立输出结果
					RHSBuilder.append("$map.put(\"result\",\""+groupOutputResult+"\");\n");
					RHSBuilder.append("$map.put(\"desc\",\""+groupOutputExplain+"\");\n");
				}
			}
		}
    }
    
    /**
     * 原子规则参数处理
     * @param cwmRuleAtoms
     * @return
     */
    public String paramExcute(String inputEnParam,String inputParamType,String moduleId) {
    		if("字符常量".equals(inputParamType)) {
    			inputEnParam = "\""+inputEnParam+"\"";
    		}else if("计算变量".equals(inputParamType)) {
    			List<CwmDataDetail> cwmDataDetails = cwmDataDetailMapper.getDataDetailByModuleIdAndEnName(moduleId, inputEnParam);
				if(cwmDataDetails != null && cwmDataDetails.size() > 0) {
					CwmDataDetail cwmDataDetail = cwmDataDetails.get(0);
					String calculate = StaticMethod.nullObject2String(cwmDataDetail.getFieldCalculate());
					inputEnParam = calculate;
				}
    		}
    		return inputEnParam;
    }
    
    /**
     * 将list中的字符串以指定符号连接为一个字符串
     * @param strList
     * @param connectWord
     * @return
     */
    public String listToString(List<String> strList,String connectWord) {
    		if("and".equals(connectWord)) {
    			connectWord = " && ";
    		}else {
    			connectWord = " || ";
    		}
    		if (strList == null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        result.append("(");
        boolean flag=false;
        for (String string : strList) {
            if (flag) {
                result.append(connectWord); // 分隔符
            }else {
                flag=true;
            }
            result.append(string);
        }
        result.append(")");
        return result.toString();
    }
	
	
	 /**
     * 生成规则信息
     *
     * @param sceneId 场景ID
     * @param id      规则ID
     * @return 规则信息
     */
    private RuleInfo generateRuleInfo(String ruleId,String content) {
        RuleInfo ruleInfo = new RuleInfo();
        ruleInfo.setRuleId(ruleId);
        ruleInfo.setContent(content);
        return ruleInfo;
    }
    
    
    public static void main(String[] args) {
		String a = "{\\x}";
		try {
			Object json = JSON.parse(a);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
