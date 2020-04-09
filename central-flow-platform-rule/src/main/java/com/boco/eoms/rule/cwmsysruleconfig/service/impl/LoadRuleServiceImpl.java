package com.boco.eoms.rule.cwmsysruleconfig.service.impl;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.drools.core.base.RuleNameEndsWithAgendaFilter;
import org.drools.core.impl.KnowledgeBaseImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.rule.cwmsysruleatoms.mapper.CwmRuleAtomsMapper;
import com.boco.eoms.rule.cwmsysruleatoms.model.CwmRuleAtoms;
import com.boco.eoms.rule.cwmsysruleconfig.service.LoadRuleService;
import com.boco.eoms.rule.cwmsysruleconfig.util.KieUtils;
import com.boco.eoms.rule.cwmsysrulegroupoutput.mapper.CwmRuleGroupOutputMapper;
import com.boco.eoms.rule.cwmsysrulegroupoutput.model.CwmRuleGroupOutput;
import com.boco.eoms.rule.cwmsysrulegroupoutputparams.mapper.CwmRuleGroupOutputParamsMapper;
import com.boco.eoms.rule.cwmsysrulegroupoutputrel.mapper.CwmRuleGroupOutputRelMapper;
import com.boco.eoms.rule.cwmsysrulegrouprel.mapper.CwmRuleGroupRelMapper;
import com.boco.eoms.rule.cwmsysrulegrouprel.model.CwmRuleGroupRel;
import com.boco.eoms.rule.cwmsysrulegroups.mapper.CwmRuleGroupsMapper;
import com.boco.eoms.rule.cwmsysrulegroups.model.CwmRuleGroups;
import com.boco.eoms.rule.cwmsysrulerel.controller.CwmRuleRelRestController;

@Service
public class LoadRuleServiceImpl implements LoadRuleService {
	
	private Logger logger = LoggerFactory.getLogger(CwmRuleRelRestController.class);
	
	public static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
	
	/**
     * key:kcontainerName,value:KieContainer，每个场景对应一个KieContainer
     */
    private final ConcurrentMap<String, KieContainer> kieContainerMap = new ConcurrentHashMap<>();
	
	@Autowired
    private static KieServices kieServices;

	@Autowired
	private CwmRuleGroupsMapper cwmRuleGroupsMapper;
	
	@Autowired
	private CwmRuleAtomsMapper cwmRuleAtomsMapper;
	
	@Autowired
	private CwmRuleGroupOutputMapper cwmRuleGroupOutputMapper;
	
	@Autowired
	private CwmRuleGroupOutputParamsMapper cwmRuleGroupOutputParamsMapper;
	
	@Autowired
	private CwmRuleGroupRelMapper cwmRuleGroupRelMapper;
	
	@Autowired
	private CwmRuleGroupOutputRelMapper cwmRuleGroupOutputRelMapper;
	
	
    /**
     * 加载规则
     * @param drlList drl字符串集合
     */
    @Override
    public void loadService(Map<String,String> map) {
        //初始化drools对象
        KieUtils.initAndNotClear();
        for(Map.Entry<String, String> entry:map.entrySet()) {
        		String id = StaticMethod.nullObject2String(entry.getKey());
        		String rule = StaticMethod.nullObject2String(entry.getValue());
        		reload(id, rule);
        }
//        //写入规则
//        for(String str : drlList){
//        		KieFileSystem kfs = KieUtils.getKieFileSystem();
//            kfs.write("src/main/resources/rules/" + str.hashCode() + ".drl", str);
//            //获取build对象
//            KieBuilder kieBuilder = KieUtils.getKieServices().newKieBuilder(kfs);
//            // 编译此时的builder中所有的规则
//            kieBuilder.buildAll();
//            if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
//                throw new RuntimeException("Build Errors:\n" + kieBuilder.getResults().toString());
//            }
//        }
    }
    
