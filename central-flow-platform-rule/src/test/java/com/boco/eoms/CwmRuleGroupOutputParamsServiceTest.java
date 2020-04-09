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
import com.boco.eoms.rule.cwmsysrulegroupoutputparams.model.CwmRuleGroupOutputParams;
import com.boco.eoms.rule.cwmsysrulegroupoutputparams.service.ICwmRuleGroupOutputParamsService;

/**
 * 规则集合输出参数单元测试
 * @author chenjianghe
 *
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = CentralFlowPlatformRuleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CwmRuleGroupOutputParamsServiceTest {

	@Autowired
	private ICwmRuleGroupOutputParamsService cwmRuleGroupOutputParamsService;
	
	private Logger logger = LoggerFactory.getLogger(CwmRuleGroupOutputParamsServiceTest.class);
	
	@Test
	public void cwmRuleGroupParamsCRUD() throws Exception{
		
		CwmRuleGroupOutputParams cwmRuleGroupOutputParams = new CwmRuleGroupOutputParams();
		
		String id = UUIDHexGenerator.getInstance().getID();
		
		cwmRuleGroupOutputParams.setId(id);
		cwmRuleGroupOutputParams.setGroupOutputParamsId(UUIDHexGenerator.getInstance().getID());
		cwmRuleGroupOutputParams.setOutputCnParam1("派单时间");
		cwmRuleGroupOutputParamsService.insert(
				cwmRuleGroupOutputParams);
		
		cwmRuleGroupOutputParams.setOutputCnParam1("告警时间");

		cwmRuleGroupOutputParamsService.updateByPrimaryKey(cwmRuleGroupOutputParams);
		
		CwmRuleGroupOutputParams cwmRuleGroupOutputParams1 = cwmRuleGroupOutputParamsService.selectByPrimaryKey(id);
		Assert.assertEquals(cwmRuleGroupOutputParams1.getId(), id);
		
		cwmRuleGroupOutputParamsService.deleteByPrimaryKey(id);
		
		logger.info("执行成功");
	}

}
