//  package com.boco.eoms;
//
//import java.util.Date;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.boco.eoms.base.util.UUIDHexGenerator;
//import com.boco.eoms.rule.cwmsysrulemodules.model.CwmRuleModules;
//import com.boco.eoms.rule.cwmsysrulemodules.service.ICwmRuleModulesService;
//
///**
// * 规则平台模块分类表单元测试类
// * @author 
// *类上的事务注解
// */
//@RunWith(SpringRunner.class)
//@Transactional
//@SpringBootTest(classes = CentralFlowPlatformRuleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class CwmRuleModulesServiceTest{
//	@Autowired
//    private ICwmRuleModulesService cwmRuleModulesService;
//	
//	private Logger logger = LoggerFactory.getLogger(CwmRuleModulesServiceTest.class);
//	
////	/**
////	 * 规则平台模块分类表crud
////	 * @throws Exception
////	 */
////	@Test
////	public void cwmRuleModulesCRUD() throws Exception{
////		CwmRuleModules cwmRuleModules = new CwmRuleModules();
////		String id = UUIDHexGenerator.getInstance().getID();
////		cwmRuleModules.setId(id);//新增信息生成id
////		cwmRuleModules.setModuleName("测试规则模块");
////		cwmRuleModules.setDeleted("0");
////		cwmRuleModules.setParentModuleId(id);
////		cwmRuleModules.setCreateTime(new Date());
////		cwmRuleModules.setCreateUserId("admin");
////		cwmRuleModules.setAppId("123");
////		//插入
////		cwmRuleModulesService.insert(cwmRuleModules);
////		//更新
////		cwmRuleModules.setCreateUserId("ssh");
////		cwmRuleModulesService.updateByPrimaryKey(cwmRuleModules);
////		//查询
////		CwmRuleModules cwmRuleModules_db = cwmRuleModulesService.selectByPrimaryKey(id);
////		logger.info(cwmRuleModules_db.toString());
////		//断言
////		Assert.assertEquals(cwmRuleModules_db.getId(),id);
////		//清除
////		cwmRuleModulesService.deleteByPrimaryKey(id);
////	
////	}
//}
