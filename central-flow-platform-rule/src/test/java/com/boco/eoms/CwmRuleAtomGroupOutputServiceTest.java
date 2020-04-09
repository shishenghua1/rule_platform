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
import com.boco.eoms.rule.cwmsysruleatomgroupoutput.model.CwmRuleAtomGroupOutput;
import com.boco.eoms.rule.cwmsysruleatomgroupoutput.service.ICwmRuleAtomGroupOutputService;

/**
 * 原子规则集合输出单元测试类
 * @author chenjianghe
 *
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = CentralFlowPlatformRuleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CwmRuleAtomGroupOutputServiceTest {

	@Autowired
	private ICwmRuleAtomGroupOutputService cwmRuleAtomGroupOutputService;
	
	private Logger logger = LoggerFactory.getLogger(CwmRuleAtomGroupRelServiceTest.class);
	
	@Test
	public void cwmRuleAtomGroupOutputCRUD() throws Exception{
		CwmRuleAtomGroupOutput cwmRuleAtomGroupOutput = new CwmRuleAtomGroupOutput();
		String id = UUIDHexGenerator.getInstance().getID();
		
		cwmRuleAtomGroupOutput.setId(id);
		cwmRuleAtomGroupOutput.setAtomGroupId(UUIDHexGenerator.getInstance().getID());
		cwmRuleAtomGroupOutput.setAtomGroupRelId(UUIDHexGenerator.getInstance().getID());
		cwmRuleAtomGroupOutput.setParamResult("true");
		cwmRuleAtomGroupOutput.setParamResultDescription("通过");
		cwmRuleAtomGroupOutput.setDeleted("0");
		
		cwmRuleAtomGroupOutputService.insert(cwmRuleAtomGroupOutput);
		
		cwmRuleAtomGroupOutput.setParamResult("false");
		
		cwmRuleAtomGroupOutputService.updateByPrimaryKey(cwmRuleAtomGroupOutput);
		
		CwmRuleAtomGroupOutput cwmRuleAtomGroupOutput1 = cwmRuleAtomGroupOutputService.selectByPrimaryKey(id);
		
		Assert.assertEquals(cwmRuleAtomGroupOutput1.getId(),id);
		
		cwmRuleAtomGroupOutputService.deleteByPrimaryKey(id);
		
	}

}
