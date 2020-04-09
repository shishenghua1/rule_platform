package com.boco.eoms.rule.oauth2.model;

public class Oauth2Request {
	private String clientId;
	private String[] scope;
	private RequestParameter requestParameters;
	private String[] resourceIds;
	private String[] authorities;
	private boolean approved;
	private boolean refresh;
	private String redirectUri;
	private String[] responseTypes;
	
	private String extensions;
	
	private String grantType;
	private String refreshTokenRequest;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String[] getScope() {
		return scope;
	}
	public void setScope(String[] scope) {
		this.scope = scope;
	}
	public RequestParameter getRequestParameters() {
		return requestParameters;
	}
	public void setRequestParameters(RequestParameter requestParameters) {
		this.requestParameters = requestParameters;
	}
	public String[] getResourceIds() {
		return resourceIds;
	}
	public void setResourceIds(String[] resourceIds) {
		this.resourceIds = resourceIds;
	}
	public String[] getAuthorities() {
		return authorities;
	}
	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public boolean isRefresh() {
		return refresh;
	}
	public void setRefresh(boolean refresh) {
		this.refresh = refresh;
	}
	public String getRedirectUri() {
		return redirectUri;
	}
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	public String[] getResponseTypes() {
		return responseTypes;
	}
	public void setResponseTypes(String[] responseTypes) {
		this.responseTypes = responseTypes;
	}
	public String getExtensions() {
		return extensions;
	}
	public void setExtensions(String extensions) {
		this.extensions = extensions;
	}
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public String getRefreshTokenRequest() {
		return refreshTokenRequest;
	}
	public void setRefreshTokenRequest(String refreshTokenRequest) {
		this.refreshTokenRequest = refreshTokenRequest;
	}
	
	
}
