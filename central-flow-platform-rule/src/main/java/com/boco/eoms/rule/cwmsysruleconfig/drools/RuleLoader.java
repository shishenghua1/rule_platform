//package com.boco.eoms.rule.cwmsysruleconfig.drools;
//
//import java.text.MessageFormat;
//import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//import org.kie.api.KieServices;
//import org.kie.api.builder.KieBuilder;
//import org.kie.api.builder.KieFileSystem;
//import org.kie.api.builder.Message;
//import org.kie.api.builder.Results;
//import org.kie.api.builder.model.KieBaseModel;
//import org.kie.api.builder.model.KieModuleModel;
//import org.kie.api.runtime.KieContainer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import com.boco.eoms.base.util.StaticMethod;
//import com.boco.eoms.rule.cwmsysruleatoms.model.CwmRuleAtoms;
//import com.boco.eoms.rule.cwmsysruleatoms.service.ICwmRuleAtomsService;
//import com.boco.eoms.rule.cwmsysruleconfig.model.RuleInfo;
//import com.boco.eoms.rule.cwmsysruleconfig.service.RuleInfoService;
//
///**
// * 规则加载类
// * @author chenjianghe
// *
// */
//@Component
//public class RuleLoader implements ApplicationRunner{
//	
//	private Logger logger = LoggerFactory.getLogger(RuleLoader.class);
//	
//	/**
//     * key:kcontainerName,value:KieContainer，每个场景对应一个KieContainer
//     */
//    private final ConcurrentMap<String, KieContainer> kieContainerMap = new ConcurrentHashMap<>();
//    
//    @Autowired
//    private RuleInfoService ruleInfoService;
//    
//	@Override
//	public void run(ApplicationArguments arg0) throws Exception {
////		reloadAll();
//	}
//	
//	/**
//     * 构造kcontainerName
//     *
//     * @param ruleId
//     * @return kcontainerName
//     */
//    private String buildKcontainerName(String ruleId) {
//        return "kcontainer_" + ruleId;
//    }
//
//    /**
//     * 构造kbaseName
//     *
//     * @param ruleId
//     * @return kbaseName
//     */
//    private String buildKbaseName(String ruleId) {
//        return "kbase_" + ruleId;
//    }
//
//    /**
//     * 构造ksessionName
//     *
//     * @param sruleId
//     * @return ksessionName
//     */
//    private String buildKsessionName(String ruleId) {
//        return "ksession_" + ruleId;
//    }
//
//    /**
//     * 获取指定规则的session
//     * @param ruleId
//     * @return
//     */
//    KieContainer getKieContainerByRuleId(String ruleId) {
//        return kieContainerMap.get(buildKcontainerName(ruleId));
//    }
//    
//    /**
//     * 重新加载所有规则
//     */
//    public void reloadAll() {
//    		List<RuleInfo> ruleInfoList = ruleInfoService.getAllRuleGroupsNonDraft();
//    		if(ruleInfoList != null && ruleInfoList.size() > 0) {
//    			for(RuleInfo ruleInfo : ruleInfoList) {
//    				String ruleId = StaticMethod.nullObject2String(ruleInfo.getRuleId());
//    				String content = StaticMethod.nullObject2String(ruleInfo.getContent());
//    				reload(ruleId, content);
//    			}
//    			logger.info("加载所有规则成功!");
//    		}else {
//    			logger.info("未查询到规则，ruleInfoList为空!");
//    		}
//    }
//    
//    /**
//     * 重新加载指定规则
//     *
//     * @param ruleId
//     */
//    public void reload(String ruleId) {
//    		RuleInfo ruleInfo = ruleInfoService.getRuleInfoByRuleId(ruleId);
//    		String content = "";
//    		if(ruleInfo!=null){
//            content = StaticMethod.nullObject2String(ruleInfo.getContent());
//            reload(ruleId, content);
//            logger.info("reload success");
//        }else {
//        		logger.info(ruleId+"规则已关闭或者规则不存在，请检查！");
//        }
//        
//    }
//    
//    /**
//     * 加载规则
//     *
//     * @param sceneId   场景ID
//     * @param ruleInfos 规则列表
//     */
//    private void reload(String ruleId,String content) {
//        KieServices kieServices = KieServices.get();
//        KieModuleModel kieModuleModel = kieServices.newKieModuleModel();
//        KieBaseModel kieBaseModel = kieModuleModel.newKieBaseModel(buildKbaseName(ruleId));
//        kieBaseModel.setDefault(true);
//        kieBaseModel.addPackage(MessageFormat.format("rules.scene_{0}", String.valueOf(ruleId)));
//        kieBaseModel.newKieSessionModel(buildKsessionName(ruleId));
//
//        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//        String fullPath = MessageFormat.format("src/main/resources/rules/scene_{0}/rule_{1}.drl", ruleId, ruleId);
//        kieFileSystem.write(fullPath, content);
//        kieFileSystem.writeKModuleXML(kieModuleModel.toXML());
//
//        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
//        Results results = kieBuilder.getResults();
//        if (results.hasMessages(Message.Level.ERROR)) {
//            throw new IllegalStateException("rule error");
//        }
//
//        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
//        kieContainerMap.put(buildKcontainerName(ruleId), kieContainer);
//    }
//
//}
