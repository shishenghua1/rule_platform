//package com.boco.eoms;
//
//import java.util.Date;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.boco.eoms.base.util.UUIDHexGenerator;
//import com.boco.eoms.rule.cwmsysruleapps.model.CwmRuleApps;
//import com.boco.eoms.rule.cwmsysruleapps.service.ICwmRuleAppsService;
//
///**
// * 规则平台场景分类表单元测试类
// * @author 
// *类上的事务注解
// */
//@RunWith(SpringRunner.class)
//@Transactional
//@SpringBootTest(classes = CentralFlowPlatformRuleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class CwmRuleAppsServiceTest{
//	@Autowired
//    private ICwmRuleAppsService cwmRuleAppsService;
//	
////	/**
////	 * 规则平台场景分类表crud
////	 * @throws Exception
////	 */
////	@Test
////	public void cwmRuleScenesCRUD() throws Exception{
////		CwmRuleApps cwmRuleScenes = new CwmRuleApps();
////		String id = UUIDHexGenerator.getInstance().getID();
////		cwmRuleScenes.setId(id);//新增信息生成id
////		cwmRuleScenes.setAppName("规则场景测试1");
////		cwmRuleScenes.setDeleted("0");
////		cwmRuleScenes.setCreateTime(new Date());
////		cwmRuleScenes.setCreateUserId("admin");
////		//插入
////		cwmRuleAppsService.insert(cwmRuleScenes);
////		
////		//更新
////		cwmRuleScenes.setCreateUserId("ssh");
////		cwmRuleAppsService.updateByPrimaryKey(cwmRuleScenes);
////		
////		//判断插入是否成功
////		CwmRuleApps cwmRuleScenes_db = cwmRuleAppsService.selectByPrimaryKey(id);
////		//断言
////		Assert.assertEquals(cwmRuleScenes_db.getId(),id);
////				
////		//清除
////		cwmRuleAppsService.deleteByPrimaryKey(id);
////	
////	}
//}