    private void reload(String id,String rule) {
        KieServices kieServices = KieServices.get();
        KieModuleModel kieModuleModel = kieServices.newKieModuleModel();
        KieBaseModel kieBaseModel = kieModuleModel.newKieBaseModel(id);
        kieBaseModel.setDefault(true);
        kieBaseModel.addPackage(MessageFormat.format("rules.scene_{0}", String.valueOf(id)));
        kieBaseModel.newKieSessionModel(id);

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        String fullPath = MessageFormat.format("src/main/resources/rules/scene_{0}/rule_{1}.drl", String.valueOf(id), String.valueOf(id));
        kieFileSystem.write(fullPath, rule);
        kieFileSystem.writeKModuleXML(kieModuleModel.toXML());

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new IllegalStateException("rule error");
        }

        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        kieContainerMap.put(id, kieContainer);
    }
    
    public KieSession getKieSessionBySceneId(String id) {
        return kieContainerMap.get(id).getKieBase().newKieSession();
    }
    
    /**
     * 加载单条规则
     */
	@Override
	public void loadService(String drl) {
		//初始化drools对象
        KieUtils.initAndNotClear();
        KieFileSystem kfs = KieUtils.getKieFileSystem();	
        kfs.write("src/main/resources/rules/" + drl.hashCode() + ".drl", drl);
      //获取build对象
        KieBuilder kieBuilder = KieUtils.getKieServices().newKieBuilder(kfs);
        // 编译此时的builder中所有的规则
        kieBuilder.buildAll();
        if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Build Errors:\n" + kieBuilder.getResults().toString());
        }
	}

    /**
     * 获取所有规则
     */
    @Override
    public Map<String,String> getAllDrl() {
    		Map<String,String> map = new HashMap<String,String>();
    		List<String> drls = new ArrayList();
    		//获取规则集合
    		List<CwmRuleGroups> groups = cwmRuleGroupsMapper.getAllRuleGroupsNonDraft();
    		if(groups != null && groups.size() > 0) {
    			//遍历规则集合 查询关联的规则集合 和原子规则集合 
    			for(CwmRuleGroups cwmRuleGroups : groups) {
    				//规则信息
    				String groupId = StaticMethod.nullObject2String(cwmRuleGroups.getId());//规则id
    				String ruleName = StaticMethod.nullObject2String(cwmRuleGroups.getGroupName());//规则名称
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
    				if("".equals(isRepeat)) {
    					//拼接是否循环
    					ruleAttr = ruleAttr + "no-loop "+(isRepeat.equals("true")?false:true)+"";
    				}
    				
    				//drl拼接字符串
    				//规则名称用规则id来区分 不用规则名称区分避免多个模块下有叫同一个名称的规则
    				String drlStr = "package "+groupId+";"+
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
    				getDrlLHS(LHSBuilder, groupId,"");
    				LHSBuilder.append(")");
    				//根据规则id处理规则输出部分RHS
    				StringBuilder RHSBuilder = new StringBuilder();
    				getDrlRHS(RHSBuilder,groupId);
    				//替换规则字符串中LHS和RHS
    				drlStr = drlStr.replace("LHS", LHSBuilder.toString());
    				drlStr = drlStr.replace("RHS", RHSBuilder.toString());
    				logger.info("规则信息：\n"+drlStr);
    				drls.add(drlStr);
    				map.put(groupId, drlStr);
    			}
    		}
    		return map;
    }

    @Override
    public String getDrl(String groupId) {
    		//根据规则集合id查询规则集合
    		CwmRuleGroups cwmRuleGroups	= cwmRuleGroupsMapper.selectEnableRuleByRuleId(groupId);
    		if(cwmRuleGroups != null) {
    			//规则信息
			String ruleName = StaticMethod.nullObject2String(cwmRuleGroups.getGroupName());//规则名称
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
			if("".equals(isRepeat)) {
				//拼接是否循环
				ruleAttr = ruleAttr + "no-loop "+(isRepeat.equals("true")?false:true)+"";
			}	
			//drl拼接字符串
			//规则名称用规则id来区分 不用规则名称区分避免多个模块下有叫同一个名称的规则
			String drlStr = "package rules;"+
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
			getDrlLHS(LHSBuilder, groupId,"");
			LHSBuilder.append(")");
			//根据规则id处理规则输出部分RHS
			StringBuilder RHSBuilder = new StringBuilder();
			getDrlRHS(RHSBuilder,groupId);
			//替换规则字符串中LHS和RHS
			drlStr = drlStr.replace("LHS", LHSBuilder.toString());
			drlStr = drlStr.replace("RHS", RHSBuilder.toString());
			logger.info("规则信息：\n"+drlStr);
			return drlStr;
    		}
        return null;
    }
    
    
    /**
     * 根据groupId查询规则关联关系并拼接规则LHS
     * @param groupId
     */
    public void getDrlLHS(StringBuilder LHSBuilder,String parentNodeId,String connectWord) {
    		String replaceWord = "rep_123";
    		//根据父节点id查询关联信息
    		List<CwmRuleGroupRel> cwmRuleGroupRels = cwmRuleGroupRelMapper.selectEnableRelByParentNodeId(parentNodeId);
    		//遍历拼接/替换字符串
    		if(cwmRuleGroupRels != null && cwmRuleGroupRels.size() > 0) {
    			List<String> strList = new ArrayList<String>();
    			for(CwmRuleGroupRel cwmRuleGroupRel : cwmRuleGroupRels) {
    				String nodeType = StaticMethod.nullObject2String(cwmRuleGroupRel.getNodeType());//节点类型
    				String ruleRelId = StaticMethod.nullObject2String(cwmRuleGroupRel.getRuleIdRel());//关联的节点id
    				String ruleInfoRel = StaticMethod.nullObject2String(cwmRuleGroupRel.getRuleInfoRel());//节点详细信息 and/or
    				if(("atomRule").equals(nodeType)) {
    					//若当前节点为原子规则 则查询原子规则详情 并拼接规则
    					CwmRuleAtoms cwmRuleAtoms = cwmRuleAtomsMapper.selectByPrimaryKey(ruleRelId);
    					String inputEnParam1 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputEnParam1());//输入参数1英文名
    					String inputParamType1 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputParamType1());//输入参数1类型
    					String operator1 = StaticMethod.nullObject2String(cwmRuleAtoms.getOperator1());//运算标识1
    					String inputEnParam2 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputEnParam2());//输入参数2英文名
    					String inputParamType2 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputParamType2());//输入参数2类型
    					String operator2 = StaticMethod.nullObject2String(cwmRuleAtoms.getOperator2());//运算标识1/
    					String inputEnParam3 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputEnParam3());//输入参数3英文名
    					String inputParamType3 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputParamType3());//输入参数3类型
    					//根据原子规则参数和运算符拼接规则
    					inputEnParam1 = (inputParamType1.equals("字符常量"))?"\""+inputEnParam1+"\"":inputEnParam1;
    					inputEnParam2 = (inputParamType2.equals("字符常量"))?"\""+inputEnParam2+"\"":inputEnParam2;
    					inputEnParam3 = (inputParamType3.equals("字符常量"))?"\""+inputEnParam3+"\"":inputEnParam3;
    					String conditionStr = inputEnParam1+" "+operator1+" "+inputEnParam2+" "+operator2+" "+inputEnParam3;
    					strList.add(conditionStr);
    				}else if("ruleGroup".equals(nodeType)) {
    					//若当前节点为规则集合 则用变量替换 变量标识为 父节点id_当前节点id
    					String varId = parentNodeId + "_" + ruleRelId;
    					strList.add(varId);
    				}else if("condition".equals(nodeType)) {
    					strList.add(replaceWord);
    				}
    			}
    			//将strList中的字符串以 connectWord 分隔为一个字符串
    			String str = listToString(strList, connectWord);
    			//替换 LHSBuilder 中第一个出现的替换字符 replaceWord
    			if(LHSBuilder.indexOf(replaceWord) <= -1) {
    				LHSBuilder.append(replaceWord);
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
    					getDrlLHS(LHSBuilder, ruleRelId, ruleInfoRel);
    				}
    			}
    		}
    		
