package com.boco.eoms;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.rule.cwmsysdatadetail.model.CwmDataDetail;
import com.boco.eoms.rule.cwmsysdatadetail.service.ICwmDataDetailService;

/**
 * 数据详情表service层单元测试类
 * @author 
 *类上的事务注解
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = CentralFlowPlatformRuleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CwmDataDetailServiceTest{
	@Autowired
    private ICwmDataDetailService cwmDataDetailService;
	
	/**
	 * 规则平台场景分类表crud
	 * @throws Exception
	 */
	@Test
	public void cwmDataDetailCRUD() throws Exception{
		CwmDataDetail cwmDataDetail = new CwmDataDetail();
		String id = UUIDHexGenerator.getInstance().getID();
		cwmDataDetail.setId(id);
		cwmDataDetail.setDataModuleId("7eb90bc4036145bc80dcbaba5176496c");
		cwmDataDetail.setFieldCnName("派单时间");
		cwmDataDetail.setFieldEnName("paidan");
		cwmDataDetail.setFieldCalculate("a+b=c");
		//插入
		cwmDataDetailService.insert(cwmDataDetail);
		
		//更新
		cwmDataDetail.setFieldCnName("故障时间");
		cwmDataDetailService.updateByPrimaryKey(cwmDataDetail);
		
		//判断插入是否成功
		CwmDataDetail cwmDataDetail_db = cwmDataDetailService.selectByPrimaryKey(id);
		//断言
		Assert.assertEquals(cwmDataDetail_db.getId(),id);
				
		//清除
		cwmDataDetailService.deleteByCondition(id,"","");
	
	}
}
