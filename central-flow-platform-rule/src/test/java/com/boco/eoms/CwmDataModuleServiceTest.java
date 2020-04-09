//package com.boco.eoms;
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
//import com.boco.eoms.rule.cwmsysdatamodule.model.CwmDataModule;
//import com.boco.eoms.rule.cwmsysdatamodule.service.ICwmDataModuleService;
//
///**
// * 数据模块表service层单元测试类
// * @author 
// *类上的事务注解
// */
//@RunWith(SpringRunner.class)
//@Transactional
//@SpringBootTest(classes = CentralFlowPlatformRuleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class CwmDataModuleServiceTest{
//	@Autowired
//    private ICwmDataModuleService cwmDataModuleService;
//	
//	/**
//	 * 规则平台场景分类表crud
//	 * @throws Exception
//	 */
//	@Test
//	public void cwmDataModuleCRUD() throws Exception{
//		CwmDataModule cwmDataModule = new CwmDataModule();
//		String id = UUIDHexGenerator.getInstance().getID();
//		cwmDataModule.setId(id);
//		cwmDataModule.setDataModule("数据模块1");
//		cwmDataModule.setDataType("variable");
//		//插入
//		cwmDataModuleService.insert(cwmDataModule);
//		
//		//更新
//		cwmDataModule.setDataType("constant");
//		cwmDataModuleService.updateByPrimaryKey(cwmDataModule);
//		
//		//判断插入是否成功
//		CwmDataModule cwmDataModule_db = cwmDataModuleService.selectByPrimaryKey(id);
//		//断言
//		Assert.assertEquals(cwmDataModule_db.getId(),id);
//				
//		//清除
//		cwmDataModuleService.deleteByPrimaryKey(id);
//	
//	}
//}
