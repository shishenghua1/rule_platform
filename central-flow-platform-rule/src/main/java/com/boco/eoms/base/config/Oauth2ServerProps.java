package com.boco.eoms.base.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * oauth2服务配置
 * @author chenjianghe
 *
 */
@Component
@ConfigurationProperties(prefix="oauth2Server")
public class Oauth2ServerProps {

	 private String ip;
	 private String port;
	 private String version;
	 private String client_id_value_pwd_mod;
	 private String client_secret_value_pwd_mod;
	 
	 
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getClient_id_value_pwd_mod() {
		return client_id_value_pwd_mod;
	}
	public void setClient_id_value_pwd_mod(String client_id_value_pwd_mod) {
		this.client_id_value_pwd_mod = client_id_value_pwd_mod;
	}
	public String getClient_secret_value_pwd_mod() {
		return client_secret_value_pwd_mod;
	}
	public void setClient_secret_value_pwd_mod(String client_secret_value_pwd_mod) {
		this.client_secret_value_pwd_mod = client_secret_value_pwd_mod;
	}
}
