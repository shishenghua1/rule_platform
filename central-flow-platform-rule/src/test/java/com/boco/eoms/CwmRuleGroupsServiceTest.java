//package com.boco.eoms;
//
//import java.util.Date;
//import java.util.List;
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
//import com.boco.eoms.rule.cwmsysrulegroups.model.CwmRuleGroups;
//import com.boco.eoms.rule.cwmsysrulegroups.service.ICwmRuleGroupsService;
//
///**
// * 规则平台规则集合单元测试类
// * @author 
// *类上的事务注解
// */
//@RunWith(SpringRunner.class)
//@Transactional
//@SpringBootTest(classes = CentralFlowPlatformRuleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class CwmRuleGroupsServiceTest{
//	@Autowired
//    private ICwmRuleGroupsService cwmRuleGroupsService;
//	
//	private Logger logger = LoggerFactory.getLogger(CwmRuleGroupsServiceTest.class);
//	
////	/**
////	 * 规则平台规则集合crud
////	 * @throws Exception
////	 */
////	@Test
////	public void cwmRuleGroupsCRUD() throws Exception{
////		CwmRuleGroups cwmRuleGroups = new CwmRuleGroups();
////		String id = UUIDHexGenerator.getInstance().getID();
////		cwmRuleGroups.setId(id);//新增信息生成id
////		cwmRuleGroups.setGroupName("limitsubmit");
////		cwmRuleGroups.setDeleted("0");
////		cwmRuleGroups.setCreateTime(new Date());
////		cwmRuleGroups.setCreateUserId("admin");
////		cwmRuleGroups.setModuleId("1");
////		cwmRuleGroups.setGroupType("ruleGroup");
////		//插入
////		cwmRuleGroupsService.insert(cwmRuleGroups);
////		CwmRuleGroups cwmRuleGroups_db = cwmRuleGroupsService.selectByPrimaryKey(id);
////		logger.info(cwmRuleGroups_db.toString());
////		
////		List<CwmRuleGroups> list = cwmRuleGroupsService.selectByModuleId("1","ruleGroup");
////		Assert.assertEquals(list.size(),1);
////		//更新
////		cwmRuleGroups.setCreateUserId("ssh");
////		cwmRuleGroupsService.updateByPrimaryKey(cwmRuleGroups);
////		//查询
//////		CwmRuleGroups cwmRuleGroups_db = cwmRuleGroupsService.selectByPrimaryKey(id);
//////		logger.info(cwmRuleGroups_db.toString());
////		//断言
////		Assert.assertEquals(cwmRuleGroups_db.getId(),id);
////		//清除
////		cwmRuleGroupsService.deleteByPrimaryKey(id,"ruleGroup");
////	
////	}
//}
