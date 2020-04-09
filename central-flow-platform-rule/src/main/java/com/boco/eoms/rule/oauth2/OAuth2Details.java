package com.boco.eoms.rule.oauth2;

public class OAuth2Details {

	private String grantType;
	private String clientIdName;
	private String clientSecretName;
	private String clientIdValue;
	private String clientSecretValue;
	private String clientIdValuePwdMod;
	private String clientSecretValuePwdMod;
	private String accessTokenUri;
	private String userAuthorizationUri;
	private String userInfoUri;
	private String redirectUriName;
	
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public String getClientIdName() {
		return clientIdName;
	}
	public void setClientIdName(String clientIdName) {
		this.clientIdName = clientIdName;
	}
	public String getClientSecretName() {
		return clientSecretName;
	}
	public void setClientSecretName(String clientSecretName) {
		this.clientSecretName = clientSecretName;
	}
	public String getClientIdValue() {
		return clientIdValue;
	}
	public void setClientIdValue(String clientIdValue) {
		this.clientIdValue = clientIdValue;
	}
	public String getClientSecretValue() {
		return clientSecretValue;
	}
	public void setClientSecretValue(String clientSecretValue) {
		this.clientSecretValue = clientSecretValue;
	}
	public String getAccessTokenUri() {
		return accessTokenUri;
	}
	public void setAccessTokenUri(String accessTokenUri) {
		this.accessTokenUri = accessTokenUri;
	}
	public String getUserAuthorizationUri() {
		return userAuthorizationUri;
	}
	public void setUserAuthorizationUri(String userAuthorizationUri) {
		this.userAuthorizationUri = userAuthorizationUri;
	}
	public String getUserInfoUri() {
		return userInfoUri;
	}
	public void setUserInfoUri(String userInfoUri) {
		this.userInfoUri = userInfoUri;
	}
	public String getRedirectUriName() {
		return redirectUriName;
	}
	public void setRedirectUriName(String redirectUriName) {
		this.redirectUriName = redirectUriName;
	}
	public String getClientIdValuePwdMod() {
		return clientIdValuePwdMod;
	}
	public void setClientIdValuePwdMod(String clientIdValuePwdMod) {
		this.clientIdValuePwdMod = clientIdValuePwdMod;
	}
	public String getClientSecretValuePwdMod() {
		return clientSecretValuePwdMod;
	}
	public void setClientSecretValuePwdMod(String clientSecretValuePwdMod) {
		this.clientSecretValuePwdMod = clientSecretValuePwdMod;
	}
	
}
