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
import com.boco.eoms.rule.cwmsysruleatomgroupoutputrel.model.CwmRuleAtomGroupOutputRel;
import com.boco.eoms.rule.cwmsysruleatomgroupoutputrel.service.ICwmRuleAtomGroupOutputRelService;

/**
 * 原子规则集合关联原子规则输出service单元测试
 * @author chenjianghe
 *
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = CentralFlowPlatformRuleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CwmRuleAtomGroupOutputRelServiceTest {

	@Autowired
	private ICwmRuleAtomGroupOutputRelService cwmRuleAtomGroupOutputRelService;
	
	private Logger logger = LoggerFactory.getLogger(CwmRuleAtomGroupOutputRelServiceTest.class);
	
	@Test
	public void cwmRuleAtomGroupOutputRelCRUD() throws Exception{
		CwmRuleAtomGroupOutputRel cwmRuleAtomGroupOutputRel = new CwmRuleAtomGroupOutputRel();
		String id = UUIDHexGenerator.getInstance().getID();
		
		cwmRuleAtomGroupOutputRel.setId(id);
		cwmRuleAtomGroupOutputRel.setAtomGroupRelId(UUIDHexGenerator.getInstance().getID());
		cwmRuleAtomGroupOutputRel.setAtomId(UUIDHexGenerator.getInstance().getID());
		cwmRuleAtomGroupOutputRel.setAtomResult("true");
		
		cwmRuleAtomGroupOutputRelService.insert(cwmRuleAtomGroupOutputRel);
		
		cwmRuleAtomGroupOutputRel.setAtomResult("false");
		
		cwmRuleAtomGroupOutputRelService.updateByPrimaryKey(cwmRuleAtomGroupOutputRel);
		
		CwmRuleAtomGroupOutputRel cwmRuleAtomGroupOutputRel1 = cwmRuleAtomGroupOutputRelService.selectByPrimaryKey(id);
		
		Assert.assertEquals(cwmRuleAtomGroupOutputRel1.getId(),id);
		
		cwmRuleAtomGroupOutputRelService.deleteByPrimaryKey(id);
	}

}
