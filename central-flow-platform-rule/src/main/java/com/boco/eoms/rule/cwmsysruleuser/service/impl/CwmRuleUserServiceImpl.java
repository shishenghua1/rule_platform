package com.boco.eoms.rule.cwmsysruleuser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.boco.eoms.base.config.Oauth2ServerProps;
import com.boco.eoms.base.util.HttpClientServlet;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.rule.cwmsysruleuser.mapper.CwmRuleUserMapper;
import com.boco.eoms.rule.cwmsysruleuser.model.CwmRuleUser;
import com.boco.eoms.rule.cwmsysruleuser.service.ICwmRuleUserService;
import com.boco.eoms.rule.oauth2.OAuth2Client;


@Service
public class CwmRuleUserServiceImpl implements ICwmRuleUserService {

    @Autowired
    private CwmRuleUserMapper  cwmSysRuleUserMapper;
    @Autowired
	private Oauth2ServerProps oauth2ServerProps;

    /**
     * 验证用户账号唯一
     * @return true:不重复，可以使用，false:重复，不能使用
     */
    public boolean checkUserId(String user_id) {
		// TODO Auto-generated method stub
    	String type = cwmSysRuleUserMapper.checkUserId(user_id);
		if(type == null || type == ""){
			return true;
		}else{
			return false;
		}
	}
    
    /**
     * 根据user_id(账号)查询人员信息
     */
    public CwmRuleUser querysystemUserByUid(String uid) {
    	return cwmSysRuleUserMapper.querysystemUserByUid(uid);
    }
    /**
     * 登陆验证
     */
    public Object userLogin(String id,String pw){
    	String appId = "";
    	String msg = "";
    	Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("pw", pw);
		List<CwmRuleUser> uList = cwmSysRuleUserMapper.userLogin(map);
		
    	if (null!=uList && uList.size()>0){
    		Map map1 = new HashMap();
    		map1 = (Map) uList.get(0);
    		CwmRuleUser u = new CwmRuleUser();
    		StaticMethod.map2Bean(u, map1);
    		if("DISABLED".equals(u.getStatus())){
    			msg = "此账户为禁用状态，无法登陆 ！";
    		}
	    	}else{
	    		msg = "账户或密码不正确！";
	    	}
		
		if("".equals(msg)){
			CwmRuleUser user = cwmSysRuleUserMapper.querysystemUserByUid(id);
			if(null!=user.getAppId()){
				appId = user.getAppId();
			}
		}
		
		String url = "http://"+oauth2ServerProps.getIp()+":"+oauth2ServerProps.getPort()+"/"+oauth2ServerProps.getVersion()+"/oauth/token?username="+id+"&password="+pw+"&grant_type=password&scope=select&client_id="+oauth2ServerProps.getClient_id_value_pwd_mod()+"&client_secret="+oauth2ServerProps.getClient_secret_value_pwd_mod()+"";
		JSONObject tokenMsg = HttpClientServlet.httpPost(url);
		String access_token = StaticMethod.nullObject2String(tokenMsg.get("access_token"));
		
		OAuth2Client.getUserId(access_token);
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("rule_token", access_token);
		jsonobj.put("msg", msg);
		jsonobj.put("userId", id);
		jsonobj.put("keyId", id);
		jsonobj.put("appId", appId);
		jsonobj.put("description", "登陆系统:");
		return jsonobj;
    }

}
