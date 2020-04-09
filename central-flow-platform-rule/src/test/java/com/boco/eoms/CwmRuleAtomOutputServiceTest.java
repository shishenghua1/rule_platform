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
import com.boco.eoms.rule.cwmsysruleatomoutput.model.CwmRuleAtomOutput;
import com.boco.eoms.rule.cwmsysruleatomoutput.service.ICwmRuleAtomOutputService;



/**
 * 规则平台原子规则输出表单元测试类
 * @author chenjianghe
 *
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = CentralFlowPlatformRuleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CwmRuleAtomOutputServiceTest {
	
	@Autowired
	private ICwmRuleAtomOutputService cwmRuleAtomOutputService;
	
	private Logger logger = LoggerFactory.getLogger(CwmRuleAtomOutputServiceTest.class);
	
	@Test
	public void cwmRuleAtomOutputCRUD() throws Exception{
		
		CwmRuleAtomOutput cwmRuleAtomOutput = new CwmRuleAtomOutput();
		String id = UUIDHexGenerator.getInstance().getID();
		cwmRuleAtomOutput.setId(id);
		cwmRuleAtomOutput.setAtomId(UUIDHexGenerator.getInstance().getID());
		cwmRuleAtomOutput.setAtomInputId(UUIDHexGenerator.getInstance().getID());
		cwmRuleAtomOutput.setAtomOutputType("满足条件");
		cwmRuleAtomOutput.setParamResult("1");
		cwmRuleAtomOutput.setParamResultDescription("满足");
		cwmRuleAtomOutput.setParamInterEnName("param1");
		cwmRuleAtomOutput.setParamInterCnName("参数1");
		cwmRuleAtomOutput.setOrderBy(1);
		cwmRuleAtomOutput.setDeleted("0");
		cwmRuleAtomOutput.setCreateUserId("admin");
		cwmRuleAtomOutput.setCreateTime(new Date());
		
		cwmRuleAtomOutputService.insert(cwmRuleAtomOutput);
		
		cwmRuleAtomOutput.setAtomOutputType("不满足条件");
		cwmRuleAtomOutputService.updateByPrimaryKey(cwmRuleAtomOutput);
		
		CwmRuleAtomOutput cwmRuleAtomOutput1 = cwmRuleAtomOutputService.selectByPrimaryKey(id);
		Assert.assertEquals(cwmRuleAtomOutput1.getId(), id);
		
		cwmRuleAtomOutputService.deleteByPrimaryKey(id);
	}


}
