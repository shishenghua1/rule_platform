package com.boco.eoms.rule.oauth2.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.rule.cwmsysruleuser.model.CwmRuleUser;
import com.boco.eoms.rule.cwmsysruleuser.service.ICwmRuleUserService;
import com.boco.eoms.rule.oauth2.OAuth2Client;

@RestController
@RequestMapping("/oauth")
public class Oauth2ClientController {
	
	@Autowired
	private ICwmRuleUserService systemUserService;

	/**
	 * 根据access_token换取userId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getUserIdByAccessToken", method=RequestMethod.POST)
	public Object getUserIdByAccessToken(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String access_token = StaticMethod.nullObject2String(request.getParameter("access_token"));
		String userId = OAuth2Client.getUserId(access_token);
		CwmRuleUser user = systemUserService.querysystemUserByUid(userId);
		json.put("userId", userId);
		json.put("appId", user.getAppId());
		return json;
	}
}
