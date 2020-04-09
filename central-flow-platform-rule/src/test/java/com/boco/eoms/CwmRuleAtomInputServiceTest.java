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
import com.boco.eoms.rule.cwmsysruleatominput.model.CwmRuleAtomInput;
import com.boco.eoms.rule.cwmsysruleatominput.service.ICwmRuleAtomInputService;


/**
 * 规则平台原子规则输入表单元测试类
 * @author chenjianghe
 *
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = CentralFlowPlatformRuleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CwmRuleAtomInputServiceTest {

	@Autowired
	private ICwmRuleAtomInputService cwmRuleAtomInputService;
	
	private Logger logger = LoggerFactory.getLogger(CwmRuleAtomInputServiceTest.class);
	
	@Test
	public void cwmRuleAtomInputCRUD() throws Exception{
		CwmRuleAtomInput cwmRuleAtomInput = new CwmRuleAtomInput();
		String id = UUIDHexGenerator.getInstance().getID();
		cwmRuleAtomInput.setId(id);
		cwmRuleAtomInput.setAtomId(UUIDHexGenerator.getInstance().getID());
		cwmRuleAtomInput.setInputEnParam1("param1");
		cwmRuleAtomInput.setInputCnParam1("参数1");
		cwmRuleAtomInput.setInputParamType1("字符常量");
		cwmRuleAtomInput.setOperator1("<");
		cwmRuleAtomInput.setInputEnParam2("param2");
		cwmRuleAtomInput.setInputCnParam2("参数2");
		cwmRuleAtomInput.setInputParamType2("字符常量");
		cwmRuleAtomInput.setOperator2("<");
		cwmRuleAtomInput.setInputEnParam3("param3");
		cwmRuleAtomInput.setInputCnParam3("参数3");
		cwmRuleAtomInput.setInputParamType3("字符常量");
		cwmRuleAtomInput.setDeleted("0");
		cwmRuleAtomInput.setCreateUserId("admin");
		cwmRuleAtomInput.setCreateTime(new Date());
		
		cwmRuleAtomInputService.insert(cwmRuleAtomInput);
		
		cwmRuleAtomInput.setInputParamType2("字符变量");
		
		cwmRuleAtomInputService.updateByPrimaryKey(cwmRuleAtomInput);
		
		CwmRuleAtomInput cwmRuleAtomInput1 = cwmRuleAtomInputService.selectByPrimaryKey(id);
		
		Assert.assertEquals(cwmRuleAtomInput1.getId(), id);
		
		cwmRuleAtomInputService.deleteByPrimaryKey(id);
	} 
	
}
