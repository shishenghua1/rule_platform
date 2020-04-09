package com.boco.eoms.base.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 网关服务配置
 * @author chenjianghe
 *
 */
@Component
@ConfigurationProperties(prefix="ruleProjectServer")
public class RuleProjectServer {
	private String ip;
	private String port;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
}