//    		//根据规则集合id查询规则集合关联
//    		List<CwmRuleGroupRel> cwmRuleGroupRels = cwmRuleGroupRelMapper.selectByGroupId(groupId);
//    		if(cwmRuleGroupRels != null && cwmRuleGroupRels.size() > 0) {
//    			//遍历规则集合关联
//    			for(CwmRuleGroupRel cwmRuleGroupRel : cwmRuleGroupRels) {
//    				String ruleRelId = StaticMethod.nullObject2String(cwmRuleGroupRel.getRuleIdRel());
//    				String ruleRelType = StaticMethod.nullObject2String(cwmRuleGroupRel.getNodeType());//关联规则类型
//    				String executeCondtion = StaticMethod.nullObject2String(cwmRuleGroupRel.getRuleInfoRel());//执行条件
//    				//执行条件部分处理 如果没有执行条件 则不拼执行条件和执行条件后面的连接替换符
//    				if("and".equals(executeCondtion)) {
//    					executeCondtion = "&&";
//    				}else if("or".equals(executeCondtion)) {
//    					executeCondtion = "||";
//    				}
//    				if("ruleGroup".equals(ruleRelType)) {
//    					String variableName = groupId+"_"+ruleRelId;
//    					LHSBuilder.append(" " + variableName+" "+executeCondtion);
//    				}else {
//    					//如果关联规则是原子规则 则取原子规则详情拼接
//    					CwmRuleAtoms cwmRuleAtoms = cwmRuleAtomsMapper.selectByPrimaryKey(ruleRelId);
//    					String inputEnParam1 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputEnParam1());//输入参数1英文名
//					String inputParamType1 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputParamType1());//输入参数1类型
//					String operator1 = StaticMethod.nullObject2String(cwmRuleAtoms.getOperator1());//运算标识1
//					String inputEnParam2 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputEnParam2());//输入参数2英文名
//					String inputParamType2 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputParamType2());//输入参数2类型
//					String operator2 = StaticMethod.nullObject2String(cwmRuleAtoms.getOperator2());//运算标识1/
//					String inputEnParam3 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputEnParam3());//输入参数3英文名
//					String inputParamType3 = StaticMethod.nullObject2String(cwmRuleAtoms.getInputParamType3());//输入参数3类型
//					//根据原子规则参数和运算符拼接规则
//					String conditionStr = inputEnParam1+operator1+inputEnParam2+operator2+inputEnParam3;
//					LHSBuilder.append(" " + conditionStr + " " + executeCondtion);
//    				}
//    			}
//    		}
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
				//拼接规则输出结果
				RHSBuilder.append("$map.put(\"result\",\""+groupOutputResult+"\");\n");
				RHSBuilder.append("$map.put(\"desc\",\""+groupOutputExplain+"\");\n");
			}
		}
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
    
    
    public static void main(String[] args) {
		 String drlStr = 
				 	"package rule_2d7473538515481e994ee154e740e322;global java.util.Map map;\n" + 
				 	"rule \"2d7473538515481e994ee154e740e322\"\n" + 
				 	"date-effective \"19-九月-2019\"\n" + 
				 	"date-expires \"01-一月-1970\"\n" + 
				 	"no-loop true  when\n" + 
				 	"     $map:Map(((a_8c66d0d015434218b66eaf9d6c8628f3_20_120  && a_8c66d0d015434218b66eaf9d6c8628f3_20_121  )))\n" + 
				 	"  then\n" + 
				 	"     $map.put(\"result\",\"true\");\n" + 
				 	"$map.put(\"desc\",\"不知名的结果\");\n" + 
				 	"\n" + 
				 	"end";
		 
		   KieHelper helper=new KieHelper();
		   helper.addContent(drlStr,ResourceType.DRL);
		   KnowledgeBaseImpl kieBase =(KnowledgeBaseImpl) helper.build();
		   KieSession session = kieBase.newKieSession();
		   Map<String,Object> map = new HashMap<>();
		   map.put("a_8c66d0d015434218b66eaf9d6c8628f3_20_120",true);
		   map.put("a_8c66d0d015434218b66eaf9d6c8628f3_20_121",true);
		   session.insert(map);
		   session.fireAllRules(new RuleNameEndsWithAgendaFilter("2d7473538515481e994ee154e740e322"));
		   session.dispose();
		   System.out.println(map.get("result"));
	}
    
    public static void getSb(StringBuilder sb) {
    	
    		sb.append("加上了");
    		System.out.println(sb.indexOf("a"));
    }

}