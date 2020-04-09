package com.boco.eoms.rule.cwmsysruleuser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.rule.cwmsysruleuser.service.ICwmRuleUserService;

/**
 * 人员管理Controller
 * @author chenjianghe
 *
 */
@RestController
@RequestMapping("/user")
public class CwmRuleUserController {
	
	@Autowired
	private ICwmRuleUserService cwmRuleUserService;
	
	private Logger logger = LoggerFactory.getLogger(CwmRuleUserController.class);

	
	/**
	 * 登陆系统
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/userLogin" }, method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public Object userLogin(@RequestBody JSONObject params) throws Exception {
		String id = StaticMethod.nullObject2String(params.get("id"));
		String pw = StaticMethod.nullObject2String(params.get("pw"));
		return cwmRuleUserService.userLogin(id,pw);
		
	}
}
