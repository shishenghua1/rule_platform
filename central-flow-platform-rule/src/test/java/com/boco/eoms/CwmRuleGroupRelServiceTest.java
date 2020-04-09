package com.boco.eoms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.boco.eoms.rule.cwmsysrulegrouprel.mapper.CwmRuleGroupRelMapper;
import com.boco.eoms.rule.cwmsysrulegrouprel.model.CwmRuleGroupRel;
import com.boco.eoms.rule.cwmsysrulegrouprel.service.ICwmRuleGroupRelService;

/**
 * 规则集合关联原子规则集合表单元测试类
 * @author chenjianghe
 *
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = CentralFlowPlatformRuleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CwmRuleGroupRelServiceTest {

	@Autowired
	private ICwmRuleGroupRelService cwmRuleGroupRelService;
	@Autowired
	private CwmRuleGroupRelMapper cwmRuleGroupRelMapper;
	
	private Logger logger = LoggerFactory.getLogger(CwmRuleGroupRelServiceTest.class);
	
	@Test
	public void cwmRuleGroupRelCRUD() throws Exception{
		CwmRuleGroupRel cwmRuleGroupRel = new CwmRuleGroupRel();
		
		String id = UUIDHexGenerator.getInstance().getID();
		String groupId = UUIDHexGenerator.getInstance().getID();
		
		cwmRuleGroupRel.setId(id);
		cwmRuleGroupRel.setParentNodeId(groupId);
		cwmRuleGroupRel.setNodeType("atomRule");
		cwmRuleGroupRel.setRuleInfoRel("告警清除时间-故障发生时间＞30分钟");
		cwmRuleGroupRel.setRuleIdRel("7eb90bc4036145bc80dcbaba5176496c");
		cwmRuleGroupRel.setOrderBy(1);
		cwmRuleGroupRel.setCreateTime(new Date());
		
		cwmRuleGroupRelService.insert(cwmRuleGroupRel);
		
		cwmRuleGroupRel.setRuleInfoRel("or");
		cwmRuleGroupRelService.updateByPrimaryKey(cwmRuleGroupRel);
		
		CwmRuleGroupRel cwmRuleGroupRel1 = cwmRuleGroupRelService.selectByPrimaryKey(id);
		Assert.assertEquals(cwmRuleGroupRel1.getId(), id);
		
		cwmRuleGroupRelService.deleteByPrimaryKey(id);
		
		List<CwmRuleGroupRel> CwmRuleGroupRels = new ArrayList<CwmRuleGroupRel>();
		CwmRuleGroupRels.add(cwmRuleGroupRel);
		
		cwmRuleGroupRelService.batchInsert(CwmRuleGroupRels);
		
		List<CwmRuleGroupRel> CwmRuleGroupRelsList = cwmRuleGroupRelService.selectByGroupId(groupId);
		
		Assert.assertEquals(CwmRuleGroupRelsList.size(), 1);
		
		cwmRuleGroupRelService.deleteByPrimaryKey(id);
	}

	@Test
	public void cwmRuleGroupRelBatchInsert() throws Exception{
		List<CwmRuleGroupRel> list = new ArrayList<>();
		for(int i=0;i<3;i++) {
			CwmRuleGroupRel cwmRuleGroupRel = new CwmRuleGroupRel();
			String groupId = UUIDHexGenerator.getInstance().getID();
			cwmRuleGroupRel.setId(i+"");
			cwmRuleGroupRel.setParentNodeId(groupId);
			cwmRuleGroupRel.setNodeType("atomRule");
			cwmRuleGroupRel.setRuleInfoRel("11111");
			cwmRuleGroupRel.setRuleIdRel("7eb90bc4036145bc80dcbaba5176496c");
			cwmRuleGroupRel.setOrderBy(1);
			list.add(cwmRuleGroupRel);
		}
		cwmRuleGroupRelMapper.batchInsert(list);
		cwmRuleGroupRelService.deleteByPrimaryKey("0");
		cwmRuleGroupRelService.deleteByPrimaryKey("1");
		cwmRuleGroupRelService.deleteByPrimaryKey("2");
	}
}
