package com.boco.eoms;

import java.util.Date;

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
import com.boco.eoms.rule.cwmsysruleatomgroup.model.CwmRuleAtomGroup;
import com.boco.eoms.rule.cwmsysruleatomgroup.service.ICwmRuleAtomGroupService;

/**
 * 原子规则集合单元测试类
 * @author chenjianghe
 *
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = CentralFlowPlatformRuleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CwmRuleAtomGroupServiceTest {

	@Autowired
	private ICwmRuleAtomGroupService cwmRuleAtomGroupService;
	
	private Logger logger = LoggerFactory.getLogger(CwmRuleAtomGroupServiceTest.class);
	
	@Test
	public void cwmRuleAtomGroupCRUD() throws Exception{
		
		CwmRuleAtomGroup cwmRuleAtomGroup = new CwmRuleAtomGroup();
		String id = UUIDHexGenerator.getInstance().getID();
		cwmRuleAtomGroup.setId(id);
		cwmRuleAtomGroup.setAtomGroupName("rule1");
		cwmRuleAtomGroup.setAtomGroupDescription("测试");
		cwmRuleAtomGroup.setDeleted("0");
		cwmRuleAtomGroup.setCreateUserId("admin");
		cwmRuleAtomGroup.setCreateTime(new Date());
		
		cwmRuleAtomGroupService.insert(cwmRuleAtomGroup);
		
		cwmRuleAtomGroup.setAtomGroupName("rule2");
		
		cwmRuleAtomGroupService.updateByPrimaryKey(cwmRuleAtomGroup);
		
		CwmRuleAtomGroup cwmRuleAtomGroup1 = cwmRuleAtomGroupService.selectByPrimaryKey(id);
		
		Assert.assertEquals(cwmRuleAtomGroup1.getId(),id);
		
		cwmRuleAtomGroupService.deleteByPrimaryKey(id);
		
	}

}
