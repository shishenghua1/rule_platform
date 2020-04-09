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
import com.boco.eoms.rule.cwmsysruleatomgrouprel.model.CwmRuleAtomGroupRel;
import com.boco.eoms.rule.cwmsysruleatomgrouprel.service.ICwmRuleAtomGroupRelService;

/**
 * 原子规则集合关联单元测试类
 * @author chenjianghe
 *
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = CentralFlowPlatformRuleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CwmRuleAtomGroupRelServiceTest {

	@Autowired
	private ICwmRuleAtomGroupRelService cwmRuleAtomGroupRelService;
	
	private Logger logger = LoggerFactory.getLogger(CwmRuleAtomGroupRelServiceTest.class);
	
	@Test
	public void cwmRuleAtomGroupRelCRUD() throws Exception{
		CwmRuleAtomGroupRel cwmRuleAtomGroupRel = new CwmRuleAtomGroupRel();
		String id = UUIDHexGenerator.getInstance().getID();
		
		cwmRuleAtomGroupRel.setId(id);
		cwmRuleAtomGroupRel.setAtomGroupId(UUIDHexGenerator.getInstance().getID());
		cwmRuleAtomGroupRel.setAtomId(UUIDHexGenerator.getInstance().getID());
		cwmRuleAtomGroupRel.setOrderBy(1);
		cwmRuleAtomGroupRel.setExecuteCondtion("and");
		cwmRuleAtomGroupRel.setDeleted("0");
		cwmRuleAtomGroupRel.setCreateUserId("admin");
		cwmRuleAtomGroupRel.setCreateTime(new Date());
		
		cwmRuleAtomGroupRelService.insert(cwmRuleAtomGroupRel);
		
		cwmRuleAtomGroupRel.setExecuteCondtion("or");
		
		cwmRuleAtomGroupRelService.updateByPrimaryKey(cwmRuleAtomGroupRel);
		
		CwmRuleAtomGroupRel cwmRuleAtomGroupRel1 = cwmRuleAtomGroupRelService.selectByPrimaryKey(id);
		
		Assert.assertEquals(cwmRuleAtomGroupRel1.getId(),id);
		
		cwmRuleAtomGroupRelService.deleteByPrimaryKey(id);
		
	}

}
