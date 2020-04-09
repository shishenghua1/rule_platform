package com.boco.eoms;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.rule.cwmsysrulegroupoutputrel.model.CwmRuleGroupOutputRel;
import com.boco.eoms.rule.cwmsysrulegroupoutputrel.service.ICwmRuleGroupOutputRelService;

/**
 * 规则集合原子规则排列输出结果单元测试
 * @author chenjianghe
 *
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = CentralFlowPlatformRuleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CwmRuleGroupOutputRelServiceTest {

	@Autowired
	private ICwmRuleGroupOutputRelService cwmRuleGroupOutputRelService;
	
	private Logger logger = LoggerFactory.getLogger(CwmRuleGroupOutputRelServiceTest.class);
	
	@Test
	public void cwmRuleGroupOutputRelCRUD(){
		try {

			CwmRuleGroupOutputRel cwmRuleGroupOutputRel = new CwmRuleGroupOutputRel();
			
			String id = UUIDHexGenerator.getInstance().getID();
			
			cwmRuleGroupOutputRel.setId(id);
			cwmRuleGroupOutputRel.setGroupOutputId(id);
			cwmRuleGroupOutputRel.setGroupIdRel(id);
			cwmRuleGroupOutputRel.setGroupOutputResultRel("1");
			cwmRuleGroupOutputRel.setGroupOutputExplainRel("success");
			cwmRuleGroupOutputRel.setCreateUserId("admin");
			cwmRuleGroupOutputRelService.insert(cwmRuleGroupOutputRel);
			
			cwmRuleGroupOutputRel.setGroupOutputResultRel("false");
			cwmRuleGroupOutputRelService.updateByPrimaryKey(cwmRuleGroupOutputRel);
			
//			CwmRuleGroupOutputRel cwmRuleGroupOutputRel1 = cwmRuleGroupOutputRelService.selectByPrimaryKey(id);
//			
//			Assert.assertEquals(cwmRuleGroupOutputRel1.getId(), id);
			
			cwmRuleGroupOutputRelService.deleteByPrimaryKey(id);
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
