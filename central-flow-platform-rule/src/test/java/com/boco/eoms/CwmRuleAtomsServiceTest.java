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
import com.boco.eoms.rule.cwmsysruleatoms.model.CwmRuleAtoms;
import com.boco.eoms.rule.cwmsysruleatoms.service.ICwmRuleAtomsService;

/**
 * 规则平台原子规则表单元测试类
 * @author 
 *类上的事务注解
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = CentralFlowPlatformRuleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CwmRuleAtomsServiceTest{
	@Autowired
    private ICwmRuleAtomsService cwmRuleAtomsService;
	
	private Logger logger = LoggerFactory.getLogger(CwmRuleAtomsServiceTest.class);
	
	/**
	 * 规则平台原子规则表crud
	 * @throws Exception
	 */
	@Test
	public void cwmRuleAtomsCRUD() throws Exception{
		CwmRuleAtoms cwmRuleAtoms = new CwmRuleAtoms();
		String id = UUIDHexGenerator.getInstance().getID();
		cwmRuleAtoms.setId(id);//新增信息生成id
		cwmRuleAtoms.setAtomDescription("工单手工添加的故障消除时间/告警清除时间，<告警发生时间");
		cwmRuleAtoms.setDeleted("0");
		cwmRuleAtoms.setAtomName("原子1");
		cwmRuleAtoms.setCreateTime(new Date());
		cwmRuleAtoms.setCreateUserId("admin");
		//插入
		cwmRuleAtomsService.insert(cwmRuleAtoms);
		//更新
		cwmRuleAtoms.setCreateUserId("ssh");
		cwmRuleAtomsService.updateByPrimaryKey(cwmRuleAtoms);
		//查询
		CwmRuleAtoms cwmRuleAtoms_db = cwmRuleAtomsService.selectByPrimaryKey(id);
		logger.info(cwmRuleAtoms_db.toString());
		//断言
		Assert.assertEquals(cwmRuleAtoms_db.getId(),id);
		//清除
		cwmRuleAtomsService.deleteByPrimaryKey(id);
	
	}
}
