package com.boco.eoms;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.rule.cwmsysdatadict.model.CwmDataDict;
import com.boco.eoms.rule.cwmsysdatadict.service.ICwmDataDictService;

/**
 * 数据字典表service层单元测试类
 * @author 
 *类上的事务注解
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = CentralFlowPlatformRuleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CwmDataDictServiceTest{
	@Autowired
    private ICwmDataDictService cwmDataDictService;
	
	/**
	 * 规则平台场景分类表crud
	 * @throws Exception
	 */
	@Test
	public void cwmDataModuleCRUD() throws Exception{
		CwmDataDict cwmDataDict = new CwmDataDict();
		String id = UUIDHexGenerator.getInstance().getID();
		cwmDataDict.setId(id);
		cwmDataDict.setDictCnName("字典1");
		cwmDataDict.setDictEnName("dict1");
		cwmDataDict.setDictType("字符串");
		cwmDataDict.setCreateUserId("admin");
		cwmDataDict.setParentDictId("7eb90bc4036145bc80dcbaba5176496c");
		cwmDataDict.setDictId("7eb90bc4036145bc80dcbaba5176496c");
		//插入
		cwmDataDictService.insert(cwmDataDict);
		
		//更新
		cwmDataDict.setDictCnName("字典2");
		cwmDataDictService.updateByPrimaryKey(cwmDataDict);
		
		//判断插入是否成功
		CwmDataDict cwmDataDictDb = cwmDataDictService.selectByPrimaryKey(id);
		//断言
		Assert.assertEquals(cwmDataDictDb.getId(),id);
				
		//清除
		cwmDataDictService.deleteByCondition(id,"","");
	
	}
}
