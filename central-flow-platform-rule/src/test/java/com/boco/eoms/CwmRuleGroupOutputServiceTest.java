package com.boco.eoms;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.rule.cwmsysrulegroupoutput.model.CwmRuleGroupOutput;
import com.boco.eoms.rule.cwmsysrulegroupoutput.service.ICwmRuleGroupOutputService;


/**
 * 规则集合输出表单元测试类
 * @author chenjianghe
 *
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = CentralFlowPlatformRuleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CwmRuleGroupOutputServiceTest {

	@Autowired
	private ICwmRuleGroupOutputService cwmRuleGroupOutputService;
	
	@Test
	public void cwmRuleGroupOutputCRUD() throws Exception{
		CwmRuleGroupOutput cwmRuleGroupOutput = new CwmRuleGroupOutput();
		
		String id = UUIDHexGenerator.getInstance().getID();
		
		cwmRuleGroupOutput.setId(id);
		cwmRuleGroupOutput.setGroupId(UUIDHexGenerator.getInstance().getID());
		cwmRuleGroupOutput.setGroupOutputResult("true");
		cwmRuleGroupOutput.setGroupOutputExplain("成功执行");
		
		cwmRuleGroupOutputService.insert(cwmRuleGroupOutput);
		
//		cwmRuleGroupOutput.setGroupOutputResult("false");
//		cwmRuleGroupOutputService.updateByPrimaryKey(cwmRuleGroupOutput);
		
//		CwmRuleGroupOutput cwmRuleGroupOutput1 = cwmRuleGroupOutputService.selectByPrimaryKey(id);
//		
//		Assert.assertEquals(cwmRuleGroupOutput1.getId(), id);
		
		cwmRuleGroupOutputService.deleteByPrimaryKey(id);
		
	}
}
